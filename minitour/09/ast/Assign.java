package ast;
import compiler.Position;

/** Abstract syntax for assignment statements.
 */
public class Assign extends PosStmt {

    /** The variable where the result will be saved.
     */
    private Id lhs;

    /** The expression whose value will be saved.
     */
    private Expr rhs;

    /** Default constructor.
     */
    public Assign(Position pos, Id lhs, Expr rhs) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Assign");
        lhs.indent(out, n+1);
        rhs.indent(out, n+1);
    }

    /** Generate a pretty-printed description of this abstract syntax
     *  node using the concrete syntax of the mini programming language.
     */
    public void print(TextOutput out, int n) {
        out.indent(n);
        lhs.print(out);
        out.print(" = ");
        rhs.print(out);
        out.println(";");
    }
}
