import java.util.*;

public class LongestNap{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int day = 1;
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            sc.nextLine();

            boolean[] minutes = new boolean[1440];
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(" ");
                if (parts.length < 2) continue;
                String[] start = parts[0].split(":");
                String[] end = parts[1].split(":");
                int startMin = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
                int endMin = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);
                for (int m = startMin; m < endMin; m++) {
                    minutes[m] = true;
                }
            }

            int maxNap = 0, napStart = 600;
            int i = 600;
            while (i < 1080) {
                if (!minutes[i]) {
                    int start = i;
                    int len = 0;
                    while (i < 1080 && !minutes[i]) {
                        len++;
                        i++;
                    }
                    if (len > maxNap) {
                        maxNap = len;
                        napStart = start;
                    }
                } else {
                    i++;
                }
            }

            int hour = napStart / 60;
            int min = napStart % 60;
            System.out.printf("Day #%d: the longest nap starts at %02d:%02d and will last for ", day++, hour, min);
            if (maxNap >= 60) {
                System.out.printf("%d hours and %d minutes.\n", maxNap / 60, maxNap % 60);
            } else {
                System.out.printf("%d minutes.\n", maxNap);
            }
        }
    }
}
