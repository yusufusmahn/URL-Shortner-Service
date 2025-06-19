package org.example.URLShortner.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "urls")
public class Url {
    @Id
    private String id;
    private String originalUrl;
    private String shortenedUrl;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}