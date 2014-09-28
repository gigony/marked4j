package com.gigony.marked4j;

import com.gigony.marked4j.lexer.AbstractFragmentMatcher;
import com.gigony.marked4j.parser.token.AbstractTokenProcessor;
import com.gigony.marked4j.lexer.blockmatcher.*;
import com.gigony.marked4j.lexer.token.*;
import com.gigony.marked4j.parser.token.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gigony
 * @since 9/20/14
 */
public class MarkDownRules {
    public static List<AbstractFragmentMatcher> blockMatchers = new ArrayList<AbstractFragmentMatcher>();

    public static Map<TokenType, AbstractTokenProcessor> tokenProcessor = new HashMap<TokenType, AbstractTokenProcessor>();

    static {
        blockMatchers.add(new NewLineFragmentMatcher());
        blockMatchers.add(new CodeFragmentMatcher());
        //fences(gfm)
        blockMatchers.add(new FencesFragmentMatcher());

        blockMatchers.add(new HeadFragmentMatcher());
        //nptable
        blockMatchers.add(new NPTableFragmentMatcher());


        blockMatchers.add(new LineHeadFragmentMatcher());
        blockMatchers.add(new HRFragmentMatcher());
        blockMatchers.add(new BlockQuoteFragmentMatcher());
        blockMatchers.add(new ListFragmentMatcher());
        blockMatchers.add(new HTMLFragmentMatcher());
        blockMatchers.add(new DefFragmentMatcher());
        //table(gfm)
        blockMatchers.add(new TableFragmentMatcher());
        blockMatchers.add(new ParagraphFragmentMatcher());
        blockMatchers.add(new TextFragmentMatcher());


        tokenProcessor.put(TokenType.SPACE, new SpaceToken());
        tokenProcessor.put(TokenType.HR, new HRToken());
        tokenProcessor.put(TokenType.HEADING, new HeadingToken());
        tokenProcessor.put(TokenType.CODE, new CodeToken());
        tokenProcessor.put(TokenType.BLOCKQUOTE_START, new BlockQuoteStartToken());
        tokenProcessor.put(TokenType.LIST_START, new ListStartToken());
        tokenProcessor.put(TokenType.LIST_ITEM_START, new ListItemStartToken());
        tokenProcessor.put(TokenType.LOOSE_ITEM_START, new LooseItemStartToken());
        tokenProcessor.put(TokenType.TABLE, new TableToken());
        tokenProcessor.put(TokenType.HTML, new HTMLToken());
        tokenProcessor.put(TokenType.PARAGRAPH, new ParagraphToken());
        tokenProcessor.put(TokenType.TEXT, new TextToken());



        tokenProcessor.put(TokenType.DEF, new DEFToken()); // additional token


    }
}

