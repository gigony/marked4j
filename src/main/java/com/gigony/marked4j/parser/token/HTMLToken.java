package com.gigony.marked4j.parser.token;

import com.gigony.marked4j.lexer.blockfragment.HTMLFragment;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.parser.MarkedParser;
import com.gigony.marked4j.renderer.AbstractRenderer;

/**
 * @author gigony
 * @since 9/28/14
 */
public class HTMLToken extends AbstractTokenProcessor {
    @Override
    public String tok(MarkedParser parsers, Token token) {
        HTMLFragment fragment = (HTMLFragment) token.getFragment();
        AbstractRenderer renderer = parsers.getRenderer();
        String contentText = fragment.getContentSegment().toString();
        String html = !fragment.isPre() && !parsers.getOptions().isPedantic() ?
                parsers.getInlineLexer().output(contentText) : contentText;
        return renderer.html(html);
    }
}
