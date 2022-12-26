package com.example.barrostech.awss3upload.domain.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DownloadRequestResult {

    private String downloadSignedUrl;
}
