package de.cj.games.hangman;

import de.cj.games.hangman.words.Word;
import de.cj.games.hangman.words.WordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class HangmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(HangmanApplication.class, args);
	}

	@Bean
	CommandLineRunner init(WordRepository wordRepository) {
		return args -> {
			Stream.of("Tree", "Pizza", "Mouse", "House", "Bowl").forEach(name -> {
				Word word = new Word(name, "Easy", "Find out yourself!");
				wordRepository.save(word);
			});
		};
	}

}
