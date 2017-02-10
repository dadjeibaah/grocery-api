package com.onecreation.groceries.services;

import com.onecreation.groceries.models.ItemDetail;
import com.onecreation.groceries.models.ReceiptItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dennis on 1/11/17.
 */
@Service
public class ReceiptScannerServiceImpl implements ReceiptScannerService {

    private final GoogleAPI visionAPI;
    private static final String REGEX = "([\\w+\\s]+)(\\$?\\d[\\.\\,]\\d{2}\\s[A-Z])";

    @Autowired
    public ReceiptScannerServiceImpl(GoogleAPI googleVisionAPI) {
        visionAPI = googleVisionAPI;
    }

    @Override
    public List<ReceiptItem> getReceiptInformation(MultipartFile file) {
        byte[] imageData;
        List<ReceiptItem> items;
        try {
            imageData = file.getBytes();
            String unparsedText = visionAPI.detectTextInImage(imageData);
            items = new ArrayList<>();
            Pattern pattern = Pattern.compile(REGEX);
            Matcher matches = pattern.matcher(unparsedText);
            while(matches.find()){
                items.add(new ReceiptItem(matches.group(1), new ItemDetail(matches.group(2))));
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
            items = new ArrayList<>();
            return items;
        }
        return items;
    }
}
