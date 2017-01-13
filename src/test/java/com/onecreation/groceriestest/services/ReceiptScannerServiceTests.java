package com.onecreation.groceriestest.services;

import com.onecreation.groceries.models.GroceryList;
import com.onecreation.groceries.models.Item;
import com.onecreation.groceries.services.GoogleAPI;
import com.onecreation.groceries.services.ReceiptScannerService;
import com.onecreation.groceries.services.ReceiptScannerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dennis on 1/10/17.
 */

public class ReceiptScannerServiceTests {


    private ReceiptScannerService receiptScannerService;
    private GoogleAPI mockAPI;
    private String unparsedText = "1805 W. STATE OF FRANKLIN RD.\n" +
            "423-929 1408\n" +
            "Your cashier was Robot 2\n" +
            "KROGER PLUS cusiuMER *****t*9171\n" +
            "BLACK BOX CAB SAUV\n" +
            "4.79 T\n" +
            "Age Restricted: 21\n" +
            "TAX\n" +
            "BALANCE\n" +
            "JOHSON CITY TN 37604\n" +
            "Discover Credit Purchase\n" +
            "REF: 00536R TOTAL: 5.25\n" +
            "AID: A0000001523010\n" +
            "5.25\n" +
            "DISCOVER\n" +
            "0.00\n" +
            "CHANGE\n" +
            "TOTAL NUMBER OF ITEMS SOLD\n" +
            "01/05/17 05:51p 367 81 130 781\n";


    @Before
    public void setUp() {
        mockAPI = mock(GoogleAPI.class);
        receiptScannerService = new ReceiptScannerServiceImpl(mockAPI);

    }

    @Test
    public void testReceiptInformationGeneration() throws URISyntaxException, IOException {
        when(mockAPI.detectTextInImage(any(byte[].class))).thenReturn(unparsedText);
        MockMultipartFile receiptImage = new MockMultipartFile("testimg.jpg", new byte[]{});
        GroceryList list = receiptScannerService.getReceiptInformation(receiptImage);
        Assert.notEmpty(list.getReceiptItems());
        Item item = list.getReceiptItems().get(0);
       assertThat(item.getName(), containsString("BLACK BOX CAB SAUV"));
    }

    @Test
    public void testReceiptInformationExtractionPrice() throws URISyntaxException, IOException{
        when(mockAPI.detectTextInImage(any(byte[].class))).thenReturn(unparsedText);
        MockMultipartFile receiptImage = new MockMultipartFile("testimg.jpg", new byte[]{});
        GroceryList list = receiptScannerService.getReceiptInformation(receiptImage);
        Assert.notEmpty(list.getReceiptItems());
        Item item = list.getReceiptItems().get(0);
        assertTrue(item.getPrice().compareTo(new BigDecimal("4.79")) == 0);
    }

    @Test
    public void testEachItemInReceiptList() throws URISyntaxException, IOException{
        when(mockAPI.detectTextInImage(any(byte[].class))).thenReturn(unparsedText);
        MockMultipartFile receiptImage = new MockMultipartFile("testimg.jpg", new byte[]{});
        GroceryList list = receiptScannerService.getReceiptInformation(receiptImage);
        Assert.notEmpty(list.getReceiptItems());
        for (Item i: list.getReceiptItems()) {
            assertThat(i.getPrice(), instanceOf(BigDecimal.class));
        }
    }
}
