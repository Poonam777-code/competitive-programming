import java.io.*;
import java.util.*;

public class Voting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        br.readLine(); 

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] candidates = new String[n];
            for (int i = 0; i < n; i++) {
                candidates[i] = br.readLine();
            }

            List<int[]> votes = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
                String[] parts = line.trim().split("\\s+");
                int[] ballot = new int[n];
                for (int i = 0; i < n; i++) {
                    ballot[i] = Integer.parseInt(parts[i]) - 1;
                }
                votes.add(ballot);
            }

            boolean[] eliminated = new boolean[n];
            int totalVotes = votes.size();
            boolean winnerDeclared = false;

            while (!winnerDeclared) {
                int[] voteCounts = new int[n];
                for (int[] ballot : votes) {
                    for (int choice : ballot) {
                        if (!eliminated[choice]) {
                            voteCounts[choice]++;
                            break;
                        }
                    }
                }

                int maxVotes = 0;
                int minVotes = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    if (!eliminated[i]) {
                        maxVotes = Math.max(maxVotes, voteCounts[i]);
                        minVotes = Math.min(minVotes, voteCounts[i]);
                    }
                }

                if (maxVotes > totalVotes / 2) {
                    for (int i = 0; i < n; i++) {
                        if (voteCounts[i] == maxVotes) {
                            System.out.println(candidates[i]);
                        }
                    }
                    winnerDeclared = true;
                    break;
                }

                boolean tie = true;
                for (int i = 0; i < n; i++) {
                    if (!eliminated[i] && voteCounts[i] != minVotes) {
                        tie = false;
                        break;
                    }
                }

                if (tie) {
                    for (int i = 0; i < n; i++) {
                        if (!eliminated[i]) {
                            System.out.println(candidates[i]);
                        }
                    }
                    winnerDeclared = true;
                    break;
                }

                for (int i = 0; i < n; i++) {
                    if (!eliminated[i] && voteCounts[i] == minVotes) {
                        eliminated[i] = true;
                    }
                }
            }

            if (t > 0) {
                System.out.println();
            }
        }
    }
}
