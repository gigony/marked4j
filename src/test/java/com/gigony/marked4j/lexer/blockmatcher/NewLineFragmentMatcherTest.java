package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.blockfragment.NewLineFragment;
import com.gigony.marked4j.util.TextSegment;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewLineFragmentMatcherTest {
    @Test
    public void SpaceFragmentMatchTest(){
        NewLineFragmentMatcher m = new NewLineFragmentMatcher();
        TextSegment text = new TextSegment("\n\n    printf(\"Hello World\");\n    test=5;\n# header\n");
        assertTrue(m.find(text));
        NewLineFragment block = m.getFragment();
        assertEquals("\n\n",block.getRawText());
    }

}