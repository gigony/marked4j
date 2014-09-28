package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.AbstractFragmentMatcher;
import com.gigony.marked4j.lexer.MarkedLexer;
import com.gigony.marked4j.lexer.blockfragment.HTMLFragment;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.lexer.token.TokenType;
import com.gigony.marked4j.util.TextSegment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gigony
 * @since 9/28/14
 */
public class HTMLFragmentMatcher extends AbstractFragmentMatcher {
    static final String tagPatternString = "(?!(?:"
            + "a|em|strong|small|s|cite|q|dfn|abbr|data|time|code"
            + "|var|samp|kbd|sub|sup|i|b|u|mark|ruby|rt|rp|bdi|bdo"
            + "|span|br|wbr|ins|del|img)\\b)\\w+(?!:/|[^\\w\\s@]*@)\\b";
    static final Pattern pattern = Pattern.compile("" +
            replaceAll("\\A[ ]*(?:comment[ ]*(?:\\n|\\s*$)|closed[ ]*(?:\\n{2,}|\\s*$)|closing[ ]*(?:\\n{2,}|\\s*$))",
                    "comment", "<!--[\\s\\S]*?-->",
                    "closed", "<(tag)[\\s\\S]+?<\\/\\1>",
                    "closing", "<tag(?:\"[^\"]*\"|'[^']*'|[^'\">])*?>:",
                    "tag", tagPatternString), Pattern.MULTILINE);
    Matcher matcher;
    TextSegment text;

    @Override
    public boolean find(TextSegment txt) {
        text = txt;
        matcher = pattern.matcher(txt);
        return matcher.find();
    }

    @Override
    public HTMLFragment getFragment() {
        TextSegment textSegment = text.subSequence(matcher.start(), matcher.end());
        TextSegment tag = text.subSequence(matcher.start(1), matcher.end(1));
        boolean pre = tag.contentEquals("pre") || tag.contentEquals("script") || tag.contentEquals("style");
        return new HTMLFragment(textSegment, pre, textSegment);
    }

    @Override
    public boolean process(MarkedLexer lexer, TextSegment src, boolean top, boolean bq) {
        if (find(src)) {
            HTMLFragment fragment = getFragment();
            int len = fragment.getLength();
            src.shiftStart(len);

            lexer.pushToken(new Token(lexer.getOptions().isSanitize() ? TokenType.PARAGRAPH : TokenType.HTML, fragment, fragment.getCodeStart(), fragment.getCodeEnd()));

            return true;
        }
        return false;
    }
}
