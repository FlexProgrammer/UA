/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FleX
 */
public class BlockStatement implements Statement{

    private final List<Statement> statements;

    public BlockStatement() {
        statements = new ArrayList<>();
    }
    
    public void add(Statement statement){
        statements.add(statement);
    }
    
    @Override
    public void execute() {
        for (Statement statement : statements) {
            statement.execute();
        }
    }
    
    @Override
    public String toString() {
        return "BlockStatemetnt{" + "statements=" + statements + '}';
    }
}
