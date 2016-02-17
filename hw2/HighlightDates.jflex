//Jonathan Jensen
//HighlightDates 


%%

// We use the %standalone declaration, which tells jflex that we want to
// build a self-contained program.  This will result in the definition of a
// main function that reads a file name on the command line and then passes
// all of the text from that file through the generated scanner/lexer.  We
// will produce the corresponding syntax colored output simply by printing
// it on to the standard output device; this makes it easy to inspect the
// output while debugging, but we can also use a redirect operator to
// save the contents in an HTML file instead that is suitable for viewing
// in a browser.

%standalone

// Next, we use the %class declaration, selecting HighlightDates as the name
// for the Java class that will be generated:

// %class HighlightDates

// Assuming that the JFlex source shown here is stored in HighlightDates.jflex,
// we will now be able to generate and compile our program using the
// following commands:
// 
//   jflex HighlightDates.jflex 
//   javac HighlightDates.java
//
// The resulting program can then be run as follows:
//   
//   java HighlightDates lexer-sample.txt  > lexer-sample.html

// Every HTML file that we generate should begin with set of lines to set
// basic details such as the title and the style options.  The %init
// feature of jflex provides An easy way to handle this, allowing us to
// specify some code that should be executed as part of the constructor for
// the HighlightDates constructor; in this case, we just need to print the
// initial set of lines for the start of the HTML output.  (This would also
// be a good place to initialize an output file if we wanted to write the
// output from the program directly to a file instead of just printing it
// on the standard output.)

%init{
  System.out.println("<html>");
  System.out.println("<head>");
  System.out.println("<title>Highlight Dates Web Page</title>");
  System.out.println("<style type=\"text/css\">");
  System.out.println("  body     {white-space:pre;");
  System.out.println("            background-color:#ffffcc;");
  System.out.println("            color:black;");
  System.out.println("            font-family:\"Lucida Console\",\"Courier New\",Monotype}");
  //for Dates
  System.out.println("  .date {background-color:white; color:green}");
  System.out.println("</style>");
  System.out.println("</head>");
  System.out.println("<body>");
%init}

// There is a similar %eof feature that allows us to specify code that
// will be executed when the program reaches the end of the input file.
// This provides a convenient place for us to put the code that produces
// the HTML lines that are required at the end of every output file:

%eof{
  System.out.println("</body>");
  System.out.println("</html>");
%eof}

// In general, our syntax coloring program will work by matching
// patterns in the input file and then outputing the original lexeme,
// sometimes wrapped between HTML tags to specify how the lexeme
// should be colored.  One small technical challenge in this is that
// we need to make sure that the three characters <, >, and &, which
// have special uses in HTML, are replaced by the appropriate escape
// sequences &lt;, &gt:, and &amp;, respectively in the output.
// In principle, it would be possible to handle this completely within
// the main set of jflex matching rules.  However, in this particular
// case, it seems easier (at least to this author) just to handle
// this with a little bit of Java code as shown in the following
// echo() function.  This function works by reading characters one
// at a time from the internal buffer that JFlex uses, checking for
// the three special cases, and performing the necessary output
// action each time.

%{
  void echo() {
    int len = yylength();       // Find length of current lexeme
    for (int i=0; i<len; i++) { // Run through each character in turn
      char c = yycharat(i);   
      switch (c) {              // and translate as appropriate ...
        case '<' : System.out.print("&lt;");  break;
        case '>' : System.out.print("&gt;");  break;
        case '&' : System.out.print("&amp;"); break;
        default  : System.out.print(c);       break;
      }
    }
  }
%}

// As a small technical aside, note that we have used a combination
// of the yylength() and yycharat() functions to access the text of
// the current lexeme.  We could achieve a similar effect using the
// yytext() function, but the approach used here is faster (at least
// in principle) because it avoids the overheads of constructing a
// new string object for each input element.

// The echo() method described above is good for printing the text
// of a lexeme directly, without any special syntax coloring.  For
// lexemes where we want to add some color, however, we can use the
// following tag() method, which adds an appropriate span tag before
// and after the lexeme text.

%{
  void tag(String cl) {
    System.out.print("<span class=\"" + cl + "\">");
    echo();
    System.out.print("</span>");
  }
%}

// The parameter cl is used to specify a particular token class/style;
// given the opening lines of HTML shown above, this should be a valid date.

%{
  void date()    { tag("date"); }
%}

//  We will use the following definitions to specify the syntax of dates:


// these are the LOW LEVEL building blocks of a date

