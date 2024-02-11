package stacs;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
public class HangmanController {

    
    /**
     * All the words used in Hangman.
     */
    private ArrayList<String> wordsList;

    /**
     * Randomly selected word for the current game.
     */
    private String correctWord = "";

    /**
     * Max tries for the game participant allowed in the Hangman game.
     */
    int userLife;
    private int SIX = 6;

    private ArrayList<Character> wrongList = new ArrayList<>();;
    private ArrayList<Character> correctList = new ArrayList<>();;

    private HangmanView hangmanView;
    private String userMessage = "";
    private String hiddenWord = "_____";

    public void setGame() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            // Windows
            wordsList = getWords("src\\test\\resources\\wordlist-test.txt");
        } else {
            // Assume Linux or Unix-like
            wordsList = getWords("src/test/resources/wordlist-test.txt");
        }
        correctWord = getRandomWord(this.wordsList);
        setUserLife(SIX);
        System.out.println("Random word: " + correctWord);
        if(wrongList!=null) {
            if(wrongList.size()>0) {
                wrongList.clear();
            }
        }
        if(correctList!=null) {
            if(correctList.size()>0) {
                correctList.clear();
            }
        }
        hiddenWord = "_____";
        userMessage = "";
    }

    public void startGame() {
        hangmanView.displayHangman();
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

        } else {
            // Display the result of the game. If count is greater than or equal to 0 and the hidden word,
            // no longer contains any underscores the player has won the game.
            hangmanView.displayHangman();
            System.out.println(getUserMessage() + "\n");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Would you like to play again?[y/n]: ");
            String input = scanner.nextLine();
            int counterInput = 1;
            do
            {
                if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {

                    // Checks if the user wants to play again.
                    setGame();
                    startGame();
                    counterInput= 4;
                }
                else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")){
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
            if (userLife > 0 && !Arrays.stream(hiddenWord.split("")).collect(Collectors.toList()).contains("_")) {
                System.out.println();
                setUserMessage("\nYou won! :)");
                return true;
            } 
            else if (userLife == 0 && Arrays.stream(hiddenWord.split("")).collect(Collectors.toList()).contains("_")){
                System.out.println();
                setUserMessage("\nYou lose!");
                System.out.println("The correct word was: " + correctWord);
                return true;
            }
        return false;
    }

    private boolean checkValidInput(String userInput) {
        if (userInput.length() == 1 && Character.isLetter(userInput.charAt(0))) {
            char userInputChar = Character.toLowerCase(userInput.charAt(0));
            for (char ch : wrongList) {
                if (ch == userInputChar) {
                    System.out.println("You have already entered the character before.\nPLease try again.");
                    return false;
                }
            }
            for (char ch : correctList) {
                if (ch == userInputChar) {
                    System.out.println("You have already entered the character before.\nPLease try again.");
                    return false;
                }
            }
            // Check if the character entered by player is present in the word
            if (Arrays.stream(correctWord.split("")).collect(Collectors.toList()).contains(String.valueOf(userInputChar))) {

            // Update the hidden word based on the entered character
            for (int i = 0; i < correctWord.split("").length; i++) {
                if (correctWord.split("")[i].equals(String.valueOf(userInputChar))) {
                    StringBuilder stringBuilder = new StringBuilder(hiddenWord);
                    stringBuilder.setCharAt(i, userInputChar);

                    // Convert StringBuilder back to String
                    hiddenWord = stringBuilder.toString();
                }
            }
            correctList.add(userInputChar);
            return true;
        } else {
            userLife--;
            hangmanView.updateHangman(userLife);
            wrongList.add(userInputChar);
        }

            // Draw Hangman based on whether user entered a character present in the hidden word or not

        } else {
            System.out.println("Please enter a valid letter [a-z]");
        }
        return false;
    }

    public void decreaseUserLife() {
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
        } catch (IOException e) {
            e.printStackTrace();
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
