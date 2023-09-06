package kai.example.timeTable.controllers;

import kai.example.timeTable.services.mainService;
import kai.example.timeTable.stubs.TimeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    private mainService mainService;

    @Autowired
    public MainController(mainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("/")
    public String main(Model model) {
        return "main";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        return "aboutUs";
    }
}
