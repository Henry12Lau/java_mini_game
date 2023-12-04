import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

public class TicTacToe {
    private String player1;
    private String player2;
    public String[] board;
    private String gameBoard;
    private String intro;
    private boolean gameEnd;
    private Scanner input;
    private String turn;
    private int round;

    public TicTacToe() {
        player1 = "Player 1";
        player2 = "Player 2";
        board = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        gameBoard = String.format(
                """
                %s | %s | %s
                ----------
                %s | %s | %s
                ----------
                %s | %s | %s
                """, board[0], board[1], board[2], board[3], board[4], board[5], board[6], board[7], board[8]);
        gameEnd = false;
        input = new Scanner(System.in);
        turn = player1;
        gameEnd = false;
        round = 0;
    }

    public void startTicTacToe(int choseNumber, String pTurn) {
        if (checkDuplicate(choseNumber)) {
            System.out.println("Your number has been chosen, please try again.");
        } else {
            String boardValue = pTurn.equals(player1) ? "O": "X";
            updateBoard(choseNumber, boardValue);
            System.out.println(gameBoard);
            turn = pTurn.equals(player1) ? player2: player1;
            String result = checkWinner();
            if (result.isEmpty()) {
                System.out.println(turn + "'s turn. Enter a number (1-9) to choose a space:");
                return;
            }
            if (result.equals("p1")){
                System.out.println(player1 + " wins!\n");
            } else if (result.equals("p2")){
                System.out.println(player2 + " wins!\n");
            } else if (result.equals("draw")){
                System.out.println("Draw!\n");
            }

            gameEnd = true;
            Main.menu();
        }
    }

    public void updateBoard(int choseNumber, String boardValue) {
        board[choseNumber - 1] = String.valueOf(boardValue);
        gameBoard = String.format(
                """
                %s | %s | %s
                ----------
                %s | %s | %s
                ----------
                %s | %s | %s
                """, board[0], board[1], board[2], board[3], board[4], board[5], board[6], board[7], board[8]);
    }

    public boolean checkDuplicate(int choseNumber) {
        boolean p1 = board[choseNumber - 1].contains(String.valueOf("O"));
        boolean p2 = board[choseNumber - 1].contains(String.valueOf("X"));
        if (!p1 && !p2) {
            return false;
        } return true;
    }

    public String checkWinner() {
        String result = "";
        //check board line
        for (int i = 0; i < 8; i++) {
            String line = null;
            switch (i) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }
            if (line.equals("OOO")) {
                result = "p1";
            } else if (line.equals("XXX")) {
                result = "p2";
            }
        }
        round++;
        //check draw
        if (round == 9 && result.equals("")){
            result = "draw";
        }
        return result;
    }
    public void updatePlayerName(String name1, String name2) {
        player1 = name1;
        player2 = name2;
        turn = player1;
        intro = String.format("""
        Welcome to Tic Tac Toe!
        
        %s: O
        %s: X
        """, name1, name2);
    }

    public void run() {
        System.out.println("Please enter player 1 name: ");
        String p1Name = input.nextLine();
        System.out.println("Please enter player 2 name: ");
        String p2Name = input.nextLine();
        updatePlayerName(p1Name, p2Name);
        System.out.println(intro);
        System.out.println(gameBoard);
        System.out.println(turn + "'s turn. Enter a number (1-9) to choose a space:");
        while(!gameEnd) {
            boolean validInput = false;
            int numInput = 0;

            while (!validInput) {
                String inputStr = input.nextLine();
                try {
                    numInput = Integer.parseInt(inputStr);
                    if (numInput < 1 || numInput > 9) {
                        System.out.println("Invalid input! Please enter a number between 1 and 9.\n");
                    } else {
                        validInput = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a number between 1 and 9.\n");
                }
            }
            startTicTacToe(numInput, turn);
        }
    }
}
