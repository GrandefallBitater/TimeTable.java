package kai.example.timeTable.controllers;

import kai.example.timeTable.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FileUploadController {
    private final FileUploadService fileUploadService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("planFile") MultipartFile plan,
                                                 @RequestParam("audienceFile") MultipartFile audience,
                                                 @RequestParam("teachersFile") MultipartFile teachers) {
        return fileUploadService.prepareUploadFiles(plan, audience, teachers);
    }
    //TODO попробуй всё таки сделать здесь через один файл, очень муторно, когда принимается 3
}
