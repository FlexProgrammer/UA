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
public class IntArray implements Value{

    private final IntegerValue[] elements;

    public IntArray(int size) {
        this.elements = new IntegerValue[size];
    }
    
    public IntArray(IntegerValue[] elements) {
        this.elements = new IntegerValue[elements.length];;
        System.arraycopy(elements, 0, this.elements, 0, elements.length);
    }
    
    public IntegerValue get(int index){
        return elements[index];
    }
    
    public IntArray(IntArray value) {
        this(value.elements);
    }
    
    public void set(int index, Value value){
        elements[index] = new IntegerValue(value.toInt());
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
