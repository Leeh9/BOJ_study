import java.io.BufferedReader; // 입력 라이브러리
import java.io.IOException; // 예외 라이브러리
import java.io.InputStreamReader; // 입력 라이브러리
import java.util.StringTokenizer; // 문자열 잘라주는 라이브러리

public class Main { // 클래스 시작
	public static void main(String[] args) throws IOException { // 메인 함수 시작, 예외처리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // bufferedreader를 이용한 입력받기 선언
		StringTokenizer st = new StringTokenizer(br.readLine()); // 공백을 기준으로 한줄 읽고 문자열을 잘라줌

		int N = Integer.parseInt(st.nextToken()); // 신청한 동아리의 수 입력받기
		int S = Integer.parseInt(st.nextToken()); // 팀원 3명 능력치 합에 대한 스마트클럽 가입조건 입력받기
		int M = Integer.parseInt(st.nextToken()); // 개인 능력치에 대한 스마트클럽 가입조건 입력받기
		int[][] arr = new int[N][3]; // 동아리원들의 능력치를 입력받을 2차원 배열 선언
		for (int i = 0; i < N; i++) { // 동아리의 수만큼 반복문
			st = new StringTokenizer(br.readLine()); // 한 줄 문자열 잘라주기
			for (int j = 0; j < 3; j++) { // 팀원 3명 수만큼 반복문
				arr[i][j] = Integer.parseInt(st.nextToken()); // 팀원 한명씩 능력치 입력받기
			} // 반복문닫기
		} // 반복문닫기

		int cnt = 0; // 스마트 클럽 가입이 가능한 동아리의 수를 구할 변수 선언
		boolean[] refuse = new boolean[N]; // 가입이 가능한 동아리를 체크할 boolean배열
		for (int i = 0; i < N; i++) { // 동아리의 수만큼 반복문
			int sum = 0; // 팀원 능력치 합을 계산하기 위한 변수
			for (int j = 0; j < 3; j++) { // 팀원 3명 수만큼 반복문
				sum += arr[i][j]; // 각각 동아리 원 능력치 더하기
				if (arr[i][j] < M) { // 개인 능력치가 조건보다 낮다면
					refuse[i] = true; // 해당 동아리는 거절체크
				} // 조건문닫기
			} // 반복문닫기
			if (sum < S) { // 동아리원들의 능력치합이 S보다 낮다면
				refuse[i] = true; // 해당 동아리는 거절체크
			} // 조건문닫기

			if (!refuse[i]) { // 해당 동아리가 거절되어있지 않다면
				cnt++; // 가입이 가능하므로 카운트 +1
			} // 조건문닫기
		} // 반복문닫기
		StringBuilder sb = new StringBuilder();
		System.out.println(cnt); // 가입이 가능한 동아리의 수 출력
		for (int i = 0; i < N; i++) { // 동아리의 수만큼 반복문
			if (!refuse[i]) { // 해당 동아리가 거절되어있지 않다면
				for (int j = 0; j < 3; j++) { // 팀원 3명 수만큼 반복문
					sb.append(arr[i][j] + " ");
					//System.out.print(arr[i][j] + " "); // 해당 동아리원 능력치 출력
				} // 반복문닫기
			} // 조건문닫기
		} // 반복문닫기
		
		System.out.println(sb);
	} // 메인함수 닫기
} // 클래스 닫기