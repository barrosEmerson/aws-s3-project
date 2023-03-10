package com.example.barrostech.awss3upload.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class EbookModel {
    private UUID id;
    private String title;
    private String author;
}