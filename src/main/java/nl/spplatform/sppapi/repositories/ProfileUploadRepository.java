package nl.spplatform.sppapi.repositories;

import nl.spplatform.sppapi.models.Profile;
import nl.spplatform.sppapi.models.ProfileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileUploadRepository extends JpaRepository<ProfileUpload, Long> {
    List<ProfileUpload> findByProfileId(Long profileId);
    List<ProfileUpload> findByProfile(Profile profile);
}
