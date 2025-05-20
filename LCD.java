import java.util.Scanner;

public class LCD {

    // Representation of segments for digits 0 to 9
    private static final int[][] DIGIT_SEGMENTS = {
        {1, 1, 1, 0, 1, 1, 1}, // 0
        {0, 0, 1, 0, 0, 1, 0}, // 1
        {1, 0, 1, 1, 1, 0, 1}, // 2
        {1, 0, 1, 1, 0, 1, 1}, // 3
        {0, 1, 1, 1, 0, 1, 0}, // 4
        {1, 1, 0, 1, 0, 1, 1}, // 5
        {1, 1, 0, 1, 1, 1, 1}, // 6
        {1, 0, 1, 0, 0, 1, 0}, // 7
        {1, 1, 1, 1, 1, 1, 1}, // 8
        {1, 1, 1, 1, 0, 1, 1}  // 9
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int s = sc.nextInt(); // Size of the LCD digits
            String n = sc.next();  // The number to display on the LCD

            if (s == 0 && n.equals("0")) break; // Termination condition

            // Calculate the number of rows and columns required
            int rows = 2 * s + 3; // Rows required for each digit (top + middle + bottom)
            int cols = n.length() * (s + 3) - 1; // Columns required for the entire number

            // Array to store the output lines
            StringBuilder[] output = new StringBuilder[rows];
            for (int i = 0; i < rows; i++) {
                output[i] = new StringBuilder();
            }

            for (int idx = 0; idx < n.length(); idx++) {
                int digit = n.charAt(idx) - '0';  // Get the digit (0-9)
                int[] seg = DIGIT_SEGMENTS[digit]; // Get the corresponding segments

                // Top
                output[0].append(" ");
                output[0].append(seg[0] == 1 ? repeat("-", s) : repeat(" ", s));
                output[0].append(" ");

                // Upper segments
                for (int i = 1; i <= s; i++) {
                    output[i].append(seg[1] == 1 ? "|" : " ");
                    output[i].append(repeat(" ", s));
                    output[i].append(seg[2] == 1 ? "|" : " ");
                }

                // Middle
                output[s + 1].append(" ");
                output[s + 1].append(seg[3] == 1 ? repeat("-", s) : repeat(" ", s));
                output[s + 1].append(" ");

                // Lower segments
                for (int i = s + 2; i < rows - 1; i++) {
                    output[i].append(seg[4] == 1 ? "|" : " ");
                    output[i].append(repeat(" ", s));
                    output[i].append(seg[5] == 1 ? "|" : " ");
                }

                // Bottom
                output[rows - 1].append(" ");
                output[rows - 1].append(seg[6] == 1 ? repeat("-", s) : repeat(" ", s));
                output[rows - 1].append(" ");

                // Add space between digits
                if (idx != n.length() - 1) {
                    for (int i = 0; i < rows; i++) {
                        output[i].append(" ");
                    }
                }
            }

            // Print the output
            for (StringBuilder line : output) {
                System.out.println(line.toString());
            }
            System.out.println();
        }

        sc.close(); // Close the scanner
    }

    // Helper method to repeat a string for a specified number of times
    private static String repeat(String str, int times) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < times; i++) {
            result.append(str);
        }
        return result.toString();
    }
}
