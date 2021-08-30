package springboot.rl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.rl.model.BuyInfo;

public interface BuyInfoRepository extends JpaRepository<BuyInfo, Integer> {
}
