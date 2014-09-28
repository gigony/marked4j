package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.AbstractFragmentMatcher;
import com.gigony.marked4j.lexer.MarkedLexer;
import com.gigony.marked4j.lexer.blockfragment.ParagraphFragment;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.lexer.token.TokenType;
import com.gigony.marked4j.util.TextSegment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gigony
 * @since 9/28/14
 */
public class ParagraphFragmentMatcher extends AbstractFragmentMatcher {
    static final Pattern pattern = Pattern.compile("" +
            replaceAll("\\A((?:[^\\n]+\\n?(?!hr|heading|lheading|blockquote|tag|def))+)\\n*",
                    "hr", HRFragmentMatcher.patternString,
                    "lheading", LineHeadFragmentMatcher.patternString, // order is important ( lheading followed by heading)
                    "heading", HeadFragmentMatcher.patternString,
                    "blockquote", BlockQuoteFragmentMatcher.patternString,
                    "tag", "<" + HTMLFragmentMatcher.tagPatternString,
                    "def", DefFragmentMatcher.patternString), Pattern.MULTILINE);
    Matcher matcher;
    TextSegment text;

    @Override
    public boolean find(TextSegment txt) {
        text = txt;
        matcher = pattern.matcher(txt);
        return matcher.find();
    }

    @Override
    public ParagraphFragment getFragment() {
        TextSegment textSegment = text.subSequence(matcher.start(), matcher.end());
        TextSegment contentSegment = text.subSequence(matcher.start(1), matcher.end(1));

        if (contentSegment.charAt(contentSegment.length() - 1) == '\n')
            contentSegment.shiftEnd(-1);

        return new ParagraphFragment(textSegment, contentSegment);
    }

    @Override
    public boolean process(MarkedLexer lexer, TextSegment src, boolean top, boolean bq) {
        if (top && find(src)) {
            ParagraphFragment fragment = getFragment();
            int len = fragment.getLength();
            src.shiftStart(len);

            lexer.pushToken(new Token(TokenType.PARAGRAPH, fragment, lexer.getLastCodePos(), fragment.getCodeEnd()));


            return true;
        }
        return false;

    }
}
