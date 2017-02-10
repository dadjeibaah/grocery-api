package com.onecreation.groceries.services;

import com.onecreation.groceries.dto.ReceiptItemDto;
import com.onecreation.groceries.models.ReceiptItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dennis on 1/29/17.
 */
public interface ReceiptItemRepository extends CrudRepository<ReceiptItem, Long>, DtoConverter<ReceiptItemDto, ReceiptItem> {
}
