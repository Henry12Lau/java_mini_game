import java.util.Scanner;

public class Hangman {
    private int turn;
    private String intro;
    private Scanner input;
    private boolean gameEnd;
    private String[] wordPool;
    private int randomNum;
    private String[] body;
    private String word;
    private String[] tempWord;
    public Hangman(){
        turn = 7;
        intro = "Welcome to Hangman!";
        input = new Scanner(System.in);
        wordPool = new String[]{"tab", "table", "fork"};
//        wordPool = new String[]{"tab"};
        randomNum = (int) (Math.random() * wordPool.length);
        word = wordPool[randomNum];
        tempWord = new String[word.length()];
        for (int i =  0; i < word.length(); i++){
            tempWord[i] = "_";
        }
//        tempWord = new String[]{"_", "_", "_"};
        body = new String[]{"O", "\\", "|", "/", "|", "/", "\\"};
    }
    public void startHangMan(String t) {
        String result = "";
//        System.out.println("tempWord" + Arrays.toString(tempWord));
//        System.out.println("randomNum: "+ randomNum);
//        System.out.println("word: "+ wordPool[randomNum]);
//        System.out.println("word length: "+ word.length());
        int index = word.indexOf(t);
//        System.out.println("t: "+ t);
//        System.out.println("index: "+ index);
        if (index >= 0) {
            tempWord[index] = t;
            for (String letter : tempWord) {
                result += letter;
            }
            if (result.contains("_")){
                System.out.println(result);
            } else {
                System.out.println("You win!\n");
                Main.menu();
            }
        } else {
            turn--;
            for (String letter : tempWord) {
                result += letter;
            }
            System.out.println(result);
            if (turn <= 0) {
                System.out.println("Game Over!\n");
                gameEnd = true;
                Main.menu();
            } else {
                printBoard(turn);
                System.out.println(String.format("Incorrect guess. You have %s tries left.", turn));
            }
        }
    }
    public void printBoard(int pTurn) {
        body[turn] = "";
        System.out.println("---------");
        System.out.printf("%2s%4s\n", "|", "|");
        System.out.printf("%2s%4s\n", "|", body[0]);
        System.out.printf("%2s%3s%1s%1s\n", "|", body[1], body[2], body[3]);
        System.out.printf("%2s%4s\n", "|", body[4]);
        System.out.printf("%2s%3s%2s\n", "|", body[5], body[6]);
        System.out.printf("%2s\n", "|");
        System.out.println("_|_");
    }
    public void printBoard() {
        System.out.println("---------");
        System.out.printf("%2s%4s\n", "|", "|");
        System.out.printf("%2s%4s\n", "|", body[0]);
        System.out.printf("%2s%3s%1s%1s\n", "|", body[1], body[2], body[3]);
        System.out.printf("%2s%4s\n", "|", body[4]);
        System.out.printf("%2s%3s%2s\n", "|", body[5], body[6]);
        System.out.printf("%2s\n", "|");
        System.out.println("_|_");
    }
    public void run() {
        System.out.println(intro);
        printBoard();
        System.out.println("Please input a letter.");
        while (!gameEnd) {
            try {
                String letterInput = input.nextLine().toLowerCase();
                if (letterInput.length() != 1 || !letterInput.matches("[a-zA-Z]")) {
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
