/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;


/**
 *
 * @author FleX
 */
import java.util.Arrays;

/**
 *
 * @author FleX
 */
public class ArrayValue implements Value{

    final Value[] elements;

    public ArrayValue(int size) {
        this.elements = new Value[size];
    }
    
    public ArrayValue(Value[] elements) {
        this.elements = new Value[elements.length];
        System.arraycopy(elements, 0, this.elements, 0, elements.length);
    }
    
    public ArrayValue(ArrayValue array) {
        this(array.elements);
    }

    public Value get(int index){
        return elements[index];
    }
    
    public void set(int index, Value value){
        elements[index] = value;
    }
    
    @Override
    public double toDouble() {
        throw new RuntimeException("Can't converte to double");
    }
    
    @Override
    public int toInt() {
        throw new RuntimeException("Can't converte to int");
    }
        
    @Override
    public String asString() {
        return Arrays.toString(elements);
    }
    
    @Override
    public boolean toBoolean() {
        throw new RuntimeException("Can't converte to boolean");
    }
}
