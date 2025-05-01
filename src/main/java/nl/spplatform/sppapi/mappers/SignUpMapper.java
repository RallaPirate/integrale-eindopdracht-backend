package nl.spplatform.sppapi.mappers;

import nl.spplatform.sppapi.dtos.SignUpRequestDTO;
import nl.spplatform.sppapi.models.Profile;
import nl.spplatform.sppapi.models.User;

public class SignUpMapper {

    public static User toUser(SignUpRequestDTO signUpRequestDTO) {
        User user = new User();
        user.setEmail(signUpRequestDTO.getEmail());
        user.setRole("ROLE_USER");
        user.setPassword(signUpRequestDTO.getPassword());

        return user;
    }

    public static Profile toProfile(SignUpRequestDTO signUpRequestDTO){
        Profile profile = new Profile();
        profile.setName(signUpRequestDTO.getName());

        return profile;
    }
}
