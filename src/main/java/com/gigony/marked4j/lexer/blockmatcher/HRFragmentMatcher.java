package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.AbstractFragmentMatcher;
import com.gigony.marked4j.lexer.MarkedLexer;
import com.gigony.marked4j.lexer.blockfragment.HRFragment;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.lexer.token.TokenType;
import com.gigony.marked4j.util.TextSegment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gigony
 * @since 9/28/14
 */
public class HRFragmentMatcher extends AbstractFragmentMatcher {
    public static final String patternString = "^([ ]*[-*_]){3,}[ ]*(?:\\n+|$)";
    static final Pattern pattern = Pattern.compile("" +
            "\\A([ ]*[-*_]){3,}[ ]*(?:\\n+|$)", Pattern.MULTILINE);
    Matcher matcher;
    TextSegment text;

    @Override
    public boolean find(TextSegment txt) {
        text = txt;
        matcher = pattern.matcher(txt);
        return matcher.find();
    }

    @Override
    public HRFragment getFragment() {
        TextSegment textSegment = text.subSequence(matcher.start(), matcher.end());
        return new HRFragment(textSegment);
    }

    @Override
    public boolean process(MarkedLexer lexer, TextSegment src, boolean top, boolean bq) {
        if (find(src)) {
            HRFragment fragment = getFragment();
            int len = fragment.getLength();
            src.shiftStart(len);

            lexer.pushToken(new Token(TokenType.HR, fragment));
            return true;
        }
        return false;

    }
}
