package de.cj.games.hangman.logic;

import de.cj.games.hangman.words.Word;
import org.apache.logging.log4j.util.Strings;

public class Gamestate {

    private final Word word;
    private String solution;
    private String guessedLetters;
    private int lives;

    public Gamestate(Word word) {
        this.word = word;
        StringBuilder solution = new StringBuilder();
        for(char letter : word.getWord().toCharArray()) {
            solution.append("_");
        }
        this.solution = solution.toString();
        this.guessedLetters = Strings.EMPTY;
        this.lives = 5;
    }


    public Word getWord() {
        return word;
    }


    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }


    public String getGuessedLetters() {
        return guessedLetters;
    }

    public void setGuessedLetters(String guessedLetters) {
        this.guessedLetters = guessedLetters;
    }


    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
