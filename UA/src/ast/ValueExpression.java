/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lib.BooleanValue;
import lib.DoubleValue;
import lib.IntegerValue;
import lib.StringValue;
import lib.Value;

/**
 *
 * @author FleX
 */
public class ValueExpression implements Expression{

    private final Value value;

    public ValueExpression(double value) {
        this.value = new DoubleValue(value);
    }
    
    public ValueExpression(int value) {
        this.value = new IntegerValue(value);
    }
    
    public ValueExpression(String value) {
        this.value = new StringValue(value);
    }
    
    public ValueExpression(boolean value) {
        this.value = new BooleanValue(value);
    }
    
    @Override
    public Value eval() {
        return value;
    }
    
    @Override
    public String toString() {
        return value.asString();
    }
}
