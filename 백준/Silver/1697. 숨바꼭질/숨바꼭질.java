import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, K, min;
	static int[] dx = { -1, 1, 2 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();
		min = 0;
		if (N == K) {
			min = 0;
		} else
			bfs();
		System.out.println(min);
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		int[] visit = new int[200001];
		q.add(N);

		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				int n = q.poll();
				for (int i = 0; i < 3; i++) {
					int nx;
					if (i == 2) {
						nx = n * dx[i];
					} else {
						nx = n + dx[i];
					}
					if (nx >= 0 && nx < 200001 && visit[nx] == 0) {
						q.add(nx);
						visit[nx] = 1;
					}
					if (nx == K) {
						min = time + 1;
						return;
					}

				}
			}

			time++;
		}

	}
}