package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.blockfragment.CodeFragment;
import com.gigony.marked4j.util.TextSegment;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class FencesFragmentMatcherTest {
    @Test
    public void fencesFragmentTest(){
        FencesFragmentMatcher m = new FencesFragmentMatcher();
        TextSegment text = new TextSegment("```java\nprintf(\"Hello World\");\ntest=5;\n```\n# header\n");
        assertTrue(m.find(text));
        CodeFragment block = m.getFragment();
        assertEquals("```java\nprintf(\"Hello World\");\ntest=5;\n```\n",block.getRawText());
        assertEquals("printf(\"Hello World\");\ntest=5;",block.getCodeText());
        assertEquals("java",block.getLanguage());
        assertEquals(false,block.isEscaped());
    }

}