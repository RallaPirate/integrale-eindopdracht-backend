package nl.spplatform.sppapi.dtos;

import java.time.LocalDateTime;

public class ProfileUploadResponseDTO {
    private Long profileUploadId;
    private String filename;
    private String contentType;
    private LocalDateTime uploadedAt;
    private String title;
    private String alt;
    private String description;

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

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getAlt(){
        return alt;
    }

    public void setAlt(String alt){
        this.alt = alt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
