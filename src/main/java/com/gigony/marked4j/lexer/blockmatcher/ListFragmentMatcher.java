package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.AbstractFragmentMatcher;
import com.gigony.marked4j.lexer.MarkedLexer;
import com.gigony.marked4j.lexer.blockfragment.ListFragment;
import com.gigony.marked4j.lexer.blockfragment.ListItemFragment;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.lexer.token.TokenType;
import com.gigony.marked4j.util.MappedTextSegment;
import com.gigony.marked4j.util.TextSegment;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gigony
 * @since 9/28/14
 */
public class ListFragmentMatcher extends AbstractFragmentMatcher {
    static final String bulletPatternString = "(?:[*+-]|\\d+\\.)";
    static final String itemString = "^([ ]*)(bull)[ ][^\\n]*(?:\\n(?!\\1bull[ ])[^\\n]*)*";
    static final String patternString =
            replaceAll("^([ ]*)(bull)[ ][\\s\\S]+?(?:hr|def|\\n{2,}(?![ ])(?!\\1bull[ ])\\n*|\\s*$)",
                    "bull", bulletPatternString,
                    "hr", "\\n+(?=\\1?(?:[-*_][ ]*){3,}(?:\\n+|$))",
                    "def", "\\n+(?=" + DefFragmentMatcher.patternString + ")");
    static final Pattern pattern = Pattern.compile(patternString);
    static final Pattern itemPattern = Pattern.compile("" +
            replaceAll(itemString, "bull", bulletPatternString), Pattern.MULTILINE);
    static final Pattern bulletPattern = Pattern.compile(bulletPatternString, Pattern.MULTILINE);

    Matcher matcher;
    TextSegment text;

    @Override
    public boolean find(TextSegment txt) {
        text = txt;
        matcher = pattern.matcher(txt);

        return matcher.find();
    }

    @Override
    public ListFragment getFragment() {
        TextSegment textSegment = text.subSequence(matcher.start(), matcher.end());

        TextSegment bulletSegment = text.subSequence(matcher.start(2), matcher.end(2));
        boolean isOrdered = bulletSegment.length() > 1;
        return new ListFragment(textSegment, bulletSegment, isOrdered);
    }

    @Override
    public boolean process(MarkedLexer lexer, TextSegment src, boolean top, boolean bq) {
        if (find(src)) {
            ListFragment fragment = getFragment();
            int len = fragment.getLength();
            src.shiftStart(len);

            lexer.appendFollowingNewLinesToLastToken();

            int lastCodePos = lexer.getLastCodePos();
            lexer.pushToken(new Token(TokenType.LIST_START, fragment, lastCodePos, lastCodePos));

            String bull = fragment.getBulletSegment().toString();

            MappedTextSegment textSegment = new MappedTextSegment(fragment.getTextSegment());
            Matcher m = itemPattern.matcher(textSegment);
            ArrayList<MappedTextSegment> cap = new ArrayList<MappedTextSegment>();
            ArrayList<Integer> capStartPos = new ArrayList<Integer>();
            while (m.find()) {
                cap.add(new MappedTextSegment(textSegment, textSegment.getArrayPos(m.start()), textSegment.getArrayPos(m.end())));
                capStartPos.add(m.start());
            }

            boolean next = false;
            int l = cap.size();

            for (int i = 0; i < l; i++) {
                MappedTextSegment item = cap.get(i);
                ListItemFragment listItemFragment = new ListItemFragment(item);

                // Remove the list item's bullet
                // so it is seen as the next token.
                int space = item.length();

                item.replaceText("^[ ]*([*+-]|\\d+\\.)[ ]+", "", false);

                // Outdent whatever the
                // list item contains. Hacky.
                String itemStr = item.toString();
                if (itemStr.indexOf("\n ") != 0) {    //@TODO check
                    space -= item.length();
                    item = !lexer.getOptions().isPedantic()
                            ? item.replaceText("^[ ]{1," + space + "}", "", true)
                            : item.replaceText("^[ ]{1,4}", "", true);
                }

                // Determine whether the next list item belongs here.
                // Backpedal if it does not belong in this list.
                if (lexer.getOptions().isSmartLists() && i != l - 1) {
                    Matcher bulletMatcher = bulletPattern.matcher(cap.get(i + 1));
                    if (bulletMatcher.matches()) {
                        String b = bulletMatcher.group();
                        if (!bull.equals(b) && !(bull.length() > 1 && b.length() > 1)) {
                            src.shiftStart(capStartPos.get(i + 1) - src.getStart()); //set offset to capStartPos.get(i + 1)
                            i = l - 1;
                        }
                    }
                }

                // Determine whether item is loose or not.
                // Use: /(^|\n)(?! )[^\n]+\n\n(?!\s*$)/
                // for discount behavior.
                itemStr = item.toString();
                boolean loose = next || itemStr.matches("\\n\\n(?!\\s*$)");
                if (i != l - 1) {
                    next = item.charAt(item.length() - 1) == '\n';
                    if (!loose) loose = next;
                }

                lastCodePos = lexer.getLastCodePos();
                lexer.pushToken(new Token(loose ? TokenType.LOOSE_ITEM_START : TokenType.LIST_ITEM_START, listItemFragment, lastCodePos, listItemFragment.getCodeStart()));

                // Recurse.
                lexer.lexer(item, false, bq);

                lexer.appendFollowingNewLinesToLastToken();

                lastCodePos = lexer.getLastCodePos();
                lexer.pushToken(new Token(TokenType.LIST_ITEM_END, listItemFragment, lastCodePos, lastCodePos));

            }

            lastCodePos = lexer.getLastCodePos();
            lexer.pushToken(new Token(TokenType.LIST_END, fragment, lastCodePos, lastCodePos));

            return true;
        }
        return false;
    }
}
