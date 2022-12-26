package com.example.barrostech.awss3upload.infra;

import com.example.barrostech.awss3upload.core.properties.StorageProperties;
import com.example.barrostech.awss3upload.domain.model.FileReference;
import com.example.barrostech.awss3upload.domain.service.CloudStorageProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.awscore.AwsRequestOverrideConfiguration;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectAclRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URL;
import java.time.Duration;

@Component
@AllArgsConstructor
public class S3CloudStorageProvider implements CloudStorageProvider {

    private final S3Client s3Client;
    private final S3Presigner presigner;
    private StorageProperties storageProperties;
    @Override
    public URL generatePresignedUploadUrl(FileReference fileReference) {

        var builder = AwsRequestOverrideConfiguration.builder();

        if(fileReference.isPublicAccessible()){
            builder.putRawQueryParameter("x-amz-acl", "public-read");
        }


        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(storageProperties.getS3().getBucket())
                .key(fileReference.getPath())
                .contentType(fileReference.getContentType())
                .contentLength(fileReference.getContenLength())
                .acl(fileReference.isPublicAccessible() ? "public-read" : null)
                .overrideConfiguration(builder.build())
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(30))
                .putObjectRequest(objectRequest)
                .build();

        return presigner.presignPutObject(presignRequest).url();
    }

    @Override
    public URL generatePresignedDownloadUrl(FileReference fileReference) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(storageProperties.getS3().getBucket())
                .key(fileReference.getPath())
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(30))
                .getObjectRequest(getObjectRequest)
                .build();

        return presigner.presignGetObject(presignRequest).url();

    }

}
