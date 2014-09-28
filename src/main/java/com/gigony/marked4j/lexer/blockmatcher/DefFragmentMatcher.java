package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.AbstractFragmentMatcher;
import com.gigony.marked4j.lexer.MarkedLexer;
import com.gigony.marked4j.lexer.blockfragment.DefFragment;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.lexer.token.TokenType;
import com.gigony.marked4j.util.TextSegment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gigony
 * @since 9/28/14
 */
public class DefFragmentMatcher extends AbstractFragmentMatcher {
    static final String patternString = "" +
            "^[ ]*\\[([^\\]]+)\\]:[ ]*<?([^\\s>]+)>?(?:[ ]+[\"(]([^\\n]+)[\")])?[ ]*(?:\\n+|$)";


    static final Pattern pattern = Pattern.compile("" +
            "\\A[ ]*\\[([^\\]]+)\\]:[ ]*<?([^\\s>]+)>?(?:[ ]+[\"(]([^\\n]+)[\")])?[ ]*(?:\\n+|$)", Pattern.MULTILINE);
    Matcher matcher;

    TextSegment text;

    @Override
    public boolean find(TextSegment txt) {
        text = txt;
        matcher = pattern.matcher(txt);
        return matcher.find();
    }

    @Override
    public DefFragment getFragment() {
        TextSegment textSegment = text.subSequence(matcher.start(), matcher.end());
        TextSegment link = (text.subSequence(matcher.start(1), matcher.end(1))); //.toLowerCase();
        TextSegment href = text.subSequence(matcher.start(2), matcher.end(2));
        TextSegment title = text.subSequence(matcher.start(3), matcher.end(3));
        return new DefFragment(textSegment, link, href, title);
    }

    @Override
    public boolean process(MarkedLexer lexer, TextSegment src, boolean top, boolean bq) {
        if ((!bq && top) && find(src)) {
            DefFragment fragment = getFragment();
            int len = fragment.getLength();
            src.shiftStart(len);

            // this token should be ignored when parsing (different from marked.js)
            lexer.pushToken(new Token(TokenType.DEF, fragment, fragment.getCodeStart(), fragment.getCodeEnd()));

            lexer.addLink(fragment.getLinkText(), fragment.getHrefText(), fragment.getTitleText());
            return true;
        }
        return false;

    }

}
