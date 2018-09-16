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
public class PrintStatement implements Statement{

    private final Expression expr;
    private final boolean isLn;

    public PrintStatement(Expression expr, boolean isLn) {
        this.expr = expr;
        this.isLn = isLn;
    }

    @Override
    public void execute() {
        if(isLn)System.out.println(expr.eval().asString());
        else System.out.print(expr.eval().asString());
    }
    
}
