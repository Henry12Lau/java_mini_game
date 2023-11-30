import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Hangman {
    private int turn;
    private String intro;
    private Scanner input;
    private boolean gameEnd;
    private String[] wordPool;
    private int randomNum;
    private String[] hangMan;
    private String[] result;
    private String gameBoard;
    public Hangman(){
        turn = 7;
        intro = "Welcome to Hangman!";
        input = new Scanner(System.in);
        wordPool = new String[]{"tab", "table", "fork"};
        randomNum = (int) (Math.random() * wordPool.length);
        hangMan = new String[]{"O", "\\", "|", "/", "|", "/", "\\"};
    }
    public void startHangMan(String input) {
        String word = wordPool[randomNum];
        String[] hiddenWord = {};
        for (int i =  0; i < word.length(); i++){
            hiddenWord[i] = "_";
        }
        System.out.println("hiddenWord" + hiddenWord);
        System.out.println("randomNum: "+ randomNum);
        System.out.println("word: "+ wordPool[randomNum]);
        int index = word.indexOf(input);
        if (index >= 0) {
            result[index] = input;
            for (String letter : result) {
                word += letter;
            }
            System.out.println(word);
        } else {
            turn--;
            for (String letter : result) {
                word += letter;
            }
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
        System.out.printf("%2s%4s\n", "|", hangMan[0]);
        System.out.printf("%2s%3s%1s%1s\n", "|", hangMan[1], hangMan[2], hangMan[3]);
        System.out.printf("%2s%4s\n", "|", hangMan[4]);
        System.out.printf("%2s%3s%2s\n", "|", hangMan[5], hangMan[6]);
        System.out.printf("%2s\n", "|");
        System.out.println("_|_");
    }
    public void run() {
        System.out.println(intro);
        printBoard();
        while (!gameEnd) {
            try {
                String letterInput = input.nextLine();
                System.out.println("Guess a letter: " + letterInput);
                startHangMan(letterInput);
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid letter.");
                input.nextLine();
            }
        }
    }
}
