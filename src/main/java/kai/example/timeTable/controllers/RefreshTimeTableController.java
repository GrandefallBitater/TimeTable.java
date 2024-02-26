package kai.example.timeTable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class RefreshTimeTableController {
    @GetMapping(value = "/refresh")
    public String searchRefreshView(Model model) {
        return "chooseGroup";
    }

    @RequestMapping(value = "/refreshSession")
    public String RefreshSession(Model model,
                                 @RequestParam("lesson-name") String name,
                                 @RequestParam("time") String time,
                                 @RequestParam("teacher") String teacher,
                                 @RequestParam("type") String type,
                                 @RequestParam("audienceNumber") String audienceNumber) {
        //тут вызов проверок на кореектность введённых данных и возвращение расписания для обновления на странице (сейчас не работает)
        return "refreshTimeTable";
    }
}
