package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.blockfragment.TableFragment;
import com.gigony.marked4j.util.TextSegment;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TableFragmentMatcherTest {

    @Test
    public void tableFragmentMatcherTest() {
        TableFragmentMatcher m = new TableFragmentMatcher();
        TextSegment text = new TextSegment("| name  | age | gender    | money  |\n" +
                "|-------|:---:|-----------|-------:|\n" +
                "| rhio  | 384 | robot     | $3,000 |\n" +
                "| haroo | .3  | bird      | $430   |\n" +
                "| jedi  | ?   | undefined | $0     |\nprint");
        assertTrue(m.find(text));
        TableFragment block = m.getFragment();
        assertEquals(4,block.getHeader().length);
        assertEquals("| rhio  | 384 | robot     | $3,000 |",block.getCellBlocks()[0]);
        assertEquals("| haroo | .3  | bird      | $430   |",block.getCellBlocks()[1]);

    }


}