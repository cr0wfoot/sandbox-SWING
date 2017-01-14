/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trainings;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
 
 
public class TableWithButtons {
 
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
				JButton btn = ((TableWithButton)table).getLsButtons().get(row);
				return btn;
			}
			else  return super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
					row, column);
		}
	}
 
	private static class TableWithButton extends JTable {
		
		private List<JButton> lsButtons = new LinkedList<JButton>();
		
		public TableWithButton(Object[][] values, String[] titles) {
			super(values, titles);
                        final TableWithButton inst = this;
			for(int index = 0; index < titles.length; index++) {
				JButton btn = new JButton();
                                final int row = index;
                                btn.addActionListener(new ActionListener() {
                                    
                                            @Override
                                            public void actionPerformed(ActionEvent e) {

                                                System.out.println(inst.getValueAt(row, 0));
                                            }
                                    });
				btn.setText(String.valueOf(index));
				lsButtons.add(btn);
			}
			
		}
 
		public List<JButton> getLsButtons() {
			return lsButtons;
		}
                
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
				JButton btn = ((TableWithButton)table).getLsButtons().get(row);
				return btn;
			} else
				return new JLabel(value.toString());
		}
	}
 
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
	
		
		Object[][] values = {{"1", "1"}, {"2", "2"}};
		String[] titles = {"Столбец 1", "Столбец 2"};
		JTable table = new TableWithButton(values, titles);
		
		table.getColumn("Столбец 2").setCellEditor(new CellEditor(1));
		table.getColumn("Столбец 2").setCellRenderer(new CellRender(1));
                
                

		
	
		
		frame.add(table);
		frame.pack();
		frame.setVisible(true);
	}
 
}