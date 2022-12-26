package com.example.barrostech.awss3upload.domain.service;

import com.example.barrostech.awss3upload.domain.model.FileReference;

import java.net.URL;

public interface CloudStorageProvider {
    URL generatePresignedUploadUrl(FileReference fileReference);
    URL generatePresignedDownloadUrl(FileReference fileReference);
}
