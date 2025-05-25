package nl.spplatform.sppapi.services;

import jakarta.persistence.EntityNotFoundException;
import nl.spplatform.sppapi.dtos.ProfileUploadRequestDTO;
import nl.spplatform.sppapi.dtos.ProfileUploadResponseDTO;
import nl.spplatform.sppapi.mappers.ProfileUploadMapper;
import nl.spplatform.sppapi.models.Profile;
import nl.spplatform.sppapi.models.ProfileUpload;
import nl.spplatform.sppapi.repositories.ProfileRepository;
import nl.spplatform.sppapi.repositories.ProfileUploadRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfileUploadService {

    private final ProfileUploadRepository profileUploadRepository;
    private final ProfileRepository profileRepository;
    @Value("${upload.path}")
    private String uploadPath;

    public ProfileUploadService(ProfileUploadRepository profileUploadRepository, ProfileRepository profileRepository) {
        this.profileUploadRepository = profileUploadRepository;
        this.profileRepository = profileRepository;
    }

    public ProfileUploadResponseDTO createProfileUpload(MultipartFile file, ProfileUploadRequestDTO profileUploadRequestDTO) throws IOException {
        Profile profile = profileRepository.findById(profileUploadRequestDTO.getProfileId()).orElseThrow(() -> new EntityNotFoundException("Profiel niet gevonden"));

        String originalFilename = file.getOriginalFilename();
        String extension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        }

        Path uploadDir = Paths.get(uploadPath);
        if (Files.notExists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        String newFilename = UUID.randomUUID() + extension;
        Path filePath = uploadDir.resolve(newFilename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        ProfileUpload profileUpload = new ProfileUpload();
        profileUpload.setFilename(newFilename);
        profileUpload.setFilepath(filePath.toString());
        profileUpload.setContentType(file.getContentType());
        profileUpload.setDescription(profileUploadRequestDTO.getDescription());
        profileUpload.setProfile(profile);
        profileUpload.setTitle(profileUploadRequestDTO.getTitle());
        profileUpload.setAlt(profileUploadRequestDTO.getAlt());

        profileUploadRepository.save(profileUpload);

        return ProfileUploadMapper.toProfileUploadResponseDTO(profileUpload);
    }

    public List<ProfileUploadResponseDTO> getUploadsByProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new EntityNotFoundException("Profiel niet gevonden"));
        Sort defaultSort = Sort.by(Sort.Direction.DESC, "uploadedAt");

        List<ProfileUpload> uploads = profileUploadRepository.findByProfile(profile, defaultSort);
        return uploads.stream().map(ProfileUploadMapper::toProfileUploadResponseDTO).collect(Collectors.toList());
    }
}
