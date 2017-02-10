package com.onecreation.groceries.services;

import com.onecreation.groceries.models.Groceries;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dennis on 2/4/17.
 */
public interface GroceriesRepository extends CrudRepository<Groceries, Long>, DtoConverter {
}
