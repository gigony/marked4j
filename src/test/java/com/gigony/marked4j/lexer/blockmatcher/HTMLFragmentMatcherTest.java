package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.blockfragment.HTMLFragment;
import com.gigony.marked4j.util.TextSegment;
import org.junit.Test;

import static org.junit.Assert.*;

public class HTMLFragmentMatcherTest {
    @Test
    public void htmlFragmentMatchTest() {
        HTMLFragmentMatcher m = new HTMLFragmentMatcher();
        TextSegment text = new TextSegment("<table> <tr> <td> test </td> </tr> </table>\n\n\nprint");
        assertTrue(m.find(text));
        HTMLFragment block = m.getFragment();
        assertEquals("<table> <tr> <td> test </td> </tr> </table>\n\n\n", block.getRawText());
        assertFalse(block.isPre());
        assertEquals("<table> <tr> <td> test </td> </tr> </table>\n\n\n", block.getContentSegment().toString());

    }

}