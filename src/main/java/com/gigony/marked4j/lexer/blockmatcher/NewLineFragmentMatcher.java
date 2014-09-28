package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.AbstractFragmentMatcher;
import com.gigony.marked4j.lexer.MarkedLexer;
import com.gigony.marked4j.lexer.blockfragment.NewLineFragment;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.lexer.token.TokenType;
import com.gigony.marked4j.util.TextSegment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gigony
 * @since 9/28/14
 */
public class NewLineFragmentMatcher extends AbstractFragmentMatcher {
    static final Pattern pattern = Pattern.compile("\\A\\n+", Pattern.MULTILINE);
    Matcher matcher;
    TextSegment text;

    @Override
    public boolean find(TextSegment txt) {
        text = txt;
        matcher = pattern.matcher(txt);
        return matcher.find();
    }

    @Override
    public NewLineFragment getFragment() {
        TextSegment textSegment = text.subSequence(matcher.start(), matcher.end());
        return new NewLineFragment(textSegment);
    }

    @Override
    public boolean process(MarkedLexer lexer, TextSegment src, boolean top, boolean bq) {
        if (find(src)) {
            NewLineFragment fragment = getFragment();
            int len = fragment.getLength();
            src.shiftStart(len);
            if (len > 1) {
                lexer.pushToken(new Token(TokenType.SPACE, fragment));
            }
        }
        return false;
    }
}
