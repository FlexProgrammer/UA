/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

/**
 *
 * @author FleX
 */
public class ContinueStatement extends RuntimeException implements Statement{

    @Override
    public void execute() {
        throw this;
    }
    
}
