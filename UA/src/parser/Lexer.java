/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FleX
 */
public class Lexer {
    
    private static final String OPERATOR_CHARS = "+-*/()[]{}<>=!/&|;,.?";
    private static final Map<String, TokenType> OPERATORS;
    static{
        OPERATORS = new HashMap<>();
        OPERATORS.put("+", TokenType.PLUS);
        OPERATORS.put("++", TokenType.PLUSPLUS);
        OPERATORS.put("-", TokenType.MINUS);
        OPERATORS.put("--", TokenType.MINUSMINUS);
        OPERATORS.put("*", TokenType.STAR);
        OPERATORS.put("/", TokenType.SLASH);
        OPERATORS.put("(", TokenType.LPAREN);
        OPERATORS.put(")", TokenType.RPAREN);
        OPERATORS.put("[", TokenType.LBRAKET);
        OPERATORS.put("]", TokenType.RBRAKET);
        OPERATORS.put("{", TokenType.LBRACE);
        OPERATORS.put("}", TokenType.RBRACE);
        OPERATORS.put("=", TokenType.EQ);
        OPERATORS.put(",", TokenType.COMMA);
        OPERATORS.put(".", TokenType.DOT);
        OPERATORS.put(";", TokenType.SEMICOLON);
        OPERATORS.put("?", TokenType.TERNARY);
        
        OPERATORS.put("+=", TokenType.PLUSEQ);
        OPERATORS.put("-=", TokenType.MINUSEQ);
        OPERATORS.put("*=", TokenType.STAREQ);
        OPERATORS.put("/=", TokenType.SLASHEQ);
        
        OPERATORS.put("==", TokenType.EQEQ);
        OPERATORS.put("<", TokenType.LT);
        OPERATORS.put("<=", TokenType.LTEQ);
        OPERATORS.put(">", TokenType.GT);
        OPERATORS.put(">=", TokenType.GTEQ);
        OPERATORS.put("!", TokenType.NOT);
        OPERATORS.put("!=", TokenType.NOTEQ);
        
        OPERATORS.put("&", TokenType.AMP);
        OPERATORS.put("&&", TokenType.AMPAMP);
        OPERATORS.put("|", TokenType.BAR);
        OPERATORS.put("||", TokenType.BARBAR);
    }
    private final String input;
    private final int length;
    
    private final List<Token> tokens;
    
    private int pos;
    
    public Lexer(String input) {
        this.input = input;
        length = input.length();
        
        tokens = new ArrayList<>();
    }
    
    public List<Token> tokenize(){
        while(pos != length){
            final char current = peek(0);
            if (Character.isDigit(current)) tokenizeNumber();
            else if (Character.isLetter(current)) tokenizeWord();
            else if(OPERATOR_CHARS.indexOf(current) != -1) {
                tokenizeOperator();
            }else if (current == '"'){
                tokenizeText();
            }
            else {
                next();
            }
        }
        
        return tokens;
    }
    
    private void tokenizeNumber(){
        char current = peek(0);
        final StringBuilder buffer = new StringBuilder();
        boolean isDouble = false;
        while(true){
            if (current == '.'){
                if (Character.isLetter(peek(1))){
                    if(isDouble) addToken(TokenType.DOUBLENUMBER, buffer.toString());
                    else addToken(TokenType.INTEGERNUMBER, buffer.toString());
                    addToken(TokenType.DOT);
                    pos++;
                    return;
                }
                isDouble = true;
                if (buffer.indexOf(".") != -1)throw new RuntimeException("Invalid float number");
            }else if (!Character.isDigit(current)){
                break;
            }
            buffer.append(current);
            current = next();
        }
            if(isDouble) addToken(TokenType.DOUBLENUMBER, buffer.toString());
                    else addToken(TokenType.INTEGERNUMBER, buffer.toString());
    }
    
