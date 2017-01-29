package com.onecreation.groceries.controllers;

import com.onecreation.groceries.models.GroceryList;
import com.onecreation.groceries.models.ReceiptItems;
import com.onecreation.groceries.services.ReceiptScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by dennis on 1/10/17.
 */
@RestController
@RequestMapping(value = "/receipts")
public class ReceiptsController {

    private final ReceiptScannerService receiptScannerService;

    @Autowired
    public  ReceiptsController(ReceiptScannerService receiptScannerService){
        this.receiptScannerService = receiptScannerService;
    }

    @RequestMapping(value ="/images",method = RequestMethod.POST)
    public ResponseEntity<Object> getGroceryListFromReceipt(@RequestBody MultipartFile file) throws IOException {
        GroceryList receipt = receiptScannerService.getReceiptInformation(file);
        return new ResponseEntity<>(receipt, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> saveReceiptList(@RequestBody List<ReceiptItems> listOfReceiptItems){
        return new ResponseEntity<>(listOfReceiptItems, HttpStatus.OK);
    }

}
