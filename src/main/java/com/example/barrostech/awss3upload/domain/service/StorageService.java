package com.example.barrostech.awss3upload.domain.service;

import com.example.barrostech.awss3upload.domain.model.FileReference;
import com.example.barrostech.awss3upload.domain.repository.FileReferenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StorageService {

    private final CloudStorageProvider cloudStorageProvider;

    private final FileReferenceRepository fileReferenceRepository;
    public UploadRequestResult generateUploadUrl(FileReference fileReference) {
        Objects.requireNonNull(fileReference);
        fileReferenceRepository.save(fileReference);
        URL presignedUploadUrl = cloudStorageProvider.generatePresignedUploadUrl(fileReference);
        return new UploadRequestResult(fileReference.getId(),presignedUploadUrl.toString());
    }
}
