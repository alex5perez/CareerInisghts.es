package com.etrivium.backend.repository;

import com.etrivium.backend.controller.response.GetCourseStructureResponse;
import com.etrivium.backend.repository.request.DataGetCourseStructureRequest;
import com.etrivium.backend.repository.request.DataQuestionAnsweringRequest;
import com.etrivium.backend.repository.request.UnsplashFindPhotoOrientation;
import com.etrivium.backend.repository.response.UnsplashResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImageRepository {
    @Autowired
    RestTemplate restTemplate;

    @Value("${unsplash.api.key}")
    private String unsplashApiKey;

    private final String UNSPLASH_API_URL_SEARCH = "https://api.unsplash.com/search/photos";

    public UnsplashResponse findPhotos(String query, UnsplashFindPhotoOrientation unsplashFindPhotoOrientation) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Client-ID " + unsplashApiKey);

        var urlParams = UNSPLASH_API_URL_SEARCH + "?content_filter=high&orientation=" + unsplashFindPhotoOrientation.name() + "&query=" + query + "&per_page=10";

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<UnsplashResponse> response = restTemplate.exchange(
                urlParams, HttpMethod.GET, requestEntity, UnsplashResponse.class);
        return response.getBody();
    }
}
