package com.example.swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

public class ButtonAsTableCellExample extends JFrame {

    private ButtonAsTableCellExample() {
        super("JButtonTable Example");

        DefaultTableModel dm = new DefaultTableModel() {
            public void setValueAt(Object value, int rowIndex, int columnIndex) {
                System.out.println("hg");
            }
        };
        dm.setDataVector(new Object[][]{{"button 1", "foo"},
                        {"button 2", "bar"}},
                new Object[]{"Button", "String"});

        JTable table = new Tab(dm);
        table.getColumn("Button").setCellRenderer(new ButtonRenderer());
        table.getColumn("Button").setCellEditor(new ButtonEditor(new JCheckBox()));
        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll);
        setSize(400, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        ButtonAsTableCellExample frame = new ButtonAsTableCellExample();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static class Tab extends JTable {
        private List<JButton> b = new LinkedList<>();

        Tab(DefaultTableModel dm) {
            super(dm);
            JButton bt = new JButton();
            bt.addActionListener(e -> System.out.println(0));
            b.add(bt);
            bt = new JButton();
            bt.addActionListener(e -> System.out.println(1));
            b.add(bt);
        }

        List<JButton> getB() {
            return b;
        }
    }

    private static class ButtonEditor extends DefaultCellEditor {
        JButton button;
        private String label;
        private boolean isPushed;

        ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
//            if (isSelected) {
//                button.setForeground(sophiademo.getSelectionForeground());
//                button.setBackground(sophiademo.getSelectionBackground());
//            } else {
//                button.setForeground(sophiademo.getForeground());
//                button.setBackground(sophiademo.getBackground());
//            }
            label = (value == null) ? "" : value.toString();
            button = ((Tab) table).getB().get(row);
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                JOptionPane.showMessageDialog(button, label + ": Ouch!");
                // System.out.println(label + ": Ouch!");
            }
            isPushed = false;
            return new String(label);
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

    private static class ButtonRenderer extends JButton implements TableCellRenderer {

        ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }
}