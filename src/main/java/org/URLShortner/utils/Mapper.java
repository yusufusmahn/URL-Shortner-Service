package org.URLShortner.utils;

import org.URLShortner.data.models.Url;
import org.URLShortner.data.models.User;
import org.URLShortner.dtos.requests.UrlRequest;
import org.URLShortner.dtos.requests.UserRequest;
import org.URLShortner.dtos.responses.OriginalUrlResponse;
import org.URLShortner.dtos.responses.UrlResponse;
import org.URLShortner.dtos.responses.UserResponse;

import java.time.LocalDateTime;
import java.util.Random;

public class Mapper {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SHORT_URL_LENGTH = 6;
    private static final Random random = new Random();

    public static Url toEntity(UrlRequest urlRequest) {
        Url url = new Url();
        url.setOriginalUrl(urlRequest.getOriginalUrl());
        url.setShortenedUrl(generateShortUrl());
        url.setCreatedAt(LocalDateTime.now());
        return url;
    }

    public static UrlResponse toResponse(Url url) {
        UrlResponse response = new UrlResponse();
        response.setShortenedUrl(url.getShortenedUrl());
        response.setMessage("URL shortened successfully");
        return response;
    }

    private static String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder(SHORT_URL_LENGTH);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            shortUrl.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return shortUrl.toString();
    }

    public static User toEntity(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        return user;
    }

    public static UserResponse toResponse(User user, String message) {
        UserResponse response = new UserResponse();
        response.setUsername(user.getUsername());
        response.setMessage(message);
        return response;
    }

    public static OriginalUrlResponse toOriginalResponse(Url url) {
        OriginalUrlResponse response = new OriginalUrlResponse();
        response.setOriginalUrl(url.getOriginalUrl());
        response.setMessage("Original URL retrieved successfully");
        return response;
    }

}