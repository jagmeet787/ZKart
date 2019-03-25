package zkart.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import zkart.entity.Item;
import zkart.exception.StorageException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageService {

    public void uploadFile(MultipartFile file,String fileName) {

        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }

        try {
            InputStream is = file.getInputStream();
            String path = "";
            System.out.println("storage service");
            if (System.getProperty("os.name").toLowerCase().contains("windows")) 
            	path = "C:\\itemImages\\";
            else path = "/home/satyam/Desktop/itemImages/";
            
            Path dest=Paths.get(path+fileName);
            
            System.out.println("Saving " + fileName + " in " + path + " .");
            
            Files.copy(is, dest,
                    StandardCopyOption.REPLACE_EXISTING);
        
        } catch (IOException e) {

            String msg = String.format("Failed to store file", file.getName());

            throw new StorageException(msg, e);
        }

    }
}