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
public class StringValue implements Value {

    private final String value;

    public StringValue(String value) {
        this.value = value;
    }
    
    @Override
    public int toInt() {
        try{
            return Integer.parseInt(value);
        }catch(NumberFormatException e){
            throw new RuntimeException("Не можливо конвертувати в ціле число стрічку : " + value);
        }
    }

    @Override
    public double toDouble() {
        try{
            return Double.parseDouble(value);
        }catch(NumberFormatException e){
            throw new RuntimeException("Не можливо конвертувати в дріб стрічку : " + value);
        }
    }

    @Override
    public String asString() {
        return value;
    }

    @Override
    public boolean toBoolean() {
        return !value.isEmpty();
    }

    @Override
    public String toString() {
        return value;
    }
    
}
