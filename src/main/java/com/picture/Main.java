package com.picture;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class Main {

    //Массив содержащий заголоки таблицы
    Object[] headers = { "Name", "Surname", "Telephone" };

    //Массив содержащий информацию для таблицы
    Object[][] data = {
            { "1", "Smith", "1112221" },
            { "2", "Black", "2221111" },
            { "3", "White", "3334444" },
            { "12", "Black", "2235111" },
            { "11", "Black", "2221511" },
            { "10", "Black", "2221111" },
            { "7", "Red", "2121111" },
            { "8", "Green", "2321111" },
    };

    static JPanel p = new JPanel();

    //Объект таблицы
    static JTable jTabPeople;

    static JLabel lab;

    Main() {
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
        TableColumnModel columnModel = jTabPeople.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(210);
        columnModel.getColumn(2).setPreferredWidth(70);
        jTabPeople.setShowGrid(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setVerticalAlignment(SwingConstants.TOP);
        jTabPeople.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        jTabPeople.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jTabPeople.setEnabled(false);
        lab = new JLabel("<html>Nazionalniy Zapovidnik<br>Sophia of Kyiv</html>");

        JTable jTabPeople1 = new JTable(data, headers);
        jTabPeople1.setRowHeight(30);
        TableColumnModel columnModel1 = jTabPeople1.getColumnModel();
        columnModel1.getColumn(0).setPreferredWidth(20);
        columnModel1.getColumn(1).setPreferredWidth(210);
        columnModel1.getColumn(2).setPreferredWidth(70);
        jTabPeople1.setShowGrid(false);
        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment( SwingConstants.CENTER );
        DefaultTableCellRenderer rightRenderer1 = new DefaultTableCellRenderer();
        rightRenderer1.setVerticalAlignment(SwingConstants.TOP);
        jTabPeople1.getColumnModel().getColumn(1).setCellRenderer(rightRenderer1);
        jTabPeople1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer1 );
        jTabPeople1.setEnabled(false);

        p.add(jTabPeople1);
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
                new Main();
                jTabPeople.setValueAt("new", 2, 0);
            }
        });


    }
}
