package com.onecreation.groceries.dto;

import java.sql.Date;

/**
 * Created by dennis on 2/5/17.
 */
public class GroceryDto {
    private String groceryListName;
    private Date dateStarted;

    public String getGroceryListName() {
        return groceryListName;
    }

    public void setGroceryListName(String groceryListName) {
        this.groceryListName = groceryListName;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }
}
