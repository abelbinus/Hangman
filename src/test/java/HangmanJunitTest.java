import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stacs.Hangman;
import stacs.HangmanController;
import stacs.HangmanView;

/**
 * Test class for HangmanController.
 */
public class HangmanJunitTest {

    private HangmanController hangmanController;
    private HangmanView hangmanView;
    /**
     * Sets up objects before each test method.
     */
    @BeforeEach
    public void setUpBeforeEach() {
        // Instantiating a new Hangman object
        //noinspection InstantiationOfUtilityClass
        new Hangman();
        hangmanController = new HangmanController();
        hangmanView = new HangmanView();
    }

    /**
     * Tests if the words list is not null.
     */
    @Test
    public void testWordsListNotNull() {
        ArrayList<String> wordsList = hangmanController.getWords("wordlist-test.txt");
        assertNotNull(wordsList);
    }

    /**
     * Tests if the words list is not empty.
     */
    @Test
    public void testWordsListNotEmpty() {
        ArrayList<String> wordsList = hangmanController.getWords("wordlist-test.txt");
        assertFalse(wordsList.isEmpty());
    }

    /**
     * Tests the length of a randomly selected word.
     */
    @Test
    public void testWordLength() {
        hangmanController.setObject(hangmanView);
        ArrayList<String> wordsList = hangmanController.getWords("wordlist-test.txt");
        String word = hangmanController.getRandomWord(wordsList);
        assertNotNull(word);
        assertEquals(5, word.length());
    }

    /**
     * Tests getting the initial hidden word.
     */
    @Test
    public void testGetHiddenWordInitial() {
        // Arrange
        HangmanController hangmanController = new HangmanController();

        // Act
        String hiddenWord = hangmanController.getHiddenWord();

        // Assert
        assertNotNull(hiddenWord);
        assertEquals(5, hiddenWord.length());
    }

    /**
     * Tests getting the hidden word after setting.
     */
    @Test
    public void testGetHiddenWordAfterSetting() {
        // Arrange
        HangmanController hangmanController = new HangmanController();
        String expectedHiddenWord = "Surya";

        // Act
        hangmanController.setHiddenWord(expectedHiddenWord);
        String hiddenWord = hangmanController.getHiddenWord();

        // Assert
        assertNotNull(hiddenWord);
        assertEquals(expectedHiddenWord, hiddenWord);
    }

    /**
     * Tests the game end condition (win).
     */
    @Test
    public void testGameEndWin() {
        // Arrange
        HangmanController hangmanController = new HangmanController();
        hangmanController.setUserLife(1); // Set user life to a positive value
        hangmanController.setHiddenWord("Peter"); // Set the hidden word

        // Act
        boolean gameEnded = hangmanController.gameEnd();

        // Assert
        assertTrue(gameEnded); // Expecting gameEnd to return true (win condition)
        assertEquals("\nYou won! :)", hangmanController.getUserMessage()); // Verify user message
    }

    /**
     * Tests the game end condition (loss).
     */
    @Test
    public void testGameEndLoss() {
        // Arrange
        HangmanController hangmanController = new HangmanController();
        hangmanController.setUserLife(0); // Set user life to 0
        hangmanController.setHiddenWord("pet_r"); // Set the hidden word

        // Act
        boolean gameEnded = hangmanController.gameEnd();

        // Assert
        assertTrue(gameEnded); // Expecting gameEnd to return true (loss condition)
        assertEquals("\nYou lose!", hangmanController.getUserMessage()); // Verify user message
    }

    /**
     * Tests the game not ending condition.
     */
    @Test
    public void testGameNotEnd() {
        // Arrange
        HangmanController hangmanController = new HangmanController();
        hangmanController.setUserLife(1); // Set user life to a positive value
        hangmanController.setHiddenWord("S_r__"); // Set the hidden word

        // Act
        boolean gameEnded = hangmanController.gameEnd();

        // Assert
        assertFalse(gameEnded); // Expecting gameEnd to return false (game not ended)
        assertTrue(hangmanController.getUserMessage().isEmpty()); // User message should be null
    }

    /**
     * Tests getting the player's score.
     */
    @Test
    public void testGetPlayerScore() {
        // Arrange
        int expectedScore = 6; // Set the expected score

        // Act
        int actualScore = hangmanController.getPlayerScore();

        // Assert
        assertEquals(expectedScore, actualScore); // Check if the actual score matches the expected score
    }

}
