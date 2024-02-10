import stacs.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HangmanJunitTest {

    private HangmanController hangmanController;
    private Hangman hangmanGame;

    @BeforeEach
    public void setUpBeforeEach() {
        hangmanGame = new Hangman(); // Remove Hangman declaration here
        hangmanGame.fetchAllWords();
        hangmanController = new HangmanController(hangmanGame.getWords());
    }

    @Test
    public void testFetchAllWords() {
        List<String> wordsList = hangmanGame.getWords();
        assertNotNull(wordsList);
        assertFalse(wordsList.isEmpty());
        // Add more specific tests if needed, such as checking the content of wordsList
    }
}
