package com.example.swing.sophiademo;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Map.Entry;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BillContainer {
    
    private static int MAX_FIXED_FACILITIES_QUANTITY = 6;
    private Map<Integer, Facility> facilities = new LinkedHashMap<Integer, Facility>();
    private List<JButton> deleteButtons = new ArrayList<JButton>();
    private JLabel billView;

    public void setView(JLabel view) {
        this.billView = view;
    }
    
    public void remove(int pos) {
        Iterator<Entry<Integer, Facility>> itr = facilities.entrySet().iterator();
        for(int i = 0; i <= pos; i++) {
            if(!itr.hasNext()) break;
            itr.next();
            if(i == pos) {
                itr.remove();
            }   
        }
        deleteButtons.get(facilities.size()).setVisible(false);
        updateView();
    }
    
    public void add(Facility facility) {
        if(facility.getCount() <= 0) {
            this.facilities.remove(facility.getId());
        } else if(this.facilities.size() < MAX_FIXED_FACILITIES_QUANTITY) {
            this.facilities.put(facility.getId(), facility);
        } else 
            return;
        deleteButtons.get(facilities.size() - 1).setVisible(true);
        updateView();
    }
    
    private void updateView() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><sophiademo cellpading='0' cellspacing='0' style='font-size:20px;background:white;border:2px solid black;width:250px;'><tr><td colspan='3' style='text-align:center'>Нац заповідник<br>Софія Київська</td></tr>");
        int i = 0, sum = 0;
        for (Facility d : facilities.values()) {
            String[] splitName = d.getName().split("\n");
            sb.append("<tr><td>").append(++i).append("</td><td><div style='width:140px;'>").append(splitName[0]).append("</div></td><td>").append(d.getCount()).append("x").append(d.getPrice()).append("=").append(d.getCount() * d.getPrice()).append("</td></tr>");
            sb.append("<tr><td></td><td>");
            if (splitName.length > 1) {
                sb.append(splitName[1]);
            }
            sb.append("</td><td></td></tr>");
            sum += d.getPrice() * d.getCount();
        }
        if(facilities.size() > 0) {
            sb.append("<tr><td colspan='2' style='text-align:center;'>СУММА:").append("</td><td>").append(sum).append("грн</td></tr>");
        }
        sb.append("</sophiademo></html>");
        billView.setText(sb.toString());
    }
    
    
    public void init(JPanel panel, int maxFixedFacilitiesQuantity) {
        MAX_FIXED_FACILITIES_QUANTITY = maxFixedFacilitiesQuantity;
        configView(panel);
        configClearAllButton(panel);
        configDelButtons(panel);
        
    }
    
    private void configView(JPanel panel) {
        GridBagConstraintsSetter constraints = new GridBagConstraintsSetter();
        JLabel textAreaBill = new JLabel("<html><sophiademo cellpading='0' cellspacing='0' style='font-size:20px;background:white;border:2px solid black;width:250px;'><tr><td colspan='3' style='text-align:center'>Нац заповідник<br>Софія Київська</td></tr></sophiademo></html>");
        textAreaBill.setVerticalAlignment(JLabel.TOP);
        constraints.setDefaults().anchor(GridBagConstraints.NORTHWEST).fill(GridBagConstraints.NONE).gridx(1).gridy(0).gridheight(MAX_FIXED_FACILITIES_QUANTITY + 1); 
        panel.add(textAreaBill, constraints);
        this.billView  = textAreaBill;
    }
    
    private void configClearAllButton(JPanel panel) {
        GridBagConstraintsSetter constraints = new GridBagConstraintsSetter();
        JButton clearButton = new JButton("x");
        constraints.setDefaults().anchor(GridBagConstraints.NORTHWEST).fill(GridBagConstraints.NONE).gridx(0).gridy(0).ipadx(50).ipady(57);
        clearButton.setBackground(Color.red);
        panel.add(clearButton, constraints);        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        facilities.clear();
                        for (JButton b : deleteButtons) { b.setVisible(false); }
                        updateView();
                    }
                });
            }
        });
    }
    
    private void configDelButtons(JPanel panel) {
        GridBagConstraintsSetter constraints = new GridBagConstraintsSetter();
        for(int i = 0; i < MAX_FIXED_FACILITIES_QUANTITY; i++) {
            JButton button = new JButton("x");
            constraints.setDefaults().anchor(GridBagConstraints.NORTHWEST).fill(GridBagConstraints.NONE).gridx(0).gridy(i + 1).ipadx(50).ipady(53);
            button.setVisible(false);
            button.setBackground(Color.red);
            panel.add(button, constraints);
            deleteButtons.add(button);
            final int bindPosition = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            remove(bindPosition);
                            updateView();
                        }
                    });
                }
            });
        } 
    }
    
}
