package com.example.lidotk.menu.Bean;

import java.util.List;



/**
 * Created by lidotk on 2017/4/17.
 */
public class FoodType {

    private String type;
    private List<Food> product;



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public List<Food> getProduct() {
        return product;
    }

    public void setProduct(List<Food> product) {
        this.product = product;
    }
}
