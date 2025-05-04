package nl.spplatform.sppapi.services;

import nl.spplatform.sppapi.dtos.PostRequestDTO;
import nl.spplatform.sppapi.dtos.PostResponseDTO;
import nl.spplatform.sppapi.mappers.PostMapper;
import nl.spplatform.sppapi.models.Post;
import nl.spplatform.sppapi.models.User;
import nl.spplatform.sppapi.repositories.PostRepository;
import nl.spplatform.sppapi.repositories.UpvoteRepository;
import nl.spplatform.sppapi.repositories.UserRepository;
import nl.spplatform.sppapi.models.Profile;
import nl.spplatform.sppapi.repositories.ProfileRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UpvoteRepository upvoteRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public PostService(PostRepository postRepository, UpvoteRepository upvoteRepository, UserRepository userRepository, ProfileRepository profileRepository){
        this.postRepository = postRepository;
        this.upvoteRepository = upvoteRepository;
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    public List<PostResponseDTO> getAllPosts(List<String> region, String sort, String query) {

        Sort sortOrder;
        Sort defaultSort = Sort.by(Sort.Direction.DESC, "createdAt");
        boolean sortInMemory = false;

        switch (sort != null ? sort : "default") {
            case ("oldest"):
                sortOrder = Sort.by(Sort.Direction.ASC, "createdAt");
                break;
            case ("unpopular"):
            case ("popular"):
                sortInMemory = true;
                sortOrder = null;
                break;
            case ("default"):
            default:
                sortOrder = defaultSort;
                break;
        }

        List<Post> posts;
         if(region != null && !region.isEmpty() && sortOrder != null){
             posts = postRepository.findByRegionIn(region, sortOrder);}
         else if(sortOrder != null) {
             posts = postRepository.findAll(sortOrder);}
         else if(region != null && !region.isEmpty()){
             sortOrder = defaultSort;
             posts = postRepository.findByRegionIn(region, sortOrder);}
         else {
             posts = postRepository.findAll();
         }

        if(query != null && !query.isBlank()){
            String lowerQuery = query.toLowerCase();
            posts = posts.stream()
                    .filter(post -> post.getTitle().toLowerCase().contains(lowerQuery) ||
                            post.getText().toLowerCase().contains(lowerQuery))
                    .collect(Collectors.toList());
        }

        List<PostResponseDTO> response =posts.stream()
                .map(post -> {
                    int upvoteCount = upvoteRepository.countByPost_postId(post.getPostId());
                    return PostMapper.toResponseDTO(post, upvoteCount);
                })
                .collect(Collectors.toList());

        if (sortInMemory) {
            if ("popular".equals(sort)) {
                response.sort(Comparator.comparingInt(PostResponseDTO::getUpvoteCount).reversed());
            } else if ("unpopular".equals(sort)) {
                response.sort(Comparator.comparingInt(PostResponseDTO::getUpvoteCount));
            }
        }

        return response;
    }
//
//
//        }
//        return posts.stream()
//                .map(post -> {
//                    int upvoteCount = upvoteRepository.countByPost_postId(post.getPostId());
//                    return PostMapper.toResponseDTO(post, upvoteCount);
//                })
//                .collect(Collectors.toList());
//
//    }

    public List<PostResponseDTO> getPostsByProfileId(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profiel niet gevonden"));

        User user = profile.getUser();
        List<Post> posts;
        posts = postRepository.findByUser(user);
        List<PostResponseDTO> response =posts.stream()
                .map(post -> {
                    int upvoteCount = upvoteRepository.countByPost_postId(post.getPostId());
                    return PostMapper.toResponseDTO(post, upvoteCount);
                })
                .collect(Collectors.toList());
        return response;
    }

    public PostResponseDTO createPost(PostRequestDTO postRequestDTO){

        Long userId = postRequestDTO.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gebruiker bestaat niet"));
        Post post = PostMapper.toPost(postRequestDTO, user);
        Post savedPost = postRepository.save(post);
        return PostMapper.toResponseDTO(savedPost, 0);
    }
}
