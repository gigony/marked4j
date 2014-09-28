package com.gigony.marked4j.util;

import java.util.Arrays;

/**
 * @author gigony
 * @since 9/27/14
 */
public class IntegerBuffer {
    int[] buf;
    int cap;
    int limit;

    public IntegerBuffer(int capacity) {
        buf = new int[capacity];
        cap = capacity;
        limit = 0;
    }

    public void append(int[] srcPos, int oldIdx, int seqEndPos) {
        int seqLen = seqEndPos - oldIdx;
        int newLen = limit + seqLen;

        ensureCapacity(newLen);

        System.arraycopy(srcPos, oldIdx, buf, limit, seqLen);
        limit = newLen;
    }

    private void ensureCapacity(int minCap) {
        if (cap < minCap) {
            int newCap = cap * 2;
            if (newCap - minCap < 0)
                newCap = minCap;
            buf = Arrays.copyOf(buf, newCap);
            cap = newCap;
        }
    }

    public void append(int val, int length) {
        int newLen = limit + length;
        ensureCapacity(newLen);
        Arrays.fill(buf, limit, newLen, val);
        limit = newLen;
    }

    public int[] toIntArray() {
        return Arrays.copyOf(buf, limit);
    }
}
