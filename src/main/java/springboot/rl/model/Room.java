package springboot.rl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 20)
    private String uploadImageName;
    @Column(nullable = false, length = 100)
    private String storeImageName;
    @Column(nullable = false, length = 100, unique = true)
    private String roomName;
    @Lob// 대용량 데이터
    private String roomDesc;
    private String roomType;
    private int maxPerson;
    private int price;
}
