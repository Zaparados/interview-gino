package de.cj.games.hangman.logic;

import de.cj.games.hangman.words.Word;
import de.cj.games.hangman.words.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.stream.IntStream;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GameStateController {

    public record TryRequest(String character, Gamestate gamestate) {}

    @Autowired
    WordRepository wordRepository;

    public GameStateController(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @GetMapping("/gamestate")
    public Gamestate createGameState() {
        Iterable<Word> words = wordRepository.findAll();

        Random random = new Random();

        int index = random.nextInt((int) words.spliterator().getExactSizeIfKnown());
        Iterator<Word> iterator = words.iterator();

        for (int i = 0; i < index; i++) {
            iterator.next();
        }

        return new Gamestate(iterator.next());
    }

    @PostMapping("/gamestate")
    public Gamestate tryCharacter(@RequestBody TryRequest tryRequest) {
        Gamestate gameState = tryRequest.gamestate;
        char character = tryRequest.character.toLowerCase(Locale.getDefault()).charAt(0);

        int lives = gameState.getLives();
        String word = gameState.getWord().getWord();
        String guessedLetters = gameState.getGuessedLetters();

        gameState.setGuessedLetters(guessedLetters + character + ", ");

        int[] indices = IntStream.range(0, word.length())
                .filter(i -> word.toLowerCase(Locale.getDefault()).charAt(i) == character)
                .toArray();

        StringBuilder newSolution = new StringBuilder(gameState.getSolution());
        for(int index : indices) {
            newSolution.setCharAt(index, character);
        }
        gameState.setSolution(newSolution.toString());

        if(indices.length < 1) {
            gameState.setLives(lives - 1);
        }

        if(lives == 1) {
            gameState.setSolution("YOU LOST \n The word was \"" + word + "\". \n These are the letters you tried: " + guessedLetters);
            return gameState;
        }

        return gameState;
    }
}
