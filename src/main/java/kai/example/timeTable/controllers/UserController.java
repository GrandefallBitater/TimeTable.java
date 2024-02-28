package kai.example.timeTable.controllers;

import kai.example.timeTable.models.User;
import kai.example.timeTable.models.UserJson;
import kai.example.timeTable.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user")
    public String users(Model model) {
        model.addAttribute("users", userService.findUsers());
        return "Users";
    }

    @RequestMapping(value = "/changeUser", method = RequestMethod.POST)
    public @ResponseBody String postChangeUser(@RequestBody UserJson JSON) {
        User user = new User(JSON.getUsername(), JSON.getPassword(), JSON.getEnabled(), JSON.getRole());
        return userService.changeUser(user, JSON.getOldUserName());
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public @ResponseBody String postCreateUser(@RequestBody UserJson JSON) {
        User user = new User(JSON.getUsername(), JSON.getPassword(), JSON.getEnabled(), JSON.getRole());
        return userService.createUser(user);
    }

    @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
    public @ResponseBody String postDeleteUser(@RequestBody UserJson JSON) {
        User user = new User();
        user.setName(JSON.getUsername());
        return userService.removeUser(user);
    }
}
