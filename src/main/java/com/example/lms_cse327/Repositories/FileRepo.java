package com.example.lms_cse327.Repositories;

import com.example.lms_cse327.Models.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<FileInfo, Long> {

}
