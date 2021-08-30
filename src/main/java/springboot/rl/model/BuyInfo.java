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
public class BuyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buyNum;
    private int paid;
    @Column(length = 100)
    private String buyerName;
    @Column(length = 100)
    private String buyRoom;

}
