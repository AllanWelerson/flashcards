package com.flashcards.flashcards.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.flashcards.flashcards.models.UserModel;

public interface UserService {
    
    List<UserModel> findAll();

    UserModel save(UserModel userModel);

    Optional<UserModel> findById(UUID userId);
}
