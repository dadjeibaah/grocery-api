package com.onecreation.groceries.controllers;

import com.onecreation.groceries.dto.GroceryDto;
import com.onecreation.groceries.dto.ReceiptItemDto;
import com.onecreation.groceries.models.Groceries;
import com.onecreation.groceries.models.ReceiptItem;
import com.onecreation.groceries.services.GroceriesRepository;
import com.onecreation.groceries.services.ReceiptItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dennis on 2/4/17.
 */
@RestController
@RequestMapping(value = "/groceries")
public class GroceriesController {

    private final GroceriesRepository groceriesRepository;
    private final ReceiptItemRepository receiptItemRepository;

    @Autowired
    public GroceriesController(GroceriesRepository groceriesRepository, ReceiptItemRepository receiptItemRepository) {
        this.groceriesRepository = groceriesRepository;
        this.receiptItemRepository = receiptItemRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Groceries>> list() {
        return new ResponseEntity<>(groceriesRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Groceries> show(@PathVariable Long id){
        Groceries grocery = groceriesRepository.findOne(id);
        return new ResponseEntity<>(grocery, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Groceries> post(@RequestBody GroceryDto dto){
        Groceries grocery = (Groceries) groceriesRepository.getModelFromDto(dto);
        Groceries savedGrocery = groceriesRepository.save(grocery);
        return new ResponseEntity<>(savedGrocery, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/item", method = RequestMethod.GET)
    public ResponseEntity<List<ReceiptItem>> listItems(@PathVariable Long id){
        List<Long> items = groceriesRepository.findOne(id)
                .getReceiptItems()
                .stream()
                .map(n -> n.getId())
                .collect(Collectors.toList());
        return new ResponseEntity<>(
                (List<ReceiptItem>) receiptItemRepository.findAll(items),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/item",method = RequestMethod.POST)
    public ResponseEntity<Groceries> addItemToGroceries(@RequestBody ReceiptItemDto dto,
                                                        @PathVariable Long id){
        Groceries groceries = groceriesRepository.findOne(id);
        ReceiptItem receiptItem = receiptItemRepository.getModelFromDto(dto);
        receiptItemRepository.save(receiptItem);
        groceries.addReceiptItem(receiptItem);
        return new ResponseEntity<>(groceriesRepository.save(groceries), HttpStatus.OK);
    }
}
