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
public class Variable {
    
    private String name;
    private VariableType type;

    public Variable(String name, VariableType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public VariableType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(VariableType type) {
        this.type = type;
    }
}
