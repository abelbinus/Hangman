package stacs;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The HangmanView class handles the display and user interaction in the Hangman game.
 */
public class HangmanView {

    /**
     * ArrayList to store the lines of the hangman display.
     */
    ArrayList<String> hangmanDisplay = new ArrayList<>();

    /**
     * Array representing the content of the hangman display.
     */
    String[] hangmanDisplayContent = {
            "     _____",
            "     |    |",
            "          |",
            "          |",
            "          |",
            "          |",
            "__________|"
    };

    /**
     * Constructs a new HangmanView object and sets up the hangman display.
     */
    public HangmanView() {
        setHangmanDisplay();
    }

    /**
     * Sets up the hangman display based on the content array.
     */
    public void setHangmanDisplay() {
        if(!hangmanDisplay.isEmpty()) {
            hangmanDisplay.clear();
        }
        for (String s : hangmanDisplayContent) {
            hangmanDisplay.add(s);
        }
    }

    /**
     * Displays the hangman display.
     */
    public void displayHangman() {
        for (String s : hangmanDisplay) {
            System.out.println(s);
        }
    }

    /**
     * Updates the hangman display based on the number of incorrect guesses.
     * @param updateDisplayIndex The index representing the number of incorrect guesses.
     */
    public void updateHangman(int updateDisplayIndex) {
        // Update the hangman display based on the number of incorrect guesses
        if (updateDisplayIndex == 5) {
            hangmanDisplay.set(2, "     O    |");
        }
        else if (updateDisplayIndex == 4) {
            hangmanDisplay.set(3, "    /     |");
        }
        else if (updateDisplayIndex == 3) {
            hangmanDisplay.set(3, "    /|    |");
        }

        else if (updateDisplayIndex == 2) {
            hangmanDisplay.set(3, "    /|\\   |");
        }

        else if (updateDisplayIndex == 1) {
            hangmanDisplay.set(4, "    /     |");
        }

        else if (updateDisplayIndex == 0) {
            hangmanDisplay.set(4, "    / \\   |");
        }
    }

    /**
     * Prompts the user to input a single character and returns the input.
     * @param hiddenWord The hidden word with underscores representing unknown letters.
     * @return The user input.
     */
    public String userInput(String hiddenWord) {
        System.out.println();
        System.out.println(hiddenWord);

        // Accept user input
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter a single character from a to z: ");
        String userInput = scanner.nextLine();
        return userInput;
    }
    
    /**
     * Displays the number of attempts left.
     * @param userLife The number of attempts left.
     */
    public void displayUserLife(int userLife){
        // Display the number of attempts left
        System.out.println();
        System.out.println("You have " + userLife + " chances left");
    }

     /**
     * Displays the wrong letters entered by the user.
     * @param wrongList The list of wrong letters.
     */
    public void displayWrongLetters(ArrayList<Character> wrongList) {
        // Display the wrong letters entered by the user.
        System.out.println();
        System.out.print("Wrong letters: ");
        for (char c : wrongList) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
