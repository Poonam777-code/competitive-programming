import java.io.*;

public class StacksOfFlapjacks {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;

            String[] tokens = line.trim().split("\\s+");
            int n = tokens.length;
            int[] stack = new int[n];

            for (int i = 0; i < n; i++) {
                stack[i] = Integer.parseInt(tokens[i]);
            }

            System.out.println(line.trim());

            for (int size = n; size > 1; size--) {
                int maxIndex = findMaxIndex(stack, size);
                if (maxIndex != size - 1) {
                    if (maxIndex != 0) {
                        flip(stack, maxIndex);
                        System.out.print((n - maxIndex) + " ");
                    }
                    flip(stack, size - 1);
                    System.out.print((n - (size - 1)) + " ");
                }
            }
            System.out.println("0");
        }
    }

    static int findMaxIndex(int[] arr, int size) {
        int maxIdx = 0;
        for (int i = 1; i < size; i++) {
            if (arr[i] > arr[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    static void flip(int[] arr, int end) {
        for (int i = 0, j = end; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
