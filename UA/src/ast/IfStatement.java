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
public class IfStatement implements Statement{

    private final Expression condition; 
    private final Statement ifStatement;
    private final Statement elseStatement;

    public IfStatement(Expression condition, Statement ifStatement, Statement elseStatement) {
        this.condition = condition;
        this.ifStatement = ifStatement;
        this.elseStatement = elseStatement;
    }
    
    @Override
    public void execute() {
        if(condition.eval().toBoolean()){
            ifStatement.execute();
        }
        else if (elseStatement != null){
            elseStatement.execute();
        }
    }
    
}
