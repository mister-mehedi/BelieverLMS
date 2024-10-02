package com.example.lms_cse327.Services.Impl;
import java.util.List;

import com.example.lms_cse327.Models.FileInfo;
import com.example.lms_cse327.Repositories.FileRepo;
import com.example.lms_cse327.Services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    // @Autowired annotation used to inject
    // the object dependency of FileRepository
    @Autowired
    FileRepo fileRepository;

    @Override
    public List<FileInfo> getAllFiles() {
        // fetch all the files form database
        return fileRepository.findAll();
    }
    public void saveAllFilesList(List<FileInfo> fileList) {
        // Save all the files into database
        for (FileInfo fileModal : fileList)
            fileRepository.save(fileModal);
    }
}
