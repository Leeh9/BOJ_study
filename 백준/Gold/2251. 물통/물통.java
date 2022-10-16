import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
	static int A, B, C;
	static boolean[][][] visit;
	static HashSet<Integer> set = new HashSet<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		int a = A;
		int b = B;
		int c = C;
		visit = new boolean[A + 1][B + 1][C + 1];
		dfs(0, 0, c);
		ArrayList<Integer> list = new ArrayList<>(set);
		Collections.sort(list);

		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+" ");
		}
	}

	private static void dfs(int a, int b, int c) {
		if (visit[a][b][c]) {
			return;
		}
		visit[a][b][c] = true;

		if (a == 0) {
			set.add(c);
		}
		// A 처리
		if (b + a > B) {
			dfs(a - (B - b), B, c);
		} else {
			dfs(0, b + a, c);
		}

		if (c + a > C) {
			dfs(a - (C - c), b, C);
		} else {
			dfs(0, b, c + a);
		}

		// B 처리
		if (a + b > A) {
			dfs(A, b - (A - a), c);
		} else {
			dfs(a + b, 0, c);
		}

		if (c + b > C) {
			dfs(a, b - (C - c), C);
		} else {
			dfs(a, 0, c + b);
		}

		// C 처리
		if (c + a > A) {
			dfs(A, b, c - (A - a));
		} else {
			dfs(c + a, b, 0);
		}

		if (c + b > B) {
			dfs(a, B, c - (B - b));
		} else {
			dfs(a, c + b, 0);
		}
	}
}