import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int T, K;
	static String W;
	static int[][] a;
	static boolean[] visit;
	static ArrayList<Integer>[] list = new ArrayList[26];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			W = br.readLine();
			K = Integer.parseInt(br.readLine());
			for (int i = 0; i < 26; i++) {
				list[i] = new ArrayList<>();
			}
			for (int i = 0; i < W.length(); i++) {
				int t = W.charAt(i) - 'a';
				list[t].add(i);
			}
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < 26; i++) {
				if (list[i].size() >= K) {
					for (int j = 0; j < list[i].size(); j++) {
						if (j + K <= list[i].size()) {
							int len = list[i].get(j + K - 1) - list[i].get(j) + 1;
							max = Math.max(max, len);
							min = Math.min(min, len);
						}
					}
				}
			}

			if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
				System.out.println("-1");
			} else {
				System.out.println(min + " " + max);
			}
		}

	}
}