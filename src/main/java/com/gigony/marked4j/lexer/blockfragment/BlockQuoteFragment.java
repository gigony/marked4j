package com.gigony.marked4j.lexer.blockfragment;

import com.gigony.marked4j.util.TextSegment;

/**
 * @author gigony
 * @since 9/21/14
 */
public class BlockQuoteFragment extends Fragment {
    TextSegment strippedSegment;

    public BlockQuoteFragment(TextSegment textSegment, TextSegment sSegment) {
        super(textSegment);
        strippedSegment = sSegment;
    }

    public TextSegment getStrippedSegment() {
        return strippedSegment;
    }
}
