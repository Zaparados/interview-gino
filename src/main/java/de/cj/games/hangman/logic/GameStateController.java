package de.cj.games.hangman.logic;

import de.cj.games.hangman.words.Word;
import de.cj.games.hangman.words.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
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
        char character = tryRequest.character.charAt(0);

        String word = gameState.getWord().getWord();
        gameState.setGuessedLetters(gameState.getGuessedLetters() + character);

        int[] indices = IntStream.range(0, word.length())
                .filter(i -> word.charAt(i) == character)
                .toArray();

        StringBuilder newSolution = new StringBuilder(gameState.getSolution());
        for(int index : indices) {
            newSolution.setCharAt(index, character);
        }
        gameState.setSolution(newSolution.toString());

        if(indices.length < 1) {
            gameState.setLives(gameState.getLives() - 1);
        }

        return gameState;
    }
}
