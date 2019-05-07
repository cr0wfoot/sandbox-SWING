/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.swing.sophiademo.facilities;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ChooseCategoryPane extends JPanel {

        private JButton common;
        private JButton child;
        private JButton free;
        private JButton custom;
        private String state;

        public ChooseCategoryPane() {
            setLayout(new GridBagLayout());
            common = new JButton("Зв");
            common.setActionCommand("com");
            child = new JButton("Д");
            child.setActionCommand("ch");
            free = new JButton("Б");
            free.setActionCommand("fr");
            custom = new JButton("і");
            custom.setActionCommand("cust");

            add(common);
            add(child);
            add(free);
            add(custom);

            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    state = e.getActionCommand();
                    System.out.println("State = " + state);
                }
            };

            common.addActionListener(listener);
            child.addActionListener(listener);
            free.addActionListener(listener);
            custom.addActionListener(listener);
        }

        public void addActionListener(ActionListener listener) {
            common.addActionListener(listener);
            child.addActionListener(listener);
            free.addActionListener(listener);
            custom.addActionListener(listener);
        }

        public String getState() {
            return state;
        }
    }
