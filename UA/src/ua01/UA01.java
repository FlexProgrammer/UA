/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua01;

import Parser.Lexer;
import Parser.Parser;
import Parser.Token;
import ast.Statement;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
/**
 *
 * @author FleX
 */
public class UA01 {
    
    public static void main(String[] args) throws IOException {
        
        final String input = new String( Files.readAllBytes(Paths.get("programm.txt")), "UTF-8");
        final List<Token> tokens = new Lexer(input).tokenize();
//        for (Token token : tokens) {
//            System.out.println(token);
//        }
        final Statement programm = new Parser(tokens).parse();
        programm.execute();
    }
    
}
