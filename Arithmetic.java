import java.util.*;

public class Arithmetic {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (true) {
			int n1 = in.nextInt();
			int n2 = in.nextInt();
			if (n1 == 0 && n2 == 0)
				break;

			int carries = 0;
			int carry = 0;
			while (n1 != 0 || n2 != 0) {
				int sum = carry + n1 % 10 + n2 % 10;
				if (sum >= 10)
					++carries;
				carry = sum / 10;
				n1 /= 10;
				n2 /= 10;
			}
			if (carries == 0)
				System.out.println("No carry operation.");
			else if (carries == 1)
				System.out.println("1 carry operation.");
			else
				System.out.println(carries + " carry operations.");
		}
	}
}