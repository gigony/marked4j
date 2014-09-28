package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.blockfragment.BlockQuoteFragment;
import com.gigony.marked4j.util.TextSegment;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlockQuoteFragmentMatcherTest {
    @Test
    public void blockQuoteFragmentMatchTest(){
        BlockQuoteFragmentMatcher m = new BlockQuoteFragmentMatcher();
        TextSegment text = new TextSegment(">    >asdfas \n\n\nprint");
        assertTrue(m.find(text));
        BlockQuoteFragment block = m.getFragment();
        assertEquals(">    >asdfas \n\n\n",block.getRawText());
        assertEquals("    >asdfas \n\n\n",block.getStrippedSegment().toString());
    }

}