package alcohol.backend;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private String username;

    @PostMapping("/set")
    public String setUsername(@RequestParam String username) {
        this.username = username;
        return "Username erfolgreich gesetzt: " + username;
    }

    @GetMapping("/get")
    public String getUsername() {
        if (this.username == null) {
            return "Kein Username gesetzt!";
        }

        return this.username;
    }
}
