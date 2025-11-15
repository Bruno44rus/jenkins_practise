package com.example.doggo.controller;

import com.example.doggo.dto.DogRecordDto;
import com.example.doggo.model.DogRecord;
import com.example.doggo.repository.DogRecordRepository;
import com.example.doggo.service.DogApiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dog")
public class DogController {

    private final DogApiService service;
    private final DogRecordRepository repo;

    public DogController(DogApiService service, DogRecordRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    /**
     * GET /dog/random
     * Запрос в external API, сохранение записи и возврат DTO
     */
    @GetMapping("/random")
    public DogRecordDto random() {
        DogRecord r = service.fetchRandomDog();
        return new DogRecordDto(r.getId(), r.getUrl(), r.getSizeBytes(), r.getCreatedAt().toString());
    }

    /**
     * GET /dog/history
     * Возвращает всю историю (новые — в конце; при желании можно сортировать)
     */
    @GetMapping("/history")
    public List<DogRecordDto> history() {
        return repo.findAll().stream()
                .map(r -> new DogRecordDto(r.getId(), r.getUrl(), r.getSizeBytes(), r.getCreatedAt().toString()))
                .collect(Collectors.toList());
    }

    /**
     * GET /dog/history/{id}
     */
    @GetMapping("/history/{id}")
    public DogRecordDto getById(@PathVariable Long id) {
        return repo.findById(id)
                .map(r -> new DogRecordDto(r.getId(), r.getUrl(), r.getSizeBytes(), r.getCreatedAt().toString()))
                .orElseThrow(() -> new ResourceNotFoundException("Record not found: " + id));
    }
}