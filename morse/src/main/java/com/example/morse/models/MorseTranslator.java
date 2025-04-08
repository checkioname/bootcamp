package com.example.morse.models;

import com.example.morse.Translator;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MorseTranslator implements Translator {
    private HashMap<Character, String> morseRules;

    public MorseTranslator() {
        morseRules = new HashMap<>();
        initializeMorseRules();
    }
    private void initializeMorseRules() {
        morseRules.put('A', ".-");
        morseRules.put('B', "-...");
        morseRules.put('C', "-.-.");
        morseRules.put('D', "-..");
        morseRules.put('E', ".");
        morseRules.put('F', "..-.");
        morseRules.put('G', "--.");
        morseRules.put('H', "....");
        morseRules.put('I', "..");
        morseRules.put('J', ".---");
        morseRules.put('K', "-.");
        morseRules.put('L', ".-..");
        morseRules.put('M', "--");
        morseRules.put('N', "-.");
        morseRules.put('O', "---");
        morseRules.put('P', ".--.");
        morseRules.put('Q', "--.-");
        morseRules.put('R', ".-.");
        morseRules.put('S', "...");
        morseRules.put('T', "-");
        morseRules.put('U', "..-");
        morseRules.put('V', "...-");
        morseRules.put('W', ".--");
        morseRules.put('X', "-..-");
        morseRules.put('Y', "-.--");
        morseRules.put('Z', "--..");
        morseRules.put('1', ".----");
        morseRules.put('2', "..---");
        morseRules.put('3', "...--");
        morseRules.put('4', "....-");
        morseRules.put('5', ".....");
        morseRules.put('6', "-....");
        morseRules.put('7', "--...");
        morseRules.put('8', "---..");
        morseRules.put('9', "----.");
        morseRules.put('0', "-----");
    }

    public String translate(String encodedMessage) {
        StringBuilder decodedMessage = new StringBuilder();
        for (char c : encodedMessage.toUpperCase().toCharArray()) {
            String morseCode = morseRules.get(c);
            if (morseCode != null) {
                decodedMessage.append(morseCode).append(" ");
            }
        }
        return decodedMessage.toString().trim();
    }
}
