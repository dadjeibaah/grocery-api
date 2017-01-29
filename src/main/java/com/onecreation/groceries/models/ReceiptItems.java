package com.onecreation.groceries.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by dennis on 1/27/17.
 */
@Entity
@Table(name = "receipt_items")
public class ReceiptItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String itemName;
    private BigDecimal price;
}
