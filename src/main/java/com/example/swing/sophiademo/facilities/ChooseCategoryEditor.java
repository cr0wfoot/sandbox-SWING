/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.swing.sophiademo.facilities;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

public class ChooseCategoryEditor extends AbstractCellEditor implements TableCellEditor {

        private ChooseCategoryPane chooseCategoryPane;

        public ChooseCategoryEditor() {
            chooseCategoryPane = new ChooseCategoryPane();
            chooseCategoryPane.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            stopCellEditing();
                        }
                    });
                }
            });
        }

        @Override
        public Object getCellEditorValue() {
            return chooseCategoryPane.getState();
        }

        @Override
        public boolean isCellEditable(EventObject e) {
            return true;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                chooseCategoryPane.setBackground(table.getSelectionBackground());
            } else {
                chooseCategoryPane.setBackground(table.getBackground());
            }
            
            return chooseCategoryPane;
        }
    }

