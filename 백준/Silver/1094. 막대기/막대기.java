import java.io.BufferedReader; // 입력 받는 라이브러리
import java.io.IOException; // 예외 처리 라이브러리
import java.io.InputStreamReader; // 입력 받는 라이브러리

public class Main { // 클래스 시작
	public static void main(String[] args) throws IOException { // 메인 함수 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력받는 버퍼드리더 선언

		int[] arr = { 64, 32, 16, 8, 4, 2, 1 }; // 가능한 경우의 수 배열 선언
			int N = Integer.parseInt(br.readLine()); // 길이 입력 받기
			int cnt  = 0 ; // 가능한 개수 변수
			for(int j=0; j<7; j++) { // 배열안의 가장 큰 수 부터 반복문 시작
				if(arr[j] <= N) { // 배열안의 막대기가 입력받은 수보다 작으면
					cnt++; // 카운트 증가
					N -= arr[j]; // 해당 수만큼 감소시키기
				} // 조건문 닫기
			} // 반복문 닫기
			
			System.out.println(cnt); // 테스트케이스 번호와 정답 출력
	} // 메인함수 닫기
} // 클래스 닫기
