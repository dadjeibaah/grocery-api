package com.onecreation.groceries.services;

import com.onecreation.groceries.dto.ReceiptItemDto;
import com.onecreation.groceries.models.ItemDetail;
import com.onecreation.groceries.models.ReceiptItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by dennis on 1/29/17.
 */
public class ItemDetailRepositoryImpl implements ItemDetailRepositoryCustom {

    private final
    ReceiptItemRepository receiptItemRepository;

    @Autowired
    public ItemDetailRepositoryImpl(ReceiptItemRepository receiptItemRepository) {
        this.receiptItemRepository = receiptItemRepository;
    }

    @Override
    public ItemDetail convertDtoToModel(ReceiptItemDto item) {
        ReceiptItem newReceiptItem = receiptItemRepository.save(new ReceiptItem(item.getItemName()));
        ItemDetail detail = new ItemDetail(item.getPrice());
        detail.setReceiptItem(newReceiptItem);
        return detail;
    }
}
