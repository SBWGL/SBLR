package springboot.rl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springboot.rl.model.Reservation;
import springboot.rl.model.Room;
import springboot.rl.model.roomForm.UploadFile;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {

    @Query(value = "select * from room where id =?1", nativeQuery = true)
    public UploadFile findImage(int id);

//    @Query(value = "select * from room where startDate =?1and roomType =2? and maxPerson =?3", nativeQuery = true)
//    public List<Room> findByReservation(String checkOut, String roomType, int countPerson);
}
