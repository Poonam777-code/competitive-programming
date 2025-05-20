import java.util.*;
class thetrip {
    public static void main(String... args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                int n = sc.nextInt();
                if (n == 0) break;

                double[] money = new double[n];
                for (int i = 0; i < n; i++) {
                    money[i] = sc.nextDouble();
                }

                double ans = getAns(money, n);
                System.out.println(String.format("$%.2f", ans));
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static double getAns(double[] money, int n) {
        double diff, mean = 0.0, pos = 0.0, neg = 0.0;

        for (int i = 0; i < n; i++) {
            mean += money[i];
        }
        mean /= n;

        for (int i = 0; i < n; i++) {
            diff = Math.round((money[i] - mean) * 100.0) / 100.0;
            if (diff >= 0) {
                pos += diff;
            } else {
                neg += diff;
            }
        }
        return Math.max(pos, -neg);
    }
}