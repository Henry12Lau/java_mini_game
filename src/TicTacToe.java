import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    private String player1;
    private String player2;
    public String[] board;
    private String gameBoard;
    private String intro;
    private boolean gameEnd;
    private Scanner input;
    private String turn;

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
        intro = """
            Welcome to Tic Tac Toe!
            
            Player 1: O
            Player 2: X
            """;
        gameEnd = false;
        input = new Scanner(System.in);
        turn = player1;
        gameEnd = false;
    }

    public void startTicTacToe(int choseNumber, String pTurn) {
        if (checkDuplicate(choseNumber)) {
            System.out.println("Your number has been chosen, please try again.");
        } else {
            String boardValue = pTurn.equals("Player 1") ? "O": "X";
            updateBoard(choseNumber, boardValue);
            System.out.println(gameBoard);
            turn = pTurn.equals(player1) ? player2: player1;
            checkWinner();
            String result = checkWinner();
            if (result.isEmpty()) {
                System.out.println(turn + "'s turn. Enter a number (1-9) to choose a space:");
            } else if (result.equals("p1")){
                System.out.println("Player 1 wins!\n");
                gameEnd = true;
                Main.menu();
            } else if (result.equals("p2")){
                System.out.println("Player 2 wins!\n");
                gameEnd = true;
                Main.menu();
            } else if (result.equals("draw")){
                System.out.println("Draw!\n");
                gameEnd = true;
                Main.menu();
            }
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
        boolean duplicate = false;
        boolean p1 = board[choseNumber - 1].contains(String.valueOf("O"));
        boolean p2 = board[choseNumber - 1].contains(String.valueOf("X"));
        if (!p1 && !p2) {
            return duplicate;
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
        //check draw
        for (int i = 0; i < 9; i ++) {
            if (Arrays.asList(board).contains(
                    String.valueOf(i + 1))) {
                break;
            }
            else if (i == 8) {
                result = "draw";
            }
        }
        return result;
    }

    public void run() {
        System.out.println(intro);
        System.out.println(gameBoard);
        while(!gameEnd) {
            try {
                int numInput = input.nextInt();
                startTicTacToe(numInput, turn);
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                input.nextInt();
            }
        }
    }
}
