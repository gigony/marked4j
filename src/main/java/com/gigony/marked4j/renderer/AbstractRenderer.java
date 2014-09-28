package com.gigony.marked4j.renderer;

import com.gigony.marked4j.parser.token.TableToken;

/**
 * @author gigony
 * @since 9/27/14
 */
public abstract class AbstractRenderer {
    StringBuffer body;

    public AbstractRenderer() {
        body = new StringBuffer();
    }


    public void append(String text) {
        body.append(text);
    }

    public StringBuffer getBody() {
        return body;
    }

    public abstract String hr();


    public abstract String heading(String text, int level, String raw);

    public abstract String code(String code, String lang, boolean escaped);

    public abstract String blockquote(String quote);

    public abstract String list(String body, boolean ordered);

    public abstract String listItem(String text);

    public abstract String html(String html);

    public abstract String paragraph(String text);

    public abstract String tablecell(String content, TableToken.TableFlag flags);

    public abstract String tablerow(String content);

    public abstract String table(String header, String body);
}
