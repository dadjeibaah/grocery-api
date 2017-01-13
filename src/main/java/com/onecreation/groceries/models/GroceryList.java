package com.onecreation.groceries.models;

import java.util.List;

/**
 * Created by dennis on 1/11/17.
 */
public class GroceryList {
    private List<Item> receiptItems;

    public GroceryList(List<Item> items) {
        this.receiptItems = items;
    }

    public GroceryList() {

    }

    public List<Item> getReceiptItems() {
        return receiptItems;
    }
}
