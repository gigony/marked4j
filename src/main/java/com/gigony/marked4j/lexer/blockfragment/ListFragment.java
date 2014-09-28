package com.gigony.marked4j.lexer.blockfragment;

import com.gigony.marked4j.util.TextSegment;

/**
 * @author gigony
 * @since 9/21/14
 */
public class ListFragment extends Fragment {
    private boolean ordered;
    TextSegment bulletSegment;

    public ListFragment(TextSegment textSegment, TextSegment bulletSeg, boolean isOrdered) {
        super(textSegment);
        bulletSegment = bulletSeg;
        ordered = isOrdered;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public TextSegment getBulletSegment() {
        return bulletSegment;
    }
}
