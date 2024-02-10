import stacs.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HangmanJunitTest {

    private HangmanController hangmanController;
    private Hangman hangmanGame;

    @BeforeEach
    public void setUpBeforeEach() {
        hangmanGame = new Hangman();
        hangmanController = new HangmanController();
    }

    @Test
    public void testWordsListNotNull() {
        List<String> wordsList = hangmanController.getWords();
        assertNotNull(wordsList);
    }

    @Test
    public void testWordsListNotEmpty() {
        List<String> wordsList = hangmanController.getWords();
        assertFalse(wordsList.isEmpty());
    }

    @Test
    public void testRandomWordSelection() {
        String word1 = hangmanController.getRandomWord();
        String word2 = hangmanController.getRandomWord();
        assertNotNull(word1);
        assertNotNull(word2);
        assertNotEquals(word1, word2);
    }

    @Test
    public void testWordLength() {
        String word = hangmanController.getRandomWord();
        assertNotNull(word);
        assertTrue(word.length() == 5);
    }

    @Test
    public void testIncorrectFilePath() {
        HangmanController controller = new HangmanController();
        List<String> words = controller.readWordList("nonexistent_file.txt");
        assertTrue(words.isEmpty());
    }

    @Test
    public void testFileNotAccessible() {
        HangmanController controller = new HangmanController();
        List<String> words = controller.readWordList("wordlist.txt"); // Assuming file exists but not accessible
        assertTrue(words.isEmpty());
    }
}
