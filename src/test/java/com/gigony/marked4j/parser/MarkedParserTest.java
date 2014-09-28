package com.gigony.marked4j.parser;

import com.github.rjeschke.txtmark.Processor;
import org.junit.Test;
import org.pegdown.PegDownProcessor;

import static org.junit.Assert.*;

public class MarkedParserTest {

    @Test
    public void parseTest(){
        MarkedParser parser = new MarkedParser();
        long time=System.currentTimeMillis();
        String html = parser.parse("# marked\n" +
                "\n" +
                "> A full-featured markdown parser and compiler, written in JavaScript. Built\n" +
                "> for speed.\n" +
                "\n" +
                "[![NPM version](https://badge.fury.io/js/marked.png)][badge]\n" +
                "\n" +
                "## Install\n" +
                "\n" +
                "``` bash\n" +
                "npm install marked --save\n" +
                "```\n" +
                "\n" +
                "## Usage\n" +
                "\n" +
                "Minimal usage:\n" +
                "\n" +
                "```js\n" +
                "var marked = require('marked');\n" +
                "console.log(marked('I am using __markdown__.'));\n" +
                "// Outputs: <p>I am using <strong>markdown</strong>.</p>\n" +
                "```\n" +
                "\n" +
                "Example setting options with default values:\n" +
                "\n" +
                "```js\n" +
                "var marked = require('marked');\n" +
                "marked.setOptions({\n" +
                "  renderer: new marked.Renderer(),\n" +
                "  gfm: true,\n" +
                "  tables: true,\n" +
                "  breaks: false,\n" +
                "  pedantic: false,\n" +
                "  sanitize: true,\n" +
                "  smartLists: true,\n" +
                "  smartypants: false\n" +
                "});\n" +
                "\n" +
                "console.log(marked('I am using __markdown__.'));\n" +
                "```\n" +
                "\n" +
                "### Browser\n" +
                "\n" +
                "```html\n" +
                "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\"/>\n" +
                "  <title>Marked in the browser</title>\n" +
                "  <script src=\"lib/marked.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div id=\"content\"></div>\n" +
                "  <script>\n" +
                "    document.getElementById('content').innerHTML =\n" +
                "      marked('# Marked in browser\\n\\nRendered by **marked**.');\n" +
                "  </script>\n" +
                "</body>\n" +
                "</html>\n" +
                "```\n" +
                "\n" +
                "## marked(markdownString [,options] [,callback])\n" +
                "\n" +
                "### markdownString\n" +
                "\n" +
                "Type: `string`\n" +
                "\n" +
                "String of markdown source to be compiled.\n" +
                "\n" +
                "### options\n" +
                "\n" +
                "Type: `object`\n" +
                "\n" +
                "Hash of options. Can also be set using the `marked.setOptions` method as seen\n" +
                "above.\n" +
                "\n" +
                "### callback\n" +
                "\n" +
                "Type: `function`\n" +
                "\n" +
                "Function called when the `markdownString` has been fully parsed when using\n" +
                "async highlighting. If the `options` argument is omitted, this can be used as\n" +
                "the second argument.\n" +
                "\n" +
                "## Options\n" +
                "\n" +
                "### highlight\n" +
                "\n" +
                "Type: `function`\n" +
                "\n" +
                "A function to highlight code blocks. The first example below uses async highlighting with\n" +
                "[node-pygmentize-bundled][pygmentize], and the second is a synchronous example using\n" +
                "[highlight.js][highlight]:\n" +
                "\n" +
                "```js\n" +
                "var marked = require('marked');\n" +
                "\n" +
                "var markdownString = '```js\\n console.log(\"hello\"); \\n```';\n" +
                "\n" +
                "// Async highlighting with pygmentize-bundled\n" +
                "marked.setOptions({\n" +
                "  highlight: function (code, lang, callback) {\n" +
                "    require('pygmentize-bundled')({ lang: lang, format: 'html' }, code, function (err, result) {\n" +
                "      callback(err, result.toString());\n" +
                "    });\n" +
                "  }\n" +
                "});\n" +
                "\n" +
                "// Using async version of marked\n" +
                "marked(markdownString, function (err, content) {\n" +
                "  if (err) throw err;\n" +
                "  console.log(content);\n" +
                "});\n" +
                "\n" +
                "// Synchronous highlighting with highlight.js\n" +
                "marked.setOptions({\n" +
                "  highlight: function (code) {\n" +
                "    return require('highlight.js').highlightAuto(code).value;\n" +
                "  }\n" +
                "});\n" +
                "\n" +
                "console.log(marked(markdownString));\n" +
                "```\n" +
                "\n" +
                "#### highlight arguments\n" +
                "\n" +
                "`code`\n" +
                "\n" +
                "Type: `string`\n" +
                "\n" +
                "The section of code to pass to the highlighter.\n" +
                "\n" +
                "`lang`\n" +
                "\n" +
                "Type: `string`\n" +
                "\n" +
                "The programming language specified in the code block.\n" +
                "\n" +
                "`callback`\n" +
                "\n" +
                "Type: `function`\n" +
                "\n" +
                "The callback function to call when using an async highlighter.\n" +
                "\n" +
                "### renderer\n" +
                "\n" +
                "Type: `object`\n" +
                "Default: `new Renderer()`\n" +
                "\n" +
                "An object containing functions to render tokens to HTML.\n" +
                "\n" +
                "#### Overriding renderer methods\n" +
                "\n" +
                "The renderer option allows you to render tokens in a custom manor. Here is an\n" +
                "example of overriding the default heading token rendering by adding an embedded anchor tag like on GitHub:\n" +
                "\n" +
                "```javascript\n" +
                "var marked = require('marked');\n" +
                "var renderer = new marked.Renderer();\n" +
                "\n" +
                "renderer.heading = function (text, level) {\n" +
                "  var escapedText = text.toLowerCase().replace(/[^\\w]+/g, '-');\n" +
                "\n" +
                "  return '<h' + level + '><a name=\"' +\n" +
                "                escapedText +\n" +
                "                 '\" class=\"anchor\" href=\"#' +\n" +
                "                 escapedText +\n" +
                "                 '\"><span class=\"header-link\"></span></a>' +\n" +
                "                  text + '</h' + level + '>';\n" +
                "},\n" +
                "\n" +
                "console.log(marked('# heading+', { renderer: renderer }));\n" +
                "```\n" +
                "This code will output the following HTML:\n" +
                "```html\n" +
                "<h1>\n" +
                "  <a name=\"heading-\" class=\"anchor\" href=\"#heading-\">\n" +
                "    <span class=\"header-link\"></span>\n" +
                "  </a>\n" +
                "  heading+\n" +
                "</h1>\n" +
                "```\n" +
                "\n" +
                "#### Block level renderer methods\n" +
                "\n" +
                "- code(*string* code, *string* language)\n" +
                "- blockquote(*string* quote)\n" +
                "- html(*string* html)\n" +
                "- heading(*string* text, *number*  level)\n" +
                "- hr()\n" +
                "- list(*string* body, *boolean* ordered)\n" +
                "- listitem(*string*  text)\n" +
                "- paragraph(*string* text)\n" +
                "- table(*string* header, *string* body)\n" +
                "- tablerow(*string* content)\n" +
                "- tablecell(*string* content, *object* flags)\n" +
                "\n" +
                "`flags` has the following properties:\n" +
                "\n" +
                "```js\n" +
                "{\n" +
                "    header: true || false,\n" +
                "    align: 'center' || 'left' || 'right'\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "#### Inline level renderer methods\n" +
                "\n" +
                "- strong(*string* text)\n" +
                "- em(*string* text)\n" +
                "- codespan(*string* code)\n" +
                "- br()\n" +
                "- del(*string* text)\n" +
                "- link(*string* href, *string* title, *string* text)\n" +
                "- image(*string* href, *string* title, *string* text)\n" +
                "\n" +
                "### gfm\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `true`\n" +
                "\n" +
                "Enable [GitHub flavored markdown][gfm].\n" +
                "\n" +
                "### tables\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `true`\n" +
                "\n" +
                "Enable GFM [tables][tables].\n" +
                "This option requires the `gfm` option to be true.\n" +
                "\n" +
                "### breaks\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `false`\n" +
                "\n" +
                "Enable GFM [line breaks][breaks].\n" +
                "This option requires the `gfm` option to be true.\n" +
                "\n" +
                "### pedantic\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `false`\n" +
                "\n" +
                "Conform to obscure parts of `markdown.pl` as much as possible. Don't fix any of\n" +
                "the original markdown bugs or poor behavior.\n" +
                "\n" +
                "### sanitize\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `false`\n" +
                "\n" +
                "Sanitize the output. Ignore any HTML that has been input.\n" +
                "\n" +
                "### smartLists\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `true`\n" +
                "\n" +
                "Use smarter list behavior than the original markdown. May eventually be\n" +
                "default with the old behavior moved into `pedantic`.\n" +
                "\n" +
                "### smartypants\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `false`\n" +
                "\n" +
                "Use \"smart\" typograhic punctuation for things like quotes and dashes.\n" +
                "\n" +
                "## Access to lexer and parser\n" +
                "\n" +
                "You also have direct access to the lexer and parser if you so desire.\n" +
                "\n" +
                "``` js\n" +
                "var tokens = marked.lexer(text, options);\n" +
                "console.log(marked.parser(tokens));\n" +
                "```\n" +
                "\n" +
                "``` js\n" +
                "var lexer = new marked.Lexer(options);\n" +
                "var tokens = lexer.lex(text);\n" +
                "console.log(tokens);\n" +
                "console.log(lexer.rules);\n" +
                "```\n" +
                "\n" +
                "## CLI\n" +
                "\n" +
                "``` bash\n" +
                "$ marked -o hello.html\n" +
                "hello world\n" +
                "^D\n" +
                "$ cat hello.html\n" +
                "<p>hello world</p>\n" +
                "```\n" +
                "\n" +
                "## Philosophy behind marked\n" +
                "\n" +
                "The point of marked was to create a markdown compiler where it was possible to\n" +
                "frequently parse huge chunks of markdown without having to worry about\n" +
                "caching the compiled output somehow...or blocking for an unnecesarily long time.\n" +
                "\n" +
                "marked is very concise and still implements all markdown features. It is also\n" +
                "now fully compatible with the client-side.\n" +
                "\n" +
                "marked more or less passes the official markdown test suite in its\n" +
                "entirety. This is important because a surprising number of markdown compilers\n" +
                "cannot pass more than a few tests. It was very difficult to get marked as\n" +
                "compliant as it is. It could have cut corners in several areas for the sake\n" +
                "of performance, but did not in order to be exactly what you expect in terms\n" +
                "of a markdown rendering. In fact, this is why marked could be considered at a\n" +
                "disadvantage in the benchmarks above.\n" +
                "\n" +
                "Along with implementing every markdown feature, marked also implements [GFM\n" +
                "features][gfmf].\n" +
                "\n" +
                "## Benchmarks\n" +
                "\n" +
                "node v0.8.x\n" +
                "\n" +
                "``` bash\n" +
                "$ node test --bench\n" +
                "marked completed in 3411ms.\n" +
                "marked (gfm) completed in 3727ms.\n" +
                "marked (pedantic) completed in 3201ms.\n" +
                "robotskirt completed in 808ms.\n" +
                "showdown (reuse converter) completed in 11954ms.\n" +
                "showdown (new converter) completed in 17774ms.\n" +
                "markdown-js completed in 17191ms.\n" +
                "```\n" +
                "\n" +
                "__Marked is now faster than Discount, which is written in C.__\n" +
                "\n" +
                "For those feeling skeptical: These benchmarks run the entire markdown test suite 1000 times. The test suite tests every feature. It doesn't cater to specific aspects.\n" +
                "\n" +
                "### Pro level\n" +
                "\n" +
                "You also have direct access to the lexer and parser if you so desire.\n" +
                "\n" +
                "``` js\n" +
                "var tokens = marked.lexer(text, options);\n" +
                "console.log(marked.parser(tokens));\n" +
                "```\n" +
                "\n" +
                "``` js\n" +
                "var lexer = new marked.Lexer(options);\n" +
                "var tokens = lexer.lex(text);\n" +
                "console.log(tokens);\n" +
                "console.log(lexer.rules);\n" +
                "```\n" +
                "\n" +
                "``` bash\n" +
                "$ node\n" +
                "> require('marked').lexer('> i am using marked.')\n" +
                "[ { type: 'blockquote_start' },\n" +
                "  { type: 'paragraph',\n" +
                "    text: 'i am using marked.' },\n" +
                "  { type: 'blockquote_end' },\n" +
                "  links: {} ]\n" +
                "```\n" +
                "\n" +
                "## Running Tests & Contributing\n" +
                "\n" +
                "If you want to submit a pull request, make sure your changes pass the test\n" +
                "suite. If you're adding a new feature, be sure to add your own test.\n" +
                "\n" +
                "The marked test suite is set up slightly strangely: `test/new` is for all tests\n" +
                "that are not part of the original markdown.pl test suite (this is where your\n" +
                "test should go if you make one). `test/original` is only for the original\n" +
                "markdown.pl tests. `test/tests` houses both types of tests after they have been\n" +
                "combined and moved/generated by running `node test --fix` or `marked --test\n" +
                "--fix`.\n" +
                "\n" +
                "In other words, if you have a test to add, add it to `test/new/` and then\n" +
                "regenerate the tests with `node test --fix`. Commit the result. If your test\n" +
                "uses a certain feature, for example, maybe it assumes GFM is *not* enabled, you\n" +
                "can add `.nogfm` to the filename. So, `my-test.text` becomes\n" +
                "`my-test.nogfm.text`. You can do this with any marked option. Say you want\n" +
                "line breaks and smartypants enabled, your filename should be:\n" +
                "`my-test.breaks.smartypants.text`.\n" +
                "\n" +
                "To run the tests:\n" +
                "\n" +
                "``` bash\n" +
                "cd marked/\n" +
                "node test\n" +
                "```\n" +
                "\n" +
                "### Contribution and License Agreement\n" +
                "\n" +
                "If you contribute code to this project, you are implicitly allowing your code\n" +
                "to be distributed under the MIT license. You are also implicitly verifying that\n" +
                "all code is your original work. `</legalese>`\n" +
                "\n" +
                "## License\n" +
                "\n" +
                "Copyright (c) 2011-2014, Christopher Jeffrey. (MIT License)\n" +
                "\n" +
                "See LICENSE for more info.\n" +
                "\n" +
                "[gfm]: https://help.github.com/articles/github-flavored-markdown\n" +
                "[gfmf]: http://github.github.com/github-flavored-markdown/\n" +
                "[pygmentize]: https://github.com/rvagg/node-pygmentize-bundled\n" +
                "[highlight]: https://github.com/isagalaev/highlight.js\n" +
                "[badge]: http://badge.fury.io/js/marked\n" +
                "[tables]: https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet#wiki-tables\n" +
                "[breaks]: https://help.github.com/articles/github-flavored-markdown#newlines");
        System.out.println("Elapsed Time="+(System.currentTimeMillis()-time));

//        MarkDownLexer lexer = parser.getLexer();
//        lexer.print(lexer.getTokens());
//        System.out.println("--------------------------");
//        System.out.println(html);

    }
    @Test
    public void parboiledTest(){
        PegDownProcessor processor=new PegDownProcessor();
        long time=System.currentTimeMillis();
        String html = processor.markdownToHtml("# marked\n" +
                "\n" +
                "> A full-featured markdown parser and compiler, written in JavaScript. Built\n" +
                "> for speed.\n" +
                "\n" +
                "[![NPM version](https://badge.fury.io/js/marked.png)][badge]\n" +
                "\n" +
                "## Install\n" +
                "\n" +
                "``` bash\n" +
                "npm install marked --save\n" +
                "```\n" +
                "\n" +
                "## Usage\n" +
                "\n" +
                "Minimal usage:\n" +
                "\n" +
                "```js\n" +
                "var marked = require('marked');\n" +
                "console.log(marked('I am using __markdown__.'));\n" +
                "// Outputs: <p>I am using <strong>markdown</strong>.</p>\n" +
                "```\n" +
                "\n" +
                "Example setting options with default values:\n" +
                "\n" +
                "```js\n" +
                "var marked = require('marked');\n" +
                "marked.setOptions({\n" +
                "  renderer: new marked.Renderer(),\n" +
                "  gfm: true,\n" +
                "  tables: true,\n" +
                "  breaks: false,\n" +
                "  pedantic: false,\n" +
                "  sanitize: true,\n" +
                "  smartLists: true,\n" +
                "  smartypants: false\n" +
                "});\n" +
                "\n" +
                "console.log(marked('I am using __markdown__.'));\n" +
                "```\n" +
                "\n" +
                "### Browser\n" +
                "\n" +
                "```html\n" +
                "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\"/>\n" +
                "  <title>Marked in the browser</title>\n" +
                "  <script src=\"lib/marked.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div id=\"content\"></div>\n" +
                "  <script>\n" +
                "    document.getElementById('content').innerHTML =\n" +
                "      marked('# Marked in browser\\n\\nRendered by **marked**.');\n" +
                "  </script>\n" +
                "</body>\n" +
                "</html>\n" +
                "```\n" +
                "\n" +
                "## marked(markdownString [,options] [,callback])\n" +
                "\n" +
                "### markdownString\n" +
                "\n" +
                "Type: `string`\n" +
                "\n" +
                "String of markdown source to be compiled.\n" +
                "\n" +
                "### options\n" +
                "\n" +
                "Type: `object`\n" +
                "\n" +
                "Hash of options. Can also be set using the `marked.setOptions` method as seen\n" +
                "above.\n" +
                "\n" +
                "### callback\n" +
                "\n" +
                "Type: `function`\n" +
                "\n" +
                "Function called when the `markdownString` has been fully parsed when using\n" +
                "async highlighting. If the `options` argument is omitted, this can be used as\n" +
                "the second argument.\n" +
                "\n" +
                "## Options\n" +
                "\n" +
                "### highlight\n" +
                "\n" +
                "Type: `function`\n" +
                "\n" +
                "A function to highlight code blocks. The first example below uses async highlighting with\n" +
                "[node-pygmentize-bundled][pygmentize], and the second is a synchronous example using\n" +
                "[highlight.js][highlight]:\n" +
                "\n" +
                "```js\n" +
                "var marked = require('marked');\n" +
                "\n" +
                "var markdownString = '```js\\n console.log(\"hello\"); \\n```';\n" +
                "\n" +
                "// Async highlighting with pygmentize-bundled\n" +
                "marked.setOptions({\n" +
                "  highlight: function (code, lang, callback) {\n" +
                "    require('pygmentize-bundled')({ lang: lang, format: 'html' }, code, function (err, result) {\n" +
                "      callback(err, result.toString());\n" +
                "    });\n" +
                "  }\n" +
                "});\n" +
                "\n" +
                "// Using async version of marked\n" +
                "marked(markdownString, function (err, content) {\n" +
                "  if (err) throw err;\n" +
                "  console.log(content);\n" +
                "});\n" +
                "\n" +
                "// Synchronous highlighting with highlight.js\n" +
                "marked.setOptions({\n" +
                "  highlight: function (code) {\n" +
                "    return require('highlight.js').highlightAuto(code).value;\n" +
                "  }\n" +
                "});\n" +
                "\n" +
                "console.log(marked(markdownString));\n" +
                "```\n" +
                "\n" +
                "#### highlight arguments\n" +
                "\n" +
                "`code`\n" +
                "\n" +
                "Type: `string`\n" +
                "\n" +
                "The section of code to pass to the highlighter.\n" +
                "\n" +
                "`lang`\n" +
                "\n" +
                "Type: `string`\n" +
                "\n" +
                "The programming language specified in the code block.\n" +
                "\n" +
                "`callback`\n" +
                "\n" +
                "Type: `function`\n" +
                "\n" +
                "The callback function to call when using an async highlighter.\n" +
                "\n" +
                "### renderer\n" +
                "\n" +
                "Type: `object`\n" +
                "Default: `new Renderer()`\n" +
                "\n" +
                "An object containing functions to render tokens to HTML.\n" +
                "\n" +
                "#### Overriding renderer methods\n" +
                "\n" +
                "The renderer option allows you to render tokens in a custom manor. Here is an\n" +
                "example of overriding the default heading token rendering by adding an embedded anchor tag like on GitHub:\n" +
                "\n" +
                "```javascript\n" +
                "var marked = require('marked');\n" +
                "var renderer = new marked.Renderer();\n" +
                "\n" +
                "renderer.heading = function (text, level) {\n" +
                "  var escapedText = text.toLowerCase().replace(/[^\\w]+/g, '-');\n" +
                "\n" +
                "  return '<h' + level + '><a name=\"' +\n" +
                "                escapedText +\n" +
                "                 '\" class=\"anchor\" href=\"#' +\n" +
                "                 escapedText +\n" +
                "                 '\"><span class=\"header-link\"></span></a>' +\n" +
                "                  text + '</h' + level + '>';\n" +
                "},\n" +
                "\n" +
                "console.log(marked('# heading+', { renderer: renderer }));\n" +
                "```\n" +
                "This code will output the following HTML:\n" +
                "```html\n" +
                "<h1>\n" +
                "  <a name=\"heading-\" class=\"anchor\" href=\"#heading-\">\n" +
                "    <span class=\"header-link\"></span>\n" +
                "  </a>\n" +
                "  heading+\n" +
                "</h1>\n" +
                "```\n" +
                "\n" +
                "#### Block level renderer methods\n" +
                "\n" +
                "- code(*string* code, *string* language)\n" +
                "- blockquote(*string* quote)\n" +
                "- html(*string* html)\n" +
                "- heading(*string* text, *number*  level)\n" +
                "- hr()\n" +
                "- list(*string* body, *boolean* ordered)\n" +
                "- listitem(*string*  text)\n" +
                "- paragraph(*string* text)\n" +
                "- table(*string* header, *string* body)\n" +
                "- tablerow(*string* content)\n" +
                "- tablecell(*string* content, *object* flags)\n" +
                "\n" +
                "`flags` has the following properties:\n" +
                "\n" +
                "```js\n" +
                "{\n" +
                "    header: true || false,\n" +
                "    align: 'center' || 'left' || 'right'\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "#### Inline level renderer methods\n" +
                "\n" +
                "- strong(*string* text)\n" +
                "- em(*string* text)\n" +
                "- codespan(*string* code)\n" +
                "- br()\n" +
                "- del(*string* text)\n" +
                "- link(*string* href, *string* title, *string* text)\n" +
                "- image(*string* href, *string* title, *string* text)\n" +
                "\n" +
                "### gfm\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `true`\n" +
                "\n" +
                "Enable [GitHub flavored markdown][gfm].\n" +
                "\n" +
                "### tables\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `true`\n" +
                "\n" +
                "Enable GFM [tables][tables].\n" +
                "This option requires the `gfm` option to be true.\n" +
                "\n" +
                "### breaks\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `false`\n" +
                "\n" +
                "Enable GFM [line breaks][breaks].\n" +
                "This option requires the `gfm` option to be true.\n" +
                "\n" +
                "### pedantic\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `false`\n" +
                "\n" +
                "Conform to obscure parts of `markdown.pl` as much as possible. Don't fix any of\n" +
                "the original markdown bugs or poor behavior.\n" +
                "\n" +
                "### sanitize\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `false`\n" +
                "\n" +
                "Sanitize the output. Ignore any HTML that has been input.\n" +
                "\n" +
                "### smartLists\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `true`\n" +
                "\n" +
                "Use smarter list behavior than the original markdown. May eventually be\n" +
                "default with the old behavior moved into `pedantic`.\n" +
                "\n" +
                "### smartypants\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `false`\n" +
                "\n" +
                "Use \"smart\" typograhic punctuation for things like quotes and dashes.\n" +
                "\n" +
                "## Access to lexer and parser\n" +
                "\n" +
                "You also have direct access to the lexer and parser if you so desire.\n" +
                "\n" +
                "``` js\n" +
                "var tokens = marked.lexer(text, options);\n" +
                "console.log(marked.parser(tokens));\n" +
                "```\n" +
                "\n" +
                "``` js\n" +
                "var lexer = new marked.Lexer(options);\n" +
                "var tokens = lexer.lex(text);\n" +
                "console.log(tokens);\n" +
                "console.log(lexer.rules);\n" +
                "```\n" +
                "\n" +
                "## CLI\n" +
                "\n" +
                "``` bash\n" +
                "$ marked -o hello.html\n" +
                "hello world\n" +
                "^D\n" +
                "$ cat hello.html\n" +
                "<p>hello world</p>\n" +
                "```\n" +
                "\n" +
                "## Philosophy behind marked\n" +
                "\n" +
                "The point of marked was to create a markdown compiler where it was possible to\n" +
                "frequently parse huge chunks of markdown without having to worry about\n" +
                "caching the compiled output somehow...or blocking for an unnecesarily long time.\n" +
                "\n" +
                "marked is very concise and still implements all markdown features. It is also\n" +
                "now fully compatible with the client-side.\n" +
                "\n" +
                "marked more or less passes the official markdown test suite in its\n" +
                "entirety. This is important because a surprising number of markdown compilers\n" +
                "cannot pass more than a few tests. It was very difficult to get marked as\n" +
                "compliant as it is. It could have cut corners in several areas for the sake\n" +
                "of performance, but did not in order to be exactly what you expect in terms\n" +
                "of a markdown rendering. In fact, this is why marked could be considered at a\n" +
                "disadvantage in the benchmarks above.\n" +
                "\n" +
                "Along with implementing every markdown feature, marked also implements [GFM\n" +
                "features][gfmf].\n" +
                "\n" +
                "## Benchmarks\n" +
                "\n" +
                "node v0.8.x\n" +
                "\n" +
                "``` bash\n" +
                "$ node test --bench\n" +
                "marked completed in 3411ms.\n" +
                "marked (gfm) completed in 3727ms.\n" +
                "marked (pedantic) completed in 3201ms.\n" +
                "robotskirt completed in 808ms.\n" +
                "showdown (reuse converter) completed in 11954ms.\n" +
                "showdown (new converter) completed in 17774ms.\n" +
                "markdown-js completed in 17191ms.\n" +
                "```\n" +
                "\n" +
                "__Marked is now faster than Discount, which is written in C.__\n" +
                "\n" +
                "For those feeling skeptical: These benchmarks run the entire markdown test suite 1000 times. The test suite tests every feature. It doesn't cater to specific aspects.\n" +
                "\n" +
                "### Pro level\n" +
                "\n" +
                "You also have direct access to the lexer and parser if you so desire.\n" +
                "\n" +
                "``` js\n" +
                "var tokens = marked.lexer(text, options);\n" +
                "console.log(marked.parser(tokens));\n" +
                "```\n" +
                "\n" +
                "``` js\n" +
                "var lexer = new marked.Lexer(options);\n" +
                "var tokens = lexer.lex(text);\n" +
                "console.log(tokens);\n" +
                "console.log(lexer.rules);\n" +
                "```\n" +
                "\n" +
                "``` bash\n" +
                "$ node\n" +
                "> require('marked').lexer('> i am using marked.')\n" +
                "[ { type: 'blockquote_start' },\n" +
                "  { type: 'paragraph',\n" +
                "    text: 'i am using marked.' },\n" +
                "  { type: 'blockquote_end' },\n" +
                "  links: {} ]\n" +
                "```\n" +
                "\n" +
                "## Running Tests & Contributing\n" +
                "\n" +
                "If you want to submit a pull request, make sure your changes pass the test\n" +
                "suite. If you're adding a new feature, be sure to add your own test.\n" +
                "\n" +
                "The marked test suite is set up slightly strangely: `test/new` is for all tests\n" +
                "that are not part of the original markdown.pl test suite (this is where your\n" +
                "test should go if you make one). `test/original` is only for the original\n" +
                "markdown.pl tests. `test/tests` houses both types of tests after they have been\n" +
                "combined and moved/generated by running `node test --fix` or `marked --test\n" +
                "--fix`.\n" +
                "\n" +
                "In other words, if you have a test to add, add it to `test/new/` and then\n" +
                "regenerate the tests with `node test --fix`. Commit the result. If your test\n" +
                "uses a certain feature, for example, maybe it assumes GFM is *not* enabled, you\n" +
                "can add `.nogfm` to the filename. So, `my-test.text` becomes\n" +
                "`my-test.nogfm.text`. You can do this with any marked option. Say you want\n" +
                "line breaks and smartypants enabled, your filename should be:\n" +
                "`my-test.breaks.smartypants.text`.\n" +
                "\n" +
                "To run the tests:\n" +
                "\n" +
                "``` bash\n" +
                "cd marked/\n" +
                "node test\n" +
                "```\n" +
                "\n" +
                "### Contribution and License Agreement\n" +
                "\n" +
                "If you contribute code to this project, you are implicitly allowing your code\n" +
                "to be distributed under the MIT license. You are also implicitly verifying that\n" +
                "all code is your original work. `</legalese>`\n" +
                "\n" +
                "## License\n" +
                "\n" +
                "Copyright (c) 2011-2014, Christopher Jeffrey. (MIT License)\n" +
                "\n" +
                "See LICENSE for more info.\n" +
                "\n" +
                "[gfm]: https://help.github.com/articles/github-flavored-markdown\n" +
                "[gfmf]: http://github.github.com/github-flavored-markdown/\n" +
                "[pygmentize]: https://github.com/rvagg/node-pygmentize-bundled\n" +
                "[highlight]: https://github.com/isagalaev/highlight.js\n" +
                "[badge]: http://badge.fury.io/js/marked\n" +
                "[tables]: https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet#wiki-tables\n" +
                "[breaks]: https://help.github.com/articles/github-flavored-markdown#newlines");
        System.out.println("Elapsed Time="+(System.currentTimeMillis()-time));
    }
    @Test
    public void txtmarkTest(){

        long time=System.currentTimeMillis();

        String html = Processor.process("# marked\n" +
                "\n" +
                "> A full-featured markdown parser and compiler, written in JavaScript. Built\n" +
                "> for speed.\n" +
                "\n" +
                "[![NPM version](https://badge.fury.io/js/marked.png)][badge]\n" +
                "\n" +
                "## Install\n" +
                "\n" +
                "``` bash\n" +
                "npm install marked --save\n" +
                "```\n" +
                "\n" +
                "## Usage\n" +
                "\n" +
                "Minimal usage:\n" +
                "\n" +
                "```js\n" +
                "var marked = require('marked');\n" +
                "console.log(marked('I am using __markdown__.'));\n" +
                "// Outputs: <p>I am using <strong>markdown</strong>.</p>\n" +
                "```\n" +
                "\n" +
                "Example setting options with default values:\n" +
                "\n" +
                "```js\n" +
                "var marked = require('marked');\n" +
                "marked.setOptions({\n" +
                "  renderer: new marked.Renderer(),\n" +
                "  gfm: true,\n" +
                "  tables: true,\n" +
                "  breaks: false,\n" +
                "  pedantic: false,\n" +
                "  sanitize: true,\n" +
                "  smartLists: true,\n" +
                "  smartypants: false\n" +
                "});\n" +
                "\n" +
                "console.log(marked('I am using __markdown__.'));\n" +
                "```\n" +
                "\n" +
                "### Browser\n" +
                "\n" +
                "```html\n" +
                "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\"/>\n" +
                "  <title>Marked in the browser</title>\n" +
                "  <script src=\"lib/marked.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div id=\"content\"></div>\n" +
                "  <script>\n" +
                "    document.getElementById('content').innerHTML =\n" +
                "      marked('# Marked in browser\\n\\nRendered by **marked**.');\n" +
                "  </script>\n" +
                "</body>\n" +
                "</html>\n" +
                "```\n" +
                "\n" +
                "## marked(markdownString [,options] [,callback])\n" +
                "\n" +
                "### markdownString\n" +
                "\n" +
                "Type: `string`\n" +
                "\n" +
                "String of markdown source to be compiled.\n" +
                "\n" +
                "### options\n" +
                "\n" +
                "Type: `object`\n" +
                "\n" +
                "Hash of options. Can also be set using the `marked.setOptions` method as seen\n" +
                "above.\n" +
                "\n" +
                "### callback\n" +
                "\n" +
                "Type: `function`\n" +
                "\n" +
                "Function called when the `markdownString` has been fully parsed when using\n" +
                "async highlighting. If the `options` argument is omitted, this can be used as\n" +
                "the second argument.\n" +
                "\n" +
                "## Options\n" +
                "\n" +
                "### highlight\n" +
                "\n" +
                "Type: `function`\n" +
                "\n" +
                "A function to highlight code blocks. The first example below uses async highlighting with\n" +
                "[node-pygmentize-bundled][pygmentize], and the second is a synchronous example using\n" +
                "[highlight.js][highlight]:\n" +
                "\n" +
                "```js\n" +
                "var marked = require('marked');\n" +
                "\n" +
                "var markdownString = '```js\\n console.log(\"hello\"); \\n```';\n" +
                "\n" +
                "// Async highlighting with pygmentize-bundled\n" +
                "marked.setOptions({\n" +
                "  highlight: function (code, lang, callback) {\n" +
                "    require('pygmentize-bundled')({ lang: lang, format: 'html' }, code, function (err, result) {\n" +
                "      callback(err, result.toString());\n" +
                "    });\n" +
                "  }\n" +
                "});\n" +
                "\n" +
                "// Using async version of marked\n" +
                "marked(markdownString, function (err, content) {\n" +
                "  if (err) throw err;\n" +
                "  console.log(content);\n" +
                "});\n" +
                "\n" +
                "// Synchronous highlighting with highlight.js\n" +
                "marked.setOptions({\n" +
                "  highlight: function (code) {\n" +
                "    return require('highlight.js').highlightAuto(code).value;\n" +
                "  }\n" +
                "});\n" +
                "\n" +
                "console.log(marked(markdownString));\n" +
                "```\n" +
                "\n" +
                "#### highlight arguments\n" +
                "\n" +
                "`code`\n" +
                "\n" +
                "Type: `string`\n" +
                "\n" +
                "The section of code to pass to the highlighter.\n" +
                "\n" +
                "`lang`\n" +
                "\n" +
                "Type: `string`\n" +
                "\n" +
                "The programming language specified in the code block.\n" +
                "\n" +
                "`callback`\n" +
                "\n" +
                "Type: `function`\n" +
                "\n" +
                "The callback function to call when using an async highlighter.\n" +
                "\n" +
                "### renderer\n" +
                "\n" +
                "Type: `object`\n" +
                "Default: `new Renderer()`\n" +
                "\n" +
                "An object containing functions to render tokens to HTML.\n" +
                "\n" +
                "#### Overriding renderer methods\n" +
                "\n" +
                "The renderer option allows you to render tokens in a custom manor. Here is an\n" +
                "example of overriding the default heading token rendering by adding an embedded anchor tag like on GitHub:\n" +
                "\n" +
                "```javascript\n" +
                "var marked = require('marked');\n" +
                "var renderer = new marked.Renderer();\n" +
                "\n" +
                "renderer.heading = function (text, level) {\n" +
                "  var escapedText = text.toLowerCase().replace(/[^\\w]+/g, '-');\n" +
                "\n" +
                "  return '<h' + level + '><a name=\"' +\n" +
                "                escapedText +\n" +
                "                 '\" class=\"anchor\" href=\"#' +\n" +
                "                 escapedText +\n" +
                "                 '\"><span class=\"header-link\"></span></a>' +\n" +
                "                  text + '</h' + level + '>';\n" +
                "},\n" +
                "\n" +
                "console.log(marked('# heading+', { renderer: renderer }));\n" +
                "```\n" +
                "This code will output the following HTML:\n" +
                "```html\n" +
                "<h1>\n" +
                "  <a name=\"heading-\" class=\"anchor\" href=\"#heading-\">\n" +
                "    <span class=\"header-link\"></span>\n" +
                "  </a>\n" +
                "  heading+\n" +
                "</h1>\n" +
                "```\n" +
                "\n" +
                "#### Block level renderer methods\n" +
                "\n" +
                "- code(*string* code, *string* language)\n" +
                "- blockquote(*string* quote)\n" +
                "- html(*string* html)\n" +
                "- heading(*string* text, *number*  level)\n" +
                "- hr()\n" +
                "- list(*string* body, *boolean* ordered)\n" +
                "- listitem(*string*  text)\n" +
                "- paragraph(*string* text)\n" +
                "- table(*string* header, *string* body)\n" +
                "- tablerow(*string* content)\n" +
                "- tablecell(*string* content, *object* flags)\n" +
                "\n" +
                "`flags` has the following properties:\n" +
                "\n" +
                "```js\n" +
                "{\n" +
                "    header: true || false,\n" +
                "    align: 'center' || 'left' || 'right'\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "#### Inline level renderer methods\n" +
                "\n" +
                "- strong(*string* text)\n" +
                "- em(*string* text)\n" +
                "- codespan(*string* code)\n" +
                "- br()\n" +
                "- del(*string* text)\n" +
                "- link(*string* href, *string* title, *string* text)\n" +
                "- image(*string* href, *string* title, *string* text)\n" +
                "\n" +
                "### gfm\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `true`\n" +
                "\n" +
                "Enable [GitHub flavored markdown][gfm].\n" +
                "\n" +
                "### tables\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `true`\n" +
                "\n" +
                "Enable GFM [tables][tables].\n" +
                "This option requires the `gfm` option to be true.\n" +
                "\n" +
                "### breaks\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `false`\n" +
                "\n" +
                "Enable GFM [line breaks][breaks].\n" +
                "This option requires the `gfm` option to be true.\n" +
                "\n" +
                "### pedantic\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `false`\n" +
                "\n" +
                "Conform to obscure parts of `markdown.pl` as much as possible. Don't fix any of\n" +
                "the original markdown bugs or poor behavior.\n" +
                "\n" +
                "### sanitize\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `false`\n" +
                "\n" +
                "Sanitize the output. Ignore any HTML that has been input.\n" +
                "\n" +
                "### smartLists\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `true`\n" +
                "\n" +
                "Use smarter list behavior than the original markdown. May eventually be\n" +
                "default with the old behavior moved into `pedantic`.\n" +
                "\n" +
                "### smartypants\n" +
                "\n" +
                "Type: `boolean`\n" +
                "Default: `false`\n" +
                "\n" +
                "Use \"smart\" typograhic punctuation for things like quotes and dashes.\n" +
                "\n" +
                "## Access to lexer and parser\n" +
                "\n" +
                "You also have direct access to the lexer and parser if you so desire.\n" +
                "\n" +
                "``` js\n" +
                "var tokens = marked.lexer(text, options);\n" +
                "console.log(marked.parser(tokens));\n" +
                "```\n" +
                "\n" +
                "``` js\n" +
                "var lexer = new marked.Lexer(options);\n" +
                "var tokens = lexer.lex(text);\n" +
                "console.log(tokens);\n" +
                "console.log(lexer.rules);\n" +
                "```\n" +
                "\n" +
                "## CLI\n" +
                "\n" +
                "``` bash\n" +
                "$ marked -o hello.html\n" +
                "hello world\n" +
                "^D\n" +
                "$ cat hello.html\n" +
                "<p>hello world</p>\n" +
                "```\n" +
                "\n" +
                "## Philosophy behind marked\n" +
                "\n" +
                "The point of marked was to create a markdown compiler where it was possible to\n" +
                "frequently parse huge chunks of markdown without having to worry about\n" +
                "caching the compiled output somehow...or blocking for an unnecesarily long time.\n" +
                "\n" +
                "marked is very concise and still implements all markdown features. It is also\n" +
                "now fully compatible with the client-side.\n" +
                "\n" +
                "marked more or less passes the official markdown test suite in its\n" +
                "entirety. This is important because a surprising number of markdown compilers\n" +
                "cannot pass more than a few tests. It was very difficult to get marked as\n" +
                "compliant as it is. It could have cut corners in several areas for the sake\n" +
                "of performance, but did not in order to be exactly what you expect in terms\n" +
                "of a markdown rendering. In fact, this is why marked could be considered at a\n" +
                "disadvantage in the benchmarks above.\n" +
                "\n" +
                "Along with implementing every markdown feature, marked also implements [GFM\n" +
                "features][gfmf].\n" +
                "\n" +
                "## Benchmarks\n" +
                "\n" +
                "node v0.8.x\n" +
                "\n" +
                "``` bash\n" +
                "$ node test --bench\n" +
                "marked completed in 3411ms.\n" +
                "marked (gfm) completed in 3727ms.\n" +
                "marked (pedantic) completed in 3201ms.\n" +
                "robotskirt completed in 808ms.\n" +
                "showdown (reuse converter) completed in 11954ms.\n" +
                "showdown (new converter) completed in 17774ms.\n" +
                "markdown-js completed in 17191ms.\n" +
                "```\n" +
                "\n" +
                "__Marked is now faster than Discount, which is written in C.__\n" +
                "\n" +
                "For those feeling skeptical: These benchmarks run the entire markdown test suite 1000 times. The test suite tests every feature. It doesn't cater to specific aspects.\n" +
                "\n" +
                "### Pro level\n" +
                "\n" +
                "You also have direct access to the lexer and parser if you so desire.\n" +
                "\n" +
                "``` js\n" +
                "var tokens = marked.lexer(text, options);\n" +
                "console.log(marked.parser(tokens));\n" +
                "```\n" +
                "\n" +
                "``` js\n" +
                "var lexer = new marked.Lexer(options);\n" +
                "var tokens = lexer.lex(text);\n" +
                "console.log(tokens);\n" +
                "console.log(lexer.rules);\n" +
                "```\n" +
                "\n" +
                "``` bash\n" +
                "$ node\n" +
                "> require('marked').lexer('> i am using marked.')\n" +
                "[ { type: 'blockquote_start' },\n" +
                "  { type: 'paragraph',\n" +
                "    text: 'i am using marked.' },\n" +
                "  { type: 'blockquote_end' },\n" +
                "  links: {} ]\n" +
                "```\n" +
                "\n" +
                "## Running Tests & Contributing\n" +
                "\n" +
                "If you want to submit a pull request, make sure your changes pass the test\n" +
                "suite. If you're adding a new feature, be sure to add your own test.\n" +
                "\n" +
                "The marked test suite is set up slightly strangely: `test/new` is for all tests\n" +
                "that are not part of the original markdown.pl test suite (this is where your\n" +
                "test should go if you make one). `test/original` is only for the original\n" +
                "markdown.pl tests. `test/tests` houses both types of tests after they have been\n" +
                "combined and moved/generated by running `node test --fix` or `marked --test\n" +
                "--fix`.\n" +
                "\n" +
                "In other words, if you have a test to add, add it to `test/new/` and then\n" +
                "regenerate the tests with `node test --fix`. Commit the result. If your test\n" +
                "uses a certain feature, for example, maybe it assumes GFM is *not* enabled, you\n" +
                "can add `.nogfm` to the filename. So, `my-test.text` becomes\n" +
                "`my-test.nogfm.text`. You can do this with any marked option. Say you want\n" +
                "line breaks and smartypants enabled, your filename should be:\n" +
                "`my-test.breaks.smartypants.text`.\n" +
                "\n" +
                "To run the tests:\n" +
                "\n" +
                "``` bash\n" +
                "cd marked/\n" +
                "node test\n" +
                "```\n" +
                "\n" +
                "### Contribution and License Agreement\n" +
                "\n" +
                "If you contribute code to this project, you are implicitly allowing your code\n" +
                "to be distributed under the MIT license. You are also implicitly verifying that\n" +
                "all code is your original work. `</legalese>`\n" +
                "\n" +
                "## License\n" +
                "\n" +
                "Copyright (c) 2011-2014, Christopher Jeffrey. (MIT License)\n" +
                "\n" +
                "See LICENSE for more info.\n" +
                "\n" +
                "[gfm]: https://help.github.com/articles/github-flavored-markdown\n" +
                "[gfmf]: http://github.github.com/github-flavored-markdown/\n" +
                "[pygmentize]: https://github.com/rvagg/node-pygmentize-bundled\n" +
                "[highlight]: https://github.com/isagalaev/highlight.js\n" +
                "[badge]: http://badge.fury.io/js/marked\n" +
                "[tables]: https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet#wiki-tables\n" +
                "[breaks]: https://help.github.com/articles/github-flavored-markdown#newlines");
        System.out.println("Elapsed Time="+(System.currentTimeMillis()-time));
        System.out.println(html);
    }

    @Test
    public void npTableParsingTest(){
        MarkedParser parser=new MarkedParser();
        String html=parser.parse("Markdown | Less | Pretty\n" +
                "--- | --- | ---\n" +
                "*Still* | `renders` | **nicely**\n" +
                "1 | 2 | 3\nprint");
        assertEquals("<table>\n" +
                "<thead>\n" +
                "<tr>\n" +
                "<th>Markdown</th>\n" +
                "<th>Less</th>\n" +
                "<th>Pretty</th>\n" +
                "</tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>*Still*</td>\n" +
                "<td>`renders`</td>\n" +
                "<td>**nicely**</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>1</td>\n" +
                "<td>2</td>\n" +
                "<td>3</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<p>print</p>\n",html);
    }

    @Test
    public void tableParsingTest() {
        MarkedParser parser = new MarkedParser();
        String html = parser.parse("| name  | age | gender    | money  |\n" +
                "|-------|:---:|-----------|-------:|\n" +
                "| rhio  | 384 | robot     | $3,000 |\n" +
                "| haroo | .3  | bird      | $430   |\n" +
                "| jedi  | ?   | undefined | $0     |");
        System.out.println(html);
    }


}