package com.trabalhoa3.delivery.domain.services;

import com.trabalhoa3.delivery.domain.entities.User;
import com.trabalhoa3.delivery.domain.exceptions.UserExistsException;
import com.trabalhoa3.delivery.domain.ports.UserRepository;

public class CreateNewUser {
    UserRepository userRepo;

    public CreateNewUser(UserRepository userRepo) {
        System.out.println("1: " + userRepo.toString());
        this.userRepo = userRepo;
    }

    public User execute(User user) {
        System.out.println(user.toString());
        User userFromDB = this.userRepo.findByEmail(user.getEmail());

        if (userFromDB != null) {
            throw new UserExistsException();
        }

        return this.userRepo.save(user);
    }
}
