import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long X = sc.nextInt();
		long Y = sc.nextInt();
		long W = sc.nextInt();
		long S = sc.nextInt();

		long res = 0;
		if (X == 0) {
			if (Y == 0) {
				System.out.println(res);
				return;
			}
//				else {
//				System.out.println(W * Y);
//				return;
//			}
		}
//			else if (Y == 0) {
//			System.out.println(W * X);
//			return;
//		}
		long min = Math.min(X, Y);
		long max = Math.max(X, Y);
		if (W * 2 < S) {
			res = W * (X + Y);
		} else {
			res = S * min;
			if (S * 2 < W * 2) {
				long temp = (max - min);
				if (temp % 2 == 1) {
					res += (S * 2) * (temp - 1) / 2 + W;
				} else {
					res += (S * 2) * (temp) / 2;
				}
			} else {
				res += W * (max - min);
			}
		}

		System.out.println(res);
	}
}