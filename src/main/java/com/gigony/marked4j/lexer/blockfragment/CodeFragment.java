package com.gigony.marked4j.lexer.blockfragment;

import com.gigony.marked4j.util.TextSegment;

/**
 * @author gigony
 * @since 9/21/14
 */
public class CodeFragment extends Fragment {
    private final TextSegment codeSegment;
    private String language;
    private boolean escaped;

    public CodeFragment(TextSegment segment, TextSegment codeSegment) {
        this(segment, codeSegment, "", false);
    }

    public CodeFragment(TextSegment segment, TextSegment cSegment, String lang, boolean isEscaped) {
        super(segment);
        codeSegment = cSegment;
        language = lang;
        escaped = isEscaped;
    }

    public String getCodeText() {
        return codeSegment.toString();
    }

    public String getLanguage() {
        return language;
    }

    public boolean isEscaped() {
        return escaped;
    }
}
