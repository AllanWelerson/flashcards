package com.flashcards.flashcards.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flashcards.flashcards.models.DeckModel;

public interface DeckRepository extends JpaRepository<DeckModel, UUID> {

    @Query(value = "SELECT * FROM tb_decks WHERE user_id = :userId AND id = :deckId", nativeQuery = true)
    Optional<DeckModel> findDeckIntoUser(@Param("userId") UUID userId, @Param("deckId") UUID deckId);

}
