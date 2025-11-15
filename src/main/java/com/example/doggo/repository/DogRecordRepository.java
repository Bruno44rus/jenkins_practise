package com.example.doggo.repository;

import com.example.doggo.model.DogRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRecordRepository extends JpaRepository<DogRecord, Long> {
    // можно добавить вспомогательные методы при необходимости
}