package kai.example.timeTable.controllers;

import kai.example.timeTable.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("timeTables", searchService.search(group));
        return "timeTableMain";
    }

    @GetMapping(value = "/search")
    public String handleFileUploadNotFound(Model model) {
        model.addAttribute("timeTables", searchService.search("null"));
        return "timeTableMain";
    }
}
