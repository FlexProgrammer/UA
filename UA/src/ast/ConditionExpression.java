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
public class ConditionExpression implements Expression{

    private final String operator;
    private final Expression expr1;
    private final Expression expr2;

    public ConditionExpression(String operator, Expression expr1, Expression expr2) {
        this.operator = operator;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
    
    @Override
    public Value eval() {
        final Value value1 = expr1.eval();
        final Value value2 = expr2.eval();
        
        double num1, num2;
        if(value1 instanceof StringValue && value2 instanceof StringValue){
            num1 = value1.asString().compareTo(value2.asString());
            num2 = 0;
        }
        else{
            num1 = value1.toDouble();
            num2 = value2.toDouble();
        }
        
        switch(operator){
            case ">":return new BooleanValue(num1 > num2);
            case ">=":return new BooleanValue(num1 >= num2);
            case "<":return new BooleanValue(num1 < num2);
            case "<=":return new BooleanValue(num1 <= num2);
            case "==":return new BooleanValue(num1 == num2);
            case "!=":return new BooleanValue(num1 != num2);
            case "&&":return new BooleanValue(num1 != 0 && num2 != 0);
            case "||":return new BooleanValue(num1 != 0 || num2 != 0);
        }
        
        throw new RuntimeException("Невідома операція!");
    }
    
    @Override
    public String toString() {
        return String.format("(%s %s %s)", expr1, operator, expr2);
    }
}
