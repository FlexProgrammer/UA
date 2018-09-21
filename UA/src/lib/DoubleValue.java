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
public class DoubleValue implements Value{

    private final double value;

    public DoubleValue(double value) {
        this.value = value;
    }
    
    @Override
    public int toInt() {
        return (int) ((value - 0.5 < Math.floor(value)) ? Math.floor(value) : Math.ceil(value));
    }

    @Override
    public double toDouble() {
        return value;
    }

    @Override
    public String asString() {
        return String.valueOf(value);
    }

    @Override
    public boolean toBoolean() {
        return (value != 0.0);
    }

    @Override
    public String toString() {
        return asString(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
