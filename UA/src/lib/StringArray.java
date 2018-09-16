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
public class StringArray implements Value{

    private final StringValue[] elements;

    public StringArray(int size) {
        this.elements = new StringValue[size];
    }
    
    public StringArray(StringValue[] elements) {
        this.elements = new StringValue[elements.length];
        System.arraycopy(elements, 0, this.elements, 0, elements.length);
    }
    
    public StringValue get(int index){
        return elements[index];
    }
    
    public StringArray(StringArray value) {
        this(value.elements);
    }
    
    public void set(int index, Value value){
        elements[index] = new StringValue(value.asString());
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
