package stacs;

import java.util.ArrayList;
import java.util.Scanner;

public class HangmanView {

    ArrayList<String> hangmanDisplay = new ArrayList<>();
    String[] hangmanDisplayContent = {
            "     _____",
            "     |    |",
            "          |",
            "          |",
            "          |",
            "          |",
            "__________|"
    };
    
    public HangmanView() {
        setHangmanDisplay();
    }
    public void setHangmanDisplay() {
        for (String s : hangmanDisplayContent) {
            hangmanDisplay.add(s);
        }
    }
    public void displayHangman () {
        for (String s : hangmanDisplay) {
            System.out.println(s);
        }
    }

    public void updateHangman(int updateDisplayIndex) {
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
            hangmanDisplay.set(3, "    /|\\  |");
        }

        else if (updateDisplayIndex == 1) {
            hangmanDisplay.set(4, "    /      |");
        }

        else if (updateDisplayIndex == 0) {
            hangmanDisplay.set(4, "    / \\   |");
        }
        displayHangman();
    }

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
    
    public void displayUserLife(int userLife){
        // Display the number of attempts left
        System.out.println();
        System.out.println("You have " + userLife + " chances left");
    }
    public void displayWrongLetters(ArrayList<Character> wrongList){
        // Display the wrong letters entered by the user.
        System.out.println();
        System.out.println("Wrong letters: " + wrongList.toString());
    }
}
