package com.gigony.marked4j.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gigony
 * @since 9/22/14
 */
public class MappedTextSegment extends TextSegment {
    char[] codeArr;
    int[] srcPos;

    public MappedTextSegment(TextSegment segment) {
        super(Arrays.copyOfRange(segment.getSrcArr(), segment.getStart(), segment.getEnd()), 0, segment.length());
        if (segment instanceof MappedTextSegment) {
            MappedTextSegment mappedSegment = ((MappedTextSegment) segment);
            codeArr = mappedSegment.codeArr;
            srcPos = Arrays.copyOfRange(mappedSegment.getSrcPosArr(), mappedSegment.getStart(), mappedSegment.getEnd());
        } else {
            codeArr = segment.getSrcArr();
            int len = segment.length();
            srcPos = new int[len];
            int pos = segment.getStart();
            for (int i = 0; i < len; i++, pos++) {
                srcPos[i] = pos;
            }
        }
    }

    public int[] getSrcPosArr() {
        return srcPos;
    }

    public MappedTextSegment(MappedTextSegment segment, int arrStart, int arrEnd) {
        super(Arrays.copyOfRange(segment.getSrcArr(), arrStart, arrEnd), 0, arrEnd - arrStart);
        codeArr = segment.codeArr;
        srcPos = Arrays.copyOfRange(segment.getSrcPosArr(), arrStart, arrEnd);
    }

    public MappedTextSegment(String text, int base) {
        super(text);
        codeArr = srcArr;
        int srcLen = srcArr.length;
        srcPos = new int[srcLen];
        int pos = base;
        for (int i = 0; i < srcLen; i++, pos++) {
            srcPos[i] = pos;
        }
    }

    public MappedTextSegment(String text) {
        this(text, 0);
    }

    public MappedTextSegment(char[] chArr, int base) {
        super(chArr);
        codeArr = chArr;
        int srcLen = chArr.length;
        srcPos = new int[srcLen];
        int pos = base;
        for (int i = 0; i < srcLen; i++, pos++) {
            srcPos[i] = pos;
        }
    }

    public MappedTextSegment(char[] chArr, int off, int len, int base) {
        super(chArr, off, len);
        codeArr = chArr;
        int srcLen = codeArr.length;
        srcPos = new int[srcLen];
        int pos = base;
        for (int i = 0; i < srcLen; i++, pos++) {
            srcPos[i] = pos;
        }
    }


    /**
     * Replace text (only smaller replacement is supported)
     *
     * @param regularExp
     * @param replacement
     * @param replaceAll
     */
    public MappedTextSegment replaceText(String regularExp, String replacement, boolean replaceAll) {
        Pattern pattern = Pattern.compile(regularExp, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(this);
        if (!matcher.find())
            return this;

        StringBuffer buf = new StringBuffer(length());


        IntegerBuffer srcPosBuf = new IntegerBuffer(length());
        int replacementLen = replacement.length();
        int oldIdx = 0;
        int delta = 0;
        while (true) {
            int targetLen = matcher.end() - matcher.start();
            int gap = targetLen - replacementLen;
            delta += gap;
            int seqEndPos = offset + matcher.start();
            buf.append(subSequence(oldIdx, seqEndPos));
            buf.append(replacement);
            srcPosBuf.append(srcPos, oldIdx, seqEndPos + Math.min(targetLen, replacementLen));
            if (gap < 0)
                srcPosBuf.append(getCodePos(matcher.end()) - 1, -gap);
            oldIdx = offset + matcher.end();
            if (!replaceAll || !matcher.find()) break;
        }

        if (oldIdx < srcArr.length) {
            buf.append(subSequence(oldIdx, srcArr.length));
            srcPosBuf.append(srcPos, oldIdx, srcArr.length);
        }

        srcArr = buf.toString().toCharArray();
        srcPos = srcPosBuf.toIntArray();
        length -= delta;
        return this;
    }


    @Override
    public MappedTextSegment subSequence(int start, int end) {
        if (start == -1)
            return new MappedTextSegment(this, getArrayPos(start), getArrayPos(start));
        else
            return new MappedTextSegment(this, getArrayPos(start), getArrayPos(end));


    }

    @Override
    public int getCodePos(int pos) {
        int arrayPos = getArrayPos(pos);
        if (arrayPos >= srcPos.length) {
            return srcPos[srcPos.length - 1] + 1;
        }
        return srcPos[arrayPos];
    }


    @Override
    public int getCodeStart() {
        return getCodePos(0);
    }

    @Override
    public int getCodeEnd() {
        return getCodePos(length);
    }
}
