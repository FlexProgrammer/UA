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
public interface Function {
    void voidExecute(Value[] args);
    Value valueExecute(Value[] args, VariableType type);
}
