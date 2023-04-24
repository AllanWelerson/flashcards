package com.flashcards.flashcards.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashcards.flashcards.models.CardModel;
import com.flashcards.flashcards.repositories.CardRepository;
import com.flashcards.flashcards.services.CardService;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Override
    public CardModel save(CardModel cardModel) {
        return cardRepository.save(cardModel);
    }

}
