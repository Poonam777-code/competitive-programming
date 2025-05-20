
import java.util.*;

public class CommonPermutation  {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String s1 = sc.nextLine();
            if (!sc.hasNextLine()) break;
            String s2 = sc.nextLine();

            int[] freq1 = new int[26];
            int[] freq2 = new int[26];

            for (char c : s1.toCharArray()) {
                if (Character.isLowerCase(c)) {
                    freq1[c - 'a']++;
                }
            }

            for (char c : s2.toCharArray()) {
                if (Character.isLowerCase(c)) {
                    freq2[c - 'a']++;
                }
            }

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                int minCount = Math.min(freq1[i], freq2[i]);
                for (int j = 0; j < minCount; j++) {
                    result.append((char) (i + 'a'));
                }
            }

            System.out.println(result);
        }

        sc.close();
    }
}
