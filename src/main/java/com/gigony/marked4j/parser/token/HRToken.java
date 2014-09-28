package com.gigony.marked4j.parser.token;

import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.parser.MarkedParser;

/**
 * @author gigony
 * @since 9/28/14
 */
public class HRToken extends AbstractTokenProcessor {

    @Override
    public String tok(MarkedParser parsers, Token token) {
        return parsers.getRenderer().hr();
    }
}
