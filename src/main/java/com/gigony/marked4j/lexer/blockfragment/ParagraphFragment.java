package com.gigony.marked4j.lexer.blockfragment;

import com.gigony.marked4j.util.TextSegment;

/**
 * @author gigony
 * @since 9/21/14
 */
public class ParagraphFragment extends Fragment {
    TextSegment contentSegment;

    public ParagraphFragment(TextSegment segment, TextSegment contSegment) {
        super(segment);
        contentSegment = contSegment;
    }

    public TextSegment getContentSegment() {
        return contentSegment;
    }
}
