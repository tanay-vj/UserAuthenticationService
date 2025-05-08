package org.example.userauthenticationservicemarch2025.services;


import org.example.userauthenticationservicemarch2025.models.User;
import org.example.userauthenticationservicemarch2025.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User getUserDetails(Long userId) {
        Optional<User> userOptional = userRepo.findUserById(userId);
        return userOptional.orElse(null);
    }
}
