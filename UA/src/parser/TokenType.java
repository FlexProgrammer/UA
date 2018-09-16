/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

/**
 *
 * @author FleX
 */
public enum TokenType {
    
    DECLARATION,
    
    DOUBLENUMBER,
    INTEGERNUMBER,
    
    WORD,
    TEXT,
    
    INPUT,
    PRINT,
    PRINTLN,
    IF,
    ELSE,
    WHILE,
    FOR,
    BREAK,
    CONTINUE,
    RETURN,
    SWITCH,
    CASE,
    DEFAULT,
    
    PLUS,
    PLUSPLUS,
    PLUSEQ,
    MINUS,
    MINUSMINUS,
    MINUSEQ,
    STAR,
    STAREQ,
    SLASH,
    SLASHEQ,
    EQ,
    EQEQ,
    LT,//<
    LTEQ,
    GT,//>
    GTEQ,
    NOT,//!
    NOTEQ,
    DOT,
    
    BAR,//|
    BARBAR,//||
    AMP,//&
    AMPAMP,//&&
    TERNARY,
    
    TRUE,
    FALSE,
    
    LPAREN,// (
    RPAREN,// )
    LBRACE,// {
    RBRACE,// }
    LBRAKET,// [
    RBRAKET,// ]
    COMMA,
    COLON,//:
    SEMICOLON,// ;
    
    EOF
}
