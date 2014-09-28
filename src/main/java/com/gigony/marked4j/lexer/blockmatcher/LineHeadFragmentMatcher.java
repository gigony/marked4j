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
public class LineHeadFragmentMatcher extends AbstractFragmentMatcher {
    public static final String patternString = "^([^\\n]+)\\n[ ]*(=|-){2,}[ ]*(?:\\n+|$)";
    static final Pattern pattern = Pattern.compile("" +
            "\\A([^\\n]+)\\n[ ]*(=|-){2,}[ ]*(?:\\n+|$)", Pattern.MULTILINE);
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
        TextSegment title = text.subSequence(matcher.start(1), matcher.end(1));
        int depth = text.charAt(matcher.start(2)) == '=' ? 1 : 2;
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
