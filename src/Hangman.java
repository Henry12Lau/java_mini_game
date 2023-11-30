import java.util.Arrays;
import java.util.Scanner;

public class Hangman {
    private int turn;
    private String intro;
    private Scanner input;
    private boolean gameEnd;
    private String[] wordPool;
    private int randomNum;
    private String[] parts;
    private String word;
    private String[] tempWord;
    private String gameBoard;
    public Hangman(){
        turn = 7;
        intro = "Welcome to Hangman!";
        input = new Scanner(System.in);
        wordPool = new String[]{"tab", "table", "fork"};
        randomNum = (int) (Math.random() * wordPool.length);
        word = wordPool[randomNum];
        tempWord = new String[word.length()];
        parts = new String[]{"O", "\\", "|", "/", "|", "/", "\\"};
    }
    public void startHangMan(String t) {
        String result = "";
        for (int i =  0; i < word.length(); i++){
            tempWord[i] = "_";
        }
//        System.out.println("tempWord" + Arrays.toString(tempWord));
//        System.out.println("randomNum: "+ randomNum);
//        System.out.println("word: "+ wordPool[randomNum]);
//        System.out.println("word length: "+ word.length());
        int index = word.indexOf(t);
        System.out.println("t: "+ t);
        System.out.println("index: "+ index);
        if (index >= 0) {
            tempWord[index] = t;
            for (String letter : tempWord) {
                result += letter;
            }
            System.out.println("tempWord 2: " + result);
        } else {
            turn--;
//            for (String letter : hiddenWorld) {
//                word += letter;
//            }
            if (turn == 0) {
                System.out.println("Game Over!");
                gameEnd = true;
            } else {
                System.out.println(word);
                System.out.println(String.format("Incorrect guess. You have %s tries left.", turn));
            }
        }
    }
    public void printBoard() {
        System.out.println("---------");
        System.out.printf("%2s%4s\n", "|", "|");
        System.out.printf("%2s%4s\n", "|", parts[0]);
        System.out.printf("%2s%3s%1s%1s\n", "|", parts[1], parts[2], parts[3]);
        System.out.printf("%2s%4s\n", "|", parts[4]);
        System.out.printf("%2s%3s%2s\n", "|", parts[5], parts[6]);
        System.out.printf("%2s\n", "|");
        System.out.println("_|_");
    }
    public void run() {
        System.out.println(intro);
        printBoard();
        while (!gameEnd) {
            try {
                String letterInput = input.nextLine();
                if (letterInput.length() != 1) {
                    System.out.println("Invalid input! Please enter a valid letter.");
                } else {
                    System.out.println("Guess a letter: " + letterInput);
                    startHangMan(letterInput);
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid letter.");
                input.nextLine();
            }
        }
    }
}
