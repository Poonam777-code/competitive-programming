import java.util.*;

public class Bridge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = Integer.parseInt(sc.nextLine().trim());

        for (int t = 0; t < testCases; t++) {
            if (sc.hasNextLine()) {
                String blank = sc.nextLine().trim();
            }

            int n = Integer.parseInt(sc.nextLine().trim());
            List<Integer> people = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                people.add(Integer.parseInt(sc.nextLine().trim()));
            }

            Collections.sort(people);
            List<String> steps = new ArrayList<>();
            int totalTime = 0;

            while (people.size() > 3) {
                int a = people.get(0);
                int b = people.get(1);
                int y = people.get(people.size() - 2);
                int z = people.get(people.size() - 1);

                int time1 = b + a + z + b;
                int time2 = z + a + y + a;

                if (time1 < time2) {
                    totalTime += time1;
                    steps.add(a + " " + b);
                    steps.add(String.valueOf(a));
                    steps.add(y + " " + z);
                    steps.add(String.valueOf(b));
                } else {
                    totalTime += time2;
                    steps.add(a + " " + z);
                    steps.add(String.valueOf(a));
                    steps.add(a + " " + y);
                    steps.add(String.valueOf(a));
                }

                people.remove(people.size() - 1);
                people.remove(people.size() - 1);
            }

            if (people.size() == 3) {
                int a = people.get(0);
                int b = people.get(1);
                int c = people.get(2);
                totalTime += a + b + c;
                steps.add(a + " " + b);
                steps.add(String.valueOf(a));
                steps.add(a + " " + c);
            } else if (people.size() == 2) {
                totalTime += people.get(1);
                steps.add(people.get(0) + " " + people.get(1));
            } else if (people.size() == 1) {
                totalTime += people.get(0);
                steps.add(String.valueOf(people.get(0)));
            }

            System.out.println(totalTime);
            for (String s : steps) {
                System.out.println(s);
            }

            if (t != testCases - 1) {
                System.out.println();
            }
        }
        sc.close();
    }
}
