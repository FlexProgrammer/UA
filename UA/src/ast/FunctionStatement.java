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
public class FunctionStatement implements Statement{

    private final FunctionExpression function;

    public FunctionStatement(FunctionExpression function) {
        this.function = function;
    }
    
    @Override
    public void execute() {
        function.eval();
    }
    
}
