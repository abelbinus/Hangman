package stacs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The HangmanController class manages the logic of the Hangman game.
 */
public class HangmanController {

    /**
     * Randomly selected word for the current game.
     */
    private String correctWord = "";

    /**
     * Max tries for the game participant allowed in the Hangman game.
     */
    int userLife;

     /**
     * Default number of lives for the player.
     */
    private final int SIX = 6;

    /**
     * List to keep track of incorrectly guessed letters.
     */
    private final ArrayList<Character> wrongList = new ArrayList<>();

    /**
     * List to keep track of correctly guessed letters.
     */
    private final ArrayList<Character> correctList = new ArrayList<>();

    /**
     * Instance of HangmanView to manage the game's display.
     */
    private HangmanView hangmanView;

    /**
     * Message for the user.
     */
    private String userMessage = "";

    /**
     * The hidden word with underscores representing unknown letters.
     */
    private String hiddenWord = "_____";

    /**
     * Message from the system. Correct or Incorrect Choice
     */
    private String systemMessage = "";

    /**
     * The player's score.
     */
    private int playerScore = SIX;

     /**
     * Sets up the game by selecting a random word and initializing variables.
     */
    public void setGame() {
        // Setup hangman display
        hangmanView.setHangmanDisplay();
        ArrayList<String> wordsList;
        wordsList = getWords("wordlist.txt");
         // Select a random word from the lists
        correctWord = getRandomWord(wordsList);
         // Initialize user life, lists, hidden word, and messages
        setUserLife(SIX);
        if (!wrongList.isEmpty()) {
            wrongList.clear();
        }
        if (!correctList.isEmpty()) {
            correctList.clear();
        }
        hiddenWord = "_____";
        userMessage = "";
        playerScore = SIX;
        systemMessage = "";
    }

    /**
     * Starts the Hangman game.
     */
    public void startGame() {
        hangmanView.displayHangman();
        if(!systemMessage.isEmpty()) {
            System.out.println(systemMessage);
        }
        if(userLife<SIX) {
            hangmanView.displayUserLife(userLife);
            hangmanView.displayWrongLetters(wrongList);
        }
        String userInput = hangmanView.userInput(hiddenWord);
        checkValidInput(userInput);
        // Check if there are still hidden letters and life points left
        if (!gameEnd()) {
            // Recursive call to continue the game
            startGame();
        }
        else {
            // Display the result of the game. If count is greater than or equal to 0 and the hidden word,
            hangmanView.displayHangman();
            System.out.println(getUserMessage() + "\n");
            // Prompt the user to play again
            System.out.println("Your score is " + getPlayerScore() + "\n");

            Scanner scanner = new Scanner(System.in);
            // Checks if the user wants to play again.
            System.out.print("Would you like to play again?[y/n]: ");
            String input = scanner.nextLine();
            int counterInput = 1;
            do
            {
                if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                    // if Yes, set the game again and start it.
                    setGame();
                    startGame();
                    counterInput= 4;
                }
                else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")){
                    // if No, exit the game.
                    System.out.println("\n\nThank you for playing Hangman!!!\nHave a nice day : )");
                    counterInput= 4;
                }
                else {
                    System.out.println("\nWrong Input!!!\nTry Again\nYou have " + counterInput + " number of chances left.");
                    counterInput++;
                }
            }while(counterInput<4);
            scanner.close();
        }
    }

     /**
     * Retrieves the player's score.
     * @return The player's score.
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * Retrieves the user message.
     * @return The user message.
     */
    public String getUserMessage() {
        return userMessage;
    }

     /**
     * Sets the user message.
     * @param userMessage The message to set.
     */
    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    /**
     * Sets the hidden word.
     * @param hiddenWord The hidden word.
     */
    public void setHiddenWord (String hiddenWord) {
        this.hiddenWord = hiddenWord;
    }

    /**
     * Retrieves the hidden word.
     * @return The hidden word.
     */
    public String getHiddenWord () {
        return hiddenWord;
    }

    /**
     * Checks if the game has ended.
     * @return True if the game has ended, false otherwise.
     */
    public boolean gameEnd() {
        if (userLife > 0 && !Arrays.stream(hiddenWord.split("")).toList().contains("_")) {
            System.out.println();
            setUserMessage("\nYou won! :)");
            playerScore +=5;
            return true;
        }
        else if (userLife == 0 && Arrays.stream(hiddenWord.split("")).toList().contains("_")){
            System.out.println();
            setUserMessage("\nYou lose!");
            System.out.println("The correct word was: " + correctWord);
            return true;
        }
        return false;
    }

    /**
     * Checks if the user input is valid.
     * @param userInput The user input.
     */
    private void checkValidInput(String userInput) {
        // check if the userinput is valid or not
        if (userInput.length() == 1 && Character.isLetter(userInput.charAt(0))) {
            // single character check successful.
            char userInputChar = Character.toLowerCase(userInput.charAt(0));
            // check if character has been used before.
            for (char ch : wrongList) {
                if (ch == userInputChar) {
                    System.out.println("You have already entered the character before.\nPLease try again.");
                    return;
                }
            }
            for (char ch : correctList) {
                if (ch == userInputChar) {
                    System.out.println("You have already entered the character before.\nPLease try again.");
                    return;
                }
            }
            // Check if the character entered by player is present in the word
            if (Arrays.stream(correctWord.split("")).toList().contains(String.valueOf(userInputChar))) {
                // Update the hidden word based on the entered character
                for (int i = 0; i < correctWord.split("").length; i++) {
                    if (correctWord.split("")[i].equals(String.valueOf(userInputChar))) {
                        StringBuilder stringBuilder = new StringBuilder(hiddenWord);
                        stringBuilder.setCharAt(i, userInputChar);
                        // Convert StringBuilder back to String
                        hiddenWord = stringBuilder.toString();
                    }
                }
                systemMessage = "\nCorrect Choice!!!";
                // increase player score and add to used list.
                playerScore += 3;
                correctList.add(userInputChar);
            }
            else {
                // decrease player score and add to used list.
                systemMessage = "\nIncorrect Choice!!!";
                decreaseUserLife();
                playerScore--;
                hangmanView.updateHangman(userLife);
                wrongList.add(userInputChar);
            }
        }
        else {
            System.out.println("Please enter a valid letter [a-z]");
        }
    }

    /**
     * Decreases the user's life.
     */
    private void decreaseUserLife() {
        userLife--;
    }

    /**
     * Sets the user's life.
     * @param userLife The user's life.
     */
    public void setUserLife(int userLife) {
        this.userLife = userLife;
    }

    /**
     * Reads words from a file.
     * @param fileName The name of the file.
     * @return The list of words.
     */
    public ArrayList<String> getWords(String fileName) {
        ArrayList<String> words = new ArrayList<>();
        try {
            // Use class loader to load the resource
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            if (inputStream != null) {
                // Read lines from the input stream
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        words.add(line);
                    }
                }
            } else {
                System.out.println("File not found: " + fileName);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return words;
    }

    /**
     * Retrieves a random word from a list.
     * @param wordsList The list of words.
     * @return A random word.
     */
    public String getRandomWord(ArrayList<String> wordsList) {
        Random random = new Random();
        int randomIndex = random.nextInt(wordsList.size());
        return wordsList.get(randomIndex);
    }

    /**
     * Sets the HangmanView object.
     * @param hangmanView The HangmanView object.
     */
    public void setObject(HangmanView hangmanView) {
        this.hangmanView = hangmanView;
    }
}
