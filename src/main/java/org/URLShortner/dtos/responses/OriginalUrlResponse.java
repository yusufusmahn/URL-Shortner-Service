package org.URLShortner.dtos.responses;

import lombok.Data;

@Data
public class OriginalUrlResponse {
    private String originalUrl;
    private String message;
}
