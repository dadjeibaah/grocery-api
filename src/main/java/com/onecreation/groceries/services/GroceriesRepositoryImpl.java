package com.onecreation.groceries.services;

import com.onecreation.groceries.dto.GroceryDto;
import com.onecreation.groceries.models.Groceries;

/**
 * Created by dennis on 2/5/17.
 */
public class GroceriesRepositoryImpl implements DtoConverter<GroceryDto, Groceries> {

    @Override
    public Groceries getModelFromDto(GroceryDto dto) {
        return new Groceries(dto.getGroceryListName(), dto.getDateStarted());
    }
}
