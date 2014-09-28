package com.gigony.marked4j.util;

/**
 * @author gigony
 * @since 9/27/14
 */
public class TextUtility {
    public static String escape(String html, boolean encode) {
        return html.replaceAll(!encode ? "&(?!#?\\w+;)" : "&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;")
                .replace("'", "&#39;");
    }

    public static String getQuoteBlockText(TextSegment textSegment, int tabWidth) {
        String text = textSegment.toString();
        StringBuffer buf = new StringBuffer(text.length());

        String[] splits = text.split("\n");
        for (String line : splits) {
            if (line.length() > tabWidth)
                buf.append(line.substring(tabWidth));
            buf.append('\n');
        }
        return buf.toString();

        /*char[] charArr=textSegment.getSrcArr();
        int start=textSegment.getOffset();
        int length = textSegment.getLength();
        int end = start + length;
        StringBuffer buf=new StringBuffer(length);
        int spaceCount=0;
        boolean start=0;
        for(int i=start;i<end;i++){
            case(charArr[i]){
                '\n': spaceCount
            }
        } */
    }
}
