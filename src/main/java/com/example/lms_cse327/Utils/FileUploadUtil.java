package com.example.lms_cse327.Utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadUtil {

    public final String upload_folder = "src\\main\\resources\\static\\UploadedFiles";

    public boolean saveFile(MultipartFile FileName){
        boolean isUpload =false;

        try{
            Files.copy(FileName.getInputStream()
                    ,Paths.get(upload_folder+File.separator, FileName.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            isUpload = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isUpload;

    }

}
