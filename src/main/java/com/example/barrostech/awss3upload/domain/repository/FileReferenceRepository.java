package com.example.barrostech.awss3upload.domain.repository;

import com.example.barrostech.awss3upload.domain.model.FileReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileReferenceRepository extends JpaRepository<FileReference, UUID> {
}
