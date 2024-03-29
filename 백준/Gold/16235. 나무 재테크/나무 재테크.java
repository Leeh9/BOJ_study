import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] arr, food, Tree;
	static int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static PriorityQueue<Point> p = new PriorityQueue<>();
	static ArrayList<Point> DieTree = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		food = new int[N][N];
		Tree = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				food[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			Arrays.fill(arr[i], 5);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			p.add(new Point(x - 1, y - 1, age));

		}
		while (K > 0) {
			Spring();
			Summer();
			Fall();
			Winter();
			K--;
		}

		System.out.println(p.size());
	}

	private static void Spring() {
		int size = p.size();
		ArrayList<Point> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			Point n = p.poll();
			if (arr[n.x][n.y] >= n.age) {
				// 나이가 증가하는 나무 리스트를 일단 임시저장
				list.add(new Point(n.x, n.y, n.age + 1));
				arr[n.x][n.y] -= n.age;
			} else {
				DieTree.add(new Point(n.x, n.y, n.age));
			}
		}
		// 저장해놓은 리스트 옮기기
		for (int i = 0; i < list.size(); i++) {
			Point n = list.get(i);
			p.add(new Point(n.x, n.y, n.age));
		}
	}

	private static void Summer() {
		// 죽은 나무들 양분으로 변환
		for (int i = 0; i < DieTree.size(); i++) {
			Point n = DieTree.get(i);
			arr[n.x][n.y] += n.age / 2;
		}
		DieTree.clear();
	}

	private static void Fall() {
		ArrayList<Point> BirthTree = new ArrayList<>();
		int size = p.size();
		for (int i = 0; i < size; i++) {
			Point n = p.poll();
			if (n.age % 5 == 0) {
				for (int k = 0; k < 8; k++) {
					int nx = n.x + dx[k];
					int ny = n.y + dy[k];
					if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
						BirthTree.add(new Point(nx, ny, 1));
					}
				}
			}
			BirthTree.add(new Point(n.x, n.y, n.age));
		}

		for (int i = 0; i < BirthTree.size(); i++) {
			Point n = BirthTree.get(i);
			p.add(new Point(n.x, n.y, n.age));
		}
	}

	private static void Winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] += food[i][j];
			}
		}
	}

	static class Point implements Comparable<Point> {
		int x;
		int y;
		int age;

		public Point(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Point o) {
			return this.age - o.age;
		}
	}
}