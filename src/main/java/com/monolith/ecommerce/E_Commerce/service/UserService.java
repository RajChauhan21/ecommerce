package com.monolith.ecommerce.E_Commerce.service;

import com.monolith.ecommerce.E_Commerce.DTO.LoginRequest;
import com.monolith.ecommerce.E_Commerce.DTO.UserDto;
import com.monolith.ecommerce.E_Commerce.DTO.UserProductDto;
import com.monolith.ecommerce.E_Commerce.DTO.UserProfileDto;
import com.monolith.ecommerce.E_Commerce.entity.User;
import com.monolith.ecommerce.E_Commerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<?> register(User user){
        Optional<User> findUser = repository.findByEmail(user.getEmail());
        if (findUser.isPresent()){
            return new ResponseEntity<>("email already present", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        emailService.sendEmailForSignUp(user.getEmail(),"chavanshivam998@gmail.com");
        return new ResponseEntity<>(repository.save(user), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> signIn(LoginRequest user){
        Optional<User> findUser = repository.findByEmail(user.getEmail());
        System.out.println(findUser.get().getPassword());
        if (findUser.isPresent() && bCryptPasswordEncoder.matches(user.getPassword(),findUser.get().getPassword())){
            UserProfileDto userProfileDto = new UserProfileDto();
            userProfileDto.setId(findUser.get().getId());
            userProfileDto.setName(findUser.get().getName());
            userProfileDto.setEmail(findUser.get().getEmail());
            userProfileDto.setPhone(findUser.get().getPhone());
            userProfileDto.setToken(jwtService.generateToken(findUser.get()));
            userProfileDto.setAddress(findUser.get().getAddress());
            userProfileDto.setCity(findUser.get().getCity());
            userProfileDto.setAvatar(findUser.get().getAvatar());
            userProfileDto.setCountry(findUser.get().getCountry());
            userProfileDto.setPostalCode(findUser.get().getPostalCode());
            return new ResponseEntity<>(userProfileDto,HttpStatus.ACCEPTED);
        }
//        emailService.sendEmailForSignIn(user.getEmail(),"chavanshivam998@gmail.com");
        return new ResponseEntity<>("Please check email and password",HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<UserProductDto> getUserById(int id) {
        User user = repository.findById(id).get();
        UserProductDto userProductDto = new UserProductDto();
        userProductDto.setId(user.getId());
        userProductDto.setEmail(user.getEmail());
        userProductDto.setName(user.getName());
//        userProductDto.setClothingPreferences(user.getClothingPreferences());
//        userProductDto.setAppliancePreferences(user.getAppliancePreferences());
//        userProductDto.setHouseholdPreferences(user.getHouseholdPreferences());
        userProductDto.setCarts(user.getCarts());
        return new ResponseEntity<>(userProductDto,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> updateProfile(UserProfileDto profileDto){
        Optional<User> user = repository.findById(profileDto.getId());
        if (user.isEmpty()){
            return new ResponseEntity<>("User not found",HttpStatus.BAD_REQUEST);
        }

        user.get().setEmail(profileDto.getEmail());
        user.get().setAddress(profileDto.getAddress());
        user.get().setAvatar(profileDto.getAvatar());
        user.get().setCountry(profileDto.getCountry());
        user.get().setPhone(profileDto.getPhone());
        user.get().setPostalCode(profileDto.getPostalCode());
        user.get().setName(profileDto.getName());
        user.get().setCity(profileDto.getCity());

        repository.save(user.get());

        return new ResponseEntity<>(profileDto,HttpStatus.ACCEPTED);

    }

    public ResponseEntity<?> getUserProfileById(int id){
        Optional<User> user = repository.findById(id);
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setId(user.get().getId());
        userProfileDto.setName(user.get().getName());
        userProfileDto.setEmail(user.get().getEmail());
        userProfileDto.setToken(jwtService.generateToken(user.get()));
        userProfileDto.setAddress(user.get().getAddress());
        userProfileDto.setCity(user.get().getCity());
        userProfileDto.setAvatar(user.get().getAvatar());
        userProfileDto.setCountry(user.get().getCountry());
        userProfileDto.setPostalCode(user.get().getPostalCode());
        userProfileDto.setPhone(user.get().getPhone());
        if(user.isEmpty()){
            return new ResponseEntity<>("invalid id", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userProfileDto,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> uploadUserProfileImage(int id, MultipartFile file) throws IOException {
        String uploadDir = "uploads/profile-images/";

        if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")){ //eliminates NPE
            return new ResponseEntity<>("only images allowed",HttpStatus.BAD_REQUEST);
        }

        File folder = new File(uploadDir);
        if (!folder.exists()){
            folder.mkdirs();
        }

        String fileName = id +""+ file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir,fileName);
        String fileUrl = "http://localhost:8080/cdn/profile-images/"+fileName;
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Optional<User> user = repository.findById(id);
        user.get().setAvatar(fileUrl);
        repository.save(user.get());

        return new ResponseEntity<>(fileUrl,HttpStatus.ACCEPTED);
    }
}
