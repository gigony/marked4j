package com.gigony.marked4j.util;

/**
 * @author gigony
 * @since 9/27/14
 */

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author gigony
 * @since 9/22/14
 */
public class TreeBasedMappedTextSegment extends TextSegment {
    private static final int MAGIC_VALUE = 999999999;
    char[] codeArr;
    TreeMap<Integer, Integer> map; // array(srcArr) index => code pos(codeArr)

    public TreeBasedMappedTextSegment(TextSegment segment) {
        super(Arrays.copyOfRange(segment.getSrcArr(), segment.getStart(), segment.getEnd()), 0, segment.length());
        if (segment instanceof TreeBasedMappedTextSegment) {
            codeArr = ((TreeBasedMappedTextSegment) segment).codeArr;
            TreeMap<Integer, Integer> oldMap = ((TreeBasedMappedTextSegment) segment).map;
            map = new TreeMap<Integer, Integer>();
            int delta = segment.getStart();

//            SortedMap<Integer, Integer> subMap = oldMap.subMap(oldMap.floorKey(segment.getStart()), true, oldMap.floorKey(segment.getEnd()), true);
            for (Map.Entry<Integer, Integer> item : oldMap.entrySet()) {
                int oldValue = item.getValue();
                if (oldValue != MAGIC_VALUE)
                    map.put(item.getKey() - delta, oldValue + delta);
                else {
                    int newKey = item.getKey() - delta;
                    map.put(newKey, MAGIC_VALUE);
                    if (newKey == 0)
                        System.out.println("@#@#@#");
                }
            }
            Map.Entry<Integer, Integer> smallestEntry = getCodeInfoEntry(0);
            if (smallestEntry == null)
                System.out.println("@#@#");

            NavigableMap<Integer, Integer> subMap = map.subMap(0, true, srcArr.length, true);
            map = new TreeMap<Integer, Integer>(subMap);
            map.put(0, smallestEntry.getValue());


        } else {
            codeArr = segment.getSrcArr();
            map = new TreeMap<Integer, Integer>();
            map.put(0, segment.getStart());
        }
    }

    public TreeBasedMappedTextSegment(TreeBasedMappedTextSegment segment, int arrStart, int arrEnd) {
        super(Arrays.copyOfRange(segment.getSrcArr(), arrStart, arrEnd), 0, arrEnd - arrStart);
        TreeMap<Integer, Integer> oldMap = segment.map;
        codeArr = segment.codeArr;
        map = new TreeMap<Integer, Integer>();
        int delta = arrStart;

//        SortedMap<Integer, Integer> subMap = oldMap.subMap(oldMap.floorKey(segment.getStart()), true, oldMap.floorKey(segment.getEnd()), true);
        for (Map.Entry<Integer, Integer> item : oldMap.entrySet()) {
            int oldValue = item.getValue();
            if (oldValue != MAGIC_VALUE)
                map.put(item.getKey() - delta, oldValue + delta);
            else {
                int newKey = item.getKey() - delta;
                map.put(newKey, MAGIC_VALUE);
                if (newKey == 0)
                    System.out.println("@#@#@#");
            }
        }

        Map.Entry<Integer, Integer> smallestEntry = getCodeInfoEntry(0);
        if (smallestEntry == null)
            System.out.println("@#@#");

        NavigableMap<Integer, Integer> subMap = map.subMap(0, true, srcArr.length, true);
        map = new TreeMap<Integer, Integer>(subMap);
        map.put(0, smallestEntry.getValue());

    }

    public TreeBasedMappedTextSegment(String text, int base) {
        super(text);
        codeArr = srcArr;
        map = new TreeMap<Integer, Integer>();
        map.put(0, base);
    }

    public TreeBasedMappedTextSegment(String text) {
        this(text, 0);
    }

    public TreeBasedMappedTextSegment(char[] chArr, int base) {
        super(chArr);
        codeArr = chArr;
        map = new TreeMap<Integer, Integer>();
        map.put(0, base);
    }

    public TreeBasedMappedTextSegment(char[] chArr, int off, int len, int base) {
        super(chArr, off, len);
        codeArr = chArr;
        map = new TreeMap<Integer, Integer>();
        map.put(0, base);
    }


