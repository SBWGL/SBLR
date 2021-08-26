package springboot.rl.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springboot.rl.config.auth.PrincipalDetails;
import springboot.rl.dto.ResponseDto;
import springboot.rl.model.Review;
import springboot.rl.service.RoomService;

@Slf4j
@RestController
public class RoomAPIController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/api/rooms/detailRoom/{id}/review")
    public ResponseDto<Integer> replySave(@PathVariable int id, @RequestBody Review review,
                                          @AuthenticationPrincipal PrincipalDetails principal){
        roomService.reviewSave(principal.getUser(),id,review);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
