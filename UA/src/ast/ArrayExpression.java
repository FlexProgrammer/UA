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

/**
 *
 * @author FleX
 */
public class ArrayExpression implements Expression{

    private final VariableType type;
    private final List<Expression> indices;

    public ArrayExpression(VariableType type, List<Expression> indices) {
        this.type = type;
        this.indices = indices;
    }
    
    @Override
    public Value eval() {
        final int size = indices.size();
        switch(type){
            case INTARRAY: {
                final IntArray array = new IntArray(size);
                for (int i = 0; i < size; i++) {
                array.set(i, indices.get(i).eval());
                }
                return array;
            }
            case DOUBLEARRAY: {
                final DoubleArray array = new DoubleArray(size);
                for (int i = 0; i < size; i++) {
                array.set(i, indices.get(i).eval());
                }
                return array;
            }
            case STRINGARRAY:{
                final StringArray array = new StringArray(size);
                for (int i = 0; i < size; i++) {
                array.set(i, indices.get(i).eval());
                }
                return array;
            }
            case BOOLEANARRAY:{
                final BooleanArray array = new BooleanArray(size);
                for (int i = 0; i < size; i++) {
                array.set(i, indices.get(i).eval());
                }
                return array;
            }
            default:{
                final ArrayValue array = new ArrayValue(size);
                for (int i = 0; i < size; i++) {
                array.set(i, indices.get(i).eval());
                }
                return array;
            }
        }
        
    }
    
}
