package com.onecreation.groceries.services;

import com.google.api.services.vision.v1.model.EntityAnnotation;

import java.io.IOException;
import java.util.List;

/**
 * Created by dennis on 1/11/17.
 */
public interface GoogleAPI {
    String detectTextInImage(byte[] imageData) throws IOException;
}
