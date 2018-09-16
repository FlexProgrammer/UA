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
public class ForStatement implements Statement {

    private final Statement initialize;
    private final Expression condition;
    private final Statement iterator;
    private final Statement block;

    public ForStatement(Statement initialize, Expression condition, Statement iterator, Statement block) {
        this.initialize = initialize;
        this.condition = condition;
        this.iterator = iterator;
        this.block = block;
    }
    @Override
    public void execute() {
        for (initialize.execute(); condition.eval().toBoolean(); iterator.execute())
        {
            try{
                block.execute();  
            }catch(ContinueStatement cs){
            }catch(BreakStatement bs){
                break;
            }
        }
    }
    
}
