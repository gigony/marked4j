package com.gigony.marked4j.lexer.blockmatcher;

import com.gigony.marked4j.lexer.AbstractFragmentMatcher;
import com.gigony.marked4j.lexer.MarkedLexer;
import com.gigony.marked4j.lexer.blockfragment.TableFragment;
import com.gigony.marked4j.lexer.token.Token;
import com.gigony.marked4j.lexer.token.TokenType;
import com.gigony.marked4j.util.TextSegment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gigony
 * @since 9/28/14
 */
public class TableFragmentMatcher extends AbstractFragmentMatcher {
    static final Pattern pattern = Pattern.compile("^[ ]*\\|(.+)\\n *\\|([ ]*[-:]+[-| :]*)\\n((?:[ ]*\\|.*(?:\\n|$))*)\\n*");
    static final Pattern alignRight = Pattern.compile("^[ ]*-+:[ ]*$");
    static final Pattern alignCenter = Pattern.compile("^[ ]*:-+:[ ]*$");
    static final Pattern alignLeft = Pattern.compile("^[ ]*:-+[ ]*$");
    Matcher matcher;
    TextSegment text;

    @Override
    public boolean find(TextSegment txt) {
        text = txt;
        matcher = pattern.matcher(txt);

        return matcher.find();
    }

    @Override
    public TableFragment getFragment() {
        TextSegment textSegment = text.subSequence(matcher.start(), matcher.end());
        String[] header=text.subSequence(matcher.start(1),matcher.end(1)).toString().replaceAll("^[ ]*|[ ]*\\|[ ]*$","").split("[ ]*\\|[ ]*");
        String[] align=text.subSequence(matcher.start(2),matcher.end(2)).toString().replaceAll("^[ ]*|\\|[ ]*$","").split("[ ]*\\|[ ]*");
        String[] cellBlocks=text.subSequence(matcher.start(3),matcher.end(3)).toString().replaceAll("(?:[ ]*\\|[ ]*)?\\n$","").split("\\n");
        return new TableFragment(textSegment, header,align,cellBlocks);
    }



    @Override
    public boolean process(MarkedLexer lexer, TextSegment src, boolean top, boolean bq) {
        if (top && find(src)) {
            TableFragment fragment = getFragment();
            int len = fragment.getLength();
            src.shiftStart(len);

            String[] align = fragment.getAlign();

            for (int i = 0; i < align.length; i++) {
                if (alignRight.matcher(align[i]).matches()){
                    align[i] = "right";
                } else if (alignCenter.matcher(align[i]).matches()){
                    align[i] = "center";
                } else if (alignLeft.matcher(align[i]).matches()) {
                    align[i] = "left";
                } else {
                    align[i] = null;
                }
            }

            String[] cellBlocks = fragment.getCellBlocks();
            String[][] cells = fragment.getCells();
            for (int i = 0; i < cellBlocks.length; i++) {
                cells[i] = cellBlocks[i].replaceAll("^[ ]*\\|[ ]*|[ ]*\\|[ ]*$","").split("[ ]*\\|[ ]*");
            }

            lexer.pushToken(new Token(TokenType.TABLE, fragment));
            return true;
        }
        return false;
    }
}
