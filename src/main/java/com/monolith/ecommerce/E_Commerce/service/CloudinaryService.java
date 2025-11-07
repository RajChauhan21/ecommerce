package com.monolith.ecommerce.E_Commerce.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.monolith.ecommerce.E_Commerce.entity.User;
import com.monolith.ecommerce.E_Commerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> uploadImage(int id, MultipartFile file) throws IOException {
        try{

            //validate file
            if(file.isEmpty()){
                throw new IllegalArgumentException("Empty file");
            }

            String contentType = file.getContentType();

            //validate content type
            if (contentType == null || !contentType.startsWith("image/")){
                throw new IllegalArgumentException("Only image files are allowed");
            }

            //upload to cloudinary
            Map<String, Object> uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder","ecommerce",
                            "public_id",generatePublicId(file.getOriginalFilename()),
                            "overwrite",true,
                            "resource_type","image"
                    )
            );

            //get user by id
            Optional<User> user = userRepository.findById(id);
            String imageUrl = uploadResult.get("secure_url").toString();
            user.get().setAvatar(imageUrl);
            userRepository.save(user.get());

            return new ResponseEntity<>(uploadResult.get("secure_url").toString(), HttpStatus.CREATED);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String generatePublicId(String originalFileName) {
        String nameWithoutExtension = originalFileName.replaceFirst("[.][^.]+$", "");
        return "img_" + System.currentTimeMillis() + "_" + nameWithoutExtension;
    }
}
