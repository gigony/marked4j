package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.blockfragment.TextFragment;
import com.gigony.marked4j.util.TextSegment;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TextFragmentMatcherTest {
    @Test
    public void textFragmentMatchTest(){
        TextFragmentMatcher m = new TextFragmentMatcher();
        TextSegment text = new TextSegment(" test message\n#header1\nprint");
        assertTrue(m.find(text));
        TextFragment block = m.getFragment();
        assertEquals(" test message",block.getRawText());
    }

}