package com.onecreation.groceries.services;

import com.onecreation.groceries.models.ReceiptItem;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by dennis on 1/10/17.
 */

public interface ReceiptScannerService {
    List<ReceiptItem> getReceiptInformation(MultipartFile file);
}
