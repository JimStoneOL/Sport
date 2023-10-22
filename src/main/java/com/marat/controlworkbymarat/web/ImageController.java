package com.marat.controlworkbymarat.web;


import com.marat.controlworkbymarat.dto.Message;
import com.marat.controlworkbymarat.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/image")
@CrossOrigin
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public Message uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return imageService.uploadImage(file);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        byte[] image = imageService.getImageById(id).getImageBytes();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

}
