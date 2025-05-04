package nl.spplatform.sppapi.controllers;

import nl.spplatform.sppapi.dtos.PostResponseDTO;
import nl.spplatform.sppapi.dtos.ProfileUploadRequestDTO;
import nl.spplatform.sppapi.dtos.ProfileUploadResponseDTO;
import nl.spplatform.sppapi.repositories.PostRepository;
import nl.spplatform.sppapi.services.PostService;
import nl.spplatform.sppapi.services.ProfileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileUploadService profileUploadService;
    private final PostRepository postRepository;
    private final PostService postService;
    @Value("${upload.path}")
    private String uploadPath;

    public ProfileController(ProfileUploadService profileUploadService, PostRepository postRepository, PostService postService) {
        this.profileUploadService = profileUploadService;
        this.postRepository = postRepository;
        this.postService = postService;
    }

    @PostMapping(path = "/{profileId}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProfileUploadResponseDTO> uploadFile(@PathVariable Long profileId, @RequestParam("file") MultipartFile file, @RequestParam("description") String description) throws IOException {

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

    @GetMapping("/{profileId}/posts")
    public ResponseEntity<List<PostResponseDTO>> getPostsByProfile(@PathVariable Long profileId) {
        List<PostResponseDTO> result = postService.getPostsByProfileId(profileId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/uploads/files/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path file = Paths.get(uploadPath).resolve(filename).normalize();
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                String contentType = Files.probeContentType(file);
                return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"").body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
