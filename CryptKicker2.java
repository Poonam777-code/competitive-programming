import java.io.*;
import java.util.*;

public class CryptKicker2 {

    static final String MAGIC = "the quick brown fox jumps over the lazy dog";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        br.readLine();

        for (int caseNum = 0; caseNum < t; caseNum++) {
            if (caseNum > 0) {
                System.out.println();
            }

            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
                lines.add(line.trim());
            }

            Map<Character, Character> decryptMap = null;
            for (String candidate : lines) {
                decryptMap = tryMapping(candidate);
                if (decryptMap != null) break;
            }

            if (decryptMap == null) {
                System.out.println("No solution.");
            } else {
                for (String l : lines) {
                    System.out.println(decryptLine(l, decryptMap));
                }
            }
        }
    }

    static Map<Character, Character> tryMapping(String candidate) {
        if (candidate.length() != MAGIC.length()) return null;

        Map<Character, Character> mapFromTo = new HashMap<>();
        Map<Character, Character> mapToFrom = new HashMap<>();

        for (int i = 0; i < candidate.length(); i++) {
            char c1 = candidate.charAt(i);
            char c2 = MAGIC.charAt(i);

            if ((c1 == ' ' && c2 != ' ') || (c1 != ' ' && c2 == ' ')) {
                return null;
            }

            if (c1 != ' ') {
                if (mapFromTo.containsKey(c1) && mapFromTo.get(c1) != c2) return null;
                if (mapToFrom.containsKey(c2) && mapToFrom.get(c2) != c1) return null;

                mapFromTo.put(c1, c2);
                mapToFrom.put(c2, c1);
            }
        }

        if (mapFromTo.size() == 26) return mapFromTo;
        return null;
    }

    static String decryptLine(String line, Map<Character, Character> decryptMap) {
        StringBuilder decryptedLine = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c == ' ') {
                decryptedLine.append(' ');
            } else {
                decryptedLine.append(decryptMap.getOrDefault(c, c));
            }
        }
        return decryptedLine.toString();
    }
}
