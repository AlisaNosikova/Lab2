/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Data.Manager;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author User
 */
public class Panel extends JPanel{
    private Manager manager;
    private JButton exportButton;
    private JButton calculateButton;
    private JButton importButton;
    private JButton exitButton;
    private JFrame frame;
    private boolean isAnySelected = false;
    private ButtonGroup buttonGroup;
  
    public Panel(JFrame frame){
       
       this.frame = frame;
       this.manager = new Manager();
       this.exportButton = new JButton("Export");
       this.calculateButton = new JButton("Calculate");
       this.importButton = new JButton("Import");
       this.exitButton = new JButton("Exit");
       this.buttonGroup = new ButtonGroup();
       setLayout(new GridBagLayout());
       addButtons();
       importButton.addActionListener(new ImportActionListener());
       calculateButton.addActionListener(new CalculateActionListener());
       exportButton.addActionListener(new ExportActionListener());
       exitButton.addActionListener(new ExitActionListener());
    }
    public class ImportActionListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Выбор директории");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx");
    fileChooser.setFileFilter(filter);
    int result = fileChooser.showOpenDialog(Panel.this);
    File selectedFile = fileChooser.getSelectedFile();

    if (selectedFile != null && selectedFile.exists() && selectedFile.getName().matches(".*\\.xlsx")) {
        if (result == JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(Panel.this, fileChooser.getSelectedFile());
            chooseVar(fileChooser);
        }
    } else {
        if (selectedFile != null && !selectedFile.exists()) {
            JOptionPane.showMessageDialog(null, "Файл не существует", "Предупреждение", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Выберите файл с расширением xlsx", "Предупреждение", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    }
   public class ExportActionListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выбор директории");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if( isAnySelected == false){
            JOptionPane.showMessageDialog(null, "Нечего вписывать", "Предупреждение", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String name = JOptionPane.showInputDialog(null, "Введите имя файла:");
        if (name.trim().isEmpty()){
          JOptionPane.showMessageDialog(null, "Напишите имя", "Предупреждение", JOptionPane.ERROR_MESSAGE);
          return;
      }
        String fileName = name + ".xlsx";
        File selectedFileForExport;
        int result = fileChooser.showSaveDialog(Panel.this);
        if (result == JFileChooser.APPROVE_OPTION) {
           selectedFileForExport = new File(fileChooser.getSelectedFile(), fileName);   
                try {
                    JOptionPane.showMessageDialog(Panel.this, selectedFileForExport);
                    manager.goToWriter(selectedFileForExport.getPath().replace("\\", "\\\\"));
                } catch (FileNotFoundException exx) {
                   JOptionPane.showMessageDialog(null, "Нет доступа к файлу", "Предупреждение", JOptionPane.ERROR_MESSAGE);
                } catch(NullPointerException en){
                    JOptionPane.showMessageDialog(null, "Файл не может быть записан. Данных для экспорта нет", "Предупреждение", JOptionPane.QUESTION_MESSAGE);
                } catch (IOException ex) {
                Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            JOptionPane.showMessageDialog(null, "Выберите директорию и введите имя файла для сохранения", "Предупреждение", JOptionPane.ERROR_MESSAGE);
        }
        
}catch(NullPointerException ex){
     JOptionPane.showMessageDialog(null, "Вы отменили экспорт!", "Предупреждение", JOptionPane.ERROR_MESSAGE);
}
   }
   }   
  public class CalculateActionListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
      isAnySelected = false;
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(4, 1));
        createNewCheckBox("geometricMeans",newPanel);
        createNewCheckBox("means",newPanel);
        createNewCheckBox("maxValues",newPanel);
        createNewCheckBox("minValues",newPanel);
        createNewCheckBox("ranges",newPanel);
        createNewCheckBox("StandartDeviations",newPanel);
        createNewCheckBox("CofVars",newPanel);
        createNewCheckBox("Variances",newPanel);
        createNewCheckBox("confidenceInterval",newPanel);
        createNewCheckBox("covariance",newPanel);
        createNewCheckBox("numberOfElements",newPanel);
        ButtonGroup group = new ButtonGroup();
        
        JOptionPane.showMessageDialog(null, newPanel, "Выберите показатели, которые необходимо подсчитать", JOptionPane.PLAIN_MESSAGE);
      try {
         
          checkCheckBoxSelection( newPanel);
          if ( isAnySelected == false){
              JOptionPane.showMessageDialog(null, "Статистические показатели не выбраны!", "Предупреждение", JOptionPane.ERROR_MESSAGE);  
          }
      } catch (IOException ex) {
          Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch(NullPointerException en){
                    JOptionPane.showMessageDialog(null, "Данных для подсчета нет!", "Предупреждение", JOptionPane.QUESTION_MESSAGE);
                }
      }
  }
   public class ExitActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          frame.dispose();
        }

        
        
    }
  public boolean checkCheckBoxSelection(JPanel panel) throws IOException {
   
    Component[] components = panel.getComponents(); 
    for (Component component : components) {
            JCheckBox checkBox = (JCheckBox) component;
            if (checkBox.isSelected()) {
                manager.sendToCalculator(checkBox.getText());
                isAnySelected = true;
            }
        
    }
    return false;
  
  }
  public void createNewCheckBox(String name, JPanel newPanel){
      JCheckBox checkBox = new JCheckBox(name);
      newPanel.add(checkBox);
  }
  
    public void addCompToPanel(JPanel panel){
        JLabel label = new JLabel("Выбор варианта");
        
    }
    public void addButtons(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(exportButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(calculateButton, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        add(importButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 5;
        add(exitButton, gbc);
    }
    public void chooseVar(JFileChooser fileChooser){
        try{
        JPanel panel = new JPanel();
        JLabel choiceLabel = new JLabel("Как выбрать вариант?");
        JRadioButton bWord = new JRadioButton("Написать вариант");
        JRadioButton bNum = new JRadioButton("Ввести номер");
        panel.add(choiceLabel);
        panel.add(bWord);
        panel.add(bNum);
        buttonGroup.add(bWord);
        buttonGroup.add(bNum);
        JPanel panelText = new JPanel();
        JTextField variant = new JTextField(10);
        panelText.add(variant);
        JOptionPane.showMessageDialog(null, panel, "Выберите способ для выбора", JOptionPane.QUESTION_MESSAGE);

        if (bWord.isSelected()==true){
            JOptionPane.showMessageDialog(null, panelText, "Впишите название варианта", JOptionPane.PLAIN_MESSAGE);
            try {
                if (variant.getText().isEmpty()){
                      JOptionPane.showMessageDialog(null, "Вы ничего не ввели", "Предупреждение", JOptionPane.ERROR_MESSAGE);
                return;
        }
                manager.goToReaderToGetIndex(fileChooser.getSelectedFile().getPath(),variant.getText());
            } catch (IOException ex) {
                Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
         else if (bNum.isSelected()==true){
             
            JOptionPane.showMessageDialog(null, panelText, "Впишите номер варианта", JOptionPane.PLAIN_MESSAGE);
            try {
                 if (variant.getText().isEmpty()){
                      JOptionPane.showMessageDialog(null, "Вы ничего не ввели", "Предупреждение", JOptionPane.ERROR_MESSAGE);
                return;
        }
                 if(Integer.parseInt(variant.getText())==0){
                            JOptionPane.showMessageDialog(null, "Такого варианта нет!", "Предупреждение", JOptionPane.ERROR_MESSAGE);
                 }
                 else{
                manager.goToReader(fileChooser.getSelectedFile().getPath(),Integer.parseInt(variant.getText()));
                 }
            } catch (IOException ex) {
                Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
         else{
             JOptionPane.showMessageDialog(null, "Выберите вариант!", "Предупреждение", JOptionPane.ERROR_MESSAGE);
            
        }  
    }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null, "Такого варианта нет!", "Предупреждение", JOptionPane.ERROR_MESSAGE);
                
            }
    }
    
}
