package com.example.doggo.service;

import com.example.doggo.model.DogRecord;
import com.example.doggo.repository.DogRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class DogApiService {

    private final DogRecordRepository repo;
    private final RestTemplate rest = new RestTemplate();

    public DogApiService(DogRecordRepository repo) {
        this.repo = repo;
    }

    /**
     * Запрашивает https://random.dog/woof.json, сохраняет запись в БД и возвращает её.
     */
    public DogRecord fetchRandomDog() {
        Map<?,?> resp = rest.getForObject("https://random.dog/woof.json", Map.class);
        if (resp == null) {
            throw new RuntimeException("Empty response from random.dog");
        }

        String url = resp.get("url") != null ? resp.get("url").toString() : null;
        Long size = null;
        Object sizeObj = resp.get("fileSizeBytes");
        if (sizeObj instanceof Number) {
            size = ((Number) sizeObj).longValue();
        } else if (sizeObj != null) {
            try { size = Long.parseLong(sizeObj.toString()); } catch (Exception ignored) {}
        }

        DogRecord rec = new DogRecord(url, size);
        return repo.save(rec);
    }
}