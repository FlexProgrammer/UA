/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lib.Variables;

/**
 *
 * @author FleX
 */
public class AssignmentStatement implements Statement {

    private final String name;
    private final Expression expr;

    public AssignmentStatement(String name, Expression expr) {
        this.name = name;
        this.expr = expr;
    }
    
    @Override
    public void execute() {
        Variables.set(name, expr.eval());
    }
    
}
