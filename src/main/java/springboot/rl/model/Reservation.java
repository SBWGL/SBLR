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
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50)
    private String checkIn;
    @Column(length = 50)
    private String checkOut;
    private String roomType;
    private int countPerson;

//    @ManyToOne
//    @JoinColumn(name = "roomId")
//    private Room room;
}
