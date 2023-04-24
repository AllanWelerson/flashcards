package com.flashcards.flashcards.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flashcards.flashcards.dtos.CardDto;
import com.flashcards.flashcards.models.CardModel;
import com.flashcards.flashcards.models.DeckModel;
import com.flashcards.flashcards.services.CardService;
import com.flashcards.flashcards.services.DeckService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CardController {

    @Autowired
    CardService cardService;

    @Autowired
    DeckService deckService;

    @PostMapping("/decks/{deckId}/cards")
    public ResponseEntity<Object> saveCard(
            @PathVariable(value = "deckId") UUID deckId,
            @RequestBody CardDto cardDto) {
        Optional<DeckModel> deckModelOptional = deckService.findById(deckId);
        if (!deckModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Deck Not Found");
        }
        var cardModel = new CardModel();
        cardModel.setTextFront(cardDto.getTextFront());
        cardModel.setTextBack(cardDto.getTextBack());
        cardModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        cardModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        cardModel.setNextDate(LocalDateTime.now(ZoneId.of("UTC")));
        cardModel.setSkipMinutes(0);
        cardModel.setDeck(deckModelOptional.get());
        cardService.save(cardModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardModel);
    }
}
