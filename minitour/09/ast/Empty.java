package ast;
import compiler.Position;

/** Abstract syntax for empty statements.
 */
public class Empty extends PosStmt {

    /** Default constructor.
     */
    public Empty(Position pos) {
        super(pos);
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Empty");
    }

    /** Generate a pretty-printed description of this abstract syntax
     *  node using the concrete syntax of the mini programming language.
     */
    public void print(TextOutput out, int n) {
        out.indent(n);
        out.println(";");
    }
}