    /**
     * Replace text (only smaller replacement is supported)
     *
     * @param regularExp
     * @param replacement
     * @param replaceAll
     */
    public TreeBasedMappedTextSegment replaceText(String regularExp, String replacement, boolean replaceAll) {
        Pattern pattern = Pattern.compile(regularExp, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(this);
        if (!matcher.find())
            return this;

        StringBuffer buf = new StringBuffer(length());
        int replacementLen = replacement.length();
        int oldIdx = 0;
        int oldPivot = Integer.MIN_VALUE;
        int delta = 0;
        HashMap<Integer, Integer> addedEntry = new HashMap<Integer, Integer>();
        TreeMap<Integer, Integer> newMap = new TreeMap<Integer, Integer>();

        while (true) {
            int targetLen = matcher.end() - matcher.start();
            int gap = targetLen - replacementLen;

            int srcFoundPos = offset + matcher.start() + replacementLen;
            int insertionPoint = srcFoundPos - delta;
//            if (delta != 0) {
            NavigableMap<Integer, Integer> targetMap = map.subMap(oldPivot, true, insertionPoint, false);
            ArrayList<Integer> keySet = new ArrayList<Integer>(targetMap.keySet());
            for (Integer key : keySet) {
                int newKey = key - delta;
                int oldValue = map.get(key);
                if (oldValue != MAGIC_VALUE)
                    newMap.put(newKey, oldValue + delta);
                else
                    newMap.put(newKey, MAGIC_VALUE);

            }
//            }
            oldPivot = insertionPoint;
            delta += gap;

            if (gap != 0)
                addedEntry.put(insertionPoint, map.floorEntry(srcFoundPos + targetLen).getValue() + delta);

            if (gap < 0) {
                addedEntry.put(insertionPoint + gap, MAGIC_VALUE);
            }

            buf.append(subSequence(oldIdx, offset + matcher.start()));
            buf.append(replacement);
            oldIdx = offset + matcher.end();
            if (!replaceAll || !matcher.find()) break;
        }


        int finalArrLen = buf.length() + (srcArr.length - oldIdx);
        // append remaining elements, ignoring elements larger than scope
        if (oldPivot < srcArr.length) {
            NavigableMap<Integer, Integer> targetMap = map.subMap(oldPivot, false, srcArr.length, false);
            ArrayList<Integer> keySet = new ArrayList<Integer>(targetMap.keySet());
            for (Integer key : keySet) {
                int newKey = key - delta;
                int oldValue = map.get(key);
                if (newKey < finalArrLen) {
                    if (oldValue != MAGIC_VALUE)
                        newMap.put(newKey, oldValue + delta);
                    else
                        newMap.put(newKey, MAGIC_VALUE);
                }
            }
        }
//        // remove elements smaller than scope
//        NavigableMap<Integer, Integer> targetMap = map.subMap(Integer.MIN_VALUE, false, map.floorKey(0), false);
//        ArrayList<Integer> keySet = new ArrayList<Integer>(targetMap.keySet());
//        for (Integer key : keySet)
//            map.remove(key);

        // add new entry
        for (Map.Entry<Integer, Integer> entry : addedEntry.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (key < finalArrLen) {
//                if(!(key==0 && value==MAGIC_VALUE))
                if (key == 0 && value == MAGIC_VALUE)
                    System.out.println("@#@#");
                newMap.put(key, value);
            }
        }

        // remove elements smaller than scope
        Map.Entry<Integer, Integer> smallestEntry = newMap.floorEntry(0);

        // set base element
        if (smallestEntry.getKey() < 0) {
            NavigableMap<Integer, Integer> targetMap = newMap.subMap(Integer.MIN_VALUE, false, 0, false);
            ArrayList<Integer> keySet = new ArrayList<Integer>(targetMap.keySet());
            for (Integer key : keySet)
                newMap.remove(key);
            newMap.remove(smallestEntry.getKey());
            newMap.put(0, smallestEntry.getValue());
        }

        if (oldIdx != srcArr.length) {
            buf.append(subSequence(oldIdx, srcArr.length));
        }

        srcArr = buf.toString().toCharArray();
        length -= delta;
        map = newMap;

        return this;
    }

    @Override
    public TreeBasedMappedTextSegment subSequence(int start, int end) {
        if (start == -1)
            return new TreeBasedMappedTextSegment(this, getArrayPos(start), getArrayPos(start));
        else
            return new TreeBasedMappedTextSegment(this, getArrayPos(start), getArrayPos(end));


    }

    @Override
    public int getCodePos(int pos) {
        int arrayPos = getArrayPos(pos);
        while (true) {
            Map.Entry<Integer, Integer> entry = map.floorEntry(arrayPos);
            if (entry == null) {
                System.out.println("@#@#@#");
                return -1;
            }
            int value = entry.getValue();
            if (value != MAGIC_VALUE)
                return value + arrayPos;
            else {
                arrayPos = entry.getKey() - 1;
            }
        }
    }


    private Map.Entry<Integer, Integer> getCodeInfoEntry(int pos) {
        int arrayPos = getArrayPos(pos);
        while (true) {
            Map.Entry<Integer, Integer> entry = map.floorEntry(arrayPos);
            if (entry == null) {
                System.out.println("@#@#@#");
                return null;
            }
            int value = entry.getValue();
            if (value != MAGIC_VALUE)
                return entry;
            else {
                arrayPos = entry.getKey() - 1;
            }
        }
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
