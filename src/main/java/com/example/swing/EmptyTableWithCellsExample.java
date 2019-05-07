package com.example.swing;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class EmptyTableWithCellsExample {

    public static void main(String[] args) {
        final JFrame frame = new JFrame("TableExample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(createTable());
        frame.pack();
        SwingUtilities.invokeLater(() -> {
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    static JComponent createTable() {
        final JTable table = new JTable(9, 9) {
            private static final long serialVersionUID = 0;

            Color B = Color.BLACK;
            Color C = new Color(0, 0, 0, 0);
            final Border[][] borders = {
                    {new ZoneBorder(B, C, C, B), new ZoneBorder(B, C, C, C), new ZoneBorder(B, B, C, C)},
                    {new ZoneBorder(C, C, C, B), new ZoneBorder(C, C, C, C), new ZoneBorder(C, B, C, C)},
                    {new ZoneBorder(C, C, B, B), new ZoneBorder(C, C, B, C), new ZoneBorder(C, B, B, C)}
            };

            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component result = super.prepareRenderer(renderer, row, column);
                if (result instanceof JComponent && column == 2) {
                    ((JComponent) result).setBorder(borders[1][0]);
                }
                return result;
            }
        };
        table.setRowHeight(28);
        table.setGridColor(Color.BLACK);
        TableColumnModel tcm = table.getColumnModel();
        for (int c = 0; c < table.getColumnCount(); ++c) {
            TableColumn tc = tcm.getColumn(c);
            tc.setPreferredWidth(28);
        }
        JPanel inner = new JPanel(new GridLayout());
        inner.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        inner.add(table);
        return inner;
    }

    private static class ZoneBorder implements Border {

        private static final int WIDTH = 1;
        private Color colorN, colorE, colorS, colorW;

        ZoneBorder(Color colorN, Color colorE, Color colorS, Color colorW) {
            this.colorN = colorN;
            this.colorE = colorE;
            this.colorS = colorS;
            this.colorW = colorW;
        }

        public boolean isBorderOpaque() {
            return false;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(WIDTH, WIDTH, WIDTH, WIDTH);
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Color old = g.getColor();
            if (colorN != null) {
                g.setColor(colorN);
                g.fillRect(x, y, width, WIDTH);
            }
            if (colorE != null) {
                g.setColor(colorE);
                g.fillRect(x + width - WIDTH, y, WIDTH, height);
            }
            if (colorS != null) {
                g.setColor(colorS);
                g.fillRect(x, y + height - WIDTH, width, WIDTH);
            }
            if (colorW != null) {
                g.setColor(colorW);
                g.fillRect(x, y, WIDTH, height);
            }
            g.setColor(old);
        }
    }
}