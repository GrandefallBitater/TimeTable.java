package kai.example.timeTable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class errorController {

    @RequestMapping("/Error")
    public String error(){
        return "errorPage";
    }
}
