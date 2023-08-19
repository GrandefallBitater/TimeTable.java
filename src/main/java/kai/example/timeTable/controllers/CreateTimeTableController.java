package kai.example.timeTable.controllers;

import kai.example.timeTable.services.createTimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CreateTimeTableController {
    private createTimeTableService createTimeTableService;

    @Autowired
    public CreateTimeTableController(createTimeTableService createTimeTableService) {
        this.createTimeTableService = createTimeTableService;
    }

    @RequestMapping("/create")
    public String show() {
        return "createTimeTable";
    }

    @RequestMapping("/create/createTimeTable")
    public @ResponseBody String create() {
        createTimeTableService.createTimeTable();
        return "создание началось";
    }
}
