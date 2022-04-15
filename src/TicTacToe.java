import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private final char HUMAN_TURN = 'X';
    private final char AI_TURN = 'O';
    private final char EMPTY_PLACE = '·';
    private Random random;
    private Scanner scaner;
    private char[][] table;
    private  int tableSize = 5;

    TicTacToe() {
        table = new char[tableSize][tableSize];
        random = new Random();
        scaner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        new TicTacToe().game();
    }

    public void game(){
        initTable();
        printTable();

        while (true) {
            turnHuman();
            printTable();
            if(checkWin(HUMAN_TURN)) {
                System.out.println("YOU WON!");
                break;
            }
            if (isTableFull()){
                System.out.println("DRAW!");
                break;
            }

            turnAI();
            printTable();
            if(checkWin(AI_TURN)) {
                System.out.println("COMPUTER WON!");
                break;
            }
            if (isTableFull()){
                System.out.println("DRAW!");
                break;
            }
        }
    }

    private boolean isTableFull() {
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                if (table[i][j] == EMPTY_PLACE) return false;
            }
        }
        return true;
    }

    private boolean checkWin(char symbol) {

        //check strokes
        boolean flag = true;
        for (int i = 0; i < tableSize; i++) {
            flag = true;
            for (int j = 0; j < tableSize; j++) {
                if (table[i][j] != symbol) flag = false;
            }
            if (flag) return true;
        }

        //check verticals
        for (int i = 0; i < tableSize; i++) {
            flag = true;
            for (int j = 0; j < tableSize; j++) {
                if (table[j][i] != symbol) flag = false;
            }
            if (flag) return true;
        }

        //check diagonal1
        flag = true;
        for (int i = 0; i < tableSize; i++) {
            if (table[i][i] != symbol) flag = false;
        }
        if (flag) return true;

        //check diagonal2
        flag = true;
        for (int i = 0; i < tableSize; i++) {
            if (table[tableSize - 1 - i][i] != symbol) flag = false;
        }
        if (flag) return true;

        return false;
    }

    private void turnHuman(){
        int x, y;
        do {
            System.out.println("Enter x y from [1.." + tableSize + "]");
            x = scaner.nextInt() - 1;
            y = scaner.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[x][y] = HUMAN_TURN;
    }

    private boolean isCellValid(int x, int y) {
        if (x < 0 || x >= tableSize || y < 0 || y >= tableSize) return false;
        return table[x][y] == EMPTY_PLACE;
    }

    private void turnAI(){
        int x, y;
        System.out.println("Computer turn");
        if (!isHumanPreWin(HUMAN_TURN)) {
            do {
                x = random.nextInt(tableSize);
                y = random.nextInt(tableSize);
            } while (!isCellValid(x, y));
            table[x][y] = AI_TURN;
        }
    }

    private boolean isHumanPreWin(char symbol) {

        //check strokes
        int flag = 0;
        for (int i = 0; i < tableSize; i++) {
            flag = 0;
            for (int j = 0; j < tableSize; j++) {
                if (table[i][j] == symbol) flag++;
                if (flag == (tableSize - 1)) {
                    for (int k = 0; k < tableSize; k++) {
                        if (isCellValid(i, k)) {
                            table[i][k] = AI_TURN;
                            return true;
                        }
                    }
                }
            }
        }

        //check verticals
        flag = 0;
        for (int i = 0; i < tableSize; i++) {
            flag = 0;
            for (int j = 0; j < tableSize; j++) {
                if (table[j][i] == symbol) flag++;
                if (flag == (tableSize - 1)) {
                    for (int k = 0; k < tableSize; k++) {
                        if (isCellValid(k, i)) {
                            table[k][i] = AI_TURN;
                            return true;
                        }
                    }
                }
            }
        }

        //check diagonal1
        flag = 0;
        for (int i = 0; i < tableSize; i++) {
            if (table[i][i] == symbol) flag++;
        }
        if (flag == (tableSize - 1)) {
            for (int i = 0; i < tableSize; i++) {
                if (isCellValid(i, i)) {
                    table[i][i] = AI_TURN;
                    return true;
                }
            }
        }

        //check diagonal2
        flag = 0;
        for (int i = 0; i < tableSize; i++) {
            if (table[tableSize - 1 - i][i] != symbol) flag++;
        }
        if (flag == (tableSize - 1)) {
            for (int i = 0; i < tableSize; i++) {
                if (isCellValid(tableSize - 1 - i, i)) {
                    table[tableSize - 1 - i][i] = AI_TURN;
                    return true;
                }
            }
        }

        return false;
    }

    private void initTable(){
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                table[i][j] = EMPTY_PLACE;
            }
        }
        System.out.println("Game is started.");
    }

    private void printTable(){
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }
}
