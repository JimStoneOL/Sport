package com.marat.controlworkbymarat.service;


import com.marat.controlworkbymarat.dto.Message;
import com.marat.controlworkbymarat.entity.ImageModel;
import com.marat.controlworkbymarat.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    public Message uploadImage(MultipartFile file) throws IOException {
        ImageModel imageModel = new ImageModel();
        imageModel.setImageBytes(compressBytes(file.getBytes()));
        imageModel.setName(file.getOriginalFilename());
        imageRepository.save(imageModel);
        return new Message("Изображение загружено");
    }

    public ImageModel getImageById(String imageId) {
        Optional<ImageModel> isImageModel = imageRepository.findById(imageId);
        if (isImageModel.isEmpty()) {

            ImageModel imageModel = new ImageModel();
            try {
                File file = new File("src/main/java/com/darx/darx/image/picture.png");
                FileInputStream in = new FileInputStream(file);
                long size = file.length();
                byte[] temp = new byte[(int) size];
                in.read(temp);
                in.close();
                imageModel.setImageBytes(temp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return imageModel;
        }
        ImageModel existImage = isImageModel.get();
        existImage.setImageBytes(decompressBytes(existImage.getImageBytes()));
        return existImage;
    }


    private byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toByteArray();
    }

    private static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException e) {

        }
        return outputStream.toByteArray();
    }
}

