package kai.example.timeTable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class createTimeTableController {
    @RequestMapping("/create")
    public String show(){
        return "createTimeTable";
    }
}
