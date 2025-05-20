package nl.spplatform.sppapi.dtos;

public class ProfileUploadRequestDTO {

    private String description;
    private Long profileId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
}
