package com.example.swing.sophiademo;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GridBagConstraintsSetter extends GridBagConstraints {
    
    public GridBagConstraintsSetter setDefaults() { 
        gridx = RELATIVE;
        gridy = RELATIVE;
        gridwidth = 1;
        gridheight = 1;
        weightx = 0;
        weighty = 0;
        anchor = CENTER;
        fill = NONE;
        insets = new Insets(0, 0, 0, 0);
        ipadx = 0;
        ipady = 0;
        return this;
    }
    
    public void setConfig(int fill, int anchor, int gridx, int gridy, int gridwidth, int gridheight, int ipadx, int ipady, double weightx, double weighty, Insets insets) {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        this.weightx = weightx;
        this.weighty = weighty;
        this.anchor = anchor;
        this.fill = fill;
        this.insets = insets;
        this.ipadx = ipadx;
        this.ipady = ipady;
    }
    
    public GridBagConstraintsSetter fill(int fill) {
        this.fill = fill;
        return this;
    }
    
    public GridBagConstraintsSetter anchor(int anchor) {
        this.anchor = anchor;
        return this;
    }
    
    public GridBagConstraintsSetter gridx(int gridx) {
        this.gridx = gridx;
        return this;
    }
    
    public GridBagConstraintsSetter gridy(int gridy) {
        this.gridy = gridy;
        return this;
    }
    
    public GridBagConstraintsSetter ipadx(int ipadx) {
        this.ipadx = ipadx;
        return this;
    }
    
    public GridBagConstraintsSetter ipady(int ipady) {
        this.ipady = ipady;
        return this;
    }
    
    public GridBagConstraintsSetter weightx(double weightx) {
        this.weightx = weightx;
        return this;
    }
    
    public GridBagConstraintsSetter weighty(double weighty) {
        this.weighty = weighty;
        return this;
    }
    
    public GridBagConstraintsSetter gridwidth(int gridwidth) {
        this.gridwidth = gridwidth;
        return this;
    }
    
    public GridBagConstraintsSetter gridheight(int gridheight) {
        this.gridheight = gridheight;
        return this;
    }
    
    public GridBagConstraintsSetter insets(int top, int left, int bottom, int right) {
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }
        
}
