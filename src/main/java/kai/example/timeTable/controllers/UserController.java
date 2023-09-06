package kai.example.timeTable.controllers;

import kai.example.timeTable.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user")
    public String users(Model model) {
        model.addAttribute("users", userService.findUsers());
        return "Users";
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String postCreateUser(Model model) {
        model.addAttribute("information", true);
        return "createUser";
    }

    @GetMapping(value = "/user/create")
    public String getCreateUser(Model model) {
        model.addAttribute("information", false);
        return "createUser";
    }

    @RequestMapping(value = "/user/change", method = RequestMethod.POST)
    public String postChangeUser(Model model) {
        model.addAttribute("information", true);
        return "changeUser";
    }

    @GetMapping(value = "/user/change")
    public String getChangeUser(Model model) {
        model.addAttribute("information", false);
        return "changeUser";
    }
}
