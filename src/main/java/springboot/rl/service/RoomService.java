package springboot.rl.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.rl.file.FileStore;
import springboot.rl.model.Reservation;
import springboot.rl.model.Review;
import springboot.rl.model.Room;
import springboot.rl.model.User;
import springboot.rl.model.BuyInfo;
import springboot.rl.model.roomForm.RoomSaveForm;
import springboot.rl.model.roomForm.RoomUpdateForm;
import springboot.rl.model.roomForm.UploadFile;
import springboot.rl.repository.BuyInfoRepository;
import springboot.rl.repository.ReservationRepository;
import springboot.rl.repository.ReviewRepository;
import springboot.rl.repository.RoomRepository;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private FileStore fileStore;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private BuyInfoRepository buyInfoRepository;

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

    @Transactional
    public void update(int id,RoomSaveForm form, String selected) throws IOException {
        Room room = roomRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("요청하신 Id를 찾을 수 없습니다.");
        });
        UploadFile uploadFile = fileStore.storeFile(form.getRoomImage());
        room.setRoomName(form.getRoomName());
        room.setRoomDesc(form.getRoomDesc());
        if (selected.equals(null)){
            room.setRoomType(form.getRoomType());
        }else{
            room.setRoomType(selected);
        }
        room.setId(form.getId());
        room.setPrice(form.getPrice());
        room.setMaxPerson(form.getMaxPerson());
        if (uploadFile.equals(null)){
            UploadFile image = findImage(id);
            room.setUploadImageName(image.getUploadImageName());
            room.setStoreImageName(image.getStoreImageName());
        }else {
            room.setUploadImageName(uploadFile.getUploadImageName());
            room.setStoreImageName(uploadFile.getStoreImageName());
        }
    }

    @Transactional
    public void reviewSave(User user, int id, Review review){
        Room room = roomRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("요청하신 객실을 찾을 수 없습니다.");
        });
        review.setUser(user);
        review.setRoom(room);
        log.info("저장된 review={}",review);
        reviewRepository.save(review);
    }

    public RoomUpdateForm transRoom(Room room){
        RoomUpdateForm roomUpdateForm = RoomUpdateForm.builder()
                .roomName(room.getRoomName())
                .roomDesc(room.getRoomDesc())
                .roomType(room.getRoomType())
                .roomImage(new UploadFile(room.getUploadImageName(), room.getStoreImageName()))
                .price(room.getPrice())
                .id(room.getId())
                .maxPerson(room.getMaxPerson())
                .build();
        return roomUpdateForm;
    }

    public UploadFile findImage(int id){
        return roomRepository.findImage(id);
    }

    public void deleteReview(int reviewId){
        reviewRepository.delete(reviewRepository.findById(reviewId).orElseThrow(() -> {
            return new IllegalArgumentException("요청하신 리뷰를 찾을 수 없습니다.");
        }));
    }

//    public void saveReservation(Reservation reservation){
//        reservationRepository.save(reservation);
//    }

    public List<Room> findRoomByReservation(Reservation reservation) throws ParseException {
        List<Room> rooms = roomRepository.findAll();
        List<Room> findRoom = new ArrayList<>();
        for (Room room : rooms) {
            String[] convertDate = reservation.getCheckIn().split("/");
            String month = convertDate[0];
            String day = convertDate[1];
            String year = convertDate[2];
            Timestamp RstartDate = Timestamp.valueOf(year + "-" + month + "-" + day + " 00:00:00.000000");
            Timestamp endDate = room.getEndDate();
            if (RstartDate.after(endDate) && reservation.getRoomType().equals(room.getRoomType()) &&
                    reservation.getCountPerson() <= room.getMaxPerson()){
                findRoom.add(room);
            }
        }
        for (Room room : findRoom) {
            findRooms.add(room);
        }
        return findRoom;
    }

    public static List<Room> findRooms = new ArrayList<>();

    public List<Room> getFindRoom(){
        return findRooms;
    }

    public void buyInfo(BuyInfo buyInfo){
        buyInfoRepository.save(buyInfo);
    }

    public List<BuyInfo> getByInfo(){
        return buyInfoRepository.findAll();
    }

}
