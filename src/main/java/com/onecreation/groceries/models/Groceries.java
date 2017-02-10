package com.onecreation.groceries.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by dennis on 2/4/17.
 */
@Entity
public class Groceries {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String groceryListName;
    private Date date;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groceries")
    private List<ReceiptItem> receiptItems;

    public Groceries(){

    }
    public Groceries(String groceryListName, Date dateStarted){
        this.groceryListName = groceryListName;
        this.date = dateStarted;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroceryListName() {
        return groceryListName;
    }

    public void setGroceryListName(String groceryListName) {
        this.groceryListName = groceryListName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ReceiptItem> getReceiptItems() {
        return receiptItems;
    }

    public void setReceiptItems(List<ReceiptItem> receiptItems) {
        this.receiptItems = receiptItems;
    }


    public void addReceiptItem(ReceiptItem item) {
         this.receiptItems.add(item);
    }
}
