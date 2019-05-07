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

public class IncDecCountPane extends JPanel {

        private JButton accept;
        private JButton reject;
        private String state;

        public IncDecCountPane() {
            setLayout(new GridBagLayout());
            accept = new JButton("+");
            accept.setActionCommand("accept");
            reject = new JButton("-");
            reject.setActionCommand("reject");

            add(accept);
            add(reject);

            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    state = e.getActionCommand();
                    System.out.println("State = " + state);
                }
            };

            accept.addActionListener(listener);
            reject.addActionListener(listener);
        }

        public void addActionListener(ActionListener listener) {
            accept.addActionListener(listener);
            reject.addActionListener(listener);
        }

        public String getState() {
            return state;
        }
    }
