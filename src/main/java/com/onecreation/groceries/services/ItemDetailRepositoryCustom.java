package com.onecreation.groceries.services;

import com.onecreation.groceries.dto.ReceiptItemDto;
import com.onecreation.groceries.models.ItemDetail;
import com.onecreation.groceries.models.ReceiptItem;

import java.util.List;

/**
 * Created by dennis on 1/29/17.
 */
public interface ItemDetailRepositoryCustom {
    ItemDetail convertDtoToModel(ReceiptItemDto item);
}
