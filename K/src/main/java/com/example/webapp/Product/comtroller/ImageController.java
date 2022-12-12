package com.example.webapp.Product.comtroller;

import com.example.webapp.Mod.Image;
import com.example.webapp.repozitory.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageByID (@PathVariable Long id){
        Image img = imageRepository.findById(id).orElse(null);
        return ResponseEntity.ok().header("fileName",img.getOriginalFileName()).contentType(MediaType.valueOf(img.getContentType())).contentLength(img.getSize()).body(new InputStreamResource(new ByteArrayInputStream(img.getBytes())));
    }

}
