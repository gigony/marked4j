package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.blockfragment.TableFragment;
import com.gigony.marked4j.util.TextSegment;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NPTableFragmentMatcherTest {

    @Test
    public void NPTableFragmentTest() {
        NPTableFragmentMatcher m = new NPTableFragmentMatcher();
        TextSegment text = new TextSegment("Markdown | Less | Pretty\n" +
                "--- | --- | ---\n" +
                "*Still* | `renders` | **nicely**\n" +
                "1 | 2 | 3\nprint");
        assertTrue(m.find(text));
        TableFragment block = m.getFragment();
        assertEquals(3,block.getHeader().length);
        assertEquals("*Still* | `renders` | **nicely**",block.getCellBlocks()[0]);
        assertEquals("1 | 2 | 3",block.getCellBlocks()[1]);

    }


}