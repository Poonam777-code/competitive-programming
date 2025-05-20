import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

class elfdescribingSequence {
    static class Team implements Comparable<Team> {
        String name;
        int points, gamesPlayed, wins, ties, losses, goalDiff, goalScored, goalsAgainst;

        Team(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(Team other) {
            if (this.points != other.points) return other.points - this.points;
            if (this.wins != other.wins) return other.wins - this.wins;
            if (this.goalDiff != other.goalDiff) return other.goalDiff - this.goalDiff;
            if (this.goalScored != other.goalScored) return other.goalScored - this.goalScored;
            if (this.gamesPlayed != other.gamesPlayed) return this.gamesPlayed - other.gamesPlayed;
            return this.name.compareToIgnoreCase(other.name);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String tournament = br.readLine();
            int n = Integer.parseInt(br.readLine());
            Map<String, Team> teams = new HashMap<>();

            for (int i = 0; i < n; i++) {
                String name = br.readLine();
                teams.put(name.toLowerCase(), new Team(name));
            }

            int games = Integer.parseInt(br.readLine());

            for (int i = 0; i < games; i++) {
                String line = br.readLine();
                String[] parts = line.split("#");
                String team1 = parts[0];
                String[] score = parts[1].split("@");
                int g1 = Integer.parseInt(score[0]);
                int g2 = Integer.parseInt(score[1]);
                String team2 = parts[2];

                Team t1 = teams.get(team1.toLowerCase());
                Team t2 = teams.get(team2.toLowerCase());

                t1.gamesPlayed++;
                t2.gamesPlayed++;

                t1.goalScored += g1;
                t2.goalScored += g2;

                t1.goalsAgainst += g2;
                t2.goalsAgainst += g1;

                t1.goalDiff = t1.goalScored - t1.goalsAgainst;
                t2.goalDiff = t2.goalScored - t2.goalsAgainst;

                if (g1 > g2) {
                    t1.points += 3;
                    t1.wins++;
                    t2.losses++;
                } else if (g2 > g1) {
                    t2.points += 3;
                    t2.wins++;
                    t1.losses++;
                } else {
                    t1.points++;
                    t2.points++;
                    t1.ties++;
                    t2.ties++;
                }
            }

            List<Team> list = new ArrayList<>(teams.values());
            Collections.sort(list);

            pw.println(tournament);
            for (int i = 0; i < list.size(); i++) {
                Team team = list.get(i);
                pw.printf("%d) %s %dp, %dg (%d-%d-%d), %dgd (%d-%d)\n",
                        i + 1,
                        team.name,
                        team.points,
                        team.gamesPlayed,
                        team.wins,
                        team.ties,
                        team.losses,
                        team.goalDiff,
                        team.goalScored,
                        team.goalsAgainst);
            }
            if (t > 0) pw.println();
        }
        pw.flush();
    }
}
