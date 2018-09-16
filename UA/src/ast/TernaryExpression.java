/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lib.Value;

/**
 *
 * @author FleX
 */
public class TernaryExpression implements Expression{

    private final Expression condition;
    private final Expression exprTrue;
    private final Expression exprFalse;

    public TernaryExpression(Expression condition, Expression exprTrue, Expression exprFalse) {
        this.condition = condition;
        this.exprTrue = exprTrue;
        this.exprFalse = exprFalse;
    }
    
    @Override
    public Value eval() {
        return (condition.eval().toBoolean()) ? exprTrue.eval() : exprFalse.eval();
    }
    
}
