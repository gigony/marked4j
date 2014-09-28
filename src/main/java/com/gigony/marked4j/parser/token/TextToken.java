package com.gigony.marked4j.parser.token;

import com.gigony.marked4j.lexer.InlineLexer;
import com.gigony.marked4j.lexer.blockfragment.TextFragment;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.parser.MarkedParser;
import com.gigony.marked4j.renderer.AbstractRenderer;

/**
 * @author gigony
 * @since 9/28/14
 */
public class TextToken extends AbstractTokenProcessor {
    @Override
    public String tok(MarkedParser parsers, Token token) {
        TextFragment fragment = (TextFragment) token.getFragment();
        AbstractRenderer renderer = parsers.getRenderer();
        InlineLexer inlineLexer = parsers.getInlineLexer();
        return renderer.paragraph(
                inlineLexer.output(fragment.getRawText()));
    }
}
