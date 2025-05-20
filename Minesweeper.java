import java.util.Scanner;

public class Minesweeper {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int fieldNumber = 1;

        while (true) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            sc.nextLine(); // consume newline

            if (n == 0 && m == 0) break;

            char[][] grid = new char[n][m];

            for (int i = 0; i < n; i++) {
                grid[i] = sc.nextLine().toCharArray();
            }

            if (fieldNumber > 1) {
                System.out.println();
            }

            System.out.println("Field #" + fieldNumber + ":");

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == '*') {
                        System.out.print('*');
                    } else {
                        int count = 0;
                        for (int dx = -1; dx <= 1; dx++) {
                            for (int dy = -1; dy <= 1; dy++) {
                                int ni = i + dx;
                                int nj = j + dy;
                                if (ni >= 0 && ni < n && nj >= 0 && nj < m && grid[ni][nj] == '*') {
                                    count++;
                                }
                            }
                        }
                        System.out.print(count);
                    }
                }
                System.out.println();
            }

            fieldNumber++;
        }

        sc.close();
    }
}
