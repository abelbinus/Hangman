package stacs;
public class Hangman {
    public static void main(String[] args) {
        HangmanController hangmanController = new HangmanController();
        HangmanView hangmanView = new HangmanView();
        hangmanController.setObject(hangmanView);
        hangmanController.setGame();
        hangmanController.startGame();
    }

}
