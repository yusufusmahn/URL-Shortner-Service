package org.URLShortner.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ApiResponse {
    private Object data;
    private boolean success;

    public ApiResponse(Object data, boolean success) {
        this.data = data;
        this.success = success;
    }
}