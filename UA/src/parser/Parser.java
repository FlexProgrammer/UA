
package Parser;


import ast.AssignmentStatement;
import ast.BinaryExpression;
import ast.BlockStatement;
import ast.BreakStatement;
import ast.ConditionExpression;
import ast.ContinueStatement;
import ast.DeclarationStatement;
import ast.Expression;
import ast.ForStatement;
import ast.FunctionDefineStatement;
import ast.FunctionExpression;
import ast.FunctionStatement;
import ast.IfStatement;
import ast.PrintStatement;
import ast.ReturnStatement;
import ast.Statement;
import ast.SwitchStatement;
import ast.TernaryExpression;
import ast.UnaryExpression;
import ast.ValueExpression;
import ast.VariableExpression;
import ast.WhileStatement;
import java.util.ArrayList;
import java.util.List;
import lib.Variable;
import lib.VariableType;

/**
 *
 * @author FleX
 */
public class Parser {
    
    private static final Token EOF = new Token("", TokenType.EOF);
    
    private final List<Token> tokens;
    private final int size;
    
    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        size = tokens.size();
    }
    
    public Statement parse(){
        BlockStatement statement = new BlockStatement();
        
        while(!match(TokenType.EOF)){
            statement.add(statement());
        }
        
        return statement;
    }
    
    private Statement statement(){
        Token current = get(0);
        if(current.getType() == TokenType.DECLARATION && get(2).getType() != TokenType.LPAREN){
            match(TokenType.DECLARATION);
            final String name = consume(TokenType.WORD);
            if(match(TokenType.EQ)){
                return declarationStatement(current.getText(), name, true);
            }
            return declarationStatement(current.getText(), name, false);
        }
        if(current.getType() == TokenType.DECLARATION && get(2).getType() == TokenType.LPAREN){
            return functionDefine(current.getText());
        }
        if(current.getType() == TokenType.WORD && get(1).getType() == TokenType.LPAREN){
            return new FunctionStatement(function());
        }
        if(match(TokenType.PRINT)){
            return new PrintStatement(expression(), false);
        }
        if(match(TokenType.PRINTLN)){
            return new PrintStatement(expression(), true);
        }
        if(match(TokenType.IF)){
            return ifElse();
        }
        if(match(TokenType.WHILE)){
            return whileStatement();
        }
        if(match(TokenType.FOR)){
            return forStatement();
        }
        if(match(TokenType.CONTINUE)){
            return new ContinueStatement();
        }
        if(match(TokenType.BREAK)){
            return new BreakStatement();
        }
        if(match(TokenType.RETURN)){
            return new ReturnStatement(expression());
        }
        if(match(TokenType.SWITCH)){
            return switchStatement();
        }
        return assignmentStatement(current.getText());
    }
    
    private Statement assignmentStatement(String name){
        match(TokenType.WORD);
        match(TokenType.EQ);
        return new AssignmentStatement(name, expression());
    }
    
    private Statement declarationStatement(String type, String name, boolean isAssignment){
        switch(type){
            case "int":{
                return new DeclarationStatement(name, VariableType.INT, isAssignment ? expression() : null);
            }
            case "double":{
                return new DeclarationStatement(name, VariableType.DOUBLE, isAssignment ? expression() : null);
            }
            case "string":{
                return new DeclarationStatement(name, VariableType.STRING, isAssignment ? expression() : null);
            }
            default:{
                return new DeclarationStatement(name, VariableType.BOOLEAN, isAssignment ? expression() : null);
            }
        }
    }
    
    private Statement switchStatement(){
        match(TokenType.LPAREN);
        final Expression operator = expression();
        match(TokenType.RPAREN);
        match(TokenType.LBRACE);
        List<Expression> cases = new ArrayList<>();
        List<Statement> statements = new ArrayList<>();
        while(true){
            if(match(TokenType.CASE)){
                cases.add(expression());
                match(TokenType.COLON);
                statements.add(statementOrBlock());
                continue;
            }
            if(match(TokenType.DEFAULT)){
                cases.add(null);
                match(TokenType.COLON);
                statements.add(statementOrBlock());
                continue;
            }
            break;
        }
        match(TokenType.RBRACE);
        return new SwitchStatement(operator, cases, statements);
    }
    
    private FunctionExpression function(){
        final String name = consume(TokenType.WORD);
        match(TokenType.LPAREN);
        final FunctionExpression function = new FunctionExpression(name);
        while(!match(TokenType.RPAREN)){
            function.addArg(expression());
            match(TokenType.COMMA);
        }
        return function;
    }
    
    private Statement functionDefine(String type){
        match(TokenType.DECLARATION);
        final String name = consume(TokenType.WORD);
        match(TokenType.LPAREN);
        final List<Variable> names = new ArrayList<>();
        while(!match(TokenType.RPAREN)){
            switch(consume(TokenType.DECLARATION)){
                case "int": names.add(new Variable(consume(TokenType.WORD), VariableType.INT));break;
                case "double": names.add(new Variable(consume(TokenType.WORD), VariableType.DOUBLE));break;
                case "string": names.add(new Variable(consume(TokenType.WORD), VariableType.STRING));break;
                default: names.add(new Variable(consume(TokenType.WORD), VariableType.BOOLEAN));
            }
            match(TokenType.COMMA);
        }
        final Statement body = statementOrBlock();
        switch(type){
            case "int":return new FunctionDefineStatement(VariableType.INT, name, names, body);
            case "double":return new FunctionDefineStatement(VariableType.DOUBLE, name, names, body);
            case "string":return new FunctionDefineStatement(VariableType.STRING, name, names, body);
            case "boolean":return new FunctionDefineStatement(VariableType.BOOLEAN, name, names, body);
            default: return new FunctionDefineStatement(VariableType.VOID, name, names, body);
        }
        
    }
    
    private Statement forStatement(){
        match(TokenType.LPAREN);
        final String current = consume(TokenType.DECLARATION);
        final String name = consume(TokenType.WORD);
        match(TokenType.EQ);
        final Statement initialize = declarationStatement(current, name, true);
        match(TokenType.SEMICOLON);
        final Expression condition = expression();
        match(TokenType.SEMICOLON);
        final Statement iterator = assignmentStatement(name);
        match(TokenType.RPAREN);
        final Statement block = statementOrBlock();
        return new ForStatement(initialize, condition, iterator, block);
    }
    
    private Statement statementOrBlock(){
        if (match(TokenType.LBRACE)){
            return block();
        }
        return statement();
    }
    
    private Statement block(){
        final BlockStatement block = new BlockStatement();
        while(!match(TokenType.RBRACE)){
            block.add(statement());
        }
        return block;
    }
    
    private Statement whileStatement(){
        match(TokenType.LPAREN);
        final Expression condition = expression();
        match(TokenType.RPAREN);
        match(TokenType.LBRACE);
        final Statement whileStatement = block();
        return new WhileStatement(condition, whileStatement);
    }
    
    private Statement ifElse(){
        match(TokenType.LPAREN);
        final Expression condition = expression();
        match(TokenType.RPAREN);
        final Statement ifStatement = statementOrBlock();
        Statement elseStatement = null;
        if (match(TokenType.ELSE)){
            elseStatement = statementOrBlock();
        }
        return new IfStatement(condition, ifStatement, elseStatement);
    }
    
    private Expression ternary(Expression condition){
        final Expression exprTrue = expression();
        match(TokenType.COLON);
        final Expression exprFalse = expression();
        return new TernaryExpression(condition, exprTrue, exprFalse);
    }
    
    private Expression expression(){
        return logicalOr();
    }
    
    private Expression logicalOr(){
        Expression result = logicalAnd();
        
        while(true){
            if(match(TokenType.BARBAR)){ 
                result = new ConditionExpression("||", result, logicalAnd());
            }
            break;
        }
        
        return result;
    }
    
    private Expression logicalAnd(){
        Expression result = equality();
        
        while(true){
            if(match(TokenType.AMPAMP)){ 
                result = new ConditionExpression("&&", result, equality());
            }
            break;
        }
        
        return result;
    }
    
    private Expression equality(){
        Expression result = condition();
        
        if(match(TokenType.EQEQ)){ 
            result = new ConditionExpression("==", result, condition());
        }
        if(match(TokenType.NOTEQ)){
            result = new ConditionExpression("!=", result, condition());
        }
        
        return result;
    }
    
    private Expression condition(){
        Expression result = aditive();
        
        while(true){
            if(match(TokenType.LT)){ 
                result = new ConditionExpression("<", result, aditive());
                continue;
            }
            if(match(TokenType.LTEQ)){
                result = new ConditionExpression("<=", result, aditive());
                continue;
            }
            if(match(TokenType.GT)){
                result = new ConditionExpression(">", result, aditive());
                continue;
            }
            if(match(TokenType.GTEQ)){
                result = new ConditionExpression(">=", result, aditive());
                continue;
            }
            break;
        }
        
        return result;
    }
    
    private Expression aditive(){
        Expression result = multiplicative();
        
        while(true){
            if(match(TokenType.PLUS)){
                result = new BinaryExpression("+", result, multiplicative());
                continue;
            }
            if(match(TokenType.MINUS)){
                result = new BinaryExpression("-", result, multiplicative());
                continue;
            }
            break;
        }
        
        return result;
    }
    
    private Expression multiplicative(){
        Expression result = unary();
        
        while(true){
            if(match(TokenType.STAR)){
                result = new BinaryExpression("*", result, unary());
                continue;
            }
            if(match(TokenType.SLASH)){
                result = new BinaryExpression("/", result, unary());
                continue;
            }
            break;
        }
        
        return result;
    }
    
    public Expression unary(){
        if(match(TokenType.MINUS)){
            return new UnaryExpression(primary());
        }
        final Expression result = primary();
        if(match(TokenType.TERNARY)) return ternary(result);
        return result;
    }
    
    public Expression primary(){
        Token current = get(0);
        if (match(TokenType.INTEGERNUMBER)){
            return new ValueExpression(Integer.parseInt(current.getText()));
        }
        if (match(TokenType.DOUBLENUMBER)){
            return new ValueExpression(Double.parseDouble(current.getText()));
        }
        if (match(TokenType.LPAREN)){
            final Expression result = expression();
            match(TokenType.RPAREN);
            return result;
        }
        if (match(TokenType.TEXT)){
            return new ValueExpression(current.getText());
        }
        if (match(TokenType.TRUE)){
            return new ValueExpression(true);
        }
        if (match(TokenType.FALSE)){
            return new ValueExpression(false);
        }
        if (current.getType() == TokenType.WORD && get(1).getType() == TokenType.LPAREN){
            return function();
        }
        if (match(TokenType.WORD)){
            return new VariableExpression(current.getText());
        }
        throw new RuntimeException("Помилка");
    }
    
    private String consume(TokenType type){
        Token token = get(0);
        if(type != token.getType())return "";
        pos++;
        return token.getText();
    }
    
    private boolean match(TokenType type){
        Token token = get(0);
        if(type != token.getType())return false;
        pos++;
        return true;
    }
    
    private Token get(int relativePosition){
        int position = pos + relativePosition;
        if (position >= size) return EOF;
        return tokens.get(position);
    }
    
}
