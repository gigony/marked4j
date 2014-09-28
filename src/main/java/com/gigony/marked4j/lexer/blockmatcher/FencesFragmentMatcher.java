package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.AbstractFragmentMatcher;
import com.gigony.marked4j.lexer.MarkedLexer;
import com.gigony.marked4j.lexer.blockfragment.CodeFragment;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.lexer.token.TokenType;
import com.gigony.marked4j.util.TextSegment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gigony
 * @since 9/28/14
 */
public class FencesFragmentMatcher extends AbstractFragmentMatcher {
    static final Pattern pattern = Pattern.compile("\\A[ ]*(`{3,}|~{3,})[ ]*(\\S+)?[ ]*\\n([\\s\\S]+?)\\s*\\1[ ]*(?:\\n+|$)", Pattern.MULTILINE);
    Matcher matcher;
    TextSegment text;

    @Override
    public boolean find(TextSegment txt) {
        text = txt;
        matcher = pattern.matcher(txt);
        return matcher.find();
    }

    @Override
    public CodeFragment getFragment() {
        TextSegment textSegment = text.subSequence(matcher.start(), matcher.end());
        String language = text.subSequence(matcher.start(2),matcher.end(2)).toString();
        TextSegment codeSegment = text.subSequence(matcher.start(3),matcher.end(3));
        return new CodeFragment(textSegment, codeSegment,language,false);
    }

    @Override
    public boolean process(MarkedLexer lexer, TextSegment src, boolean top, boolean bq) {
        if (find(src)) {
            CodeFragment fragment = getFragment();
            int len = fragment.getLength();
            src.shiftStart(len);

            lexer.pushToken(new Token(TokenType.CODE, fragment));
            return true;
        }
        return false;
    }
}
