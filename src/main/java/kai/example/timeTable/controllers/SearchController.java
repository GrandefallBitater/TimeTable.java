package kai.example.timeTable.controllers;

import kai.example.timeTable.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SearchController {
    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("group") String group,
                                   Model model) {
        model.addAttribute("timeTable", searchService.search(group));
        return "timeTableMain";
    }

    @GetMapping(value = "/search")
    public @ResponseBody String handleFileUploadNotFound(Model model,
                                                         @RequestParam("group") String group) {
        model.addAttribute("timeTable", searchService.search(group));
        return "timeTableMain";
    }
    @RequestMapping (value = "/refreshView", method = RequestMethod.POST)
    public String searchRefresh(Model model,
                                              @RequestParam("group") String group) {
        model.addAttribute("timeTable", searchService.search(group));
        return "refreshTimeTable";
    }
}
