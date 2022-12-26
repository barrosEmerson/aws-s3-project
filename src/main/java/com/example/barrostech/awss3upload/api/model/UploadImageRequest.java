package com.example.barrostech.awss3upload.api.model;

import com.example.barrostech.awss3upload.domain.model.FileReference;
import com.example.barrostech.awss3upload.validation.AllowedContentTypes;
import com.example.barrostech.awss3upload.validation.AllowedFileExtensions;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class UploadImageRequest {

    @NotBlank
    @AllowedFileExtensions({"png","jpg"})
    private String fileName;
    @NotBlank
    @AllowedContentTypes({"image/jpg","image/png"})
    private String contentType;
    @NotNull
    @Min(1)
    private Long contentLength;

    public FileReference toDomain(){
        return FileReference.builder()
                .name(this.fileName)
                .contentType(this.contentType)
                .contenLength(this.contentLength)
                .type(FileReference.Type.IMAGE)
                .build();
    }
}
