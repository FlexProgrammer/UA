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
public class BooleanValue implements Value{

    private final boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }
    
    @Override
    public int toInt() {
        return value ? 1 : 0;
    }

    @Override
    public double toDouble() {
        return value ? 1.0 : 0.0;
    }

    @Override
    public String asString() {
        return value ? "правда" : "брехня";
    }

    @Override
    public boolean toBoolean() {
        return value;
    }

    @Override
    public String toString() {
        return asString(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
