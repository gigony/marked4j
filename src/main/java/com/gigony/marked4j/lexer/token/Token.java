package com.gigony.marked4j.lexer.token;

import com.gigony.marked4j.lexer.blockfragment.Fragment;

/**
 * @author gigony
 * @since 9/22/14
 */
public class Token {
    Fragment fragment;
    TokenType tokenType;
    int codeStart;
    int codeEnd;

    public Token(TokenType name, Fragment frag) {
        this(name, frag, frag.getCodeStart(), frag.getCodeEnd());
    }

    public Token(TokenType name, Fragment frag, int s, int e) {
        tokenType = name;
        fragment = frag;
        codeStart = s;
        codeEnd = e;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public int getCodeStart() {
        return codeStart;
    }

    public int getCodeEnd() {
        return codeEnd;
    }

    public void setCodeStart(int codeStart) {
        this.codeStart = codeStart;
    }

    public void setCodeEnd(int codeEnd) {
        this.codeEnd = codeEnd;
    }
}
