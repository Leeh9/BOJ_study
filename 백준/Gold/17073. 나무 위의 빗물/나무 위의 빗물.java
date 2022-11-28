import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, W , cnt = 0;
	static ArrayList<Integer>[] list;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		list = new ArrayList[N+1];
		
		for(int i=1; i<N+1; i++) {
			list[i] = new ArrayList<>();
		}
		visit = new boolean[N + 1];
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list[a].add(b);
			list[b].add(a);
		}
		
		for(int i=2; i<N+1; i++) {
			if(list[i].size()==1) {
				cnt++;
			}
		}
		System.out.println(W/(cnt*1.0));

	}

}
