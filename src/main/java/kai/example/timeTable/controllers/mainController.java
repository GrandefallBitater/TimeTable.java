package kai.example.timeTable.controllers;

import kai.example.timeTable.Services.mainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainController {
        @Autowired
        private mainService mainService;

        @RequestMapping("/")
        public String main(Model model){
            return "main";
        }

        @RequestMapping("/about")
        public String about(Model model){
                return "aboutUs";
        }

        @RequestMapping("/users")
        public String users(Model model){
                return "Users";
        }
}
