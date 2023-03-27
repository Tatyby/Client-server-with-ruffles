import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static final int SIZE = 3;  // размер поля
    public static final String ZERO = "O";
    public static final String CROSS = "X";
    public static final String EMPTY = "-";

    public static void main(String[] args) throws IOException {
        String[][] field = {

                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY},

        };
        String[][] go2 = {

                {EMPTY, EMPTY, CROSS},
                {EMPTY, ZERO, EMPTY},
                {EMPTY, EMPTY, EMPTY},

        };
        String[][] go4 = {

                {EMPTY, EMPTY, CROSS},
                {EMPTY, ZERO, EMPTY},
                {EMPTY, ZERO, CROSS},

        };
        String[][] go6 = {

                {EMPTY, EMPTY, CROSS},
                {EMPTY, ZERO, CROSS},
                {EMPTY, ZERO, CROSS},

        };


        System.out.println("Server started.");
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    out.println("Вот поле, размером " + SIZE + ". Будем на нем играть");
                    out.println(arrToString(field));
                    out.println("Ход № 1. Ходи крестиками - Х");

                    final String go1 = in.readLine();
                    System.out.println("User походил крестиками");
                    prinField(StringToArr(go1));

                    out.println("Хожу я ноликами - 0");
                    out.println(arrToString(go2));
                    out.println("Ход № 2. Ходи крестиками - Х");

                    final String go3 = in.readLine();
                    System.out.println("User походил крестиками");
                    prinField(StringToArr(go3));

                    out.println("Хожу я ноликами - 0");
                    out.println(arrToString(go4));
                    out.println("Ход № 3. Ходи крестиками - Х");

                    final String go5 = in.readLine();
                    System.out.println("User походил крестиками");
                    prinField(StringToArr(go5));
                   // String[][] arr = StringToArr(go5);

                    out.println(whoWin(go6));
                    System.out.println(whoWin(go6));
                }

            }
        }
    }

    public static String arrToString(String[][] field) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String[] ch1 : field) {
            for (String ch2 : ch1) {
                stringBuilder.append(ch2).append(" ");
            }
            stringBuilder.append(",");
        }
        String string = stringBuilder.toString();
        return string;
    }

    public static String[][] StringToArr(String str) {


        String[] rows = str.split(",");
        String[][] matrix = new String[rows.length][];
        int r = 0;
        for (String row : rows) {
            matrix[r++] = row.split("\\|");
        }
        return matrix;
    }

    public static void prinField(String field[][]) {
        for (String[] row : field) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static String whoWin(String field[][]) {
        String whoWin;

        if (isWin(field, CROSS)) {
            whoWin = "Победили крестики";
        } else if (isWin(field, ZERO)) {
            whoWin = "Победили нолики";
        } else {
            whoWin = "Никто не победил";
        }
        return whoWin;
    }

    public static boolean isWin(String[][] field, String player) {
        int count = 0;
        for (int i = 0; i < SIZE; i++) { // проверка столбцов
            for (int j = 0; j < SIZE; j++) {
                if (field[j][i].equals(player)) {
                    count++;
                }
                if (count == SIZE) {
                    return true;
                }
            }
            count = 0;
        }
        count = 0;

        for (int i = 0; i < SIZE; i++) { // проверка строк
            for (int j = 0; j < SIZE; j++) {

                if (field[i][j].equals(player)) {
                    count++;
                }
                if (count == SIZE) {
                    return true;
                }
            }
            count = 0;
        }
        count = 0;
        for (int i = 0; i < SIZE; i++) {

            if (field[i][i].equals(player)) { //проверка диагонали слева направо
                count++;
            }
        }
        if (count == SIZE) {
            return true;
        }
        count = 0;

        for (int i = 0; i < SIZE; i++) {
            if (field[i][SIZE - 1 - i].equals(player)) { //проверка диагонали справа налево
                count++;
            }
        }
        if (count == SIZE) {
            return true;
        }
        return false;

    }
}