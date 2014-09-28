package com.gigony.marked4j.lexer.blockfragment;

import com.gigony.marked4j.util.TextSegment;

/**
 * @author gigony
 * @since 9/21/14
 */
public class DefFragment extends Fragment {
    TextSegment linkText;
    TextSegment hrefText;
    TextSegment titleText;

    public DefFragment(TextSegment textSegment, TextSegment link, TextSegment href, TextSegment title) {
        super(textSegment);
        linkText = link;
        hrefText = href;
        titleText = title;
    }

    public TextSegment getLinkText() {
        return linkText;
    }

    public TextSegment getHrefText() {
        return hrefText;
    }

    public TextSegment getTitleText() {
        return titleText;
    }
}
