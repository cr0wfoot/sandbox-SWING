/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trainings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Alex
 */
public class ButtonEditor extends DefaultCellEditor {
  protected JButton button;
  private String    label;
  private boolean   isPushed;
  
  public ButtonEditor(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }
  
  @Override
  public Component getTableCellEditorComponent(JTable table, Object value,
                   boolean isSelected, int row, int column) {
    /*if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else{
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }*/
    label = (value ==null) ? "" : value.toString();
    button = ((JButtonTableExample.Tab)table).getB().get(row);
    button.setText( label );
    isPushed = true;
    return button;
  }
  
  @Override
  public Object getCellEditorValue() {
    if (isPushed)  {
      //
      JOptionPane.showMessageDialog(button ,label + ": Ouch!");
      // System.out.println(label + ": Ouch!");
    }
    isPushed = false;
    return new String( label ) ;
  }
    
  @Override
  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }
  
  @Override
  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}

