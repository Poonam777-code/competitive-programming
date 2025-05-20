
    import java.util.Scanner;

public class WERTYU  {
    public static void main(String[] args) {
        String keys = "`1234567890-=QWERTYUIOP[]\\ASDFGHJKL;'ZXCVBNM,./";
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                if (ch == ' ' || ch == '\n') {
                    result.append(ch);
                } else {
                    int idx = keys.indexOf(ch);
                    if (idx > 0) {
                        result.append(keys.charAt(idx - 1));
                    } else {
                        result.append(ch);
                    }
                }
            }
            System.out.println(result.toString());
        }
        sc.close();
    }
}
