package com.gigony.marked4j.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gigony
 * @since 9/20/14
 */
public class TextSegment implements CharSequence {
    char[] srcArr;
    int offset;
    int length;

    public TextSegment(String text) {
        this(text.toCharArray(), 0, text.length());
    }

    public TextSegment(char[] chArr) {
        this(chArr, 0, chArr.length);
    }

    public TextSegment(char[] chArr, int off, int len) {
        srcArr = chArr;
        offset = off;
        length = len;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public char charAt(int index) {
        return srcArr[offset + index];
    }

    @Override
    public TextSegment subSequence(int start, int end) {
        if (start == -1)
            return new TextSegment(srcArr, offset, 0);
        else
            return new TextSegment(srcArr, offset + start, end - start);
    }

    @Override
    public String toString() {
        if (srcArr != null && length >= 0) {
            return new String(srcArr, offset, length);
        }
        return "";
    }

    public int getOffset() {
        return offset;
    }

    public char[] getSrcArr() {
        return srcArr;
    }


    public int getStart() {
        return offset;
    }

    public boolean shiftStart() {
        return shiftStart(1);
    }

    public boolean shiftStart(int val) {
        int pos = offset + val;
        if (val <= length && pos >= 0) {
            offset += val;
            length -= val;
            return true;
        }
        return false;
    }

    public int getEnd() {
        return offset + length;
    }

    public boolean shiftEnd() {
        return shiftEnd(1);
    }

    public boolean shiftEnd(int val) {
        int pos = offset + length + val;
        if (-val <= length && pos <= srcArr.length) {
            length += val;
            return true;
        }
        return false;

    }

    public boolean contentEquals(String text) {
        if (length != text.length())
            return false;
        for (int i = 0; i < length; i++) {
            if (srcArr[i] != text.charAt(i))
                return false;
        }
        return true;
    }

    public int getCodePos(int pos) {
        return offset + pos;
    }

    public int getCodeStart() {
        return offset;
    }

    public int getCodeEnd() {
        return offset + length;
    }

    public int getArrayPos(int pos) {
        return offset + pos;
    }

    public char getSrcCharAt(int arrPos) {
        return srcArr[arrPos];
    }

    public TextSegment replaceText(String regularExp, String replacement, boolean replaceAll) {
        Pattern pattern = Pattern.compile(regularExp, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(this);
        if (!matcher.find())
            return this;

        StringBuffer buf = new StringBuffer(length());
        int replacementLen = replacement.length();
        int oldIdx = 0;
        int delta = 0;

        while (true) {
            int targetLen = matcher.end() - matcher.start();
            int gap = targetLen - replacementLen;
            delta += gap;
            buf.append(subSequence(oldIdx, offset + matcher.start()));
            buf.append(replacement);
            oldIdx = offset + matcher.end();
            if (!replaceAll || !matcher.find()) break;
        }

        if (oldIdx < srcArr.length) {
            buf.append(subSequence(oldIdx, srcArr.length));
        }

        srcArr = buf.toString().toCharArray();
        length -= delta;
        return this;

    }
}
