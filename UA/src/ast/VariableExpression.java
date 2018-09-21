/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lib.Value;
import lib.Variables;

/**
 *
 * @author FleX
 */
public class VariableExpression implements Expression{

    private final String name;

    public VariableExpression(String name) {
        this.name = name;
    }
    
    @Override
    public Value eval() {
        return Variables.get(name);
    }
    
    @Override
    public String toString() {
        return Variables.get(name).asString();
    }
}
