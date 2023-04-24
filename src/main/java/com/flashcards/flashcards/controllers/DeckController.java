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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flashcards.flashcards.dtos.DeckDto;
import com.flashcards.flashcards.models.DeckModel;
import com.flashcards.flashcards.models.UserModel;
import com.flashcards.flashcards.services.DeckService;
import com.flashcards.flashcards.services.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class DeckController {

    @Autowired
    UserService userService;

    @Autowired
    DeckService deckService;

    @PostMapping("/users/{userId}/decks")
    public ResponseEntity<Object> saveDeck(
            @PathVariable(value = "userId") UUID userId,
            @RequestBody DeckDto deckDto) {
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        var deckModel = new DeckModel();
        deckModel.setTitle(deckDto.getTitle());
        deckModel.setUser(userModelOptional.get());
        deckModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        deckModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        deckService.save(deckModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(deckModel);
    }

    @PutMapping("/users/{userId}/decks/{deckId}")
    public ResponseEntity<Object> updateDeck(
            @PathVariable(value = "userId") UUID userId,
            @PathVariable(value = "deckId") UUID deckId,
            @RequestBody DeckDto deckDto) {
        Optional<DeckModel> deckModelOptional = deckService.findDeckIntoUser(userId, deckId);
        if (!deckModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        var deckModel = deckModelOptional.get();
        deckModel.setTitle(deckDto.getTitle());
        deckModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        deckService.save(deckModel);
        return ResponseEntity.status(HttpStatus.OK).body(deckModel);
    }
}
