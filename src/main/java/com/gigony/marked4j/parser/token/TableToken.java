package com.gigony.marked4j.parser.token;

import com.gigony.marked4j.lexer.InlineLexer;
import com.gigony.marked4j.lexer.blockfragment.TableFragment;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.parser.MarkedParser;
import com.gigony.marked4j.renderer.AbstractRenderer;

/**
 * @author gigony
 * @since 9/28/14
 */
public class TableToken extends AbstractTokenProcessor {
    public class TableFlag {
        public boolean header;
        public String align;

        public TableFlag(boolean header, String align) {
            this.header = header;
            this.align = align;
        }
    }

    @Override
    public String tok(MarkedParser parser, Token token) {
        TableFragment fragment = (TableFragment) token.getFragment();
        AbstractRenderer renderer = parser.getRenderer();
        InlineLexer inlineLexer = parser.getInlineLexer();


        String[] header = fragment.getHeader();
        String[] align = fragment.getAlign();
        String[][] cells = fragment.getCells();

        StringBuffer headerBuf = new StringBuffer();
        StringBuffer bodyBuf = new StringBuffer();
        StringBuffer cellBuf = new StringBuffer();

        // header

        for (int i = 0; i < header.length; i++) {
            TableFlag flags = new TableFlag(true, align[i]);
            cellBuf.append(renderer.tablecell(inlineLexer.output(header[i]), flags));
        }
        headerBuf.append(renderer.tablerow(cellBuf.toString()));

        for (int i = 0; i < cells.length; i++) {
            String[] row = cells[i];

            cellBuf.setLength(0);
            for (int j = 0; j < row.length; j++) {
                cellBuf.append(renderer.tablecell(
                        inlineLexer.output(row[j]), new TableFlag(false, (j < align.length ? align[j] : null))));
            }

            bodyBuf.append(renderer.tablerow(cellBuf.toString()));
        }
        return renderer.table(headerBuf.toString(), bodyBuf.toString());
    }
}
