package africa.musicmart.data.repositories;

import africa.musicmart.data.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, String> {
    Optional<AppUser> findByEmailIgnoreCase(String email);
}
