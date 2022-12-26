package com.example.barrostech.awss3upload.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@Builder
@Entity
public class FileReference {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2",strategy = "uuid2")
    @Column(name = "id",columnDefinition = "char(36)")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private OffsetDateTime createdAt;
    private String name;

    private String contentType;
    private Long contenLength;

    @Builder.Default
    private Boolean temp;
    @Enumerated(EnumType.STRING)
    private Type type;

    protected FileReference(){}

    public FileReference(UUID id, OffsetDateTime createdAt, String name,String contentType, Long contenLength, Boolean temp, Type type) {

        Objects.requireNonNull(name);
        Objects.requireNonNull(contentType);
        Objects.requireNonNull(contenLength);
        Objects.requireNonNull(name);

        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.contentType = contentType;
        this.contenLength = contenLength;
        this.temp = temp;
        this.type = type;
    }

    public void setTemp(Boolean temp) {
        this.temp = temp;
    }

    public String getPath() {
        return this.id+"/"+this.name;
    }

    @Getter
    @AllArgsConstructor
    public enum Type{
        DOCUMENT(false),
        IMAGE(true);

        private final boolean publicAccessible;
    }

    public boolean isPublicAccessible(){
        return this.type.isPublicAccessible();
    }

}
