package com.gigony.marked4j.parser.token;

import com.gigony.marked4j.lexer.blockfragment.ListFragment;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.lexer.token.TokenType;
import com.gigony.marked4j.parser.MarkedParser;
import com.gigony.marked4j.renderer.AbstractRenderer;

/**
 * @author gigony
 * @since 9/28/14
 */
public class ListStartToken extends AbstractTokenProcessor {
    @Override
    public String tok(MarkedParser parser, Token token) {
        ListFragment fragment = (ListFragment) token.getFragment();
        AbstractRenderer renderer = parser.getRenderer();

        StringBuffer body = new StringBuffer();
        boolean ordered = fragment.isOrdered();

        while (parser.hasNext() && parser.next().getTokenType() != TokenType.LIST_END) {
            Token nextToken = parser.getToken();
            AbstractTokenProcessor tokenProcessor = parser.getTokenProcessor(nextToken.getTokenType());
            body.append(tokenProcessor.tok(parser, nextToken));
        }

        return renderer.list(body.toString(), ordered);
    }
}
