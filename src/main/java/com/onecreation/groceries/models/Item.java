package com.onecreation.groceries.models;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;

/**
 * Created by dennis on 1/11/17.
 */
public class Item {
    private String name;
    private BigDecimal price;

    public Item(String name,  String price){
        this.name = name;
        this.price = parsePrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(String price) {

        this.price = parsePrice(price);;
    }

    private BigDecimal parsePrice(String price){
        price = price.replaceAll("[^\\d|^\\.]", "");
        return new BigDecimal(price);
    }
}
