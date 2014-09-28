package com.gigony.marked4j.lexer.blockfragment;

import com.gigony.marked4j.util.TextSegment;

/**
 * @author gigony
 * @since 9/21/14
 */
public abstract class Fragment {
    TextSegment textSegment;

    public Fragment(TextSegment segment) {
        textSegment = segment;
    }

    public String getRawText() {
        return textSegment.toString();
    }

    public int getCodeStart() {
        return textSegment.getCodeStart();
    }

    public int getCodeEnd() {
        return textSegment.getCodeEnd();
    }

    public int getCodeLength() {
        return getCodeEnd() - getCodeStart();
    }

    public int getLength() {
        return textSegment.length();
    }


    public TextSegment getTextSegment() {
        return textSegment;
    }
}
