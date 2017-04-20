package com.onecreation.groceries.services;

import com.onecreation.groceries.dto.ReceiptItemDto;
import com.onecreation.groceries.models.ReceiptItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by dennis on 1/29/17.
 */
public interface ReceiptItemRepository extends CrudRepository<ReceiptItem, Long>, DtoConverter<ReceiptItemDto, ReceiptItem>, JpaRepository<ReceiptItem, Long> {
    List<ReceiptItem> findAllByItemNameContainingIgnoreCase(String itemName);
}
