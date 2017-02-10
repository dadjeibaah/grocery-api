package com.onecreation.groceries.controllers;

import com.onecreation.groceries.dto.ReceiptItemDto;
import com.onecreation.groceries.models.ItemDetail;
import com.onecreation.groceries.models.ReceiptItem;
import com.onecreation.groceries.services.ItemDetailRepository;
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
import java.util.stream.Collectors;

/**
 * Created by dennis on 1/10/17.
 */
@RestController
@RequestMapping(value = "/receipts")
public class ReceiptsController {

    private final ReceiptScannerService receiptScannerService;
    private final ItemDetailRepository itemDetailRepository;

    @Autowired
    public  ReceiptsController(ReceiptScannerService receiptScannerService,
                               ItemDetailRepository itemDetailRepository){
        this.receiptScannerService = receiptScannerService;
        this.itemDetailRepository = itemDetailRepository;
    }

    @RequestMapping(value ="/images",method = RequestMethod.POST)
    public ResponseEntity<List<ReceiptItem>> getGroceryListFromReceipt(@RequestBody MultipartFile file) throws IOException {
        List<ReceiptItem> receipt = receiptScannerService.getReceiptInformation(file);
        return new ResponseEntity<>(receipt, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Iterable<ItemDetail>> saveReceiptList(@RequestBody List<ReceiptItemDto> listOfReceiptItems){
        List<ItemDetail> list = listOfReceiptItems
                .stream()
                .map(n -> itemDetailRepository.convertDtoToModel(n))
                .collect(Collectors.toList());
        Iterable<ItemDetail> savedItems = itemDetailRepository.save(list);
        return new ResponseEntity<>(savedItems, HttpStatus.OK);
    }

}
