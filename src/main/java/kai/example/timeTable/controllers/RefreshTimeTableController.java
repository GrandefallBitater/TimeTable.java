package kai.example.timeTable.controllers;

import kai.example.timeTable.models.Session;
import kai.example.timeTable.models.SessionJson;
import kai.example.timeTable.services.RefreshTimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RefreshTimeTableController {
    private final RefreshTimeTableService refreshTimeTableService;
    @Autowired
    public RefreshTimeTableController(RefreshTimeTableService refreshTimeTableService) {
        this.refreshTimeTableService = refreshTimeTableService;
    }

    @GetMapping(value = "/refresh")
    public String searchRefreshView() {
        return "chooseGroup";
    }

    @RequestMapping(value = "/refreshSession")
    public @ResponseBody String RefreshSession(@RequestBody SessionJson sessionJson) {
        Session session = new Session(sessionJson.getId(), sessionJson.getTime(), sessionJson.getTeacherName(), 0,
                sessionJson.getAudienceNumber(), sessionJson.getSubjectName(), sessionJson.getSubjectType());
        return refreshTimeTableService.refreshSession(session);
    }

    @RequestMapping(value = "/deleteSession")
    public @ResponseBody String DeleteSession(@RequestBody Session session) {
        return refreshTimeTableService.deleteSession(session.getId());
    }

    @RequestMapping(value = "/createSession")
    public @ResponseBody String CreateSession(@RequestBody SessionJson sessionJson) {
        Session session = new Session(sessionJson.getTime(), sessionJson.getTeacherName(), sessionJson.getGroupNumber(),
                sessionJson.getAudienceNumber(), sessionJson.getSubjectName(), sessionJson.getSubjectType(), 0);
        return refreshTimeTableService.createSession(session, sessionJson.getGroupNumber(), sessionJson.getDay());
    }
}
