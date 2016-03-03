/* Jonathan Jensen
** Assignment 3 Task 1
** Java Class Skeletons
** CS321
*/

import java.util.*;

class Class {
    private boolean isAbstract;
    private String  name;
    private Args    args;
    private Class[] subclasses;

    //default constructor

    Class(boolean isAbstract, String name, Args args, Class[] subclasses) {
        this.isAbstract = isAbstract;
        this.name = name;
        this.args = args;
        this.subclasses = subclasses;
    }

    //methods and fields go here ...

    /** Output an indented description of this class.
    
    void indent(IndentOutput out, int n) {
      out.indent(n, name);
      out.indent(n+1, "isAbstract="+isAbstract);
      out.indent(n+1, "Args");
      Args.indent(out, n+2, args, null);
      out.indent(n+1, "Classes");
      indent(out, n+2, subclasses);
    }
    */
    /** Output an indented description of an array of classes.
    
    public static void indent(IndentOutput out, int n, Class[] classes) {
      for (int i=0; i<classes.length; i++) {
        classes[i].indent(out, n);
      }
    }
    */    

    void printJava(int n){
        String abstr = "";
        if (isAbstract){
            abstr = "abstract ";
        }

        indent(n, (abstr + "class " + name + " {\n")  );
        //class body
        indent(n+1, "// declare the fields for this class\n");
        //args
        args.printJava(n+1, args, null);
        
        indent(n+1, "// declare default constructor for this class\n");
        indent(n+1, (name + "("));
        ArrayList<Arg> argArray1 = new ArrayList<>();
        args.printJavaConstr(0, args, null, argArray1);
        System.out.print(") {\n");
        args.printJavaSet(n+2, args, null);
         
        
        indent(n+1, "}\n");
        indent(n+1, "// add additional methods and fields here ...\n");
        
        indent(n, "}\n");
        printJavaSubClass(n, subclasses, name, args); 
    }

    public static void printJava(int n, Class[] c){
        for (int i=0; i<c.length; i++) {
            c[i].printJava(n);
        }
    }
    
    void printJavaSubClass(int n, String pName, Args pArgs){
        String abstr = "";
        if (isAbstract){
            abstr = "abstract ";
        }

        indent(n, (abstr + "class " + name + " extends " + pName + " {\n")  );
        //class body
        indent(n+1, "// declare the fields for this class\n");
        //args
        args.printJava(n+1, args, pArgs);
        
        indent(n+1, "// declare default constructor for this class\n");
        indent(n+1, (name + "("));
        ArrayList<Arg> argArray1 = new ArrayList<>();
        ArrayList<Arg> argArray2 = new ArrayList<>();
        args.printJavaConstr(0, pArgs, null, argArray1);
        if (argArray1.size() != 0) { 
          System.out.print(", ");
        }
        args.printJavaConstr(0, args, pArgs, argArray2);
        System.out.print(") {\n");
        args.printJavaSuper(n+2, pArgs, null);
        args.printJavaSet(n+2, args, pArgs);

        indent(n+1, "}\n");

                 

        indent(n+1, "// add additional methods and fields here ...\n");
        
        indent(n, "}\n");
        printJava(n, subclasses); 
    }

    public static void printJavaSubClass(int n, Class[] c, String pName, Args pArgs) {
      for (int i=0; i<c.length; i++) {
        c[i].printJavaSubClass(n, pName, pArgs);
      }
    }

    /** Print a given String message indented some number of
    *  spaces (currently two times the given nesting level, n).
    */
    public void indent(int n, String msg) {
      for (int i=0; i<n; i++) {
        System.out.print("  ");
      }
      System.out.print(msg);
    }

}

class Args {
    Args before;
    Arg last;

    //default constructor
    
    Args(Args before, Arg last) {
        this.before = before;
        this.last = last;
    }
    
    //methods and fields go here ...

    /** Output an indented description of a list of arguments.
    
    public static void indent(IndentOutput out, int n, Args args, Args end) {
      if (args!=end) {                    // Add to the Args class
        indent(out, n, args.before, end);
        args.last.indent(out, n);
      }
    }
    */

    public static void printJava(int n, Args args, Args end) {
      if (args!=end) {                    
        printJava(n, args.before, end);
        args.last.printJava(n);
      }
      
    }

    public static void printJavaConstr(int n, Args args, Args end, ArrayList<Arg> argArray) {
     // ArrayList<Arg> argArray = new ArrayList<Arg>();      

      if (args!=end) {
        printJavaConstr(n, args.before, end, argArray);
        argArray.add(args.last);
        //args.last.printJavaConstr(n);
      }
      for(int i = 0; i < argArray.size(); i++) {
        argArray.get(i).printJavaConstr(0);
        if (i+1 < argArray.size()) {
          System.out.print(", ");
        }
      }
    }

    public static void printJavaSet(int n, Args args, Args end) {
      if (args!=end) {
        printJavaSet(n, args.before, end);
        indent(n, ("this."));
        args.last.printJavaSet(n);
        System.out.print(" = ");
        args.last.printJavaSet(n);
        System.out.print(";\n");
      }
    } 

    public static void printJavaSuper(int n, Args args, Args end) {
      if (args!=end) {
        indent(n, "super(");
        args.last.printJavaSet(n);
        System.out.print(");\n");
      }
    }        

    /** Print a given String message indented some number of
    *  spaces (currently two times the given nesting level, n).
    */
    public static void indent(int n, String msg) {
      for (int i=0; i<n; i++) {
        System.out.print("  ");
      }
      System.out.print(msg);
    }


}

class Arg {
    private String visibility;
    private Type type;
    private String id;

    //default constructor

    Arg(String visibility, Type type, String id) {
        this.visibility = visibility;
        this.type = type;
        this.id = id;
    }

    //methods and fields go here ...

    /** Output an indented description of this argument.
     */
    public void printJava(int n) {
      StringBuffer buf = new StringBuffer();
      if (visibility!=null) {
        buf.append(visibility);
        buf.append(" ");
      }
      buf.append(type.toString());
      buf.append(" ");
      buf.append(id);
      buf.append(";\n");
      indent(n, buf.toString());
    }
    
    public void printJavaConstr(int n) {
      StringBuffer buf = new StringBuffer();
      buf.append(type.toString());
      buf.append(" ");
      buf.append(id);
      System.out.print(buf.toString());
    }

    public void printJavaSet(int n) {
      StringBuffer buf = new StringBuffer();
      buf.append(id);
      System.out.print(buf.toString());
    }
    /** Print a given String message indented some number of
    *  spaces (currently two times the given nesting level, n).
    */
    public void indent(int n, String msg) {
      for (int i=0; i<n; i++) {
        System.out.print("  ");
      }
      System.out.print(msg);
    }


}

abstract class Type {
    //default constructor
    Type(){
        
    }
    
    //methods and fields go here ...
}

class NameType extends Type {
    private String[] ids;
    
    //default constructor for this class
    NameType(String[] ids) {
        this.ids = ids;
    }
    //methods and fields go here
    public String toString() {  // Add this method to the NameType class
      if (ids.length==1) {
        return ids[0];
        } else {
          StringBuffer buf = new StringBuffer(ids[0]);
          for (int i=1; i<ids.length; i++) {
            buf.append(" . ");
            buf.append(ids[i]);
          }
          return buf.toString();
          }
    }
}

class ArrayType extends Type {
    private Type elem;
    
    //default constructor for this class
    ArrayType(Type elem) {
        this.elem = elem;
    }
    
    //methods and fields go here ...
    public String toString() {  // Add this method to the ArrayType class
      return elem.toString() + " []";
    }
}
