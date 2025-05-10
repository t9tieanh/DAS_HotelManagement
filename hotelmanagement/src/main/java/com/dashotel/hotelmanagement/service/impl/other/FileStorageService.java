package com.dashotel.hotelmanagement.service.impl.other;

import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileStorageService {

    Path baseDir;
    Path imageDir;
    Path documentDir;
    Path videoDir;
    Path otherDir;

    public FileStorageService(
            @Value("${file.base-dir}") String baseDir,
            @Value("${file.image-dir}") String imageDir,
            @Value("${file.document-dir}") String documentDir,
            @Value("${file.video-dir}") String videoDir,  @Value("${file.other-dir}") String otherFile) throws IOException {

        this.baseDir = Paths.get(baseDir).toAbsolutePath().normalize();
        this.imageDir = this.baseDir.resolve(imageDir);
        this.documentDir = this.baseDir.resolve(documentDir);
        this.videoDir = this.baseDir.resolve(videoDir);
        this.otherDir = this.baseDir.resolve(otherFile);

        Files.createDirectories(this.imageDir);
        Files.createDirectories(this.documentDir);
        Files.createDirectories(this.videoDir);
        Files.createDirectories(this.otherDir);
    }

    private String getFileExtension(String originalFilename) {
        int lastDotIndex = originalFilename.lastIndexOf(".");
        return (lastDotIndex == -1) ? "" : originalFilename.substring(lastDotIndex);
    }

    private String storeFile(MultipartFile file, Path targetDir) throws IOException {

        String fileExtension = getFileExtension(file.getOriginalFilename());
        String fileName = UUID.randomUUID() + fileExtension;
        Path targetLocation = targetDir.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    public String storeImage(MultipartFile file) throws IOException {
        return storeFile(file, imageDir);
    }

    public String storeDocument(MultipartFile file) throws IOException {
        return storeFile(file, documentDir);
    }

    public String storeVideo(MultipartFile file) throws IOException {
        return storeFile(file, videoDir);
    }

    public String storeOtherFile(MultipartFile file) throws IOException {
        return storeFile(file, otherDir);
    }


    private Resource loadFile(Path dir, String fileName) throws MalformedURLException {
        Path filePath = dir.resolve(fileName).normalize();
        return new UrlResource(filePath.toUri());
    }

    public Resource getImage(String fileName) throws MalformedURLException {
        return loadFile(imageDir, fileName);
    }

    public Resource getDocument(String fileName) throws MalformedURLException {
        return loadFile(documentDir, fileName);
    }

    public Resource getVideo(String fileName) throws MalformedURLException {
        return loadFile(videoDir, fileName);
    }

    public Resource getOtherFile(String fileName) throws MalformedURLException {
        return loadFile(otherDir, fileName);
    }

    // resize ảnh
    public byte[] resizeImage(String fileName, int width, int height) throws IOException {
        Path filePath = imageDir.resolve(fileName).normalize();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            Thumbnails.of(filePath.toFile())
                    .size(width, height)
                    .toOutputStream(outputStream);
        } catch (IOException e) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        return outputStream.toByteArray();
    }

}
