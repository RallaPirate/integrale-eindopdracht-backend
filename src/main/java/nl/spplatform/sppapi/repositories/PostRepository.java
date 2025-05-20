package nl.spplatform.sppapi.repositories;

import nl.spplatform.sppapi.models.Post;
import nl.spplatform.sppapi.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByRegionIn(List<String> region, Sort sort);

    Post findByPostId(Long postId);

    List<Post> findByUser(User user);

}

