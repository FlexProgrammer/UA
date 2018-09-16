/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lib.DoubleValue;
import lib.IntegerValue;
import lib.StringValue;
import lib.Value;

/**
 *
 * @author FleX
 */
public class BinaryExpression implements Expression{

    private final String operation;
    private final Expression expr1, expr2;

    public BinaryExpression(String operation, Expression expr1, Expression expr2) {
        this.operation = operation;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
    
    @Override
    public Value eval() {
        final Value value1 = expr1.eval();
        final Value value2 = expr2.eval();
        
        if((value1 instanceof StringValue) || (value2 instanceof StringValue)){
            switch(operation){
                case "+": return new StringValue(value1.toString() + value2.toString());
                case "*":{
                    if(value2 instanceof IntegerValue){
                        StringBuilder buffer = new StringBuilder();
                        for (int i = 0; i < value2.toInt(); i++) {
                            buffer.append(value1.asString());
                        }
                        return new StringValue(buffer.toString());
                    } 
                }
            }
        }
        
        if ((value1 instanceof DoubleValue) || (value2 instanceof DoubleValue)){
            double num1 = value1.toDouble();
            double num2 = value2.toDouble();
            switch(operation){
                case "+": return new DoubleValue(num1 + num2);
                case "-": return new DoubleValue(num1 - num2);
                case "*": return new DoubleValue(num1 * num2);
                case "/": return new DoubleValue(num1 / num2);
            }
        }
        if ((value1 instanceof IntegerValue) || (value2 instanceof IntegerValue)){
            int num1 = value1.toInt();
            int num2 = value2.toInt();
            switch(operation){
                case "+": return new IntegerValue(num1 + num2);
                case "-": return new IntegerValue(num1 - num2);
                case "*": return new IntegerValue(num1 * num2);
                case "/": return new IntegerValue(num1 / num2);
            }
        }
        throw new RuntimeException("Неправильна операція!");
    }

    @Override
    public String toString() {
        return String.format("(%s %c %s)", expr1, operation, expr2);
    }
    
}
