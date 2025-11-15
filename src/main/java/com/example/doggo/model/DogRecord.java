package com.example.doggo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dog_record")
public class DogRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text", nullable = false)
    private String url;

    private Long sizeBytes;

    private LocalDateTime createdAt;

    public DogRecord() {}

    public DogRecord(String url, Long sizeBytes) {
        this.url = url;
        this.sizeBytes = sizeBytes;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getUrl() { return url; }
    public Long getSizeBytes() { return sizeBytes; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setUrl(String url) { this.url = url; }
    public void setSizeBytes(Long sizeBytes) { this.sizeBytes = sizeBytes; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}