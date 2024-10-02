package com.example.lms_cse327.Controllers;

import com.example.lms_cse327.Models.Cohort;
import com.example.lms_cse327.Models.CohortPost;
import com.example.lms_cse327.Models.FileInfo;
import com.example.lms_cse327.Repositories.CohortPostRepo;
import com.example.lms_cse327.Repositories.CohortRepo;
import com.example.lms_cse327.Services.Impl.FileServiceImpl;
import com.example.lms_cse327.Utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CohortPostController {

    @Autowired
    FileServiceImpl fileService;

    @Autowired
    CohortPostRepo cohortPostRepo;

    @Autowired
    CohortRepo cohortRepo;

    @Autowired
    FileUploadUtil fileUploadUtil;

    @PostMapping("/post/{cohortID}")
    public String cohortPostProcess(@PathVariable Long cohortID, Model model,
                                    @RequestParam("postDesc") String postDesc,
                                    @RequestParam("listOfAttachedFiles") MultipartFile[] files) {

        Cohort cohort = cohortRepo.getById(cohortID);

        CohortPost cohortPost = new CohortPost();

        cohortPost.setPostOfCohort(cohort);
        cohortPost.setPostDesc(postDesc);
        try {
            List<FileInfo> fileList = new ArrayList<FileInfo>();
            for (MultipartFile file : files) {
                String fileContentType = file.getContentType();
                String sourceFileContent = new String(file.getBytes(), StandardCharsets.UTF_8);
                String fileName = file.getOriginalFilename();
                FileInfo fileModal = new FileInfo(fileName, sourceFileContent, fileContentType);

                fileUploadUtil.saveFile(file);
                fileList.add(fileModal);
            }

            cohortPost.setListOfAttachedFiles(fileList);

            cohortPostRepo.save(cohortPost);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return "redirect:/cohort/" + cohortID;
    }


    @RequestMapping("/file/{fileName}")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable("fileName") String fileName) throws IOException {

        File file = new File(fileUploadUtil.upload_folder + "/" + fileName);
        if (file.exists()) {

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);


            response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");

            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());

        }
    }


}
