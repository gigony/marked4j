package com.gigony.marked4j.lexer.blockfragment;

import com.gigony.marked4j.util.TextSegment;

/**
 * @author gigony
 * @since 9/21/14
 */
public class HeadFragment extends Fragment {
    TextSegment titleSegment;
    int depth;

    public HeadFragment(TextSegment textSegment, TextSegment title, int dep) {
        super(textSegment);
        titleSegment = title;
        depth = dep;
    }

    public String getTitle() {
        return titleSegment.toString();
    }

    public int getDepth() {
        return depth;
    }
}
