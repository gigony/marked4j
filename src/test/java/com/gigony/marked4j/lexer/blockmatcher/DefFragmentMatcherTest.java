package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.blockfragment.DefFragment;
import com.gigony.marked4j.util.TextSegment;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefFragmentMatcherTest {
    @Test
    public void defFragmentMatchTest(){
        DefFragmentMatcher m = new DefFragmentMatcher();
        TextSegment text = new TextSegment(" [google]: http://www.google.com \"google def\" \n\n\n");
        assertTrue(m.find(text));
        DefFragment block = m.getFragment();
        assertEquals(" [google]: http://www.google.com \"google def\" \n\n\n",block.getRawText());
        assertEquals("google",block.getLinkText().toString());
        assertEquals("http://www.google.com",block.getHrefText().toString());
        assertEquals("google def",block.getTitleText().toString());

        text = new TextSegment(" [google]: http://www.google.com    (google def) \n\n\n");
        assertTrue(m.find(text));
        block = m.getFragment();
        assertEquals(" [google]: http://www.google.com    (google def) \n\n\n",block.getRawText());
        assertEquals("google",block.getLinkText().toString());
        assertEquals("http://www.google.com",block.getHrefText().toString());
        assertEquals("google def",block.getTitleText().toString());

        text = new TextSegment(" [google]: http://www.google.com   \n\n\n");
        assertTrue(m.find(text));
        block = m.getFragment();
        assertEquals(" [google]: http://www.google.com   \n\n\n",block.getRawText());
        assertEquals("google",block.getLinkText().toString());
        assertEquals("http://www.google.com",block.getHrefText().toString());
        assertEquals("",block.getTitleText().toString());



    }

}