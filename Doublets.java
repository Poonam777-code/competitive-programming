import java.util.*;

public class Doublets {
    static Set<String> dictionary = new HashSet<>();
    static Map<Integer, Set<String>> wordsByLength = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            dictionary.add(line);
            wordsByLength.computeIfAbsent(line.length(), k -> new HashSet<>()).add(line);
        }

        boolean firstOutput = true;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split(" ");
            if (parts.length != 2) continue;

            String start = parts[0];
            String end = parts[1];

            if (!firstOutput) {
                System.out.println();
            }
            firstOutput = false;

            List<String> path = bfs(start, end);
            if (path == null) {
                System.out.println("No solution.");
            } else {
                for (String word : path) {
                    System.out.println(word);
                }
            }
        }
    }

    static List<String> bfs(String start, String end) {
        if (!dictionary.contains(start) || !dictionary.contains(end) || start.length() != end.length())
            return null;

        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(end)) break;

            for (String neighbor : generateNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (!parent.containsKey(end) && !start.equals(end)) return null;

        List<String> path = new ArrayList<>();
        String curr = end;
        path.add(curr);
        while (!curr.equals(start)) {
            curr = parent.get(curr);
            path.add(curr);
        }
        Collections.reverse(path);
        return path;
    }

    static List<String> generateNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();
        Set<String> wordSet = wordsByLength.get(word.length());

        for (int i = 0; i < chars.length; i++) {
            char original = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == original) continue;
                chars[i] = c;
                String newWord = new String(chars);
                if (wordSet.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
            chars[i] = original;
        }

        return neighbors;
    }
}
