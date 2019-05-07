package com.example.swing.sophiademo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TableRenderer {
    
    private JTable table;

    public TableRenderer(JTable table) {
        this.table = table;
    }
    
    public void setColumnsMaxPrefWidth(Integer...width) {
        int inc = 0;
        for(Integer w : width) {
            if(w != null) {
                table.getColumnModel().getColumn(inc).setPreferredWidth(w);
                table.getColumnModel().getColumn(inc).setMaxWidth(w);
            }
            inc++;
        }
    }
    
    public void setRowHeight(int height) {
        table.setRowHeight(height);
    }
    
    public void setFont(Font font) {
        table.setFont(font);
    }
    
    public void setCellEditorAndRenderer(int column, TableCellEditor editor, DefaultTableCellRenderer renderer) {
        table.getColumnModel().getColumn(column).setCellRenderer(renderer);
        table.getColumnModel().getColumn(column).setCellEditor(editor);
    }
    
    public void setColumnsAutoContentWidth() {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int prefWidth = 0;
        JTableHeader th = table.getTableHeader();
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            int prefWidthMax = 0;
            for (int j = 0; j <table.getRowCount(); j++) {
                if(table.getModel().getValueAt(j, i) == null)
                    continue;
                String s = table.getModel().getValueAt(j, i).toString();    
                prefWidth = 
                Math.round(
                    (float) th.getFontMetrics(
                        th.getFont()).getStringBounds(s,
                        th.getGraphics()
                    ).getWidth()
                );
                if ( prefWidth > prefWidthMax ) prefWidthMax = prefWidth;
            }
            column.setPreferredWidth(prefWidthMax + 10);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        }
    }

    /**
     * Get the value of sophiademo
     *
     * @return the value of sophiademo
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Set the value of sophiademo
     *
     * @param table new value of sophiademo
     */
    public void setTable(JTable table) {
        this.table = table;
    }
    
}
