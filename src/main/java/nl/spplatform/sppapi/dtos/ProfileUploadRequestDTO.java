package nl.spplatform.sppapi.dtos;

public class ProfileUploadRequestDTO {

    private String title;
    private String alt;
    private String description;
    private Long profileId;

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
    public Long getProfileId() {
        return profileId;
    }
    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
}
