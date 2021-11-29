//package com.lab1Spring.musiquorum.services;
//
//import com.lab1Spring.musiquorum.exceptions.BadRequestException;
//import com.lab1Spring.musiquorum.exceptions.FileStorageException;
//import com.lab1Spring.musiquorum.models.ClassFile;
//import com.lab1Spring.musiquorum.repositories.FileRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.Objects;
//import java.util.UUID;
//
//@Service
//public class FileService {
//
//    @Autowired
//    private FileRepository dbFileRepository;
//
//    public ClassFile storeFile(MultipartFile file) {
//        // Normalize file name
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//
//        try {
//            // Check if the file's name contains invalid characters
//            if(fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//
//            ClassFile dbClassFile = new ClassFile(fileName, file.getContentType(), new Date(), file.getBytes());
//
//            return dbFileRepository.save(dbClassFile);
//        } catch (IOException | FileStorageException ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
//        }
//    }
//
//    public ClassFile getFile(UUID fileId) {
//        return dbFileRepository.findById(fileId)
//                .orElseThrow(() -> new BadRequestException("File not found with id " + fileId));
//    }
//}
