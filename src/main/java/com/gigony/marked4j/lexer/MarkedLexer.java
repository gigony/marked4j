package com.gigony.marked4j.lexer;

import com.gigony.marked4j.MarkDownRules;
import com.gigony.marked4j.Options;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.util.MappedTextSegment;
import com.gigony.marked4j.util.TextSegment;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * @author gigony
 * @since 9/20/14
 */
public class MarkedLexer {
    TextSegment originalText;
    Deque<Token> tokens;

//    public MarkDownLexer(String originalText) {
//        this(originalText, 0, originalText.length());
//    }
//
//    public MarkDownLexer(String originalText, int off) {
//        this(originalText, off, originalText.length() - off);
//    }
//
//    public MarkDownLexer(String originalText, int off, int len) {
//        this(originalText.toCharArray(), off, len);
//    }
//
//    public MarkDownLexer(char[] chArr, int off, int len) {
//        originalText=new TextSegment(chArr,off,len);
//
//    }


    public Deque<Token> lexer(String text) {
        return lexer(text, 0, text.length());
    }

    public Deque<Token> lexer(String text, int off) {
        return lexer(text, off, text.length() - off);
    }

    public Deque<Token> lexer(String text, int off, int len) {
        return lexer(text.toCharArray(), off, len);
    }

    public Deque<Token> lexer(char[] chArr, int off, int len) {
        originalText = new TextSegment(chArr, off, len);
        tokens = new ArrayDeque<Token>();

        MappedTextSegment srcText = new MappedTextSegment(originalText);
        srcText.replaceText("\\t", "    ", true);
        Deque<Token> result = lexer(srcText, true, false);
        return result;
    }

    public void print(Deque<Token> result) {
        Iterator<Token> iter = result.iterator();
        while (iter.hasNext()) {
            Token token = iter.next();
            System.out.println(String.format("## [%s] (%d - %d)", token.getTokenType(), token.getCodeStart(), token.getCodeEnd()));
            System.out.println(String.format("\t%s             | %s | ", new String(originalText.getSrcArr(), token.getCodeStart(), token.getCodeEnd() - token.getCodeStart()).replaceAll("\\n", "\\\\n"), token.getFragment().getRawText().replaceAll("\\n", "\\\\n")));
        }
    }


    public Deque<Token> lexer(MappedTextSegment src, boolean top, boolean bq) {
        src.replaceText("^ +$", "", true);

        while (src.length() != 0) {
            for (AbstractFragmentMatcher rule : MarkDownRules.blockMatchers) {
                if (rule.process(this, src, top, bq))
                    break;
            }
        }
        return tokens;
    }

    public void pushToken(Token token) {
        tokens.addLast(token);
    }

    public Token peekLastToken() {
        return tokens.peekLast();
    }

    public int getLastCodePos() {
        if (tokens.isEmpty())
            return 0;
        return tokens.peekLast().getCodeEnd();
    }

    public Options getOptions() {
        return new Options();
    }

    public void addLink(TextSegment linkText, TextSegment hrefText, TextSegment titleText) {
        //@TODO

    }

    public void appendFollowingNewLinesToLastToken() {
        if (!tokens.isEmpty()) {
            Token peekItem = tokens.peekLast();
            int lastCodePos = peekItem.getCodeEnd();
            while (lastCodePos < originalText.getCodeEnd() && originalText.getSrcCharAt(lastCodePos) == '\n') {
                lastCodePos++;
            }
            peekItem.setCodeEnd(lastCodePos);
        }
    }

    public char getSrcCharAt(int pos) {
        return originalText.getSrcCharAt(pos);
    }

    public Deque<Token> getTokens() {
        return tokens;
    }
}
