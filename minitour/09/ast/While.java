package ast;
import compiler.Position;

/** Abstract syntax for while statements.
 */
public class While extends PosStmt {

    /** The test expression.
     */
    private Expr test;

    /** The body of this loop.
     */
    private Stmt body;

    /** Default constructor.
     */
    public While(Position pos, Expr test, Stmt body) {
        super(pos);
        this.test = test;
        this.body = body;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "While");
        test.indent(out, n+1);
        body.indent(out, n+1);
    }

    /** Generate a pretty-printed description of this abstract syntax
     *  node using the concrete syntax of the mini programming language.
     */
    public void print(TextOutput out, int n) {
        out.indent(n);
        out.print("while (");
        test.print(out);
        out.print(")");
        body.printElse(out, n);
    }
}
