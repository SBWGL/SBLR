package springboot.rl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springboot.rl.model.Room;
import springboot.rl.model.roomForm.UploadFile;

public interface RoomRepository extends JpaRepository<Room,Integer> {

    @Query(value = "select * from room where id =?1", nativeQuery = true)
    public UploadFile findImage(int id);
}
