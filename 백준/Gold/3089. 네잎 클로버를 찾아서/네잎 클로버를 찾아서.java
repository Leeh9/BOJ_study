import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int N, M, X=100000, Y=100000;
	static TreeSet<Integer>[] x;
	static TreeSet<Integer>[] y;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = new TreeSet[200001];
		y = new TreeSet[200001];
		for (int i = 0; i < 200001; i++) {
			x[i] = new TreeSet<>();
			y[i] = new TreeSet<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			a += 100000;
			b += 100000;
			x[a].add(b);
			y[b].add(a);
		}

		String line = br.readLine();
		for (int i = 0; i < M; i++) {
			char c = line.charAt(i);
			if (c == 'L') {
				X= y[Y].lower(X);
			} else if (c == 'R') {
				X= y[Y].higher(X);
			} else if (c == 'U') {
				Y= x[X].higher(Y);
			} else if (c == 'D') {
				Y= x[X].lower(Y);
			}
		}
		
		System.out.println((X-100000)+" "+(Y-100000));
	}
}