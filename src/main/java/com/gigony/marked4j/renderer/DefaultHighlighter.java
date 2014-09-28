package com.gigony.marked4j.renderer;

/**
 * @author gigony
 * @since 9/27/14
 */
public class DefaultHighlighter extends AbstractHighlighter {
    @Override
    public String highlight(String code, String lang) {
        return "code";
    }
}
