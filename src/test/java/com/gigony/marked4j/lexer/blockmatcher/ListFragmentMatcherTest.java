package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.blockfragment.ListFragment;
import com.gigony.marked4j.util.TextSegment;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ListFragmentMatcherTest {
    @Test
    public void listFragmentMatchTest(){
        ListFragmentMatcher m = new ListFragmentMatcher();
        TextSegment text = new TextSegment("* item1 \n* item2\n  * item 2-1\n  * item 2-2\n* item3\n\n\nprint");
        assertTrue(m.find(text));
        ListFragment block = m.getFragment();
        assertEquals("* item1 \n* item2\n  * item 2-1\n  * item 2-2\n* item3\n\n\n",block.getRawText());
        assertEquals("*",block.getBulletSegment().toString());
        assertFalse(block.isOrdered());
    }

}