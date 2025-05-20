import java.util.*;

public class CryptKicker{
    static List<String> dict = new ArrayList<>();
    static String[] encryptedWords;
    static Map<Character, Character> decryptMap = new HashMap<>();
    static Map<Character, Character> encryptMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine().trim());
        for (int i = 0; i < n; i++) {
            dict.add(sc.nextLine().toLowerCase());
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) {
                System.out.println();
                continue;
            }

            encryptedWords = line.toLowerCase().split(" ");
            decryptMap.clear();
            encryptMap.clear();

            if (solve(0)) {
                for (int i = 0; i < encryptedWords.length; i++) {
                    if (i > 0) System.out.print(" ");
                    for (char ch : encryptedWords[i].toCharArray()) {
                        System.out.print(decryptMap.getOrDefault(ch, '*'));
                    }
                }
                System.out.println();
            } else {
                for (int i = 0; i < encryptedWords.length; i++) {
                    if (i > 0) System.out.print(" ");
                    for (int j = 0; j < encryptedWords[i].length(); j++) {
                        System.out.print("*");
                    }
                }
                System.out.println();
            }
        }
        sc.close();
    }

    static boolean solve(int index) {
        if (index == encryptedWords.length) return true;

        String word = encryptedWords[index];
        for (String d : dict) {
            if (d.length() != word.length()) continue;

            Map<Character, Character> tempDecrypt = new HashMap<>(decryptMap);
            Map<Character, Character> tempEncrypt = new HashMap<>(encryptMap);

            boolean valid = true;
            for (int i = 0; i < word.length(); i++) {
                char c1 = word.charAt(i);
                char c2 = d.charAt(i);

                if (decryptMap.containsKey(c1) && decryptMap.get(c1) != c2) {
                    valid = false;
                    break;
                }
                if (encryptMap.containsKey(c2) && encryptMap.get(c2) != c1) {
                    valid = false;
                    break;
                }
                decryptMap.put(c1, c2);
                encryptMap.put(c2, c1);
            }

            if (valid && solve(index + 1)) return true;

            decryptMap = tempDecrypt;
            encryptMap = tempEncrypt;
        }

        return false;
    }
}