    private void tokenizeText(){
        char current = next();
        final StringBuilder buffer = new StringBuilder();
        while(true){
            if (current == '\\'){
                current = next();
                switch(current){
                    case '"': current = next(); buffer.append('"');continue;
                    case 'n': current = next(); buffer.append('\n');continue;
                    case 't': current = next(); buffer.append('\t');continue;
                    case '\\': current = next(); buffer.append('\\');continue;
                    default:throw new RuntimeException("Excessive symbol \\");
                }
            }
            if (current == '\0'){
                throw new RuntimeException("Expected symbol \"");
            }
            if (current == '"'){
                break;
            }
            buffer.append(current);
            current = next();
        }
        next();// skip closing "
        addToken(TokenType.TEXT, buffer.toString());
    }
    
    private void tokenizeOperator(){
        char current = peek(0);
        if (current == '/'){
            if(peek(1) == '/'){
                next();
                next();
                tokenizeComment();
                return;
            }else if(peek(1) == '*'){
                next();
                next();
                tokenizeMultilineComment();
                return;
            }
        }
        
        final StringBuilder buffer = new StringBuilder();
        while(true){
            final String text = buffer.toString();
            if (!OPERATORS.containsKey(text + current) && !text.isEmpty()){
                addToken(OPERATORS.get(text));
                return;
            }
            buffer.append(current);
            current = next();
        }
    }
    
    private void tokenizeComment(){
        char current = peek(0);
        while("\r\n\0".indexOf(current) == -1){
            current = next();
        }
    }
    
    private void tokenizeMultilineComment(){
        char current = peek(0);
        while(true){
            if (current == '\0')throw new RuntimeException("Missing closing tag");
            if (current == '*' && peek(1) == '/'){
                break;
            }
            current = next();
        }
        next();
        next();
    }
    
    private void tokenizeWord(){
        char current = peek(0);
        final StringBuilder buffer = new StringBuilder();
        while(true){
            if (!Character.isLetterOrDigit(current) && current != '_'){
                break;
            }
            buffer.append(current);
            current = next();
        }
        switch (buffer.toString()) {
            case "вивести":
                addToken(TokenType.PRINT);
                break;
            case "вивестинс":
                addToken(TokenType.PRINTLN);
                break;
            case "якщо":
                addToken(TokenType.IF);
                break;
            case "інакше":
                addToken(TokenType.ELSE);
                break;
            case "поки":
                addToken(TokenType.WHILE);
                break;
            case "цикл":
                addToken(TokenType.FOR);
                break;
            case "вийти":
                addToken(TokenType.BREAK);
                break;
            case "продовжити":
                addToken(TokenType.CONTINUE);
                break;
            case "повернути":
                addToken(TokenType.RETURN);
                break;
            case "ввести":
                addToken(TokenType.INPUT, "ввести");
                break;
            case "ввестиСтрічку":
                addToken(TokenType.INPUT, "ввестиСтрічку");
                break;
            case "ввестиДріб":
                addToken(TokenType.INPUT, "ввестиДріб");
                break;
            case "ввестиЧисло":
                addToken(TokenType.INPUT, "ввестиЧисло");
                break;
            case "ввестиСлово":
                addToken(TokenType.INPUT, "ввестиСлово");
                break;
            case "правда":
               addToken(TokenType.TRUE);
               break;
            case "брехня":
               addToken(TokenType.FALSE);
               break;
            case "ціле":
               addToken(TokenType.DECLARATION, "int");
               break;
            case "дріб":
               addToken(TokenType.DECLARATION, "double");
               break;
            case "стрічка":
               addToken(TokenType.DECLARATION, "string");
               break;
            case "логічний":
               addToken(TokenType.DECLARATION, "boolean");
               break;
            case "пуста":
               addToken(TokenType.DECLARATION, "void");
               break;
            case "зрівняти":
               addToken(TokenType.SWITCH);
               break;
            case "варіант":
               addToken(TokenType.CASE);
               break;
            case "замовчування":
               addToken(TokenType.DEFAULT);
               break;
            default:
                addToken(TokenType.WORD, buffer.toString());
                break;
        }
    }
    
    private char next(){
        pos++;
        return peek(0);
    }
    
    private char peek(int relativePosition){
        int position = pos + relativePosition;
        if(position >= length) return '\0';
        return input.charAt(position);
    }
    
    private void addToken(TokenType type){
        addToken(type, "");
    }
    
    private void addToken(TokenType type, String text){
        tokens.add(new Token(text, type));
    }
}
