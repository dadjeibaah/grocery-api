package com.onecreation.groceries.services;

import com.onecreation.groceries.dto.GroceryDto;
import com.onecreation.groceries.models.Groceries;

/**
 * Created by dennis on 2/5/17.
 */
public interface DtoConverter<T,S> {
    S getModelFromDto(T dto);
}
