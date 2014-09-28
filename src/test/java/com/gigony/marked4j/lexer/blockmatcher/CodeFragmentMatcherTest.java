package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.blockfragment.CodeFragment;
import com.gigony.marked4j.util.TextSegment;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CodeFragmentMatcherTest {

    @Test
    public void CodeFragmentMatchTest(){
        CodeFragmentMatcher m = new CodeFragmentMatcher();
        TextSegment text = new TextSegment("    printf(\"Hello World\");\n    test=5;\n# header\n");
        assertTrue(m.find(text));
        CodeFragment block = m.getFragment();
        assertEquals("    printf(\"Hello World\");\n    test=5;\n",block.getRawText());
        assertEquals("printf(\"Hello World\");\ntest=5;\n",block.getCodeText());
    }


}