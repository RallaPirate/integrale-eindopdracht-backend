package nl.spplatform.sppapi.services;

import nl.spplatform.sppapi.repositories.ProfileRepository;
import nl.spplatform.sppapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public UserService(ProfileRepository profileRepository, UserRepository userRepository){
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

}
