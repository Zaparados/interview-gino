package de.cj.games.hangman.words;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WordRepository extends CrudRepository<Word, Long> {}
