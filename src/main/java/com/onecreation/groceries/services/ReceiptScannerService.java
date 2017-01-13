package com.onecreation.groceries.services;

import com.onecreation.groceries.models.GroceryList;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by dennis on 1/10/17.
 */

public interface ReceiptScannerService {
    GroceryList getReceiptInformation(MultipartFile file);
}
