package com.example.shoplaptop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UpLoadService {

    private final ServletContext servletContext;

    public UpLoadService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
    
    public String saveUpLoadFile(MultipartFile file) {
        String fileName = "";
        try{
            byte[] bytes = file.getBytes();
            String rootPath = this.servletContext.getRealPath("/resources/images");

            File dir = new File(rootPath + File.separator + "product");
            if (!dir.exists()){
                dir.mkdirs();
            }

            // Tạo tên tệp với thời gian hiện tại và tên gốc của tệp
            fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();

            // Đường dẫn đầy đủ tới tệp trên server
            String filePath = dir.getAbsolutePath() + File.separator + fileName;

            // Tạo tệp trên server
            File serverFile = new File(filePath);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
