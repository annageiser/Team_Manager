package ch.fhnw.teammanager.repository;

import ch.fhnw.teammanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}