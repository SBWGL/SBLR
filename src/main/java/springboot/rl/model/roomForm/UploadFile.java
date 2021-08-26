package springboot.rl.model.roomForm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFile {

    private String uploadImageName;
    private String storeImageName;
}
