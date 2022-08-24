import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int N, M;
	static ArrayList<Integer>[] list;
	static boolean[] check;
	static boolean res;

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		list = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			graph.add(new ArrayList<>());
			int a = sc.nextInt();
			int b = sc.nextInt();
			list[a].add(b);
			list[b].add(a);
		}
		for (int i = 0; i < N; i++) {
			check = new boolean[N];
			dfs(i, 1);
			if (res) {
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);

	}

	static void dfs(int idx, int cnt) {
		if (cnt == 5) {
			res = true;
			return;
		}
		check[idx] = true;
	
		for(int i : list[idx]) {
			if(!check[i]) {
				dfs(i, cnt+1);
			}
		}
		check[idx] = false;
	}
}