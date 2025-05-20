import java.util.*;

public class Waldorf {
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine().trim());

        while (T-- > 0) {
            while (true) {
                String line = sc.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] dims = line.split("\\s+");
                    int m = Integer.parseInt(dims[0]);
                    int n = Integer.parseInt(dims[1]);

                    char[][] grid = new char[m][n];
                    for (int i = 0; i < m; i++) {
                        grid[i] = sc.nextLine().trim().toLowerCase().toCharArray();
                    }

                    int q = Integer.parseInt(sc.nextLine().trim());
                    for (int k = 0; k < q; k++) {
                        String word = sc.nextLine().trim().toLowerCase();
                        boolean found = false;

                        for (int i = 0; i < m && !found; i++) {
                            for (int j = 0; j < n && !found; j++) {
                                if (grid[i][j] == word.charAt(0)) {
                                    for (int d = 0; d < 8; d++) {
                                        int x = i, y = j;
                                        int l;
                                        for (l = 1; l < word.length(); l++) {
                                            x += dx[d];
                                            y += dy[d];
                                            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != word.charAt(l)) {
                                                break;
                                            }
                                        }
                                        if (l == word.length()) {
                                            System.out.println((i + 1) + " " + (j + 1));
                                            found = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (T > 0) System.out.println();
                    break;
                }
            }
        }

        sc.close();
    }
}
