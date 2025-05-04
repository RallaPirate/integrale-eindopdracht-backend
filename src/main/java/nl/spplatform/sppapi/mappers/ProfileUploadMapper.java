package nl.spplatform.sppapi.mappers;

import nl.spplatform.sppapi.dtos.ProfileUploadRequestDTO;
import nl.spplatform.sppapi.dtos.ProfileUploadResponseDTO;
import nl.spplatform.sppapi.models.Profile;
import nl.spplatform.sppapi.models.ProfileUpload;

public class ProfileUploadMapper {

    public static ProfileUploadResponseDTO toProfileUploadResponseDTO(ProfileUpload profileUpload) {
        ProfileUploadResponseDTO profileUploadResponseDTO = new ProfileUploadResponseDTO();
        profileUploadResponseDTO.setProfileUploadId(profileUpload.getUploadId());
        profileUploadResponseDTO.setFilename(profileUpload.getFilename());
        profileUploadResponseDTO.setContentType(profileUpload.getContentType());
        profileUploadResponseDTO.setUploadedAt(profileUpload.getUploadedAt());
        return profileUploadResponseDTO;
    }

    public static ProfileUpload toProfileUpload(ProfileUploadRequestDTO profileUploadRequestDTO, Profile profile){
        var result = new ProfileUpload();
        result.setDescription(profileUploadRequestDTO.getDescription());
        result.setProfile(profile);
        return result;
    }
}
