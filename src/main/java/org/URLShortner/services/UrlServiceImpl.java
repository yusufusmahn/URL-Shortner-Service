package org.URLShortner.services;


import org.URLShortner.data.models.Url;
import org.URLShortner.data.repositories.UrlRepository;
import org.URLShortner.dtos.requests.OriginalUrlRequest;
import org.URLShortner.dtos.requests.UrlRequest;
import org.URLShortner.dtos.responses.OriginalUrlResponse;
import org.URLShortner.dtos.responses.UrlResponse;
import org.URLShortner.exceptions.UrlShortnerServiceException;
import org.URLShortner.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public UrlResponse shortenUrl(UrlRequest urlRequest) {
        if (urlRequest == null || urlRequest.getOriginalUrl() == null || urlRequest.getOriginalUrl().trim().isEmpty()) {
            throw new UrlShortnerServiceException("Original URL is required");
        }

        try {
            new URL(urlRequest.getOriginalUrl()).toURI();
        } catch (MalformedURLException | IllegalArgumentException | URISyntaxException e) {
            throw new UrlShortnerServiceException("Invalid URL format");
        }

        Url url = Mapper.toEntity(urlRequest);
        Url savedUrl = urlRepository.save(url);
        return Mapper.toResponse(savedUrl);
    }

    @Override
    public OriginalUrlResponse getOriginalUrl(OriginalUrlRequest originalUrlRequest) {
        if (originalUrlRequest == null || originalUrlRequest.getShortenedUrl() == null || originalUrlRequest.getShortenedUrl().trim().isEmpty()) {
            throw new UrlShortnerServiceException("Shortened URL is required");
        }

        Url url = urlRepository.findByShortenedUrl(originalUrlRequest.getShortenedUrl());
        if (url == null) {
            throw new UrlShortnerServiceException("Shortened URL not found");
        }

        return Mapper.toOriginalResponse(url);
    }




}