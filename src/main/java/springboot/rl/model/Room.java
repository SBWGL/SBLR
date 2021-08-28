package springboot.rl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    @Column(nullable = false,length = 200)
    private String roomDesc;
    private String roomType;
    private int maxPerson;
    private int price;

    @OneToMany(mappedBy = "room",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"room"})// 무한 참조 방지
    @OrderBy("id desc")
    private List<Review> review;
}
