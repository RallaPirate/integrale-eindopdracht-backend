package nl.spplatform.sppapi.dtos;

public class AuthRequestDTO {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String userName) {
        this.email = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


