/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.List;
import lib.Functions;
import lib.UserDefineFunction;
import lib.Variable;
import lib.VariableType;

/**
 *
 * @author FleX
 */
public class FunctionDefineStatement implements Statement {

    private final VariableType type;
    private final String name;
    private final List<Variable> variables;
    private final Statement body;

    public FunctionDefineStatement(VariableType type, String name, List<Variable> variables, Statement body) {
        this.type = type;
        this.name = name;
        this.variables = variables;
        this.body = body;
    }
    
    @Override
    public void execute() {
        Functions.set(name, new UserDefineFunction(type, variables, body));
    }

    
    
}
