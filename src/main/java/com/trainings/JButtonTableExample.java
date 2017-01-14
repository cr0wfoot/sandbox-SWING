/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trainings;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Alex
 */
public class JButtonTableExample extends JFrame {
  
  public JButtonTableExample(){
    super( "JButtonTable Example" );
     
    DefaultTableModel dm = new DefaultTableModel() {
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            System.out.println("hg");
        }
    };
    dm.setDataVector(new Object[][]{{"button 1","foo"},
                                    {"button 2","bar"}},
                     new Object[]{"Button","String"});
                      
    JTable table = new Tab(dm);
    table.getColumn("Button").setCellRenderer(new ButtonRenderer());
    table.getColumn("Button").setCellEditor(new ButtonEditor(new JCheckBox()));
    JScrollPane scroll = new JScrollPane(table);
    getContentPane().add( scroll );
    setSize( 400, 100 );
    setVisible(true);
  }
  
  public static void main(String[] args) {
    JButtonTableExample frame = new JButtonTableExample();
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }
  
  public static class Tab extends JTable {
      private List<JButton> b = new LinkedList<JButton>();
      public Tab(DefaultTableModel dm) {
          super(dm);
          JButton bt = new JButton();
          bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(0);
            }
          });
          b.add(bt);
          bt = new JButton();
          bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(1);
            }
          });
          b.add(bt);
      }
      public List<JButton> getB() {
          return b;
      }
  }
}