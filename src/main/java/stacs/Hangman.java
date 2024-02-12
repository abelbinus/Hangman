package stacs;

/**
 * The Hangman class serves as the entry point for the Hangman game.
 */
public class Hangman {

    /**
     * The main method, which initializes the game components and starts the game.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Create instances of HangmanController and HangmanView.
        HangmanController hangmanController = new HangmanController();
        HangmanView hangmanView = new HangmanView();

        // Set HangmanView object in HangmanController.
        hangmanController.setObject(hangmanView);
        // Set up the game and start it.
        hangmanController.setGame();
        hangmanController.startGame();
    }

}
