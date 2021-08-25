package springboot.rl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.rl.model.Room;

public interface RoomRepository extends JpaRepository<Room,Integer> {
}
