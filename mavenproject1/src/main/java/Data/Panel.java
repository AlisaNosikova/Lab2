/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class Panel extends JPanel{
             JPanel panel = new JPanel(new GridLayout(3, 1));
             JTextField text1 = new JTextField();
             public Panel(){
             text1.setText("dd");
              panel.add(text1);
              JOptionPane.showMessageDialog(null, panel, "Клиенты сервиса для телефонов",JOptionPane.PLAIN_MESSAGE);
    }
}
