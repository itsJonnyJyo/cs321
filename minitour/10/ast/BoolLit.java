package ast;
import compiler.Position;

/** Abstract syntax for Boolean literals.
 */
public class BoolLit extends Expr {

    /** The value of this Boolean literal.
     */
    private boolean value;

    /** Default constructor.
     */
    public BoolLit(Position pos, boolean value) {
        super(pos);
        this.value = value;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "BoolLit(" + value + ")");
    }

    /** Generate a pretty-printed description of this expression
     *  using the concrete syntax of the mini programming language.
     */
    public void print(TextOutput out) { out.print("" + value); }

    /** Output a description of this node (with id n) in dot format,
     *  adding an extra node for each subtree.
     */
    public int toDot(DotOutput dot, int n) {
        return node(dot, "BoolLit(" + value + ")", n);
    }
}
