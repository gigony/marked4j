package com.gigony.marked4j.parser;

import com.gigony.marked4j.MarkDownRules;
import com.gigony.marked4j.Options;
import com.gigony.marked4j.lexer.MarkedLexer;
import com.gigony.marked4j.parser.token.AbstractTokenProcessor;
import com.gigony.marked4j.lexer.InlineLexer;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.lexer.token.TokenType;
import com.gigony.marked4j.renderer.AbstractRenderer;

import java.util.ArrayList;

/**
 * @author gigony
 * @since 9/13/14
 */
public class MarkedParser {
    MarkedLexer lexer = new MarkedLexer();
    ArrayList<Token> tokens;

    int tokenLen;
    int tokenOffset;
    InlineLexer inlineLexer = new InlineLexer();
    private Token token;
    private Options options = new Options();
    AbstractRenderer renderer;

    public MarkedParser() {
    }


    public String parse(String text) {
        tokens = new ArrayList<Token>(lexer.lexer(text));
        renderer = options.getRenderer();
        tokenLen = tokens.size();
        tokenOffset = 0;

        StringBuffer out = new StringBuffer(text.length());
        while (hasNext()) {
            Token token = next();
            AbstractTokenProcessor tokenProcessor = getTokenProcessor(token.getTokenType());
            if(tokenProcessor==null)
                throw new RuntimeException("Token processor for "+token.getTokenType()+" is missing!");
            String tokText = tokenProcessor.tok(this, token);
            out.append(tokText);
        }
        return out.toString();
    }

    public AbstractTokenProcessor getTokenProcessor(TokenType tokenType) {
        AbstractTokenProcessor processor = MarkDownRules.tokenProcessor.get(tokenType);
        return processor;

    }

    public Token next() {
        if (tokenOffset < tokenLen) {
            token = tokens.get(tokenOffset++);
            return token;
        }
        return null;
    }

    public boolean hasNext() {
        return tokenOffset < tokenLen;
    }

    public Token peek() {
        if (tokenOffset < tokenLen) {
            return tokens.get(tokenOffset);
        }
        return null;
    }

    public Token getToken() {
        return token;
    }

    public MarkedLexer getLexer() {
        return lexer;
    }

    public AbstractRenderer getRenderer() {
        return renderer;
    }

    public InlineLexer getInlineLexer() {
        return inlineLexer;
    }

    public String parseText() {
        //@FIXME need to trim white space
        StringBuffer body = new StringBuffer(token.getFragment().getRawText());

        while (hasNext() && peek().getTokenType() == TokenType.TEXT) {
            body.append("\n" + next().getFragment().getRawText());
        }
        return inlineLexer.output(body.toString());
    }

    public Options getOptions() {
        return options;
    }
}
