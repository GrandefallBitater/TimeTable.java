package kai.example.timeTable.controllers;

import kai.example.timeTable.models.User;
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
    public String postChangeUser(@RequestParam("user") String username, Model model) {
        model.addAttribute("user", userService.findUser(username));
        return "changeUser";
    }

    @GetMapping(value = "/user/change")
    public String getChangeUser(Model model) {
        User user = new User();
        user.setName("null");
        model.addAttribute("user", user);
        return "changeUser";
    }

    @RequestMapping(value = "/user/change/refresh", method = RequestMethod.POST)
    public String postChangeRefreshUser(@RequestParam("username") String username,
                                        @RequestParam("password") String password,
                                        @RequestParam("enabled") Boolean enabled,
                                        @RequestParam("access") String access,
                                        @RequestParam("oldName") String oldName,
                                        Model model) {
        User user = new User(username, password, enabled, access);
        userService.changeUser(user, oldName);
        model.addAttribute("users", userService.findUsers());
        return "Users";
    }

    @RequestMapping(value = "/user/create/refresh", method = RequestMethod.POST)
    public String postCreateRefreshUser(@RequestParam("username") String username,
                                        @RequestParam("password") String password,
                                        @RequestParam("enabled") Boolean enabled,
                                        @RequestParam("access") String access,
                                        Model model) {
        User user = new User(username, password, enabled, access);
        userService.createUser(user);
        model.addAttribute("users", userService.findUsers());
        return "Users";
    }

    @RequestMapping(value = "/user/change/remove", method = RequestMethod.POST)
    public String postChangeRemoveUser(@RequestParam("oldName") String username,
                                        Model model) {
        User user = new User();
        user.setName(username);
        userService.removeUser(user);
        model.addAttribute("users", userService.findUsers());
        return "Users";
    }
}
