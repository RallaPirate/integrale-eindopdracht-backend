package nl.spplatform.sppapi.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ProfileUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uploadId;

    private String filename;

    private String filepath;

    private String contentType;

    private String description;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(updatable = false)
    private LocalDateTime uploadedAt;

    public ProfileUpload() {
    }


    public ProfileUpload(Profile profile, String description) {
        this.profile = profile;
        this.description = description;
    }

    @PrePersist
    protected void onUpload() {
        this.uploadedAt = LocalDateTime.now();
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Long getUploadId() {
        return uploadId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
}
