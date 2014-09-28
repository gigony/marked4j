package com.gigony.marked4j.lexer;

import com.gigony.marked4j.lexer.blockfragment.Fragment;
import com.gigony.marked4j.util.TextSegment;

/**
 * @author gigony
 * @since 9/20/14
 */
public abstract class AbstractFragmentMatcher {
    public abstract boolean find(TextSegment text);

    public abstract Fragment getFragment();

    public abstract boolean process(MarkedLexer lexer, TextSegment src, boolean top, boolean bq);


    public static String replaceAll(String p, String... map) {
        int mapLen = map.length;
        for (int i = 0; i < mapLen; i += 2) {
            StringBuffer buf = new StringBuffer(p.length());
            int idx = 0;
            int len = p.length();
            while (idx != -1 && idx < len) {
                int nextIdx = p.indexOf(map[i], idx);
                if (nextIdx >= 0) {
                    buf.append(p.substring(idx, nextIdx));
                    buf.append(map[i + 1]);
                    idx = nextIdx + map[i].length();
                } else {
                    buf.append(p.substring(idx));
                    idx = -1;
                }
            }
            p = buf.toString();
        }
        return p;
    }
}
