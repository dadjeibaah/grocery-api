package com.onecreation.groceries.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by dennis on 1/29/17.
 */
@Entity
public class ItemDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal price;
    private String store;
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "receipt_item_id", nullable = false)
    private ReceiptItem receiptItem;

    public ItemDetail(BigDecimal price) {
        this.price = price;
    }
    public ItemDetail(String stringPrice){
        try{
            this.price = new BigDecimal(stringPrice);
        }catch (NumberFormatException e){
            this.price = new BigDecimal("0.00");
        }

    }

    public ReceiptItem getReceiptItem() {
        return receiptItem;
    }

    public void setReceiptItem(ReceiptItem receiptItem) {
        this.receiptItem = receiptItem;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
