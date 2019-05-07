/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.swing.sophiademo.facilities;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ChooseCategoryRenderer extends DefaultTableCellRenderer {

        private ChooseCategoryPane chooseCategoryPane;

        public ChooseCategoryRenderer() {
            chooseCategoryPane = new ChooseCategoryPane();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                chooseCategoryPane.setBackground(table.getSelectionBackground());
            } else {
                chooseCategoryPane.setBackground(table.getBackground());
            }
            return chooseCategoryPane;
        }
}

