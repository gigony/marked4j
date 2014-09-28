package com.gigony.marked4j.renderer;

/**
 * @author gigony
 * @since 9/27/14
 */
public abstract class AbstractHighlighter {
    public abstract String highlight(String code, String lang);
}
