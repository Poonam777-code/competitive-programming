import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static class Team implements Comparable<Team> {
        String name;
        int points, gamesPlayed, wins, ties, losses, goalsScored, goalsAgainst;

        Team(String name) {
            this.name = name;
        }

        int goalDifference() {
            return goalsScored - goalsAgainst;
        }

        @Override
        public int compareTo(Team other) {
            if (this.points != other.points) return other.points - this.points;
            if (this.wins != other.wins) return other.wins - this.wins;
            if (this.goalDifference() != other.goalDifference()) return other.goalDifference() - this.goalDifference();
            if (this.goalsScored != other.goalsScored) return other.goalsScored - this.goalsScored;
            if (this.gamesPlayed != other.gamesPlayed) return this.gamesPlayed - other.gamesPlayed;
            return this.name.compareToIgnoreCase(other.name);
        }

        @Override
        public String toString() {
            return String.format(
                "%s %dp, %dg (%d-%d-%d), %dgd (%d-%d)",
                name, points, gamesPlayed, wins, ties, losses,
                goalDifference(), goalsScored, goalsAgainst
            );
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tournaments = Integer.parseInt(br.readLine().trim());

        for (int t = 0; t < tournaments; t++) {
            String tournamentName = br.readLine().trim();

            int numTeams = Integer.parseInt(br.readLine().trim());
            Map<String, Team> teamMap = new HashMap<>();

            for (int i = 0; i < numTeams; i++) {
                String teamName = br.readLine().trim();
                teamMap.put(teamName, new Team(teamName));
            }

            int games = Integer.parseInt(br.readLine().trim());

            for (int i = 0; i < games; i++) {
                String game = br.readLine().trim();
                String[] parts = game.split("#|@");
                String team1 = parts[0];
                int score1 = Integer.parseInt(parts[1]);
                int score2 = Integer.parseInt(parts[2]);
                String team2 = parts[3];

                Team t1 = teamMap.get(team1);
                Team t2 = teamMap.get(team2);

                t1.gamesPlayed++;
                t2.gamesPlayed++;
                t1.goalsScored += score1;
                t1.goalsAgainst += score2;
                t2.goalsScored += score2;
                t2.goalsAgainst += score1;

                if (score1 > score2) {
                    t1.wins++; t1.points += 3;
                    t2.losses++;
                } else if (score1 < score2) {
                    t2.wins++; t2.points += 3;
                    t1.losses++;
                } else {
                    t1.ties++; t2.ties++;
                    t1.points++; t2.points++;
                }
            }

            List<Team> standings = new ArrayList<>(teamMap.values());
            Collections.sort(standings);

            System.out.println(tournamentName);
            for (int i = 0; i < standings.size(); i++) {
                System.out.printf("%d) %s\n", i + 1, standings.get(i));
            }

            if (t < tournaments - 1) System.out.println();
        }
    }
}
