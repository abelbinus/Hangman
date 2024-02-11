import stacs.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HangmanJunitTest {

    private HangmanController hangmanController;
    private HangmanView hangmanView;
    @BeforeEach
    public void setUpBeforeEach() {
        new Hangman();
        hangmanController = new HangmanController();
        hangmanView = new HangmanView();
    }

    @Test
    public void testWordsListNotNull() {
        ArrayList<String> wordsList = hangmanController.getWords("src\\test\\resources\\wordlist-test.txt");
        assertNotNull(wordsList);
    }

    @Test
    public void testWordsListNotEmpty() {
        ArrayList<String> wordsList = hangmanController.getWords("src\\test\\resources\\wordlist-test.txt");
        assertFalse(wordsList.isEmpty());
    }

    @Test
    public void testWordLength() {
        hangmanController.setObject(hangmanView);
        ArrayList<String> wordsList = hangmanController.getWords("src\\test\\resources\\wordlist-test.txt");
        String word = hangmanController.getRandomWord(wordsList);
        assertNotNull(word);
        assertTrue(word.length() == 5);
    }

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

}
