package com.gigony.marked4j.parser.token;

import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.lexer.token.TokenType;
import com.gigony.marked4j.parser.MarkedParser;
import com.gigony.marked4j.renderer.AbstractRenderer;

/**
 * @author gigony
 * @since 9/28/14
 */
public class BlockQuoteStartToken extends AbstractTokenProcessor {
    @Override
    public String tok(MarkedParser parser, Token token) {
        AbstractRenderer renderer = parser.getRenderer();

        StringBuffer body = new StringBuffer();
        while (parser.hasNext() && parser.next().getTokenType() != TokenType.BLOCKQUOTE_END) {
            Token nextToken = parser.getToken();
            AbstractTokenProcessor tokenProcessor = parser.getTokenProcessor(nextToken.getTokenType());
            body.append(tokenProcessor.tok(parser, nextToken));
        }

        return renderer.blockquote(body.toString());
    }
}
