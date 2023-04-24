package com.flashcards.flashcards.services;

import java.util.Optional;
import java.util.UUID;

import com.flashcards.flashcards.models.DeckModel;

public interface DeckService {

    DeckModel save(DeckModel deckModel);

    Optional<DeckModel> findDeckIntoUser(UUID userId, UUID deckId);

    Optional<DeckModel> findById(UUID deckId);

}
