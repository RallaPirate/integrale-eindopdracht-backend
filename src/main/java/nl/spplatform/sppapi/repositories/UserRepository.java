package nl.spplatform.sppapi.repositories;

import nl.spplatform.sppapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

Optional<User> findByEmail(String email);
User findByUserId(Long userId);
}
