/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import ast.ReturnStatement;
import ast.Statement;
import java.util.List;

/**
 *
 * @author FleX
 */
public class UserDefineFunction implements Function{

    private final VariableType type;
    private final List<Variable> variables;
    private final Statement body;

    public UserDefineFunction(VariableType type, List<Variable> variables, Statement body) {
        this.type = type;
        this.variables = variables;
        this.body = body;
    }
    
    public int getCount(){
        return variables.size();
    }
    
    public String getName(int index){
        if (index < 0 || index > variables.size())return "";
        return variables.get(index).getName();
    }
    
    public VariableType getType(){
        return type;
    }

    @Override
    public void voidExecute(Value[] args) {
        try{
            body.execute();
        }catch(ReturnStatement rt){
            throw new RuntimeException("Зайвий оператор \"повернути\"");
        }
    }

    @Override
    public Value valueExecute(Value[] args, VariableType type) {
        try{
            body.execute();
            throw new RuntimeException("Пропущений оператор \"повернути\"");
        }catch(ReturnStatement rt){
            switch(type){
                case INT: return new IntegerValue(rt.getResult().toInt());
                case DOUBLE: return new DoubleValue(rt.getResult().toDouble());
                case STRING: return new StringValue(rt.getResult().toString());
                default:return new BooleanValue(rt.getResult().toBoolean());
            }
        }
    }
    
}
