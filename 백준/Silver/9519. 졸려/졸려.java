import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		String line = br.readLine();

		HashSet<String> set = new HashSet<>();
		ArrayList<String> al = new ArrayList<>();
		set.add(line);
		al.add(line);

		int s = 0;
		if (line.length() % 2 == 1) {
			s = line.length() - 2;
		} else {
			s = line.length() - 1;
		}

		for (int i = 0; i < X; i++) {
			String temp = "";
			for (int j = 0; j < line.length(); j = j + 2) {
				temp += line.charAt(j);
			}
			for (int j = s; j >= 1; j = j - 2) {
				temp += line.charAt(j);
			}
			int size = set.size();
			set.add(temp);
			al.add(temp);
			if(size == set.size()) {
				break;
			}
			line = temp;
		}
		int cnt = X % set.size();
		
		System.out.println(al.get(cnt));
		

	}
}