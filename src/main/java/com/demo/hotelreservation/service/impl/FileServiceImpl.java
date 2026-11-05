package com.demo.hotelreservation.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.hotelreservation.service.FileService;

@Service
public class FileServiceImpl implements FileService {

    private final Path uploadPath;

    public FileServiceImpl(
            @Value("${file.upload-dir:uploads/room-images}") String uploadDir) {

        this.uploadPath = Paths.get(uploadDir)
                .toAbsolutePath()
                .normalize();

        try {
            Files.createDirectories(this.uploadPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory", e);
        }
    }

    @Override
    public String uploadFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Please select a file to upload");
        }

        String originalFileName = file.getOriginalFilename();

        if (originalFileName == null || originalFileName.isBlank()) {
            throw new RuntimeException("Invalid file name");
        }

        String extension = "";

        int dotIndex = originalFileName.lastIndexOf('.');

        if (dotIndex >= 0) {
            extension = originalFileName.substring(dotIndex);
        }

        String savedFileName = UUID.randomUUID() + extension;

        Path destination = uploadPath.resolve(savedFileName).normalize();

        if (!destination.startsWith(uploadPath)) {
            throw new RuntimeException("Invalid file path");
        }

        try {
            Files.copy(
                    file.getInputStream(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING);

            return savedFileName;

        } catch (IOException e) {
            throw new RuntimeException("Could not store the file", e);
        }
    }

    @Override
    public Resource downloadFile(String fileName) {

        if (fileName == null || fileName.isBlank()
                || fileName.contains("/")
                || fileName.contains("\\")) {
            throw new RuntimeException("Invalid file name");
        }

        try {
            Path filePath = uploadPath.resolve(fileName).normalize();

            if (!filePath.startsWith(uploadPath)) {
                throw new RuntimeException("Invalid file path");
            }

            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            }

            throw new RuntimeException("File not found");

        } catch (IOException e) {
            throw new RuntimeException("Could not read the file", e);
        }
    }
}