//accepted month formats
January            = "January"   | "january"   | "Jan" | "jan"
JanNum             = "1" | "01"
February           = "February"  | "february"  | "Feb" | "feb"
FebNum             = "2" | "02"
March              = "March"     | "march"     | "Mar" | "mar"
MarNum             = "3" | "03"
April              = "April"     | "april"     | "Apr" | "apr"
AprNum             = "4" | "04"
May                = "May"       | "may"                      
MayNum             = "5" | "05"
June               = "June"      | "june"      | "Jun" | "jun"
JunNum             = "6" | "06"
July               = "July"      | "july"      | "Jul" | "jul"
JulNum             = "7" | "07"
August             = "August"    | "august"    | "Aug" | "aug"
AugNum             = "8" | "08"
September          = "September" | "september" | "Sep" | "sep"
SepNum             = "9" | "09"
October            = "October"   | "october"   | "Oct" | "oct"
OctNum             = "10"
November           = "November"  | "november"  | "Nov" | "nov"
NovNum             = "11"
December           = "December"  | "december"  | "Dec" | "dec"
DecNum             = "12"

//accepted days
Day30              = [1-9] | [0-2][1-9] | [1-3][0] 
Day31              = [1-9] | [0-2][1-9] | [1-3][0] | [3][1]
Day29              = [1-9] | [0-2][1-9]

//accepted year formats between 1500 and 2500
Year               = {YearFull} | {YearShort}
YearFull           = [1][5-9][0-9][0-9] | [2][0-4][0-9][0-9] | [2][5][0][0]
YearShort          = [0-9][0-9]

//HIGHER LEVEL BUILDING BLOCKS

//the previously defined "months", organized by number of days in the month
Month31            = {January} | {March} | {May} | {July} | {August} | {October} | {December}
Month30            = {April} | {June} | {September} | {November}
Month29            = {February}

MonthNum31         = {JanNum} | {MarNum} | {MayNum} | {JulNum} | {AugNum} | {OctNum} | {DecNum}
MonthNum30         = {AprNum} | {JunNum} | {SepNum} | {NovNum}
MonthNum29         = {FebNum}

//Macros for some whitespace and/or separators that will be used
//in between components of a valid date.
LineTerminator     = \r|\n|\r\n
SpaceSep           = {LineTerminator} | [ ]
CommaSep           = {LineTerminator} | ","
ComSpaceSep        = {SpaceSep} | {CommaSep}
ComSpaceSep2       = {ComSpaceSep} | {ComSpaceSep} {ComSpaceSep}

//EVEN HIGHER LEVEL
//accepted date formats

//ie. "Month Day, Year"
DateFull1          = {Month31} {SpaceSep} {Day31} {ComSpaceSep2} {YearFull}
DateFull2          = {Month30} {SpaceSep} {Day30} {ComSpaceSep2} {YearFull}
DateFull3          = {Month29} {SpaceSep} {Day29} {ComSpaceSep2} {YearFull}

//ie. "Day Month, Year"
DateFull4          = {Day31} {SpaceSep} {Month31} {ComSpaceSep2} {YearFull}
DateFull5          = {Day30} {SpaceSep} {Month30} {ComSpaceSep2} {YearFull}
DateFull6          = {Day29} {SpaceSep} {Month29} {ComSpaceSep2} {YearFull}

//ie. "XX/XX/XX" or "XX/XX/XXX"
DateFullNum1       = {MonthNum31} "/" {Day31} "/" {Year}
DateFullNum2       = {MonthNum30} "/" {Day30} "/" {Year}
DateFullNum3       = {MonthNum29} "/" {Day29} "/" {Year}

//ie. "Month Day" or "Day Month" 
DateShort1         = {Month31} {SpaceSep} {Day31} | {Day31} {SpaceSep} {Month31}
DateShort2         = {Month30} {SpaceSep} {Day30} | {Day30} {SpaceSep} {Month30}
DateShort3         = {Month29} {SpaceSep} {Day29} | {Day29} {SpaceSep} {Month29}

//ie. "XX/XX"
DateShortNum       = {MonthNum31} "/" {Day31} | {MonthNum30} "/" {Day30} | {MonthNum29} "/" {Day29}

//groups of the accepted date formats
DateFull           = {DateFull1} | {DateFull2} | {DateFull3} | {DateFull4} | {DateFull5} | {DateFull6}
DateFullNum        = {DateFullNum1} | {DateFullNum2} | {DateFullNum3}
DateShort          = {DateShort1} | {DateShort2} | {DateShort3} | {DateShortNum}

//HIGHEST LEVEL 
DateMaster         = {DateFull} | {DateFullNum} | {DateShort}

//"BadDate" will match dates with extra "stuff" before or after them
// ie. no part of 10/10/10/10 should be highlighted even though "DateMaster" partially matches it.

BadDate            = [a-zA-Z0-9/]* {DateMaster} [a-zA-Z0-9/]*

// All that remains now is to define some rules for matching the different
// tokens that can appear in a valid mini program with corresponding actions
// to generate the appropriate output in each case.  As such, this puts us
// into the last section of our jflex input file:

%%


//Dates are matched and displayed using the "date" tag:

{DateMaster}          { date(); }

//longest lexeme rule ensures that badDates will be echo'd, not highlighted.
{BadDate}             { echo(); }

// ---------------------------------------------------------------------
