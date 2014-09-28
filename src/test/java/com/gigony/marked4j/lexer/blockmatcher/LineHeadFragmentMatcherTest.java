package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.blockfragment.HeadFragment;
import com.gigony.marked4j.util.TextSegment;
import org.junit.Test;

import static org.junit.Assert.*;

public class LineHeadFragmentMatcherTest {
    @Test
    public void lineFragmentMatchTest(){
        LineHeadFragmentMatcher m = new LineHeadFragmentMatcher();
        TextSegment text = new TextSegment("head\n----\n\n\nprint");
        assertTrue(m.find(text));
        HeadFragment block = m.getFragment();
        assertEquals("head\n----\n\n\n",block.getRawText());
        assertEquals("head",block.getTitle());
        assertEquals(2,block.getDepth());
    }

}