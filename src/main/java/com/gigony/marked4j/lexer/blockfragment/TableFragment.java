package com.gigony.marked4j.lexer.blockfragment;

import com.gigony.marked4j.util.TextSegment;

/**
 * @author gigony
 * @since 9/28/14
 */
public class TableFragment extends Fragment {
    private String[] header;
    private String[] align;
    private String[][] cells;
    private String[] cellBlocks;

    public TableFragment(TextSegment textSegment, String[] header, String[] align, String[] cellBlocks){
        super(textSegment);
        this.header=header;
        this.align=align;
        this.cellBlocks=cellBlocks;
        this.cells=new String[cellBlocks.length][];

    }

    public String[] getHeader() {
        return header;
    }

    public String[] getAlign() {
        return align;
    }

    public String[] getCellBlocks() {
        return cellBlocks;
    }

    public String[][] getCells() {
        return cells;
    }
}
