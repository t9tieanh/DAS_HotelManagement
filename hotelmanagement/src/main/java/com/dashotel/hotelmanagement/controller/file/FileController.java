package com.dashotel.hotelmanagement.controller.file;


import com.dashotel.hotelmanagement.service.other.FileStorageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Source;
import java.io.IOException;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileController {
    FileStorageService fileStorageService;

    @GetMapping("/image/resize/{fileName}")
    public ResponseEntity<byte[]> getImageResized(@PathVariable String fileName) throws IOException {
        byte[] resizedImage = fileStorageService.resizeImage(fileName, 200, 200);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"") // 🔥 Hiển thị file thay vì tải về
                .body(resizedImage);
    }

    @GetMapping("/image/{fileName}")
    public ResponseEntity<byte[]> getOriginalImage(@PathVariable String fileName) throws IOException {
        Resource image = fileStorageService.getImage(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image.getInputStream().readAllBytes());
    }

}
