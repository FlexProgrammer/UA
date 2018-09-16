/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.List;
import lib.BooleanValue;
import lib.DoubleValue;
import lib.IntegerValue;
import lib.StringValue;
import lib.Value;

/**
 *
 * @author FleX
 */
public class SwitchStatement implements Statement{

    private final Expression operator;
    private final List<Expression> cases;
    private final List<Statement> statements;

    public SwitchStatement(Expression operator, List<Expression> cases, List<Statement> statements) {
        this.operator = operator;
        this.cases = cases;
        this.statements = statements;
    }
    
    @Override
    public void execute() {
        final Value value = operator.eval();
        int index;
        for (Expression aCase : cases) {
            if(aCase == null){
                statements.get(statements.size()-1).execute(); 
            }
            else if(value instanceof IntegerValue || value instanceof DoubleValue){
                double num = value.toDouble();
                double num2 = aCase.eval().toDouble();
                if(num == num2){
                    try{
                        index = cases.indexOf(aCase);
                        for (; index < statements.size(); index++) {
                            statements.get(index).execute();
                           
                        }if(index == cases.size())break;
                    }catch(BreakStatement bs){
                        break;
                    }
                }
            }else if(value instanceof StringValue){
                String str = value.asString();
                String str2 = aCase.eval().asString();
                if(str.equals(str2)){
                    try{
                        index = cases.indexOf(aCase);
                        for (; index < statements.size(); index++) {
                            statements.get(index).execute();
                        }  if(index == cases.size())break;
                    }catch(BreakStatement bs){
                        break;
                    }
                }
            }else if(value instanceof BooleanValue){
                boolean bool = value.toBoolean();
                boolean bool2 = aCase.eval().toBoolean();
                if(bool == bool2){
                    try{
                        index = cases.indexOf(aCase);
                        for (; index < statements.size(); index++) {
                            statements.get(index).execute();
                        } if(index == cases.size())break;
                    }catch(BreakStatement bs){
                        break;
                    }
                }
            }
        }
            
    }
    
}
