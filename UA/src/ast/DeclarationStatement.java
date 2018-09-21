/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lib.Value;
import lib.VariableType;
import lib.Variables;

/**
 *
 * @author FleX
 */
public class DeclarationStatement implements Statement{

    private final String name;
    private final VariableType type;
    private final Expression expr;

    public DeclarationStatement(String name, VariableType type, Expression expr) {
        this.name = name;
        this.type = type;
        this.expr = expr;
    }
    
    @Override
    public void execute() {
        Variables.create(name, type);
        if(expr != null){
            Variables.set(name, expr.eval());
        }
    }
    
}
