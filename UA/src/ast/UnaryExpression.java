/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lib.DoubleValue;
import lib.IntegerValue;
import lib.Value;

/**
 *
 * @author FleX
 */
public class UnaryExpression implements Expression{

    private final Expression expr;

    public UnaryExpression(Expression expr) {
        this.expr = expr;
    }
    
    @Override
    public Value eval() {
        if(expr.eval() instanceof IntegerValue)return new IntegerValue(-expr.eval().toInt());
        return new DoubleValue(-expr.eval().toDouble());
    }
    
}
