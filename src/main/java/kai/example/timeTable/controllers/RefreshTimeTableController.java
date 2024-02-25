package kai.example.timeTable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class RefreshTimeTableController {
    @GetMapping(value = "/refresh")
    public String searchRefreshView(Model model) {
        return "chooseGroup";
    }
}
