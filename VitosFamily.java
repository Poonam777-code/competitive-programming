import java.util.*;

public class VitosFamily {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); 

        while (t-- > 0) {
            int n = sc.nextInt(); 
            int[] houses = new int[n];

            for (int i = 0; i < n; i++) {
                houses[i] = sc.nextInt(); 
            }

            Arrays.sort(houses); 
            int median = houses[n / 2];
            int totalDistance = 0;

            for (int i = 0; i < n; i++) {
                totalDistance += Math.abs(houses[i] - median);
            }

            System.out.println(totalDistance);
        }
    }
}
