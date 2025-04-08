package com.example.morse.controllers;

import com.example.morse.models.MorseTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/morse")
public class MorseController {
    @Autowired
    private MorseTranslator morseTranslator;

    @PostMapping("/translate")
    public ResponseEntity<String> translateToMorse(@RequestBody String word) {
        String translatedMessage = morseTranslator.translate(word);
        return ResponseEntity.ok(translatedMessage);
    }
}