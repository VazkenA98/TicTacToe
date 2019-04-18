import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Board emptyBoard = new Board();
        Players p1 = new Players(), p2 = new Players();
        boolean gameIsOver = true;
        while (gameIsOver) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome to Tic Tac Toe game!");
            System.out.println("First player please enter your name");

            String firstPlayerName = sc.nextLine();
            p1.setPlayersName(firstPlayerName);

            System.out.println("second player please enter your name");

            String secondPlayerName = sc.nextLine();
            p2.setPlayersName(secondPlayerName);

            System.out.println("Please enter size of Table");
            System.out.println("You can Enter size starting from 3");

            int size_of_table = emptyBoard.limitationOfTableSize(sc);

            System.out.println("and enter rule of winning 3 or 5");

            int rule_size = emptyBoard.limitationOfRule(sc);

            Board board = new Board(size_of_table, rule_size);

            boolean gameIsNotOver = true;
            int countForPlayersTurn = 2;
            while (gameIsNotOver) {
                board.printGameTable();
                if (countForPlayersTurn % 2 == 0) {
                    System.out.println("player "+p1.getPlayersName()+" turn");
                    boolean allClearFirstPlayer = true;
                    while (allClearFirstPlayer) {
                        System.out.println("please enter x coordinate");
                        p1.setX(board.limitationOfInteger(sc));
                        System.out.println("please enter y coordinate");
                        p1.setY(board.limitationOfInteger(sc));
                        allClearFirstPlayer = board.positionCheckingForInserting(p1.getX(), p1.getY());
                    }
                    System.out.println("please enter x ");
                    p1.setObjPlayer(board.readOnlyChar(sc, countForPlayersTurn));
                } else {

                    System.out.println("player "+p2.getPlayersName()+" turn");
                    boolean allClearSecondPlayer = true;
                    while (allClearSecondPlayer) {
                        System.out.println("please enter x coordinate");
                        p2.setX(board.limitationOfInteger(sc));
                        System.out.println("please enter y coordinate");
                        p2.setY(board.limitationOfInteger(sc));
                        allClearSecondPlayer = board.positionCheckingForInserting(p2.getX(), p2.getY());
                    }
                    System.out.println("please enter O ");
                    p2.setObjPlayer(board.readOnlyChar(sc, countForPlayersTurn));
                }
                if (countForPlayersTurn % 2 == 0) {
                    board.getInputsOfPlayers(p1.getObj(), p1.getX(), p1.getY());
                } else {
                    board.getInputsOfPlayers(p2.getObj(), p2.getX(), p2.getY());
                }
                countForPlayersTurn++;
                gameIsNotOver = board.rulesChecker(p1, p2);
            }
            board.checkingWinner(p1, p2);
            gameIsOver = board.restartGame(sc);
        }
    }
}
