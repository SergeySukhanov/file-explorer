package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInformationService {
    @Autowired
    UserRepository userRepository;

    public User getUserByEmail(String userEmail){
        Optional<User> user = userRepository.findByEmail(userEmail);
        if(user.isPresent())
            return user.get();
        else
            return null;
    }


}
