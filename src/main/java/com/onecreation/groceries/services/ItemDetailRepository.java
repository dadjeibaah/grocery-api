package com.onecreation.groceries.services;

import com.onecreation.groceries.models.ItemDetail;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dennis on 1/29/17.
 */
public interface ItemDetailRepository extends CrudRepository<ItemDetail, Long>, ItemDetailRepositoryCustom {
}
