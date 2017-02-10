package com.onecreation.groceries.dto;

import java.math.BigDecimal;

/**
 * Created by dennis on 1/29/17.
 */
public class ReceiptItemDto {
    private String itemName;
    private BigDecimal price;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
