package com.example.barrostech.awss3upload.domain.repository;

import com.example.barrostech.awss3upload.domain.model.Ebook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EbookRepository extends JpaRepository<Ebook, UUID> {
}