import java.util.*;

public class Stackemup {
    static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    static final String[] VALUES = {
        "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "Jack", "Queen", "King", "Ace"
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());

        while (t-- > 0) {
            int shuffles = Integer.parseInt(sc.nextLine());
            int[][] shuffleOrder = new int[shuffles][52];

            for (int i = 0; i < shuffles; i++) {
                int count = 0;
                while (count < 52) {
                    String[] parts = sc.nextLine().trim().split("\\s+");
                    for (String p : parts) {
                        if (!p.isEmpty()) {
                            shuffleOrder[i][count++] = Integer.parseInt(p) - 1;
                        }
                    }
                }
            }

            int[] deck = new int[52];
            for (int i = 0; i < 52; i++) deck[i] = i;

            List<String> operations = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) break;
                operations.add(line);
            }

            for (String op : operations) {
                int k = Integer.parseInt(op) - 1;
                int[] newDeck = new int[52];
                for (int i = 0; i < 52; i++) {
                    newDeck[i] = deck[shuffleOrder[k][i]];
                }
                deck = newDeck;
            }

            for (int card : deck) {
                System.out.println(VALUES[card % 13] + " of " + SUITS[card / 13]);
            }

            if (t > 0) System.out.println();
        }

        sc.close();
    }
}
