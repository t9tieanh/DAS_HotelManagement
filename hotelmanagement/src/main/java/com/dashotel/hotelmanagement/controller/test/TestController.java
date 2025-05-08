package com.dashotel.hotelmanagement.controller.test;

import com.dashotel.hotelmanagement.dto.common.test.ImageUpload;
import com.dashotel.hotelmanagement.service.other.FileStorageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestController {

    FileStorageService fileStorageService;

    @PostMapping(value = "update-profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageUpload> updateProfile(@RequestParam("avatar") MultipartFile file) throws IOException {
        String result = fileStorageService.storeImage(file);
        return ResponseEntity.ok(
                ImageUpload.builder()
                        .avatar(result)
                        .build()
        );
    }
}
