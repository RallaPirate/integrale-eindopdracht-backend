package nl.spplatform.sppapi.repositories;

import nl.spplatform.sppapi.models.Upvote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UpvoteRepository extends JpaRepository<Upvote, Long> {

    boolean existsByUser_userIdAndPost_postId(Long userId, Long postId);

    int countByPost_postId(Long postId);

    List<Upvote> findAllByUser_userId(Long userId);

    void deleteByUser_userIdAndPost_postId(Long userId, Long postId);
}
