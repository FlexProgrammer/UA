/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.Arrays;

/**
 *
 * @author FleX
 */
public class DoubleArray implements Value{

    private final DoubleValue[] elements;

    public DoubleArray(int size) {
        this.elements = new DoubleValue[size];
    }
    
    public DoubleArray(DoubleValue[] elements) {
        this.elements = new DoubleValue[elements.length];;
        System.arraycopy(elements, 0, this.elements, 0, elements.length);
    }
    
    public DoubleValue get(int index){
        return elements[index];
    }
    
    public DoubleArray(DoubleArray value) {
        this(value.elements);
    }
    
    public void set(int index, Value value){
        elements[index] = new DoubleValue(value.toInt());
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
