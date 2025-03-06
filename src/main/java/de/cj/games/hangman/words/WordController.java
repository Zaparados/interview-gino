package de.cj.games.hangman.words;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WordController {

    @Autowired
    WordRepository wordRepository;

    public WordController(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @GetMapping("/words")
    public List<Word> getWords() {
        return (List<Word>) wordRepository.findAll();
    }

    @PostMapping("/words")
    void addWords(@RequestBody Iterable<Word> words) {
        wordRepository.saveAll(words);
    }
}
