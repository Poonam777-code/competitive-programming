import java.util.*;

public class Hartals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); 

        while (T-- > 0)
         {
            int N = sc.nextInt();
            int P = sc.nextInt(); 
            boolean[] hartals = new boolean[N + 1];

            for (int i = 0; i < P; i++)
             {
                int h = sc.nextInt(); 
                for (int day = h; day <= N; day += h)
                 {
                    int weekday = day % 7;
                    if (weekday != 6 && weekday != 0) 
                    { 
                        hartals[day] = true;
                    }
                }
            }

            int lostDays = 0;
            for (int i = 1; i <= N; i++) {
                if (hartals[i]) lostDays++;
            }

            System.out.println(lostDays);
        }
        sc.close();
    }
}
