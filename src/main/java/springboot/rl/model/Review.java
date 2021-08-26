package springboot.rl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length = 200)
    private String contents;

    @ManyToOne// 하나의 방에 여러 개의 댓글
    @JoinColumn(name = "roomId")
    private Room room;

    @ManyToOne// 한명의 유저가 여러 개의 댓글
    @JoinColumn(name = "userId")
    private User user;
    @CreationTimestamp
    private Timestamp creteDate;

    public Review(String contents){
        this.contents = contents;
    }
}
