import java.math.BigInteger;
import java.util.Scanner;

public class HowManyFibs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String aStr = sc.next();
            String bStr = sc.next();
            if (aStr.equals("0") && bStr.equals("0")) break;

            BigInteger a = new BigInteger(aStr);
            BigInteger b = new BigInteger(bStr);

            BigInteger f1 = BigInteger.ONE;
            BigInteger f2 = BigInteger.valueOf(2);

            int count = 0;
            if (isInRange(f1, a, b)) count++;
            if (isInRange(f2, a, b)) count++;

            while (true) {
                BigInteger f3 = f1.add(f2);
                if (f3.compareTo(b) > 0) break;
                if (isInRange(f3, a, b)) count++;
                f1 = f2;
                f2 = f3;
            }

            System.out.println(count);
        }
        sc.close();
    }

    static boolean isInRange(BigInteger num, BigInteger low, BigInteger high) {
        return num.compareTo(low) >= 0 && num.compareTo(high) <= 0;
    }
}
