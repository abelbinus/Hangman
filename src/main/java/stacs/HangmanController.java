package stacs;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    private final ArrayList<Character> wrongList = new ArrayList<>();
    private final ArrayList<Character> correctList = new ArrayList<>();

    private HangmanView hangmanView;
    private String userMessage = "";
    private String hiddenWord = "_____";
    private String systemMessage = "";
    private int playerScore = SIX;
    public void setGame() {
        hangmanView.setHangmanDisplay();
        String osName = System.getProperty("os.name").toLowerCase();
        ArrayList<String> wordsList;
        if (osName.contains("win")) {
            // Windows
            wordsList = getWords("src\\main\\resources\\wordlist.txt");
        } else {
            // Linux or Unix-like
            wordsList = getWords("src/main/resources/wordlist.txt");
        }
        correctWord = getRandomWord(wordsList);
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
            // no longer contains any underscores the player has won the game.
            hangmanView.displayHangman();
            System.out.println(getUserMessage() + "\n");
            System.out.println("Your score is " + getPlayerScore() + "\n");

            Scanner scanner = new Scanner(System.in);
            // Checks if the user wants to play again.
            System.out.print("Would you like to play again?[y/n]: ");
            String input = scanner.nextLine();
            int counterInput = 1;
            do
            {
                if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                    //if Yes, set the game again and start it.
                    setGame();
                    startGame();
                    counterInput= 4;
                }
                else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")){
                    //if No, exit the game.
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

    public int getPlayerScore() {
        return playerScore;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public void setHiddenWord (String hiddenWord) {
        this.hiddenWord = hiddenWord;
    }

    public String getHiddenWord () {
        return hiddenWord;
    }

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

    private void checkValidInput(String userInput) {
        //check if the userinput is valid or not
        if (userInput.length() == 1 && Character.isLetter(userInput.charAt(0))) {
            //single character check successful.
            char userInputChar = Character.toLowerCase(userInput.charAt(0));
            //check if character has been used before.
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
                //increase player score and add to used list.
                playerScore += 3;
                correctList.add(userInputChar);
            }
            else {
                //decrease player score and add to used list.
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

    private void decreaseUserLife() {
        userLife--;
    }

    public void setUserLife(int userLife) {
        this.userLife = userLife;
    }

    public ArrayList<String> getWords(String fileName) {
        @SuppressWarnings({ "unchecked", "rawtypes" })
        ArrayList<String> words = new ArrayList();
        try {
            words = (ArrayList<String>) Files.readAllLines(Paths.get(fileName));
        } catch (IOException ignored) {
        }
        return(words);
    }


    public String getRandomWord(ArrayList<String> wordsList) {
        Random random = new Random();
        int randomIndex = random.nextInt(wordsList.size());
        return wordsList.get(randomIndex);
    }

    public void setObject(HangmanView hangmanView) {
        this.hangmanView = hangmanView;
    }
}
