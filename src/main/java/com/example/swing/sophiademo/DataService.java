package com.example.swing.sophiademo;

import java.util.ArrayList;
import java.util.List;

public class DataService {
    
    private List<Facility> data;
    
    public DataService() {
        data = new ArrayList<Facility>();
        data.add(new Facility(1, "Вхід", 30));
        data.add(new Facility(2, "Дзвінниця", 20));
        data.add(new Facility(3, "Хлібня", 10));
        data.add(new Facility(4, "Брама Заборовського", 20));
        data.add(new Facility(5, "Будинок Митрополита", 15));
    }
    
    public List<Facility> findAll() {
        return data;
    }
    
}
