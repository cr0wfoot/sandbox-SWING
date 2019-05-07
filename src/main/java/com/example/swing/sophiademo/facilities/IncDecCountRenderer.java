/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.swing.sophiademo.facilities;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class IncDecCountRenderer extends DefaultTableCellRenderer {

        private IncDecCountPane acceptRejectPane;

        public IncDecCountRenderer() {
            acceptRejectPane = new IncDecCountPane();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                acceptRejectPane.setBackground(table.getSelectionBackground());
            } else {
                acceptRejectPane.setBackground(table.getBackground());
            }
            return acceptRejectPane;
        }
}

