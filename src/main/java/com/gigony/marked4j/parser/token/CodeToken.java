package com.gigony.marked4j.parser.token;

import com.gigony.marked4j.lexer.InlineLexer;
import com.gigony.marked4j.lexer.blockfragment.CodeFragment;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.parser.MarkedParser;
import com.gigony.marked4j.renderer.AbstractRenderer;

/**
 * @author gigony
 * @since 9/28/14
 */
public class CodeToken extends AbstractTokenProcessor {
    @Override
    public String tok(MarkedParser parsers, Token token) {
        CodeFragment fragment = (CodeFragment) token.getFragment();
        AbstractRenderer renderer = parsers.getRenderer();
        InlineLexer inlineLexer = parsers.getInlineLexer();
        return renderer.code(
                inlineLexer.output(fragment.getCodeText()), fragment.getLanguage(), fragment.isEscaped());
    }
}
