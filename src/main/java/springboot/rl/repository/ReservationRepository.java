package springboot.rl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.rl.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
}
