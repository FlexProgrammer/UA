/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;
import java.util.List;
import lib.BooleanValue;
import lib.DoubleValue;
import lib.Functions;
import lib.IntegerValue;
import lib.StringValue;
import lib.UserDefineFunction;
import lib.Value;
import lib.VariableType;
import lib.Variables;

/**
 *
 * @author FleX
 */
public class FunctionExpression implements Expression{

    private final String name;
    private final List<Expression> variables;

    public FunctionExpression(String name, List<Expression> variables) {
        this.name = name;
        this.variables = variables;
    }

    public FunctionExpression(String name) {
        this.name = name;
        variables = new ArrayList<>();
    }
    
    public void addArg(Expression expr){
        variables.add(expr);
    }
    
    @Override
    public Value eval() {
        final int size = variables.size();
        Value[] values = new Value[size];
        for (int i = 0; i < size; i++) {
            values[i] = variables.get(i).eval();
        }
        
        final UserDefineFunction function = (UserDefineFunction) Functions.get(name);
        Variables.push();
        final VariableType type = function.getType();
        for (int i = 0; i < function.getCount(); i++) {
            if(values[i] instanceof IntegerValue) Variables.create(function.getName(i), VariableType.INT);
            if(values[i] instanceof DoubleValue) Variables.create(function.getName(i), VariableType.DOUBLE);
            if(values[i] instanceof StringValue) Variables.create(function.getName(i), VariableType.STRING);
            if(values[i] instanceof BooleanValue) Variables.create(function.getName(i), VariableType.BOOLEAN);
            Variables.set(function.getName(i), values[i]);
        }
        if(type == VariableType.VOID){
            function.voidExecute(values);
            return null;
        }
        final Value result = function.valueExecute(values, type);
        Variables.pop();
        return result;
    }
    
}
