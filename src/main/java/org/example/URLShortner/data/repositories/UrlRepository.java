package org.example.URLShortner.data.repositories;

import org.example.URLShortner.data.models.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url, String> {
    Url findByShortenedUrl(String shortenedUrl);
}