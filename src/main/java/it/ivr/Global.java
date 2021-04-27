package it.ivr;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class Global {

    @Value("${upload.path}")
    private String upPath;

    public static String uploadPath;



    public static final ResponseEntity<String> NotFoundResult = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    public static final ResponseEntity<String> OkResult = new ResponseEntity<>(HttpStatus.OK);

    public static String saveFile(MultipartFile file){
        if (file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                System.out.println(uploadDir.mkdir());
            }

            String fileName = file.getOriginalFilename();
            fileName = fileName.substring(fileName.lastIndexOf("."));
            String resultFile = UUID.randomUUID().toString() + fileName;

            try {
                file.transferTo(new File(uploadPath + "/" + resultFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resultFile;
        }
        return "";
    }

    @Value("${upload.path}")
    public void setUploadPath(String path){
        Global.uploadPath = path;
    }
}
