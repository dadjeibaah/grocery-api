package com.onecreation.groceries.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionScopes;
import com.google.api.services.vision.v1.model.*;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;


/**
 * Created by dennis on 1/11/17.
 */
@Service
public class GoogleVisionAPI implements GoogleAPI {
    private final String APPLICATION_NAME = "GroceryAPI";
    private Vision vision;


    private GoogleVisionAPI() {
        vision = getVisionAPI();
    }

    private Vision getVisionAPI() {
        Vision vision = null;
        try {
            GoogleCredential credential = GoogleCredential.getApplicationDefault().createScoped(VisionScopes.all());
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            vision = new Vision.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, credential)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace(System.out);

        }
        return vision;


    }

    public String detectTextInImage(byte[] imageData) throws IOException {
        AnnotateImageRequest request = new AnnotateImageRequest()
                .setImage(new Image().encodeContent(imageData))
                .setFeatures(ImmutableList.of(
                        new Feature()
                                .setType("TEXT_DETECTION")));

        Vision.Images.Annotate annotate = vision.images()
                .annotate(new BatchAnnotateImagesRequest().setRequests(ImmutableList.of(request)));
        annotate.setDisableGZipContent(true);

        BatchAnnotateImagesResponse batchResponse = annotate.execute();
        assert batchResponse.getResponses().size() == 1;
        AnnotateImageResponse response = batchResponse.getResponses().get(0);
        if (response.getTextAnnotations() == null) {
            throw new IOException(
                    response.getError() != null
                            ? response.getError().getMessage()
                            : "Unknown error getting image annotations");
        }

        return response.getTextAnnotations().get(0).getDescription();
    }
}
