package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.blockfragment.ParagraphFragment;
import com.gigony.marked4j.util.TextSegment;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ParagraphFragmentMatcherTest {
    @Test
    public void paragraphFragmentMatchTest(){
        ParagraphFragmentMatcher m = new ParagraphFragmentMatcher();
        TextSegment text = new TextSegment(" test message\n#header1\nprint");
        assertTrue(m.find(text));
        ParagraphFragment block = m.getFragment();
        assertEquals(" test message\n",block.getRawText());

    }

}