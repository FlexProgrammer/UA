/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author FleX
 */
public class Functions {

    private static final Map<String, Function> functions;
    
    static{
        functions = new HashMap<>();
    }
    
    public static boolean isExist(String key){
        return functions.containsKey(key);
    }
    
    public static Function get(String key){
        if (!isExist(key))throw new RuntimeException("Unknown function " + key);
        return functions.get(key);
    }
    
    public static void set(String name, Function function){
        functions.put(name, function);
    }
    
}
