package ast;
import compiler.Position;

/** Abstract syntax for unary plus expressions.
 */
public class UPlus extends UnArithExpr {

    /** Default constructor.
     */
    public UPlus(Position pos, Expr exp) {
        super(pos, exp);
    }
}
