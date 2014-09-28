package com.gigony.marked4j.parser.token;

import com.gigony.marked4j.lexer.InlineLexer;
import com.gigony.marked4j.lexer.blockfragment.HeadFragment;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.parser.MarkedParser;
import com.gigony.marked4j.renderer.AbstractRenderer;

/**
 * @author gigony
 * @since 9/28/14
 */
public class HeadingToken extends AbstractTokenProcessor {
    @Override
    public String tok(MarkedParser parsers, Token token) {
        HeadFragment fragment = (HeadFragment) token.getFragment();
        AbstractRenderer renderer = parsers.getRenderer();
        InlineLexer inlineLexer = parsers.getInlineLexer();
        return renderer.heading(
                inlineLexer.output(fragment.getTitle()), fragment.getDepth(), fragment.getTitle());
    }
}
