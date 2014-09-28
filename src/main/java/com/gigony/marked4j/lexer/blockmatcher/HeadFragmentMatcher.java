package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.AbstractFragmentMatcher;
import com.gigony.marked4j.lexer.MarkedLexer;
import com.gigony.marked4j.lexer.blockfragment.HeadFragment;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.lexer.token.TokenType;
import com.gigony.marked4j.util.TextSegment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gigony
 * @since 9/28/14
 */
public class HeadFragmentMatcher extends AbstractFragmentMatcher {
    public static final String patternString = "^[ ]*(#{1,6})[ ]*([^\\n]+?)[ ]*#*[ ]*(?:\\n+|$)";
    static final Pattern pattern = Pattern.compile("" +
            "\\A[ ]*(#{1,6})[ ]*([^\\n]+?)[ ]*#*[ ]*(?:\\n+|$)", Pattern.MULTILINE);

    Matcher matcher;
    TextSegment text;

    @Override
    public boolean find(TextSegment txt) {
        text = txt;
        matcher = pattern.matcher(txt);
        return matcher.find();
    }

    @Override
    public HeadFragment getFragment() {
        TextSegment textSegment = text.subSequence(matcher.start(), matcher.end());
        TextSegment title = text.subSequence(matcher.start(2), matcher.end(2));
        int depth = matcher.end(1) - matcher.start(1);
        return new HeadFragment(textSegment, title, depth);
    }

    @Override
    public boolean process(MarkedLexer lexer, TextSegment src, boolean top, boolean bq) {
        if (find(src)) {
            HeadFragment fragment = getFragment();
            int len = fragment.getLength();
            src.shiftStart(len);

            lexer.pushToken(new Token(TokenType.HEADING, fragment));
            return true;
        }
        return false;
    }
}
