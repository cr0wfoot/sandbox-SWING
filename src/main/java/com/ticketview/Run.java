package com.ticketview;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class Run {

    //Массив содержащий заголоки таблицы
    Object[] headers = { "", "Name", "Surname", "Telephone", "" };

    //Массив содержащий информацию для таблицы
    Object[][] data = {
            { "" , "", "Naz Zapov Sophia Kyiv", "", "" },
            { "" , "2", "Black", "2221111", "" },
            { "" , "3", "White", "3334444", "" },
            { "" , "12", "Black", "2235111", "" },
            { "" , "11", "Black", "2221511", "" },
            { "" , "10", "Black", "2221111", "" },
            { "" , "7", "Red", "2121111", "" },
            { "" , "8", "Green", "2321111", "" },
    };

    static JPanel p = new JPanel();

    //Объект таблицы
    static JTable jTabPeople;

    static JLabel lab;

    Run() {
        //Создаем новый контейнер JFrame
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JFrame jfrm = new JFrame("JTableExample");
        //Устанавливаем диспетчер компоновки
        jfrm.getContentPane().setLayout(new FlowLayout());
        //Устанавливаем размер окна
        jfrm.setSize(300, 170);
        //Устанавливаем завершение программы при закрытии окна
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Создаем новую таблицу на основе двумерного массива данных и заголовков
        jTabPeople = new JTable(data, headers);
        jTabPeople.setRowHeight(30);
        CustomRenderer cr = new CustomRenderer(jTabPeople.getDefaultRenderer(Object.class), Color.red, Color.orange, Color.pink, Color.magenta);
        jTabPeople.setDefaultRenderer(Object.class, cr);
        TableColumnModel columnModel = jTabPeople.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(20);
        columnModel.getColumn(2).setPreferredWidth(210);
        columnModel.getColumn(3).setPreferredWidth(70);
        columnModel.getColumn(4).setPreferredWidth(10);

        jTabPeople.setShowGrid(false);
        jTabPeople.setIntercellSpacing(new Dimension(0,0));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setVerticalAlignment(SwingConstants.TOP);
        //jTabPeople.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        //jTabPeople.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jTabPeople.setEnabled(false);
        lab = new JLabel("<html>Nazionalniy Zapovidnik<br>Sophia of Kyiv</html>");

        p.add(jTabPeople);
        p.setBorder(BorderFactory.createLineBorder(Color.black));
        ImageIcon water = new ImageIcon("water.gif");
        JButton b = new JButton(water);
        b.setPreferredSize(new Dimension(30,30));
        b.setBorderPainted(false);
        //b.setIcon(water);
        jfrm.getContentPane().add(p);
        jfrm.getContentPane().add(b);
        //b.addActionListener(e -> testAction());
        //Отображаем контейнер
        jfrm.setVisible(true);
    }

    private void testAction() {
        jTabPeople.setValueAt("444", 2, 0);
    }

    //Функция main, запускающаяся при старте приложения
    public static void main(String[] args) {
        //оздаем фрейм в потоке обработки событий
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Run();
                jTabPeople.setValueAt("new", 2, 0);
            }
        });


    }

    public static class CustomRenderer implements TableCellRenderer {
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
            result.setBackground(Color.white);

            if(column != 0 && row == 0) {
                result.setBorder(b1);
            }

            if(column != 0 && row == 7) {
                result.setBorder(b2);
            }
            if(column == 0) {
                result.setBorder(b);
                result.setBackground(Color.lightGray);
            }
            if(column == 4) {
                result.setBorder(b4);
            }

            return result;
        }

    }
}
