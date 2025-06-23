package org.URLShortner.dtos.responses;

import lombok.Data;

@Data
public class UrlResponse {
    private String shortenedUrl;
    private String message;
}