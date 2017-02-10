package com.onecreation.groceries.services;

import com.onecreation.groceries.dto.ReceiptItemDto;
import com.onecreation.groceries.models.ReceiptItem;

/**
 * Created by dennis on 2/5/17.
 */
public class ReceiptItemRepositoryImpl implements DtoConverter<ReceiptItemDto, ReceiptItem> {

    @Override
    public ReceiptItem getModelFromDto(ReceiptItemDto dto) {
        ReceiptItem receiptItem = new ReceiptItem(dto.getItemName());
        return receiptItem;
    }
}
