/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

/**
 *
 * @author FleX
 */
public class ArrayAssignmentStatement implements Statement{

    private final Expression expr;
    private final ArrayAccesExpression array;

    public ArrayAssignmentStatement(Expression expr, ArrayAccesExpression array) {
        this.expr = expr;
        this.array = array;
    }
    
    @Override
    public void execute() {
        array.getArray().set(array.lastIndex(), expr.eval());
    }
    
}
