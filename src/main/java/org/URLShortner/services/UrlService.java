package org.URLShortner.services;


import org.URLShortner.dtos.requests.OriginalUrlRequest;
import org.URLShortner.dtos.requests.UrlRequest;
import org.URLShortner.dtos.responses.OriginalUrlResponse;
import org.URLShortner.dtos.responses.UrlResponse;

public interface UrlService {
    UrlResponse shortenUrl(UrlRequest urlRequest);
//    String getOriginalUrl(String shortenedUrl);
    OriginalUrlResponse getOriginalUrl(OriginalUrlRequest originalUrlRequest);
}