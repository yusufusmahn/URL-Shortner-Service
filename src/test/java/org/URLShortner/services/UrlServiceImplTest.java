//package org.URLShortner.services;
//
//import org.URLShortner.data.models.Url;
//import org.URLShortner.data.repositories.UrlRepository;
//import org.URLShortner.dtos.requests.OriginalUrlRequest;
//import org.URLShortner.dtos.requests.UrlRequest;
//import org.URLShortner.dtos.responses.OriginalUrlResponse;
//import org.URLShortner.dtos.responses.UrlResponse;
//import org.URLShortner.exceptions.UrlShortnerServiceException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestPropertySource(locations = "classpath:application-test.properties")
//public class UrlServiceImplTest {
//
//    @Autowired
//    private UrlService urlService;
//
//    @Autowired
//    private UrlRepository urlRepository;
//
//    @BeforeEach
//    void setUp() {
//        urlRepository.deleteAll();
//    }
//
//    @Test
//    void shortenUrl_validUrl_success() {
//        UrlRequest request = new UrlRequest();
//        request.setOriginalUrl("https://example.com");
//
//        UrlResponse response = urlService.shortenUrl(request);
//
//        assertNotNull(response);
//        assertNotNull(response.getShortenedUrl());
//        assertEquals("URL shortened successfully", response.getMessage());
//
//        Url savedUrl = urlRepository.findByShortenedUrl(response.getShortenedUrl());
//        assertNotNull(savedUrl);
//        assertEquals("https://example.com", savedUrl.getOriginalUrl());
//    }
//
//    @Test
//    void shortenUrl_invalidUrl_throwsException() {
//        UrlRequest request = new UrlRequest();
//        request.setOriginalUrl("ht!tp:/invalid-url");
//
//        assertThrows(UrlShortnerServiceException.class, () -> urlService.shortenUrl(request));
//    }
//
//    @Test
//    void getOriginalUrl_validShortenedUrl_success() {
//        UrlRequest shortenRequest = new UrlRequest();
//        shortenRequest.setOriginalUrl("https://example.com");
//
//        UrlResponse shortenResponse = urlService.shortenUrl(shortenRequest);
//
//        OriginalUrlRequest getRequest = new OriginalUrlRequest();
//        getRequest.setShortenedUrl(shortenResponse.getShortenedUrl());
//
//        OriginalUrlResponse response = urlService.getOriginalUrl(getRequest);
//
//        assertNotNull(response);
//        assertEquals("https://example.com", response.getOriginalUrl());
//        assertEquals("Original URL retrieved successfully", response.getMessage());
//    }
//
//    @Test
//    void getOriginalUrl_invalidShortenedUrl_throwsException() {
//        OriginalUrlRequest request = new OriginalUrlRequest();
//        request.setShortenedUrl("nonexistent");
//
//        assertThrows(UrlShortnerServiceException.class, () -> urlService.getOriginalUrl(request));
//    }
//}
