// 08 A period doesn't match newlines (Part 2)

// jflex Example.jflex 
// javac Yylex.java
// java Yylex test.mini

/* user code */

%%

/* operations and declarations */

%standalone

%%

/* lexical rules */

"//"(.|\n)*		{ /* ignore one line comments */ }
