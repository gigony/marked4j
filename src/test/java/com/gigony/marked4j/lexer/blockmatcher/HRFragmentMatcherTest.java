package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.blockfragment.HRFragment;
import com.gigony.marked4j.util.TextSegment;
import org.junit.Test;

import static org.junit.Assert.*;

public class HRFragmentMatcherTest {

    @Test
    public void hrFragmentMatchTest(){
        HRFragmentMatcher m = new HRFragmentMatcher();
        TextSegment text = new TextSegment("- - - \n\n\n");
        assertTrue(m.find(text));
        HRFragment block = m.getFragment();
        assertEquals("- - - \n\n\n",block.getRawText());
    }

}