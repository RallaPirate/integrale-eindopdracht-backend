package nl.spplatform.sppapi.dtos;

public class AuthResponseDTO {

    private String token;
    private Long userId;
    private String role;
    private Long profileId;

    public Long getProfileId() {
        return profileId;
    }
    public String getToken(){
        return token;
    }
    public Long getUserId(){
        return userId;
    }
    public String getRole(){
        return role;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
    public void setToken(String token){
        this.token = token;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }
    public void setRole(String role){
        this.role = role;
    }
}
