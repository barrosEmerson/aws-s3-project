package com.example.barrostech.awss3upload.api.controller;

import com.example.barrostech.awss3upload.domain.model.FileReference;
import com.example.barrostech.awss3upload.domain.repository.FileReferenceRepository;
import com.example.barrostech.awss3upload.domain.service.DownloadRequestResult;
import com.example.barrostech.awss3upload.domain.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.net.URL;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class DownloadController {

    private final StorageService storageService;
    private final FileReferenceRepository fileReferenceRepository;

    @GetMapping("/downloads/{fileReferenceId}/{fileName}")
    public ResponseEntity<Void> downloadRequest(@PathVariable UUID fileReferenceId) {
        FileReference fileReference = fileReferenceRepository.findById(fileReferenceId)
                .orElseThrow(EntityNotFoundException::new);

        if (fileReference.isPublicAccessible()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        DownloadRequestResult downloadRequestResult = storageService.generateDownloadUrl(fileReference);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", downloadRequestResult.getDownloadSignedUrl());
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}