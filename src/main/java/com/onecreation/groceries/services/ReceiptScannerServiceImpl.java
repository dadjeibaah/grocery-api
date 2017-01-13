package com.onecreation.groceries.services;

import com.onecreation.groceries.models.GroceryList;
import com.onecreation.groceries.models.Item;
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
    public GroceryList getReceiptInformation(MultipartFile file) {
        byte[] imageData;
        GroceryList list = null;
        try {
            imageData = file.getBytes();
            String unparsedText = visionAPI.detectTextInImage(imageData);
            List<Item> items = new ArrayList<>();
            Pattern pattern = Pattern.compile(REGEX);
            Matcher matches = pattern.matcher(unparsedText);
            while(matches.find()){
                items.add(new Item(matches.group(1), matches.group(2)));
            }
            list = new GroceryList(items);

        } catch (IOException e) {
            e.printStackTrace(System.out);
            return new GroceryList();
        }


        return list;
    }
}
