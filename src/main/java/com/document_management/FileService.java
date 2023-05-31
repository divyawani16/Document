package com.document_management;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String uploadFile(MultipartFile file);
}