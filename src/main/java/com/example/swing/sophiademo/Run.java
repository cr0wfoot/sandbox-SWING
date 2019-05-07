package com.example.swing.sophiademo;

import com.example.swing.sophiademo.facilities.FacilitiesTableModel;

import javax.swing.*;
import java.awt.*;

public class Run {
   
    public static void main(String[] args) {
        new Run();
    }
    
    
    private void initAndShowFrame(Component...components) {
        JFrame frame = new JFrame("НАЦІОНАЛЬНИЙ ЗАПОВІДНИК");              
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setLayout(new BorderLayout());
               frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
               for(Component c : components)
                   frame.add(c);
               frame.pack();
               frame.setLocationRelativeTo(null);
               frame.setVisible(true);
    }

    public Run() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) { }
       
                JPanel panel0 = new JPanel();
                panel0.setBackground(Color.red);
                panel0.setLayout(new GridBagLayout()); 
                GridBagConstraintsSetter constraints = new GridBagConstraintsSetter(); 

                JPanel panel1 = new JPanel(); 
                panel1.setBackground(Color.blue); 
                panel1.setLayout(new GridBagLayout());
                constraints.anchor(GridBagConstraints.NORTHWEST).fill(GridBagConstraints.NONE).gridx(0).gridy(0);
                panel0.add(panel1, constraints); 


                JPanel panel2 = new JPanel();
                panel2.setBackground(Color.yellow);
                panel2.setLayout(new GridBagLayout());
                constraints.setDefaults().anchor(GridBagConstraints.NORTHWEST).fill(GridBagConstraints.BOTH).gridx(0).gridy(1); 
                panel0.add(panel2, constraints); 

                JPanel panel3 = new JPanel();
                panel3.setBackground(Color.green);
                constraints.setDefaults().anchor(GridBagConstraints.NORTHWEST).fill(GridBagConstraints.BOTH).gridx(1).gridy(0).weighty(1.0).weightx(1.0).gridheight(2); 
                panel0.add(panel3, constraints); 

                JButton print = new JButton("друкувати");
                constraints.setDefaults().anchor(GridBagConstraints.NORTHWEST).fill(GridBagConstraints.NONE).gridx(0).gridy(0).ipadx(100).ipady(100); 
                panel2.add(print, constraints);
                
                BillContainer billContainer = new BillContainer();
                billContainer.init(panel1, 3);

                FacilitiesTableModel model = new FacilitiesTableModel(new DataService().findAll());
                model.setBillContainer(billContainer);
                JTable table = new JTable(model);
                model.initTable(table);

                JScrollPane j = new JScrollPane(table);
                table.getTableHeader().setPreferredSize(new Dimension(j.getWidth(), 60));

                JPanel p = new JPanel(new GridLayout(1, 2));
                    p.add(j); 
                    p.add(panel0);
                
                initAndShowFrame(p);
            }
        });
    }
    
    
    
}
