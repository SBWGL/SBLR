package springboot.rl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.rl.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByUsername(String username);
}
