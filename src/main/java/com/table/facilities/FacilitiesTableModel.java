package com.table.facilities;

import com.table.BillContainer;
import com.table.Facility;
import com.table.TableRenderer;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class FacilitiesTableModel extends AbstractTableModel {

        private Class<?> servingClass = Facility.class;
        private List<Facility> facilities;
        private BillContainer billContainer;
        private List<Tuple> columnsConfig;
        
        private class Tuple {
            private Class<?> type = servingClass;
            private String title;
            private String method;
            Tuple(String title, String method) {
                this.title = title;
                this.method = method;
            }
            String getTitle() {
                return this.title;
            }
            Class<?> getReturnedClass() {
                try {
                    return method == null ? Object.class : type.getDeclaredMethod(method).getReturnType();
                } catch (Exception ex) {
                    return Object.class;
                }
            }
            Object getMethodResult(Facility obj) {
                System.out.println(type);
                try {
                    return method == null ? null : type.getDeclaredMethod(method).invoke(obj);
                } catch (Exception ex) {
                    return null;
                }
            }
        }
        
        public FacilitiesTableModel() {
            columnsConfig = new ArrayList<Tuple>();
            facilities = new ArrayList<Facility>();
            columnsConfig.add(new Tuple("№", "getId"));
            columnsConfig.add(new Tuple("<html><div style='color:blue; font-size:15px;'>Послуга</div>", "getName"));
            columnsConfig.add(new Tuple("Категорії", null));
            columnsConfig.add(new Tuple("Ціна", "getPrice"));
            columnsConfig.add(new Tuple("Кількість", "getCount"));
            columnsConfig.add(new Tuple("+/-", null));
        }
        
        public FacilitiesTableModel(List<Facility> facilities) {
            this();
            this.facilities= facilities;
        }
        
        public void setBillContainer(BillContainer billContainer) {
            this.billContainer = billContainer;
        }
        
        public void initTable(JTable table) {
            TableRenderer tableRenderer = new TableRenderer(table);
            tableRenderer.setColumnsMaxPrefWidth(30, null, 160, 60, 70, 80);
            tableRenderer.setRowHeight(new IncDecCountRenderer().getTableCellRendererComponent(table, null, true, true, 0, 0).getPreferredSize().height);
            tableRenderer.setCellEditorAndRenderer(5, new IncDecCountEditor(table), new IncDecCountRenderer());
            tableRenderer.setCellEditorAndRenderer(2, new ChooseCategoryEditor(), new ChooseCategoryRenderer());
            tableRenderer.setFont(new Font("Verdana", Font.BOLD, 12));
        }
        
        @Override
        public String getColumnName(int columnIndex) {
            return columnsConfig.get(columnIndex).getTitle();
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnsConfig.get(columnIndex).getReturnedClass();
        }

        @Override
        public final int getRowCount() {
            return facilities.size();
        }

        @Override
        public int getColumnCount() {
            return columnsConfig.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Facility obj = facilities.get(rowIndex);
            return columnsConfig.get(columnIndex).getMethodResult(obj);
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            
            if (columnIndex == 5) {
                Facility value = facilities.get(rowIndex);
                if ("accept".equals(aValue)) {
                    value.setCount(value.getCount() + 1);
                } else {
                    if(value.getCount() > 0)
                        value.setCount(value.getCount() - 1);
                }
                fireTableCellUpdated(rowIndex, columnIndex);
            }
            
            if(columnIndex == 2) {
                billContainer.add(facilities.get(rowIndex).clone());
            }
            
        }
        
        public final void add(Facility value) {
            int startIndex = getRowCount();
            facilities.add(value);
            fireTableRowsInserted(startIndex, getRowCount() - 1);
        }

        public void remove(Facility value) {
            int startIndex = facilities.indexOf(value);
            facilities.remove(value);
            fireTableRowsInserted(startIndex, startIndex);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return 
                columnIndex == 5 || 
                columnIndex == 2;
        }
        
    }