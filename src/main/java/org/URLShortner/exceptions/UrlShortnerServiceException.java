package org.URLShortner.exceptions;

import lombok.Data;

@Data
public class UrlShortnerServiceException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public UrlShortnerServiceException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = "URL_SHORTENER_ERROR";
    }
}