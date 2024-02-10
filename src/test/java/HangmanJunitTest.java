import stacs.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HangmanJunitTest {

    private HangmanController hangmanController;
    @BeforeEach
    public void setUpBeforeEach() {
        new Hangman();
        hangmanController = new HangmanController();
    }

    @Test
    public void testWordsListNotNull() {
        ArrayList<String> wordsList = hangmanController.getWords();
        assertNotNull(wordsList);
    }

    @Test
    public void testWordsListNotEmpty() {
        ArrayList<String> wordsList = hangmanController.getWords();
        assertFalse(wordsList.isEmpty());
    }

    @Test
    public void testRandomWordSelection() {
        hangmanController.startGame();
        String word1 = hangmanController.getRandomWord();
        String word2 = hangmanController.getRandomWord();
        assertNotNull(word1);
        assertNotNull(word2);
        assertNotEquals(word1, word2);
    }

    @Test
    public void testWordLength() {
        hangmanController.startGame();
        String word = hangmanController.getRandomWord();
        assertNotNull(word);
        assertTrue(word.length() == 5);
    }
}
