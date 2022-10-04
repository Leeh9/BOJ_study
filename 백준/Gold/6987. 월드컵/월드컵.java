import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] team1 = {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
    static int[] team2 = {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
    static boolean check;
    static boolean res;
    static int[][] arr = new int[6][3];
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		/*
		 * 생각해 볼 만한 전략 1. 각 팀은 승 무 패 합쳐서 5가 되어야 함 2. 무승부가 없을 때 6팀의 승, 패는 합쳐서 15가 되어야 함
		 * 3. 무승부가 있을 때 15에서 부족한 수 x2만큼 무승부에 있어야 함 4. 전승,전패,전무 팀이 있다면 그 외의 모든 팀은
		 * 1패,1승,1무 이상이 무조건 있어야 함 5. 승과 패의 수는 항상 같아야 함
		 */
		for (int t = 0; t < 4; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			boolean[][] battle = new boolean[6][6];
			int win5 = 0;
			int draw5 = 0;
			int lose5 = 0;
			check = false;
			res = false;
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j] > 5 || arr[i][j]<0) {
						check = true;
					}
				}
				// 1번
				if ((arr[i][0] + arr[i][1] + arr[i][2]) != 5) {
					check = true;
				}
			}
			int win_cnt = 0;
			int lose_cnt = 0;
			int draw_cnt = 0;
			for (int i = 0; i < 6; i++) {
				win_cnt += arr[i][0];
				draw_cnt += arr[i][1];
				lose_cnt += arr[i][2];
				if (arr[i][0] == 5) {
					win5 = i;
				}
				if (arr[i][1] == 5) {
					draw5 = i;
				}
				if (arr[i][2] == 5) {
					lose5 = i;
				}
			}

			if ((win_cnt + lose_cnt + draw_cnt) != 30) {
				check = true;
			}
			// 2번
			if (draw_cnt == 0) {
				if (win_cnt != 15 || lose_cnt != 15) {
					check = true;
				}
			}
			if (draw_cnt % 2 == 1) {
				check = true;
			}

			// 승패의 수는 같아야 함
			if (win_cnt != lose_cnt)
				check = true;

			if(!check) {
				dfs(0);
			}

			if (res)
				sb.append("1 ");
			else
				sb.append("0 ");

		}
		System.out.println(sb.toString());
	}

	private static void dfs(int idx) {
		if(res)
			return;
		
		if(idx ==15) {
			res = true;
			return;
		}
		
		if(arr[team1[idx]][0] > 0 && arr[team2[idx]][2] >0) {
			arr[team1[idx]][0]--;
			arr[team2[idx]][2]--;
			dfs(idx+1);
			arr[team1[idx]][0]++;
			arr[team2[idx]][2]++;
		}
		
		if(arr[team1[idx]][1] > 0 && arr[team2[idx]][1] >0) {
			arr[team1[idx]][1]--;
			arr[team2[idx]][1]--;
			dfs(idx+1);
			arr[team1[idx]][1]++;
			arr[team2[idx]][1]++;
		}
		
		if(arr[team1[idx]][2] > 0 && arr[team2[idx]][0] >0) {
			arr[team1[idx]][2]--;
			arr[team2[idx]][0]--;
			dfs(idx+1);
			arr[team1[idx]][2]++;
			arr[team2[idx]][0]++;
		}
	}
}