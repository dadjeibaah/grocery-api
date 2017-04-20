package com.onecreation.groceries.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dennis on 1/27/17.
 */
@Entity
public class ReceiptItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String itemName;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "receiptItem")
    private List<ItemDetail> itemDetails;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Groceries> groceries;

    public ReceiptItem(){

    }

    public ReceiptItem(String itemName){
        this.itemName = itemName;
    }

    public ReceiptItem(String itemName, ItemDetail itemDetail){
        this.itemDetails = new ArrayList<>();
        this.itemName = itemName;
        this.itemDetails.add(itemDetail);
    }



    public List<ItemDetail> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(List<ItemDetail> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
