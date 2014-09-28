package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.AbstractFragmentMatcher;
import com.gigony.marked4j.lexer.MarkedLexer;
import com.gigony.marked4j.lexer.blockfragment.BlockQuoteFragment;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.lexer.token.TokenType;
import com.gigony.marked4j.util.MappedTextSegment;
import com.gigony.marked4j.util.TextSegment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gigony
 * @since 9/28/14
 */
public class BlockQuoteFragmentMatcher extends AbstractFragmentMatcher {
    public static final String patternString = replaceAll("^([ ]*>[^\\n]+(\\n(?!def)[^\\n]+)*\\n*)+",
            "def", DefFragmentMatcher.patternString);

    static final Pattern pattern = Pattern.compile(
            replaceAll("\\A([ ]*>[^\\n]+(\\n(?!def)[^\\n]+)*\\n*)+",
                    "def", DefFragmentMatcher.patternString), Pattern.MULTILINE);
    static final Pattern prefixStripPattern = Pattern.compile("^[ ]*>");
    Matcher matcher;
    TextSegment text;

    @Override
    public boolean find(TextSegment txt) {
        text = txt;
        matcher = pattern.matcher(txt);
        return matcher.find();
    }

    @Override
    public BlockQuoteFragment getFragment() {
        TextSegment textSegment = text.subSequence(matcher.start(), matcher.end());
        Matcher stripMatcher = prefixStripPattern.matcher(textSegment);
        if (stripMatcher.find()) {
            TextSegment strippedSegment = textSegment.subSequence(stripMatcher.end(), textSegment.getEnd());
            return new BlockQuoteFragment(textSegment, strippedSegment);

        } else
            throw new RuntimeException("'>' should exist!");

    }

    @Override
    public boolean process(MarkedLexer lexer, TextSegment src, boolean top, boolean bq) {
        if (find(src)) {
            BlockQuoteFragment fragment = getFragment();
            int len = fragment.getLength();
            src.shiftStart(len);

            int codeStart = fragment.getCodeStart();
            int codeEnd = fragment.getStrippedSegment().getCodeStart();
            // correct code position
            if (lexer.getSrcCharAt(codeEnd) == ' ')
                codeEnd++;

            lexer.pushToken(new Token(TokenType.BLOCKQUOTE_START, fragment, codeStart, codeEnd));

            MappedTextSegment blockSegment = new MappedTextSegment(fragment.getTextSegment());
            blockSegment.replaceText("^[ ]*>[ ]?", "", true);

            lexer.lexer(blockSegment, top, true);

            int lastCodePos = lexer.getLastCodePos();
            lexer.pushToken(new Token(TokenType.BLOCKQUOTE_END, fragment, lastCodePos, lastCodePos));

            return true;
        }
        return false;
    }
}
