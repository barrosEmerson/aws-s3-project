package com.example.barrostech.awss3upload.api.assembler;


import com.example.barrostech.awss3upload.api.model.EbookRequest;
import com.example.barrostech.awss3upload.domain.model.Ebook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EbookModelDissembler {

    public Ebook toDomain(EbookRequest request) {
        return Ebook.builder()
                .title(request.getTitle())
                .autor(request.getAuthor())
                .build();
    }

    public Ebook toDomain(EbookRequest request, UUID ebookId) {
        return Ebook.builder()
                .id(ebookId)
                .title(request.getTitle())
                .autor(request.getAuthor())
                .build();
    }
}