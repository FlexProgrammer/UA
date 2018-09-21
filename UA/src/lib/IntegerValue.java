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
public class IntegerValue implements Value{

    private final int value;

    public IntegerValue(int value) {
        this.value = value;
    }
    
    @Override
    public int toInt() {
        return value;
    }

    @Override
    public double toDouble() {
        return (double) value;
    }

    @Override
    public String asString() {
        return String.valueOf(value);
    }

    @Override
    public boolean toBoolean() {
        return (value != 0);
    }

    @Override
    public String toString() {
        return asString(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
