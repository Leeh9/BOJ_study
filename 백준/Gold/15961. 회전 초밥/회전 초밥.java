import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, d, k, c, cnt = 0, max = 0;
	static int[] arr;
	static int[] s;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new int[N];
		s = new int[3001];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < k; i++) {
			if (s[arr[i]] == 0) {
				cnt++;
			}
			s[arr[i]]++;
		}
		max = cnt;
		for (int i = 0; i < N; i++) {
			if (max <= cnt) {
				if (s[c] == 0) {
					max = cnt + 1;
				} else {
					max = cnt;
				}
			}

			if (s[arr[i]] == 1) {
				cnt--;
			}
			s[arr[i]]--;

			if (i + k >= N) {
				int t = (i + k) % N;
				if (s[arr[t]] == 0) {
					cnt++;
				}
				s[arr[t]]++;
			} else {
				if (s[arr[i + k]] == 0) {
					cnt++;
				}
				s[arr[i + k]]++;
			}
		}
		System.out.println(max);

	}
}