/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.List;
import lib.ArrayValue;
import lib.BooleanArray;
import lib.DoubleArray;
import lib.IntArray;
import lib.StringArray;
import lib.Value;
import lib.VariableType;
import lib.Variables;

/**
 *
 * @author FleX
 */
public class ArrayAccesExpression implements Expression{

    private final String name;
    private final List<Expression> indices;

    public ArrayAccesExpression(String name, List<Expression> indices) {
        this.name = name;
        this.indices = indices;
    }
    
    @Override
    public Value eval() {
        return getArray();
    }
    
    public Value getArray(){
        final Value value = Variables.get(name);
        switch(consumeArray(value)){
            case INTARRAY: {
                IntArray array = (IntArray) value;
                final int size = indices.size() - 1;
                for (int i = 0; i < size; i++) {
                    array = consumeInt(array.get(index(i)));
                }
                return array.get(lastIndex());
            }
            case DOUBLEARRAY: {
                DoubleArray array = (DoubleArray) value;
                final int size = indices.size() - 1;
                for (int i = 0; i < size; i++) {
                    array = consumeDouble(array.get(index(i)));
                }
                return array.get(lastIndex());
            }
            case STRINGARRAY: {
                StringArray array = (StringArray) value;
                final int size = indices.size() - 1;
                for (int i = 0; i < size; i++) {
                    array = consumeString(array.get(index(i)));
                }
                return array.get(lastIndex());
            }
            case BOOLEANARRAY: {
                BooleanArray array = (BooleanArray) value;
                final int size = indices.size() - 1;
                for (int i = 0; i < size; i++) {
                    array = consumeBool(array.get(index(i)));
                }
                return array.get(lastIndex());
            }
            default:{
                ArrayValue array = (ArrayValue) value;
                final int size = indices.size() - 1;
                for (int i = 0; i < size; i++) {
                    array = consume(array.get(index(i)));
                }
                return array.get(lastIndex());
            }
        }
    }
    
    public int index(int i){
        return indices.get(i).eval().toInt();
    }
    
    public VariableType consumeArray(Value value){
        if (value instanceof ArrayValue) return VariableType.ARRAY;
        else if (value instanceof IntArray) return VariableType.INTARRAY;
        else if (value instanceof DoubleArray) return VariableType.DOUBLEARRAY;
        else if (value instanceof StringArray) return VariableType.STRINGARRAY;
        else if (value instanceof BooleanArray) return VariableType.BOOLEANARRAY;
        throw new RuntimeException("Не є массивом!");
    }
    
    public ArrayValue consume(Value value){
        if (value instanceof ArrayValue) return (ArrayValue) value;
        throw new RuntimeException("Не є массивом!");
    }
    
    public IntArray consumeInt(Value value){
        if (value instanceof IntArray) return (IntArray) value;
        throw new RuntimeException("Не є массивом!");
    }
    
    public DoubleArray consumeDouble(Value value){
        if (value instanceof DoubleArray) return (DoubleArray) value;
        throw new RuntimeException("Не є массивом!");
    }
    
    public StringArray consumeString(Value value){
        if (value instanceof StringArray) return (StringArray) value;
        throw new RuntimeException("Не є массивом!");
    }
    
    public BooleanArray consumeBool(Value value){
        if (value instanceof BooleanArray) return (BooleanArray) value;
        throw new RuntimeException("Не є массивом!");
    }
    
    public int lastIndex() {
        return indices.size() - 1;
    }
}
