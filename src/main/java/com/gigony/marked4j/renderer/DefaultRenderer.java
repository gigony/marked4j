package com.gigony.marked4j.renderer;

import com.gigony.marked4j.Options;
import com.gigony.marked4j.parser.token.TableToken;
import com.gigony.marked4j.util.TextUtility;

/**
 * @author gigony
 * @since 9/27/14
 */
public class DefaultRenderer extends AbstractRenderer {
    Options options;


    public DefaultRenderer(Options opt) {
        options = opt;

    }

    @Override
    public String hr() {
        return options.isXHTML() ? "<hr/>\n" : "<hr>\n";
    }

    @Override
    public String heading(String text, int level, String raw) {
        return String.format("<h%d id=\"%s%s\">%s</h%d>\n", level, options.getHeaderPrefix(), raw.toLowerCase().replaceAll("[^\\w]+", "-"), text, level);
    }

    @Override
    public String code(String code, String lang, boolean escaped) {

        AbstractHighlighter highlighter = options.getHighlighter();
        if (highlighter != null) {
            String out = highlighter.highlight(code, lang);
            if (out != null && !out.equals(code)) {
                escaped = true;
                code = out;
            }
        }

        if (lang == null || "".equals(lang)) {
            return String.format("<pre><code>%s\n</code></pre>", (escaped ? code : TextUtility.escape(code, true)));
        }

        return String.format("<pre><code class=\"%s%s\">%s\n</code></pre>\n", options.getLangPrefix(), TextUtility.escape(lang, true), (escaped ? code : TextUtility.escape(code, true)));

    }

    @Override
    public String blockquote(String quote) {
        return String.format("<blockquote>\n%s</blockquote>\n", quote);
    }

    @Override
    public String list(String body, boolean ordered) {
        String type = ordered ? "ol" : "ul";
        return String.format("<%s>\n%s</%s>\n", type, body, type);
    }

    @Override
    public String listItem(String text) {
        return String.format("<li>%s</li>\n", text);
    }

    @Override
    public String html(String html) {
        return html;
    }

    @Override
    public String paragraph(String text) {
        return String.format("<p>%s</p>\n", text);
    }

    @Override
    public String tablecell(String content, TableToken.TableFlag flags) {
        String type = flags.header ? "th" : "td";
        String tag = flags.align!=null
                ? String.format("<%s style=\"text-align:%s\">",type, flags.align)
                : String.format("<%s>",type);
        return String.format("%s%s</%s>\n",tag,content,type);
    }

    @Override
    public String tablerow(String content) {
        return String.format("<tr>\n%s</tr>\n",content);
    }

    @Override
    public String table(String header, String body) {
        return String.format("<table>\n<thead>\n%s</thead>\n<tbody>\n%s</tbody>\n</table>\n",header,body);
    }
}
