package kai.example.timeTable.stubs;

import kai.example.timeTable.repositories.loginRepositores.authoritiesRepository;
import kai.example.timeTable.repositories.loginRepositores.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testUserController {
    private userRepository userRepository;
    private authoritiesRepository authoritiesRepository;

    @Autowired
    public testUserController(userRepository userRepository, authoritiesRepository authoritiesRepository) {
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

    @GetMapping("/test")
    public String main(Model model) {

        return "main";
    }
}
