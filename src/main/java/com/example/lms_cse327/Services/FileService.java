package com.example.lms_cse327.Services;


import com.example.lms_cse327.Models.FileInfo;

import java.util.List;

public interface FileService {
    List<FileInfo> getAllFiles();
    void saveAllFilesList(List<FileInfo> fileList);
}