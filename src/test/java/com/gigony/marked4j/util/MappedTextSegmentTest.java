package com.gigony.marked4j.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class MappedTextSegmentTest {
    @Test
    public void insertionTest(){
        TextSegment text = new TextSegment("012*45*789");
        text = text.subSequence(2,text.length());
        assertEquals("2*45*789",text.toString());
        assertEquals(2,text.getCodePos(0));
        MappedTextSegment mappedText = new MappedTextSegment(text);
        mappedText.replaceText("\\*","",true);
        assertEquals("245789",mappedText.toString());
        assertEquals(2,mappedText.getCodePos(0));
        assertEquals(1,mappedText.getArrayPos(1));
        assertEquals(4,mappedText.getCodePos(1));

        mappedText.shiftStart(2);
        MappedTextSegment mappedText2 = new MappedTextSegment(mappedText);
        assertEquals("5789",mappedText2.toString());
        assertEquals(5,mappedText2.getCodePos(0));
        assertEquals(8,mappedText2.getCodePos(2));

        mappedText = new MappedTextSegment("\n  \n  \n    \n",0);
        mappedText = mappedText.subSequence(4,mappedText.length());
        assertEquals("  \n    \n",mappedText.toString());
        mappedText.replaceText("^[ ]+$","",true);
        assertEquals("\n\n",mappedText.toString());
        assertEquals(6,mappedText.getCodePos(0));
        assertEquals(11,mappedText.getCodePos(1));


        mappedText = new MappedTextSegment("\n  \n  \nasdf    \nt",0);
        mappedText = mappedText.subSequence(5,mappedText.length());
        assertEquals(" \nasdf    \nt",mappedText.toString());
        mappedText.replaceText("\\n", "\t", true);
        assertEquals(" \tasdf    \tt", mappedText.toString());
        mappedText.replaceText("\\t", " ", true);
        assertEquals("  asdf     t", mappedText.toString());

        mappedText = new MappedTextSegment("01234567890\nabcdefghijklmn",3);
        mappedText.replaceText("\\n","\t",true);
        mappedText.replaceText("0","3",true);
        mappedText.replaceText("3","0",true);
        mappedText.replaceText("0","",true);
        mappedText.replaceText("[567]","",true);
        mappedText.replaceText("\\n","",true);
        mappedText.replaceText("12489","",true);
        mappedText.replaceText("g","",true);
        mappedText.replaceText("[ac-n]+", "", true);
        assertEquals("\tb", mappedText.toString());


        mappedText = new MappedTextSegment("\t123456789\n\tbcdefghijklmn",0);
        mappedText.replaceText("\\t","    ",true);
        assertEquals("    123456789\n    bcdefghijklmn",mappedText.toString());
        assertEquals(1,mappedText.getCodePos(4));
        assertEquals(11,mappedText.getCodePos(14));
        assertEquals(11,mappedText.getCodePos(15));
        assertEquals(11,mappedText.getCodePos(16));
        assertEquals(11,mappedText.getCodePos(17));
        assertEquals(12,mappedText.getCodePos(18));

        mappedText2 = new MappedTextSegment(mappedText,0,20);
        mappedText2.replaceText("34", "####", true);
        assertEquals(3,mappedText2.getCodePos(6));
        assertEquals(4,mappedText2.getCodePos(7));
        assertEquals(4,mappedText2.getCodePos(8));
        assertEquals(4,mappedText2.getCodePos(9));




    }


}