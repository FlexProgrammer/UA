/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author FleX
 */
public class Variables {
    
    private static Map<String, VariableType> types;
    private static Map<String, Value> variables;
    private static final Stack<Map<String, VariableType>> typeStack;
    private static final Stack<Map<String, Value>> stack;
    
    static{
        types = new HashMap<>();
        variables = new HashMap<>();
        typeStack = new Stack<>();
        stack = new Stack<>();
    }
    
    public static void create(String name, VariableType type){
        types.put(name, type);
        variables.put(name, null);
    }
    
    public static VariableType isExist(String name){
        if (types.containsKey(name)){
            return types.get(name);
        }
        throw new RuntimeException(name + " не існує!");
    }
    
    public static Value get(String name){
        isExist(name);
        return variables.get(name);
    }
    
    public static void set(String name, Value value){
        if (value != null){
            switch(isExist(name)){
                case INT: variables.put(name, new IntegerValue(value.toInt()));break;
                case DOUBLE: variables.put(name, new DoubleValue(value.toDouble()));break;
                case STRING: variables.put(name, new StringValue(value.asString()));break;
                case BOOLEAN: variables.put(name, new BooleanValue(value.toBoolean()));break;
            }
        }
    }

    public static void push() {
        typeStack.push(new HashMap<>(types));
        stack.push(new HashMap<>(variables));
    }
    
    public static void pop(){
        types = typeStack.pop();
        variables = stack.pop();
    }
    
}
