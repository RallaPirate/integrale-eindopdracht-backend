package nl.spplatform.sppapi.dtos;

public class AuthResponseDTO {

    private String token;
    private Long userId;
    private String role;

    public String getToken(){
        return token;
    }
    public Long getUserId(){
        return userId;
    }
    public String getRole(){
        return role;
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
