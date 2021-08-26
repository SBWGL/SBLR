package springboot.rl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.rl.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
