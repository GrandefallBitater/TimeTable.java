package kai.example.timeTable.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class FileUploadService {
    private Boolean isError = false;

    public String prepareUploadFiles(MultipartFile plan, MultipartFile audience, MultipartFile teachers, MultipartFile groups) {
        uploadFiles(plan, "src/main/resources/files/plan.txt");
        uploadFiles(audience, "src/main/resources/files/audience.txt");
        uploadFiles(teachers, "src/main/resources/files/teachers.txt");
        uploadFiles(groups, "src/main/resources/files/groups.txt");
        if (isError) {
            return "Файлы загрузить не удалось";
        } else {
            return "Файлы успешно загружены";
        }
    }

    public void uploadFiles(MultipartFile file, String pathName) {
        try {
            if (file.isEmpty()) {
                deleteFiles();
                log.info("Не удалось загрузить файл " + file.getName() + " т.к. он не задан");
                return;
            } else {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(pathName));
                stream.write(bytes);
                stream.close();
            }
        } catch (Exception e) {
            deleteFiles();
            log.debug("Вам не удалось загрузить файл " + file.getName() + " " + e.getMessage());
        }
        log.info("Вы удачно загрузили файл " + file.getName());
    }

    private void deleteFiles() {
        File directory = new File("src/main/resources/files");
        try {
            FileUtils.cleanDirectory(directory);
        } catch (IOException e) {
            throw new RuntimeException("не получилось удалить файлы");
        }
        isError = true;
    }
}
