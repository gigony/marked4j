package com.gigony.marked4j;

import com.gigony.marked4j.renderer.AbstractHighlighter;
import com.gigony.marked4j.renderer.AbstractRenderer;
import com.gigony.marked4j.renderer.DefaultHighlighter;
import com.gigony.marked4j.renderer.DefaultRenderer;

/**
 * @author gigony
 * @since 9/23/14
 */
public class Options {
    private boolean gfm = true;
    private boolean tables = true;
    private boolean breaks = false;
    private boolean pedantic = false;
    private boolean sanitize = false;
    private boolean smartLists = false;
    private boolean slient = false;
    private AbstractHighlighter highlight = new DefaultHighlighter();
    private String langPrefix = "lang-";
    private boolean smartypants = false;
    private String headerPrefix = "";
    private AbstractRenderer renderer;
    private boolean xhtml = false;

    public Options() {
        renderer = new DefaultRenderer(this);
    }


    public boolean isPedantic() {
        return pedantic;
    }

    public boolean isSmartLists() {
        return smartLists;
    }

    public boolean isSanitize() {
        return sanitize;
    }

    public boolean isXHTML() {
        return xhtml;
    }

    public String getHeaderPrefix() {
        return headerPrefix;
    }

    public AbstractHighlighter getHighlighter() {
        return highlight;
    }

    public String getLangPrefix() {
        return langPrefix;
    }

    public AbstractRenderer getRenderer() {
        return renderer;
    }
}
