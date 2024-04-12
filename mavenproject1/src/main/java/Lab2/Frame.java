/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab2;

import Data.Panel;
import Data.Panel;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
    public class Frame extends JFrame{
    JButton b;
    public Frame(String title){
        super(title);
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        Panel menu = new Panel();
        Container con= this.getContentPane(); // Создаем панель содержимого
        con.setLayout(new BorderLayout());
        con.add(menu, BorderLayout.CENTER);
        setVisible(true);
    }
    
}

