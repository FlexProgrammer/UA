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
public class WhileStatement implements Statement{

    private final Expression condition;
    private final Statement whileStatement;

    public WhileStatement(Expression condition, Statement whileStatement) {
        this.condition = condition;
        this.whileStatement = whileStatement;
    }
    
    @Override
    public void execute() {
        while(condition.eval().toBoolean()){
            try{
                whileStatement.execute();
            }catch(ContinueStatement cs){
            }catch(BreakStatement bs){
                break;
            }
        }
    }
    
}
