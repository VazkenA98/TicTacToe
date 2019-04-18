import java.util.Scanner;

public class Board {
    private int sizeofboard;
    private Character[][] gameTable;
    private boolean gameIsNotOver = true;
    private int ruleOfGame;
    private static int stepsForCheckingDrowBool = 0;
    private Scanner sc = new Scanner(System.in);
    private String ruleOrderX = "";
    private String ruleOrderO = "";

    public Board() {

    }

    public Board(int sizeofboard, int ruleOfGame) {
        this.sizeofboard = sizeofboard;
        this.ruleOfGame = ruleOfGame;
        for (int i = 0; i < ruleOfGame; i++) {
            ruleOrderX += "x";
            ruleOrderO += "o";
        }
        gameTable = new Character[sizeofboard][sizeofboard];
    }

    public int getSizeOfBoard() {
        return sizeofboard;
    }

    public void setSizeOfBoard(int sizeofboard) {
        this.sizeofboard = sizeofboard;
    }

    public void printGameTable() {
        for (int i = 0; i < sizeofboard; i++) {
            for (int j = 0; j < sizeofboard; j++) {
                if (gameTable[i][j] == null) {
                    System.out.print("[" + (i + 1) + "," + (j + 1) + "]" + " ");
                } else {
                    System.out.print("  " + gameTable[i][j] + "  ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public void getInputsOfPlayers(char symbol, int x_coordinate, int y_coordinate) {
        if (gameTable[x_coordinate - 1][y_coordinate - 1] == null) {
            gameTable[x_coordinate - 1][y_coordinate - 1] = symbol;
        } else {
            System.out.println("re-enter it please");
        }
    }

    public boolean positionCheckingForInserting(int x_coordinate, int y_coordinate) {
        boolean positionsAreNotEmpty = true;
        if (gameTable[x_coordinate - 1][y_coordinate - 1] == null) {
            positionsAreNotEmpty = false;
        } else {
            System.out.println("that position isn't available renter plz");
        }
        return positionsAreNotEmpty;
    }

    public boolean rulesChecker(Players p1, Players p2) {
        stepsForCheckingDrowBool++;
        String rowOfTable = "";
        String columnOfTable = "";
        String halfOfTable = "";
        String reverseHalfOfTable = "";
        for (int i = 0; i < sizeofboard; i++) {
            for (int j = 0; j < sizeofboard; j++) {
                rowOfTable += String.valueOf(gameTable[i][j]);
                columnOfTable += String.valueOf(gameTable[j][i]);
            }
            halfOfTable += String.valueOf(gameTable[i][i]);
            reverseHalfOfTable += String.valueOf(gameTable[i][sizeofboard - i - 1]);
            gameIsNotOver = checkingWiningConditions(rowOfTable, columnOfTable, halfOfTable, reverseHalfOfTable, p1, p2);
            rowOfTable = "";
            columnOfTable = "";
            if (gameIsNotOver == false)
                break;
        }
        if (gameIsNotOver != false && stepsForCheckingDrowBool == (sizeofboard * sizeofboard)) {
            gameIsNotOver = draw();
        }
        return gameIsNotOver;
    }

    private boolean draw() {
        boolean isDraw = true;
        for (int i = 0; i < sizeofboard; i++) {

            for (int j = 0; j < sizeofboard; j++) {
                if (gameTable[i][j] != null) {
                    isDraw = false;
                }
            }
        }
        return isDraw;
    }

    void checkingWinner(Players p1, Players p2) {
        printGameTable();
        if (p1.getPlayerWin() == true && rulesChecker(p1, p2) == false) {
            System.out.println("winner is " + p1.getPlayersName());
            System.out.println("with steps of " + p1.getCount());
        } else if (p2.getPlayerWin() == true && rulesChecker(p1, p2) == false) {
            System.out.println("winner is " + p2.getPlayersName());
            System.out.println("with count of steps are = " + p2.getCount());
        } else {
            System.out.println("game is draw");
        }
        sizeofboard = 0;
        stepsForCheckingDrowBool = 0;
        p1.setCount(0);
        p2.setCount(0);
    }

    private boolean checkingWiningConditions(String row, String column, String half, String reversehalf, Players p1, Players p2) {
        boolean winingCondition = true;
        if (row.contains(ruleOrderX) || column.contains(ruleOrderX) || half.contains(ruleOrderX) || reversehalf.contains(ruleOrderX)) {
            winingCondition = false;
            p1.setPlayerWin(true);
        }
        if (row.contains(ruleOrderO) || column.contains(ruleOrderO) || half.contains(ruleOrderO) || reversehalf.contains(ruleOrderO)) {
            p2.setPlayerWin(true);
            winingCondition = false;
        }
        return winingCondition;
    }

    private int readOnlyIntegers(Scanner sc) {
        int integer = 0;
        try {
            integer = sc.nextInt();
        } catch (Exception e) {
            System.out.println("only integers are allowed");
            sc.nextLine();
            integer = readOnlyIntegers(sc);
        }
        return integer;
    }

    public int limitationOfTableSize(Scanner sc) {
        boolean valid = true;
        int userNumber = 0;
        while (valid) {
            userNumber = readOnlyIntegers(sc);
            if (userNumber >= 3) {
                valid = false;
            } else if (userNumber <= 0) {
                System.out.println("we can't create table with size of zero or less ");
                System.out.println("please re-enter again");
                valid = true;
            } else {
                System.out.println("table minimum size should start from 3");
                System.out.println("please re-enter again");
                valid = true;
            }
        }
        setSizeOfBoard(userNumber);
        return userNumber;
    }

    public int limitationOfRule(Scanner sc) {
        boolean valid = true;
        int userNumber = 0;
        while (valid) {
            userNumber = readOnlyIntegers(sc);
            if (userNumber > 2) {
                valid = false;
            } else {
                System.out.println("rule must start from 3 or more");
                valid = true;
            }
            if (userNumber > sizeofboard) {
                System.out.println("number of rule you entered is bigger than size of the table");
                valid = true;
            }
            if (userNumber <= 0) {
                System.out.println("number is smaller or equal zero we can't apply that rule");
                System.out.println("also it should start from 3 and only be prime number");
                System.out.println("please re-enter again");
                valid = true;
            }
        }
        return userNumber;
    }

    public int limitationOfInteger(Scanner sc) {
        boolean valid = true;
        int userNumber = 0;
        while (valid) {
            userNumber = readOnlyIntegers(sc);
            if (userNumber > sizeofboard) {
                System.out.println("the maximum coordinate we have is " + sizeofboard);
                System.out.println("please re-enter again");
                valid = true;
            } else if (userNumber <= 0) {
                System.out.println("the smallest coordinate is 1");
                System.out.println("please re-enter again");
                valid = true;
            } else {
                valid = false;
            }
        }
        return userNumber;
    }

    public char readOnlyChar(Scanner sc, int turn) {
        boolean valid = true;
        char userChar;
        do {
            userChar = Character.toLowerCase(sc.next().charAt(0));
            String s = String.valueOf(userChar);
            if (turn % 2 == 0) {
                if (s.length() == 1 && 'x' == s.charAt(0)) {
                    valid = false;
                } else {
                    System.out.println("invalid char please re-enter again");
                    System.out.println("you can enter only X char");
                    valid = true;
                }
            } else {
                if (s.length() == 1 && 'o' == s.charAt(0)) {
                    valid = false;
                } else {
                    System.out.println("invalid char please re-enter again");
                    System.out.println("you can enter only O char");
                    valid = true;
                }
            }

        } while (valid);
        return userChar;
    }

    public boolean restartGame(Scanner sc) {
        boolean restart = false;
        System.out.print("\n");
        System.out.println("write number of these options");
        System.out.println("1- Restart.");
        System.out.println("2- Exit.");
        int restartChecker = readOnlyIntegers(sc);
        switch (restartChecker) {
            case 1:
                restart = true;
                break;
            case 2:
                restart = false;
                break;
            default:
                return false;
        }
        return restart;
    }

}

