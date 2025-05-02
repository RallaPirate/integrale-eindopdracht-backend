package nl.spplatform.sppapi.controllers;

import nl.spplatform.sppapi.dtos.ProfileUploadRequestDTO;
import nl.spplatform.sppapi.dtos.ProfileUploadResponseDTO;
import nl.spplatform.sppapi.services.ProfileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileUploadController {

        private final ProfileUploadService profileUploadService;

        public ProfileUploadController(ProfileUploadService profileUploadService) {
            this.profileUploadService = profileUploadService;
        }

        @PostMapping("/{profileId}/upload")
        public ResponseEntity<ProfileUploadResponseDTO> uploadFile(
                @PathVariable Long profileId,
                @RequestParam("file") MultipartFile file,
                @RequestPart("description") String description) throws IOException {

            ProfileUploadRequestDTO profileUploadRequestDTO = new ProfileUploadRequestDTO();
            profileUploadRequestDTO.setProfileId(profileId);
            profileUploadRequestDTO.setDescription(description);

            ProfileUploadResponseDTO responseDTO = profileUploadService.createProfileUpload(file, profileUploadRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }

    @GetMapping("/{profileId}/uploads")
    public ResponseEntity<List<ProfileUploadResponseDTO>> getUploadsByProfile(@PathVariable Long profileId) {
        List<ProfileUploadResponseDTO> uploads = profileUploadService.getUploadsByProfile(profileId);
        return ResponseEntity.ok(uploads);
    }
    }
