package com.onecreation.groceries.controllers;

import com.onecreation.groceries.dto.ReceiptItemDto;
import com.onecreation.groceries.models.ItemDetail;
import com.onecreation.groceries.models.ReceiptItem;
import com.onecreation.groceries.services.ItemDetailRepository;
import com.onecreation.groceries.services.ReceiptItemRepository;
import com.onecreation.groceries.services.ReceiptScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dennis on 1/10/17.
 */
@RestController
@RequestMapping(value = "/receipts")
public class ReceiptsController {

    private final ReceiptScannerService receiptScannerService;
    private final ItemDetailRepository itemDetailRepository;
    private final ReceiptItemRepository receiptItemRepository;

    @Autowired
    public  ReceiptsController(ReceiptScannerService receiptScannerService,
                               ItemDetailRepository itemDetailRepository,
                               ReceiptItemRepository receiptItemRepository){
        this.receiptScannerService = receiptScannerService;
        this.itemDetailRepository = itemDetailRepository;
        this.receiptItemRepository = receiptItemRepository;
    }

    @RequestMapping(value ="/images",method = RequestMethod.POST)
    public ResponseEntity<List<ReceiptItem>> getGroceryListFromReceipt(@RequestBody MultipartFile file) throws IOException {
        List<ReceiptItem> receipt = receiptScannerService.getReceiptInformation(file);
        return new ResponseEntity<>(receipt, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Iterable<ReceiptItem>> saveReceiptList(@RequestBody List<ReceiptItem> listOfReceiptItems){
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ResponseEntity<List<ReceiptItem>> searchReceiptItems(@RequestParam String searchTerm){
        List<ReceiptItem> receiptItems = receiptItemRepository.findAllByItemNameContainingIgnoreCase(searchTerm);
        return new ResponseEntity<>(receiptItems, HttpStatus.OK);

    }

}
