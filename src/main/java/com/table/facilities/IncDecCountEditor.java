/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.table.facilities;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

public class IncDecCountEditor extends AbstractCellEditor implements TableCellEditor {

        private IncDecCountPane acceptRejectPane;

        public IncDecCountEditor(final JTable table) {
            acceptRejectPane = new IncDecCountPane();
            acceptRejectPane.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            stopCellEditing();
                            table.repaint();
                        }
                    });
                }
            });
        }

        @Override
        public Object getCellEditorValue() {
            return acceptRejectPane.getState();
        }

        @Override
        public boolean isCellEditable(EventObject e) {
            return true;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                acceptRejectPane.setBackground(table.getSelectionBackground());
            } else {
                acceptRejectPane.setBackground(table.getBackground());
            }
            
            return acceptRejectPane;
        }
    }

