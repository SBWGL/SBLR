package springboot.rl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.rl.file.FileStore;
import springboot.rl.model.Room;
import springboot.rl.model.roomForm.RoomSaveForm;
import springboot.rl.service.RoomService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private FileStore fileStore;

    @ResponseBody
    @GetMapping("/getImages/{filename}")
    public Resource getImage(@PathVariable String filename) throws MalformedURLException {
        UrlResource resource = new UrlResource("file:///" + fileStore.getFullPath(filename));
        return resource;
    }

    @GetMapping("/rooms")
    public String rooms(Model model, @PageableDefault(size = 5,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<Room> rooms = roomService.rooms(pageable);
        model.addAttribute("rooms",rooms);
        return "room/rooms";
    }

    @GetMapping("/rooms/saveForm")
    public String addRoomForm(Model model){
        model.addAttribute("room",new RoomSaveForm());
        return "room/addRoom";
    }

    @PostMapping("/rooms/saveForm")
    public String addRoom(@ModelAttribute("room") RoomSaveForm form, BindingResult bindingResult,
                          @RequestParam String selected) throws IOException {
        if (bindingResult.hasErrors()) return "room/addRoom";
        roomService.save(form, selected);
        return "room/rooms";
    }

    @GetMapping("/rooms/detailRoom/{id}")
    public String detailRoom(@PathVariable int id, Model model){
        Room room = roomService.findRoom(id);
        model.addAttribute("room",room);
        return "room/detailRoom";
    }
}
