package kai.example.timeTable.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FileUploadService {
    //TODO попробуй всё таки сделать здесь через один файл, очень муторно, когда принимается 3
    public String prepareUploadFiles(MultipartFile plan, MultipartFile audience, MultipartFile teachers) {
            uploadFiles(plan, "src/main/resources/files/plan.txt");
            uploadFiles(audience, "src/main/resources/files/audience.txt");
            uploadFiles(teachers, "src/main/resources/files/teachers.txt");
        return "Файлы успешно загружены";
    }

    public void uploadFiles(MultipartFile file, String pathName) {
        try {
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(pathName));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            log.debug("Вам не удалось загрузить файл "+ file.getName()+ " " + e.getMessage());
        }
        log.info("Вы удачно загрузили файл " + file.getName());
    }
}
