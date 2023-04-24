package com.flashcards.flashcards.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flashcards.flashcards.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

}
