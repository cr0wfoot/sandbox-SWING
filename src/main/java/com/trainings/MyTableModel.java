/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trainings;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Alex
 */
public class MyTableModel extends DefaultTableModel {
 
        private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
        private List<JButton> b = new LinkedList<JButton>();
        
 
        private List<MyBean> beans;
 
        public MyTableModel(List<MyBean> beans, List<JButton> btns) {
            super();
            this.beans = beans;
            this.b = btns;
        }
 
        public JButton getButtonByRow(int row) {
            return b.get(row);
        }
        
        public void addTableModelListener(TableModelListener listener) {
            listeners.add(listener);
        }
 
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }
 
        public int getColumnCount() {
            return 4;
        }
        
        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
            case 0:
                return "Имя";
            case 1:
                return "Размер";
            case 2:
                return "Описание";
            case 3:
                return "+";
            }
            return "";
        }
 
        public int getRowCount() {
            return beans != null ? beans.size() : 0;
        }
 
        public Object getValueAt(int rowIndex, int columnIndex) {
            MyBean bean = beans.get(rowIndex);
            switch (columnIndex) {
            case 0:
                return bean.getName();
            case 1:
                return bean.getSize();
            case 2:
                return bean.getDescription();
            case 3:
                return bean.getBtn();
            }
            return "";
        }
 
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if(columnIndex == 3)
                return true;
            return false;
        }
 
        public void removeTableModelListener(TableModelListener listener) {
            listeners.remove(listener);
        }
 
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            if(value == null) return;
            beans.get(rowIndex).setName(getValueAt(rowIndex, columnIndex).toString() + value.toString());
        }
        
        public void update() {
            super.fireTableDataChanged();
        }
 
    }
