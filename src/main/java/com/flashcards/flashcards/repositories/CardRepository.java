package com.flashcards.flashcards.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flashcards.flashcards.models.CardModel;

public interface CardRepository extends JpaRepository<CardModel, UUID> {

    @Query(value = "SELECT * FROM tb_cards WHERE deck_id = :deckId AND id = :cardId", nativeQuery = true)
    Optional<CardModel> findCardIntoDeck(@Param("deckId") UUID deckId, @Param("cardId") UUID cardId);
}
