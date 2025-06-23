package org.URLShortner.data.repositories;

import org.URLShortner.data.models.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url, String> {
    Url findByShortenedUrl(String shortenedUrl);
}