package com.gigony.marked4j.lexer;

import org.junit.Test;

public class MarkedLexerTest {

    @Test
    public void lexerTest(){
        MarkedLexer lexer = new MarkedLexer();
//        lexer.lexer("## Unordered\n" +
//                        "\n" +
//                        "Asterisks tight:\n" +
//                        "\n" +
//                        "*\tasterisk 1\n" +
//                        "*\tasterisk 2\n" +
//                        "*\tasterisk 3\n" +
//                        "\n" +
//                        "\n" +
//                        "Asterisks loose:\n" +
//                        "\n" +
//                        "*\tasterisk 1\n" +
//                        "\n" +
//                        "*\tasterisk 2\n" +
//                        "\n" +
//                        "*\tasterisk 3\n" +
//                        "\n" +
//                        "* * *\n" +
//                        "\n" +
//                        "Pluses tight:\n" +
//                        "\n" +
//                        "+\tPlus 1\n" +
//                        "+\tPlus 2\n" +
//                        "+\tPlus 3\n" +
//                        "\n" +
//                        "\n" +
//                        "Pluses loose:\n" +
//                        "\n" +
//                        "+\tPlus 1\n" +
//                        "\n" +
//                        "+\tPlus 2\n" +
//                        "\n" +
//                        "+\tPlus 3\n" +
//                        "\n" +
//                        "* * *\n" +
//                        "\n" +
//                        "\n" +
//                        "Minuses tight:\n" +
//                        "\n" +
//                        "-\tMinus 1\n" +
//                        "-\tMinus 2\n" +
//                        "-\tMinus 3\n" +
//                        "\n" +
//                        "\n" +
//                        "Minuses loose:\n" +
//                        "\n" +
//                        "-\tMinus 1\n" +
//                        "\n" +
//                        "-\tMinus 2\n" +
//                        "\n" +
//                        "-\tMinus 3\n" +
//                        "\n" +
//                        "\n" +
//                        "## Ordered\n" +
//                        "\n" +
//                        "Tight:\n" +
//                        "\n" +
//                        "1.\tFirst\n" +
//                        "2.\tSecond\n" +
//                        "3.\tThird\n" +
//                        "\n" +
//                        "and:\n" +
//                        "\n" +
//                        "1. One\n" +
//                        "2. Two\n" +
//                        "3. Three\n" +
//                        "\n" +
//                        "\n" +
//                        "Loose using tabs:\n" +
//                        "\n" +
//                        "1.\tFirst\n" +
//                        "\n" +
//                        "2.\tSecond\n" +
//                        "\n" +
//                        "3.\tThird\n" +
//                        "\n" +
//                        "and using spaces:\n" +
//                        "\n" +
//                        "1. One\n" +
//                        "\n" +
//                        "2. Two\n" +
//                        "\n" +
//                        "3. Three\n" +
//                        "\n" +
//                        "Multiple paragraphs:\n" +
//                        "\n" +
//                        "1.\tItem 1, graf one.\n" +
//                        "\n" +
//                        "\tItem 2. graf two. The quick brown fox jumped over the lazy dog's\n" +
//                        "\tback.\n" +
//                        "\t\n" +
//                        "2.\tItem 2.\n" +
//                        "\n" +
//                        "3.\tItem 3.\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "## Nested\n" +
//                        "\n" +
//                        "*\tTab\n" +
//                        "\t*\tTab\n" +
//                        "\t\t*\tTab\n" +
//                        "\n" +
//                        "Here's another:\n" +
//                        "\n" +
//                        "1. First\n" +
//                        "2. Second:\n" +
//                        "\t* Fee\n" +
//                        "\t* Fie\n" +
//                        "\t* Foe\n" +
//                        "3. Third\n" +
//                        "\n" +
//                        "Same thing but with paragraphs:\n" +
//                        "\n" +
//                        "1. First\n" +
//                        "\n" +
//                        "2. Second:\n" +
//                        "\t* Fee\n" +
//                        "\t* Fie\n" +
//                        "\t* Foe\n" +
//                        "\n" +
//                        "3. Third\n" +
//                        "\n" +
//                        "\n" +
//                        "This was an error in Markdown 1.0.1:\n" +
//                        "\n" +
//                        "*\tthis\n" +
//                        "\n" +
//                        "\t*\tsub\n" +
//                        "\n" +
//                        "\tthat"
//                );


//        lexer.lexer("# N\n" +
//                "\n" +
//                "*\tT1\n" +
//                "\t*\tT2\n" +
//                "\n");
        lexer.lexer(
                                "M\n" +
                        "\n" +
                        "-\tM1\n" +
                        "-\tM2\n" +
                        "-\tM3\n" +
                        "\n");

//        String a="* hello\nworld\n* how\nare\n* * *\nyou today?\n* test\n";
//        String b="* hello\\nworld\\n* how\\nare\\n* * *\\nyou today?\\n* test\\n";
////        String a="bba\nabbba\nbbca\nabba\n";
//
////        Matcher m = Pattern.compile("^([ab]+\\n)+").matcher(a);
//        Pattern p = Pattern.compile(ListFragmentMatcher.patternString);//"^([ ]*)((?:[*+-]|\\d+\\.))[ ][\\s\\S]+?(?:\\n+(?=\\1?(?:[-*_][ ]*){3,}(?:\\n+|$))|\\n+(?=^[ ]*\\[([^\\]]+)\\]:[ ]*<?([^\\s>]+)>?(?:[ ]+[\"(]([^\\n]+)[\")])?[ ]*(?:\\n+|$))|\\n{2,}(?![ ])(?!\\1(?:[*+-]|\\d+\\.)[ ])\\n*|\\s*$)");
//        Matcher m = p.matcher(a);
//        m.find();
//        System.out.println(m.group());
//        System.out.println("--------------");
//
//        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
//        try {
//
//            engine.eval(String.format("print(\"%s\".match(/%s/g));",b,"^([ ]*)((?:[*+-]|\\d+\\.))[ ][\\s\\S]+?(?:\\n+(?=\\1?(?:[-*_][ ]*){3,}(?:\\n+|$))|\\n+(?=^[ ]*\\[([^\\]]+)\\]:[ ]*<?([^\\s>]+)>?(?:[ ]+[\"(]([^\\n]+)[\")])?[ ]*(?:\\n+|$))|\\n{2,}(?![ ])(?!\\1(?:[*+-]|\\d+\\.)[ ])\\n*|\\s*$)"
//            ));
//        } catch (ScriptException e) {
//            e.printStackTrace();
//        }


////        m.matches();
//        int lastPos=0;
//        while(m.find()) {
/////        m.find();
//            System.out.println(m.start()+"-"+lastPos);
//           if(m.start()!=lastPos)
//                break;
//            lastPos=m.end();
//            System.out.println(m.toString());
//        }
//        System.out.println(ListFragmentMatcher.patternString);
//
    }
    @Test
    public void NPTableLexingTest(){
        MarkedLexer lexer = new MarkedLexer();
        lexer.lexer("Markdown | Less | Pretty\n" +
                "--- | --- | ---\n" +
                "*Still* | `renders` | **nicely**\n" +
                "1 | 2 | 3\nprint");
        lexer.print(lexer.getTokens());
    }

    @Test
    public void tableLexingTest(){
        MarkedLexer lexer = new MarkedLexer();
        lexer.lexer("| name  | age | gender    | money  |\n" +
                "|-------|:---:|-----------|-------:|\n" +
                "| rhio  | 384 | robot     | $3,000 |\n" +
                "| haroo | .3  | bird      | $430   |\n" +
                "| jedi  | ?   | undefined | $0     |\nprint");
        lexer.print(lexer.getTokens());
    }




}