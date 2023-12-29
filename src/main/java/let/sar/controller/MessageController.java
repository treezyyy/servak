package let.sar.controller;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("game")
public class MessageController {
    @PostMapping("five")
    public String getOne() throws IOException {
        BD alex = new BD();
        return alex.GetFive();
    }
    @PostMapping("add/{name}/{score}")
    public String postOne(@PathVariable String name,@PathVariable Integer score) throws IOException {
        BD alax = new BD();
        if (alax.AlreadyExists(name)) {
            alax.UpdatePlayer(name, score);
            return "alax update";
        } else{
            alax.Add_player(name, score);
            return "alax add";
        }
    }

}
