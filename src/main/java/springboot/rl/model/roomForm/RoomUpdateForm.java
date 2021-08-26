package springboot.rl.model.roomForm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomUpdateForm {

    private int id;
    private UploadFile roomImage;
    private String roomName;
    private String roomDesc;
    private String roomType;
    private int maxPerson;
    private int price;
}
