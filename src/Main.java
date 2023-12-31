import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        String intro = """
                =============================================
                Group members:
                
                =============================================
                Welcome to the Game Console : A Trio of Classic Games!
                Please select a game to play:

                1. Tic Tac Toe
                2. Hangman
                3. Number Guessing

                Enter the corresponding number (1-3) or enter 'q' to quit:""";
        Scanner input = new Scanner(System.in);
        TicTacToe game1 = new TicTacToe();
        Hangman game2 = new Hangman();
        NumberGuessingGame game3 = new NumberGuessingGame();
        boolean running = true;
        while (running) {
            System.out.println(intro);
            String userInput = input.nextLine();
            if (userInput.equals("1") || userInput.equals("2") || userInput.equals("3") || userInput.equals("q")){
                switch (userInput) {
                    case "1" -> game1.run();
                    case "2" -> game2.run();
                    case "3" -> game3.run();
                    case "q" -> {
                        System.out.println("You quit the game");
                        running = false;
                    }
                    default -> System.out.println("Invalid input, Please try again.\n");
                }
            } else {
                System.out.println("Invalid input, Please try again.\n");
            }
        }
    }
}