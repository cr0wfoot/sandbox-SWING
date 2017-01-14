package com.picture;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.*;


public class Run2 extends Box{

    public Run2(){
        super(BoxLayout.Y_AXIS);

        JTable table = new JTable(5,5);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0,0));//Get rid of cell spacing

        //Set your own renderer.  You'll have to set this for Number and Boolean too if you're using those
        CustomRenderer cr = new CustomRenderer(table.getDefaultRenderer(Object.class), Color.red, Color.orange, Color.pink, Color.magenta);
        table.setDefaultRenderer(Object.class, cr);

        add(table);
    }

    //Custom renderer - do what the natural renderer would do, just add a border
    public static class CustomRenderer implements TableCellRenderer{
        TableCellRenderer render;
        Border b;
        Border b1;
        Border b2;
        Border b4;


        public CustomRenderer(TableCellRenderer r, Color top, Color left,Color bottom, Color right){
            render = r;

            //It looks funky to have a different color on each side - but this is what you asked
            //You can comment out borders if you want too. (example try commenting out top and left borders)
            //b = BorderFactory.createCompoundBorder();
            //b1 = BorderFactory.createCompoundBorder();

            b1 = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(2,0,0,0,Color.black));
            b4 = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,2,0,0,Color.black));
            b2 = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,0,2,0,Color.black));
            b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,0,0,2,Color.black));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                                                       Object value, boolean isSelected, boolean hasFocus, int row,
                                                       int column) {
            JComponent result = (JComponent)render.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if(column != 0 && row == 0) {
                result.setBorder(b1);
            }

            if(column != 0 && row == 4) {
                result.setBorder(b2);
            }
            if(column == 0) {
                result.setBorder(b);
            }
            if(column == 4) {
                result.setBorder(b4);
            }

            return result;
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Run2());
        frame.validate();
        frame.pack();
        frame.setVisible(true);
    }

}