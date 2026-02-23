package net.karlite.urlshortener.service;

import net.karlite.urlshortener.entity.UrlMapping;
import net.karlite.urlshortener.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlShortenerService {

    @Autowired
    private UrlMappingRepository repository;

    private static final String BASE_URL = "http://localhost:8080/";

    public String shortenUrl(String originalUrl) {
        // FIX: Add https:// if it is missing from original url
        if(!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://"))
            originalUrl = "https://" + originalUrl;

        String shortCode = generateShortCode();

        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping.setShortCode(shortCode);
        repository.save(mapping);

        return BASE_URL + shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        return repository.findByShortCode(shortCode)
                .map(UrlMapping::getOriginalUrl)
                .orElseThrow(() -> new IllegalArgumentException("Short url not found."));
    }

    private String generateShortCode() {
        return UUID.randomUUID().toString().substring(0, 6);
    }
}
