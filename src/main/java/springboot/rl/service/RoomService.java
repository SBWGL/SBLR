package springboot.rl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.rl.file.FileStore;
import springboot.rl.model.Room;
import springboot.rl.model.roomForm.RoomSaveForm;
import springboot.rl.model.roomForm.UploadFile;
import springboot.rl.repository.RoomRepository;

import java.io.IOException;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private FileStore fileStore;

    @Transactional
    public void save(RoomSaveForm form, String selected) throws IOException {
        UploadFile imageFile = fileStore.storeFile(form.getRoomImage());
        Room room = Room.builder()
                .roomName(form.getRoomName())
                .roomDesc(form.getRoomDesc())
                .id(form.getId())
                .roomType(selected)
                .maxPerson(form.getMaxPerson())
                .price(form.getPrice())
                .uploadImageName(imageFile.getUploadImageName())
                .storeImageName(imageFile.getStoreImageName())
                .build();
        roomRepository.save(room);
    }

    @Transactional(readOnly = true)
    public Page<Room> rooms(Pageable pageable){
        return roomRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Room findRoom(int id){
        return roomRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("요청하신 객실을 찾을 수 없습니다.");
        });
    }
}
