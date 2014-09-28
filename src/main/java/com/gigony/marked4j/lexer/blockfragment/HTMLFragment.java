package com.gigony.marked4j.lexer.blockfragment;

import com.gigony.marked4j.util.TextSegment;

/**
 * @author gigony
 * @since 9/21/14
 */
public class HTMLFragment extends Fragment {
    TextSegment contentSegment;
    boolean pre;

    public HTMLFragment(TextSegment textSegment, boolean isPre, TextSegment contSegment) {
        super(textSegment);
        pre = isPre;
        contentSegment = contSegment;
    }

    public boolean isPre() {
        return pre;
    }

    public TextSegment getContentSegment() {
        return contentSegment;
    }
}
