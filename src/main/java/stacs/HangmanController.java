package stacs;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import stacs.HangmanController;
public class HangmanController {

    private ArrayList<String> wordsList;

    public void startGame() {
        String fileName = "src\\main\\resources\\wordlist.txt";
        wordsList = getWords(fileName);
        String word = getRandomWord();
        System.out.println("Random word: " + word);
    }

    public ArrayList<String> getWords(String fileName) {

        // TODO Auto-generated method stub
        @SuppressWarnings({ "unchecked", "rawtypes" })
        ArrayList<String> words = new ArrayList();
        try {
            words = (ArrayList<String>) Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return(words);
    }


    public String getRandomWord() {
        // TODO Auto-generated method stub
        Random random = new Random();
        int randomIndex = random.nextInt(wordsList.size());
        return wordsList.get(randomIndex);
    }
}
