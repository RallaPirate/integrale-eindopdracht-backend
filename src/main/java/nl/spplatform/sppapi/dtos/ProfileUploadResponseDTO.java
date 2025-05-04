package nl.spplatform.sppapi.dtos;

import java.time.LocalDateTime;

public class ProfileUploadResponseDTO {
    private Long profileUploadId;
    private String filename;
    private String contentType;
    private LocalDateTime uploadedAt;

    public Long getProfileUploadId() {
        return profileUploadId;
    }

    public void setProfileUploadId(Long profileUploadId) {
        this.profileUploadId = profileUploadId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
