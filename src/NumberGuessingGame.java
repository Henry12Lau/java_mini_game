import java.util.Scanner;
public class NumberGuessingGame {
    private int randomNumber;
    private int numberOfAttempts;
    private int maxNumOfInput;
    private int upperInterval;
    private int lowerInterval;
    private String intro;
    private Scanner input;
    private boolean gameEnd;
    private String numberStatus;
    public NumberGuessingGame() {
        randomNumber = (int)(Math.random() * 100) + 1;
        numberOfAttempts = 1;
        maxNumOfInput = 5;
        upperInterval = 100;
        lowerInterval = 1;
        intro = """
            Welcome to the Number Guessing game!
            The program has generated a number between 1 and 100.
            You have 5 attempts to guess the correct number.
            """;
        input = new Scanner(System.in);
        gameEnd = false;
        numberStatus = "low";
    }
    public void startGuessingGame(int guessNumber) {
//        System.out.println("Debug - numberOfAttempts: " +numberOfAttempts+ " maxNumOfInput: "+maxNumOfInput);
        changeRange(guessNumber);
        if (guessNumber == randomNumber) {
            System.out.println("Guess " + numberOfAttempts);
            System.out.println("Congratulations! You have guessed the correct number in " + numberOfAttempts + " attempts.");
            System.out.println("\nThanks for playing the Number Guessing game!");
            gameEnd = true;
            Main.menu();
        } else if (numberOfAttempts == maxNumOfInput){
            numberOfAttempts++;
            System.out.println("Game over! You have run out of attempts.");
            System.out.println("The correct number was: " + randomNumber);
            gameEnd = true;
            Main.menu();
        } else {
            System.out.println("Guess " + numberOfAttempts);
            System.out.println(String.format("Your guess is too %s. The number is between %s and %s. Please Try again!", numberStatus, lowerInterval, upperInterval));
            numberOfAttempts++;
            Main.menu();
        }
    }

    public void changeRange(int guessNumber) {
        if (guessNumber > randomNumber) {
            upperInterval = guessNumber;
            numberStatus = "high";
        } else {
            lowerInterval = guessNumber;
            numberStatus = "low";
        }
    }

    public void run() {
        System.out.println(intro);
        while (!gameEnd) {
            try {
                System.out.println("Random Number: " + randomNumber);
                System.out.print("Enter your guess: ");
                int guessNumber = input.nextInt();
                input.nextLine();
                startGuessingGame(guessNumber);
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                input.nextLine();
            }
        }
    }

}
