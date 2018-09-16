/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua01;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author FleX
 */
public class GUI extends JFrame {
    
    private final JButton button = new JButton("Запустити");
    private final JTextArea input = new JTextArea(200, 15);
    public JTextArea console = new JTextArea(200, 5);
    public GUI( ) throws HeadlessException {
        super("UA IDE");
        
    }
    
}
