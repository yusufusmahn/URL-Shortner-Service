package org.URLShortner.controllers;

import jakarta.validation.Valid;
import org.URLShortner.dtos.requests.OriginalUrlRequest;
import org.URLShortner.dtos.requests.UrlRequest;
import org.URLShortner.dtos.responses.ApiResponse;
import org.URLShortner.dtos.responses.OriginalUrlResponse;
import org.URLShortner.dtos.responses.UrlResponse;
import org.URLShortner.exceptions.UrlShortnerServiceException;
import org.URLShortner.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/url/shorten")
    public ResponseEntity<ApiResponse> shortenUrl(@Valid @RequestBody UrlRequest urlRequest) {
        try {
            UrlResponse response = urlService.shortenUrl(urlRequest);
            return new ResponseEntity<>(new ApiResponse(response, true), HttpStatus.CREATED);
        } catch (UrlShortnerServiceException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }



//    @GetMapping("/redirect/{shortenedUrl}")
//    public ResponseEntity<ApiResponse> getOriginalUrl(@PathVariable String shortenedUrl) {
//        try {
//            String originalUrl = urlService.getOriginalUrl(shortenedUrl);
//            return ResponseEntity.status(HttpStatus.FOUND)
//                    .header("Location", originalUrl)
//                    .body(new ApiResponse("Redirecting to original URL.", true));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ApiResponse("Shortened URL not found or invalid.", false));
//        }
//    }


    @PostMapping("/url/original")
    public ResponseEntity<ApiResponse> getOriginalUrl(@Valid @RequestBody OriginalUrlRequest originalUrlRequest) {
        try {
            OriginalUrlResponse response = urlService.getOriginalUrl(originalUrlRequest);
            return ResponseEntity.ok(new ApiResponse(response, true));
        } catch (UrlShortnerServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), false));
        }
    }

}
