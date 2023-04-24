package com.flashcards.flashcards.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashcards.flashcards.models.DeckModel;
import com.flashcards.flashcards.repositories.DeckRepository;
import com.flashcards.flashcards.services.DeckService;

@Service
public class DeckServiceImpl implements DeckService {

    @Autowired
    DeckRepository deckRepository;

    @Override
    public DeckModel save(DeckModel deckModel) {
        return deckRepository.save(deckModel);
    }

    @Override
    public Optional<DeckModel> findDeckIntoUser(UUID userId, UUID deckId) {
        return deckRepository.findDeckIntoUser(userId, deckId);
    }

    @Override
    public Optional<DeckModel> findById(UUID deckId) {
        return deckRepository.findById(deckId);
    }

}
