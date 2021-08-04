package cinema;
import java.util.Scanner;
public class Cinema {
    static int totalIncome = 0;
    static int currentIncome = 0;
    static int ticketCounter = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        char [][] matrix = new char[rows + 1][seats + 1];
        matrix[0][0] = ' ';
        for (int i = 1; i < matrix.length; i++) {
            matrix[i][0] = (char) (i + 48) ;
         }
        for (int j = 1; j < matrix[0].length; j++) {
            matrix[0][j] = (char) (j + 48);
        }
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seats; j++) {
                matrix[i][j] = 'S';
            }
        }

        if (rows * seats <= 60) {
            totalIncome = rows * seats * 10;
        } else if (rows * seats > 60) {
            int frontRows = rows / 2;
            int backRows = rows - frontRows;
            totalIncome = frontRows * 10 * seats + (backRows * 8 * seats);
        }

        boolean loop = true;
        while(loop) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int selection = scanner.nextInt();

            switch (selection){
                case 1:
                    printMatrix(rows, seats, matrix);
                    break;
                case 2:
                    buyTicket(scanner, rows, seats, matrix);
                    break;
                case 3:
                    showStatistics(rows, seats);
                    break;
                case 0:
                    loop = false;
                    return;
            }
        }
    }

    public static void buyTicket(Scanner scanner, int rows, int seats, char[][] matrix) {
        boolean seatOccupied;

        do {
            System.out.println("Enter a row number:");
            int x = scanner.nextInt();
            if (x > rows) {
                System.out.println("Wrong input!");
                break;
            }
            System.out.println("Enter a seat number in that row:");
            int y = scanner.nextInt();
            if (y > seats) {
                System.out.println("Wrong input!");
                break;
            }

            if (matrix[x][y] == 'B') {
                System.out.println("That ticket has already been purchased!");
                seatOccupied = true;
            } else {
                seatOccupied = false;
                matrix[x][y] = 'B';

                if (rows * seats <= 60) {
                    System.out.println("Ticket price: $10");
                    currentIncome += 10;
                    ticketCounter++;
                } else if (rows * seats > 60) {
                    if (x <= rows / 2) {
                        System.out.println("Ticket price: $10");
                        currentIncome += 10;
                    } else {
                        System.out.println("Ticket price: $8");
                        currentIncome += 8;
                    }
                    ticketCounter++;
                }
            }
        } while (seatOccupied);
        printMatrix(rows, seats, matrix);
    }

    private static void showStatistics(int rows, int seats) {
        double percentage = ((double)ticketCounter / (rows * seats)) * 100;

        System.out.println("Number of purchased tickets: " + ticketCounter);
        System.out.println("Percentage: " + String.format("%.2f%%", percentage ));
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    private static void printMatrix(int rows, int seats, char[][] matrix) {
        System.out.println("Cinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}