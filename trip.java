import java.util.Scanner;

class trip {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            int n = Integer.parseInt(sc.nextLine());
            if (n == 0) break;
            
            double[] money = new double[n];
            double sum = 0.0;

            for (int i = 0; i < n; i++) {
                money[i] = Double.parseDouble(sc.nextLine());
                sum += money[i];
            }

            double avg = sum / n;

            double pos = 0.0;
            double neg = 0.0;

            for (int i = 0; i < n; i++) {
                double diff = money[i] - avg;
                diff = ((int)(diff * 100)) / 100.0; // truncate towards zero
                if (diff > 0) {
                    pos += diff;
                } else {
                    neg += diff;
                }
            }

            double result = Math.max(pos, -neg);
            System.out.printf("$%.2f\n", result);
        }

        sc.close();
    }
}
