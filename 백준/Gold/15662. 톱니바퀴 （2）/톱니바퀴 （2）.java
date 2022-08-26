import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int T, K;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		arr = new int[T][8];
		for (int i = 0; i < T; i++) {
			String line = br.readLine();
			for (int j = 0; j < 8; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int go = Integer.parseInt(st.nextToken());

			turn(num - 1, go);
		}
		int cnt = 0;
		for (int i = 0; i < T; i++) {
			if (arr[i][0] == 1) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	private static void turn(int num, int go) {
		int left = arr[num][6];
		int right = arr[num][2];
		
		// 회전하는 함수
		goTurn(num, go);
		if (num - 1 >= 0 && arr[num - 1][2] != left) {
			// 왼쪽 톱니바퀴와 맞닿은 극이 다를 때 왼쪽 톱니바퀴 회전하는 함수
			turnleft(num - 1, go * -1);
		}
		if (num + 1 < T && arr[num + 1][6] != right) {
			// 오른쪽 톱니바퀴와 맞닿은 극이 다를 때 오른쪽 톱니바퀴 회전하는 함수
			turnright(num + 1, go * -1);
		}
	}

	private static void turnleft(int num, int go) {
		int left = arr[num][6];
		goTurn(num, go);
		if (num - 1 >= 0 && arr[num - 1][2] != left) {
			turnleft(num - 1, go * -1);
		}
	}

	private static void turnright(int num, int go) {
		int right = arr[num][2];
		goTurn(num, go);
		if (num + 1 < T && arr[num + 1][6] != right) {
			turnright(num + 1, go * -1);
		}
	}

	private static void goTurn(int num, int go) {
		if (go == 1) {
			int temp = arr[num][7];
			for (int i = 7; i > 0; i--) {
				arr[num][i] = arr[num][i - 1];
			}
			arr[num][0] = temp;
		}
		if (go == -1) {
			int temp = arr[num][0];
			for (int i = 0; i < 7; i++) {
				arr[num][i] = arr[num][i + 1];
			}
			arr[num][7] = temp;
		}
	}

}