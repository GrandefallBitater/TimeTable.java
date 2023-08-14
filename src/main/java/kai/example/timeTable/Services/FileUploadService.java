package kai.example.timeTable.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

@Service
public class FileUploadService {
        public String prepareUploadFiles(MultipartFile plan, MultipartFile audience, MultipartFile teachers){
            ArrayList<MultipartFile> fileArrayList = new ArrayList<>();
            fileArrayList.add(plan);
            fileArrayList.add(audience);
            fileArrayList.add(teachers);
            for (MultipartFile file:
                 fileArrayList) {
                if (!file.isEmpty()) {
                    try {
                        byte[] bytes = file.getBytes();
                        BufferedOutputStream stream =
                                new BufferedOutputStream(new FileOutputStream(new File( "-uploaded")));
                        stream.write(bytes);
                        stream.close();
                    } catch (Exception e) {
                        return "Вам не удалось загрузить " + e.getMessage();
                    }
                } else {
                    return "Вам не удалось загрузить потому что файл пустой.";
                }
            }
            return "Вы удачно загрузили файлы";
        }
}