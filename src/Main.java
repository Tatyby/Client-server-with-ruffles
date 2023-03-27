import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static final int SIZE = 3;  // размер поля
    public static final String ZERO = "O";
    public static final String CROSS = "X";
    public static final String EMPTY = "-";

    public static void main(String[] args) {
        String[][] field1 = {

                {EMPTY, EMPTY, CROSS},
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY},

        };
        String[][] field2 = {

                {EMPTY, EMPTY, CROSS},
                {EMPTY, ZERO, EMPTY},
                {EMPTY, EMPTY, CROSS},

        };
        String[][] field3 = {

                {EMPTY, EMPTY, CROSS},
                {EMPTY, ZERO, CROSS},
                {EMPTY, ZERO, CROSS},

        };

        String host = "netology.homework";

        int port = 8080;
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
//
            System.out.println(in.readLine());
            final String field = in.readLine();
            prinField(StringToArr(field));
            System.out.println(in.readLine());

            prinField(field1);
            out.println(arrToString(field1));

            System.out.println(in.readLine());
            prinField(StringToArr(in.readLine()));
            System.out.println(in.readLine());

            prinField(field2);
            out.println(arrToString(field2));

            System.out.println(in.readLine());
            prinField(StringToArr(in.readLine()));
            System.out.println(in.readLine());

            prinField(field3);
            out.println(arrToString(field3));

            System.out.println(in.readLine());




        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void prinField(String field[][]) {
        for (String[] row : field) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
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


}

