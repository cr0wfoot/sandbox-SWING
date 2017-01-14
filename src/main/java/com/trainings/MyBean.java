/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trainings;

/**
 *
 * @author Alex
 */
public class MyBean {
 
        private String name;
        private String size;
        private String description;
        private String btn;

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }
        
        
 
        public MyBean(String name, String size, String description, String btn) {
            this.name = name;
            this.size = size;
            this.description = description;
            this.btn = btn;
        }
 
        public void setName(String name) {
            this.name = name;
        }
 
        public String getName() {
            return name;
        }
 
        public void setSize(String size) {
            this.size = size;
        }
 
        public String getSize() {
            return size;
        }
 
        public void setDescription(String description) {
            this.description = description;
        }
 
        public String getDescription() {
            return description;
        }
    }
