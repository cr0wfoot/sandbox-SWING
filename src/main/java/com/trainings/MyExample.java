package com.trainings;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
 
public class MyExample extends JFrame {
 
    static int i = 0;
 
    public MyExample() {
 
        super("Тестовое окно");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        ArrayList<MyBean> beans = new ArrayList<MyBean>();
        ArrayList<JButton> btns = new ArrayList<JButton>();
 
        for (int i = 0; i < 30; i++) {
            final int tmp = i;
            JButton btn = new JButton();
            btn.setText("+");
            btns.add(btn);
            beans.add(new MyBean("Имя " + i, "Размер " + i, "Описание " + i, "+"));
        }
 
        TableModel model = new MyTableModel(beans, btns);
        JTable table = new JTable(model);
        table.getColumn("+").setCellEditor(new CellEditor(3));
	table.getColumn("+").setCellRenderer(new CellRender(3));
        //table.setValueAt("MYVAL", 1, 1);
 
        getContentPane().add(new JScrollPane(table));
 
        setPreferredSize(new Dimension(560, 420));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    public static void main(String[] args) {
        
        
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new MyExample();
            }
        });
    }
    
    private static final class CellEditor extends AbstractCellEditor implements TableCellEditor {
		
		private int buttonColumnIndex;
		
		public CellEditor(int buttonColumnIndex) {
			this.buttonColumnIndex = buttonColumnIndex;
		}
		
		@Override
		public Object getCellEditorValue() {
			return null;
		}
		
		
 
		@Override
		public Component getTableCellEditorComponent(final JTable table,
				Object value, boolean isSelected, final int row, int column) {
			
                    if (buttonColumnIndex == column) {
				JButton btn = ((MyTableModel)(table.getModel())).getButtonByRow(row);
                                if(btn.getActionListeners().length == 0)
                                btn.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        System.out.println("call ");
                                        ((MyTableModel)(table.getModel())).setValueAt("test", row, 0);
                                        table.repaint();
                                    }
                                });
				JLabel l = new JLabel();
                                l.add(btn);
                                l.add(btn);
                                l.setText("hi");
				return l;
			} else
				return new JLabel(value.toString());
		}
	}
 
    private static final class CellRender extends DefaultTableCellRenderer {
		
		private int buttonColumnIndex;
		
		public CellRender(int buttonColumnIndex) {
			this.buttonColumnIndex = buttonColumnIndex;
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (buttonColumnIndex == column) {
				JButton btn = ((MyTableModel)(table.getModel())).getButtonByRow(row);
                                JLabel l = new JLabel();
                                l.add(btn);
                                l.add(btn);
                                l.setText("hi");
				return l;
			}
			else  return super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
					row, column);
		}
	}
 
    
}
