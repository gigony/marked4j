package com.gigony.marked4j.parser.token;

import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.parser.MarkedParser;

/**
 * @author gigony
 * @since 9/27/14
 */
public abstract class AbstractTokenProcessor {

    public abstract String tok(MarkedParser parser, Token token);
}
