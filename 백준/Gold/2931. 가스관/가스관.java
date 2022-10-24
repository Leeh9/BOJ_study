import java.io.BufferedReader; //입력받는 라이브러리
import java.io.IOException; // 예외 처리 
import java.io.InputStreamReader; // 입력받는 라이브러리
import java.util.ArrayList; // 자료구조 arraylist 
import java.util.LinkedList; //자료구조 linkedlist
import java.util.Queue; // 자료구조 queue
import java.util.StringTokenizer; // 문자열 분리해주는 라이브러리

public class Main { // 클래스 시작
	static int T, R, C; // 테스트케이스, 행,열
	static int Mx, My, Zx, Zy, Rx, Ry; // 집 , 유치원 , 빈 칸 좌표 변수
	static int[] dx = { -1, 1, 0, 0 }; // 델타변수 순서대로 상,하,좌,우
	static int[] dy = { 0, 0, -1, 1 }; // 델타변수
	static char[][] map; // 지도 담을 char 배열
	static boolean[][] visit; // 장소 방문 체크에 쓰일 boolean 배열

	public static void main(String[] args) throws IOException { // 메인 함수 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력받을 버퍼드 리더 선언
		StringTokenizer st; // 문자열 잘라주는 라이브러리 선언
			st = new StringTokenizer(br.readLine()); // 문자열 한 줄 자르기
			R = Integer.parseInt(st.nextToken()); // 행 입력
			C = Integer.parseInt(st.nextToken()); // 열 입력
			map = new char[R][C]; // 맵 초기화
			visit = new boolean[R][C]; // 방문 초기화
			for (int i = 0; i < R; i++) { // 행 반복문 시작
				String line = br.readLine(); // 한줄 입력받기
				for (int j = 0; j < C; j++) { // 열 반복문 시작
					map[i][j] = line.charAt(j); // 한글자씩 넣기
					if (map[i][j] == 'M') { // 만약 M이라면
						Mx = i; // M의 행 입력받기
						My = j; // M의 열 입력받기
					}
					if (map[i][j] == 'Z') { // 만약 Z라면
						Zx = i; // Z의 행 입력받기
						Zy = j; // Z의 열 입력받기
					} // 조건문 닫기
				} // 반복문 닫기
			} // 반복문 닫기

			int Sx = 0, Sy = 0; // M과 가장 가까운 도로 좌표 초기화
			for (int i = 0; i < 4; i++) { // 상하좌우 탐색 시작
				int nx = Mx + dx[i]; // 새로운 x좌표
				int ny = My + dy[i]; // 새로운 y좌표
				if (nx >= 0 && ny >= 0 && nx < R && ny < C) { // 만약 맵 안에 있다면
					if (map[nx][ny] != '.') { // 빈칸이 아니라면
						Sx = nx; // 행 갱신
						Sy = ny; // 열 갱신
					} // 조건문 닫기
				} // 조건문 닫기
			} // 반복문 닫기
			visit[Mx][My] = true; // M방문 체크
			Rx = 0; // 빈 칸 행 초기화
			Ry = 0; // 빈 칸 열 초기화
			bfs(Sx, Sy); // bfs 시작

			ArrayList<Integer> list = new ArrayList<>(); // 빈칸의 모양을 찾을 list 선언 및 초기화
			for (int i = 0; i < 4; i++) { // 상하좌우 탐색
				int nx = Rx + dx[i]; // 빈칸 근처의 행
				int ny = Ry + dy[i]; // 빈칸 근처의 열
				if (nx >= 0 && ny >= 0 && nx < R && ny < C) { // 맵 안에 있다면
					if (i == 0 && (map[nx][ny] == '|' || map[nx][ny] == '+' || map[nx][ny] == '1' || map[nx][ny] == '4')) { // 위에 있고 갈 수 있다면
						list.add(0); // 위쪽 방향 추가
					} // 조건문 닫기
					if (i == 1 && (map[nx][ny] == '|' || map[nx][ny] == '+' || map[nx][ny] == '2' || map[nx][ny] == '3')) { // 아래에 있고 갈 수 있다면
						list.add(1); // 아래 방향 추가
					} // 조건문 닫기
					if (i == 2 && (map[nx][ny] == '-' || map[nx][ny] == '+' || map[nx][ny] == '1' || map[nx][ny] == '2')) { // 왼쪽에 있고 갈 수 있다면
						list.add(2); // 왼쪽 방향 추가
					} // 조건문 닫기
					if (i == 3 && (map[nx][ny] == '-' || map[nx][ny] == '+' || map[nx][ny] == '3' || map[nx][ny] == '4')) { // 오른쪽에 있고 갈 수 있다면
						list.add(3); // 오른쪽 방향 추가
					} // 조건문 닫기
				} // 조건문 닫기
			} // 반복문 닫기
			
			char ans = ' '; // 빈 칸의 모양인 ans 초기화
			if(list.size()==4) { // list 개수가 4라면 
				ans = '+'; // '+' 밖에 없으므로 답은 '+'
			}else { // 그게 아니라면
				
				//  순서대로 0 은 상, 1은 하, 2는 좌, 3은 우로 표현함
				int a = list.get(0); // 첫 번째 값
				int b = list.get(1); // 두 번째 값
				if(a==0) { // a가 0이고
					if(b==1) { // b가 1이면
						ans = '|'; // 상,하 이므로 답은 '|'
					}else if(b==2) { // b가 2면
						ans = '3'; // 상, 좌 이므로 답은 '3'
					}else if (b==3) { // b가 3이면
						ans = '2'; // 상, 우 이므로 답은 '2'
					} // 조건문 닫기
				} // 조건문 닫기
				if(a==1) { // a가 1이고
					if(b==2) { // b가 2라면
						ans = '4'; // 좌 ,하 이므로 답은 '4'
					}else if(b==3) { // b가 3이라면
						ans = '1'; // 하,우 이므로 답은 '1'
					} // 조건문 닫기
				} // 조건문 닫기
				if(a==2 && b==3) { // a가 2이고 b가 3이라면
					ans = '-'; // 좌,우 이므로 답은 '-'
				} // 조건문 닫기
			} // 조건문 닫기
			System.out.println((Rx+1)+" "+(Ry+1)+" "+ans); // 테스트케이스, 정답 행, 열 , 모양 출력
		
	} // 메인 함수 끝

	private static void bfs(int Sx, int Sy) { // bfs 시작
		Queue<Point> q = new LinkedList<>(); // q 선언
		q.add(new Point(Sx, Sy)); // 초기 좌표 Sx, Sy 넣기 
		visit[Sx][Sy] = true; // sx,sy 방문체크

		while (!q.isEmpty()) { // q가 빌때까지 반복
			Point n = q.poll(); // q 하나 꺼냄

			if (map[n.x][n.y] == '-') { // 해당 좌표가 -라면
				for (int i = 2; i < 4; i++) { // 좌, 우만 탐색
					int nx = n.x + dx[i]; // 새로운 x
					int ny = n.y + dy[i]; // 새로운 y
					if (nx >= 0 && ny >= 0 && nx < R && ny < C) { // 맵 안에 있다면
						if (!visit[nx][ny] && map[nx][ny] != '.') { // 방문 안했고 빈 칸 아니면
							visit[nx][ny] = true; // 방문체크
							q.add(new Point(nx, ny)); // 큐에 넣기
						} // 조건문 종료
						if (!visit[nx][ny] && map[nx][ny] == '.') { // 방문 안했고 빈칸이면
							Rx = nx; // 빈 칸 행 갱신
							Ry = ny; // 빈 칸 열 갱신
							return; // 찾았으므로 빠져나가기
						} // 조건문 종료
					} // 조건문 종료
				} // 반복문 종료
			} else if (map[n.x][n.y] == '|') {
				for (int i = 0; i < 2; i++) { // 상, 하만 탐색
					int nx = n.x + dx[i]; // 새로운 x
					int ny = n.y + dy[i]; // 새로운 y
					if (nx >= 0 && ny >= 0 && nx < R && ny < C) { // 맵 안에 있다면
						if (!visit[nx][ny] && map[nx][ny] != '.') { // 방문 안했고 빈 칸 아니면
							visit[nx][ny] = true; // 방문체크
							q.add(new Point(nx, ny)); // 큐에 넣기
						} // 조건문 종료
						if (!visit[nx][ny] && map[nx][ny] == '.') { // 방문 안했고 빈칸이면
							Rx = nx; // 빈 칸 행 갱신
							Ry = ny; // 빈 칸 열 갱신
							return; // 찾았으므로 빠져나가기
						} // 조건문 종료
					} // 조건문 종료
				} // 반복문 종료
			} else if (map[n.x][n.y] == '+') {
				for (int i = 0; i < 4; i++) { // 상하좌우 탐색
					int nx = n.x + dx[i]; // 새로운 x
					int ny = n.y + dy[i]; // 새로운 y
					if (nx >= 0 && ny >= 0 && nx < R && ny < C) { // 맵 안에 있다면
						if (!visit[nx][ny] && map[nx][ny] != '.') { // 방문 안했고 빈 칸 아니면
							visit[nx][ny] = true; // 방문체크
							q.add(new Point(nx, ny)); // 큐에 넣기
						} // 조건문 종료
						if (!visit[nx][ny] && map[nx][ny] == '.') { // 방문 안했고 빈칸이면
							Rx = nx; // 빈 칸 행 갱신
							Ry = ny; // 빈 칸 열 갱신
							return; // 찾았으므로 빠져나가기
						} // 조건문 종료
					} // 조건문 종료
				} // 반복문 종료
			} else if (map[n.x][n.y] == '1') {
				for (int i = 1; i < 4; i = i + 2) { // 하, 우 탐색
					int nx = n.x + dx[i]; // 새로운 x
					int ny = n.y + dy[i]; // 새로운 y
					if (nx >= 0 && ny >= 0 && nx < R && ny < C) { // 맵 안에 있다면
						if (!visit[nx][ny] && map[nx][ny] != '.') { // 방문 안했고 빈 칸 아니면
							visit[nx][ny] = true; // 방문체크
							q.add(new Point(nx, ny)); // 큐에 넣기
						} // 조건문 종료
						if (!visit[nx][ny] && map[nx][ny] == '.') { // 방문 안했고 빈칸이면
							Rx = nx; // 빈 칸 행 갱신
							Ry = ny; // 빈 칸 열 갱신
							return; // 찾았으므로 빠져나가기
						} // 조건문 종료
					} // 조건문 종료
				} // 반복문 종료

			} else if (map[n.x][n.y] == '2') {
				for (int i = 0; i < 4; i = i + 3) { // 상,우 탐색
					int nx = n.x + dx[i]; // 새로운 x
					int ny = n.y + dy[i]; // 새로운 y
					if (nx >= 0 && ny >= 0 && nx < R && ny < C) { // 맵 안에 있다면
						if (!visit[nx][ny] && map[nx][ny] != '.') { // 방문 안했고 빈 칸 아니면
							visit[nx][ny] = true; // 방문체크
							q.add(new Point(nx, ny)); // 큐에 넣기
						} // 조건문 종료
						if (!visit[nx][ny] && map[nx][ny] == '.') { // 방문 안했고 빈칸이면
							Rx = nx; // 빈 칸 행 갱신
							Ry = ny; // 빈 칸 열 갱신
							return; // 찾았으므로 빠져나가기
						} // 조건문 종료
					} // 조건문 종료
				} // 반복문 종료
			} else if (map[n.x][n.y] == '3') {
				for (int i = 0; i < 3; i = i + 2) { // 상,좌 탐색
					int nx = n.x + dx[i]; // 새로운 x
					int ny = n.y + dy[i]; // 새로운 y
					if (nx >= 0 && ny >= 0 && nx < R && ny < C) { // 맵 안에 있다면
						if (!visit[nx][ny] && map[nx][ny] != '.') { // 방문 안했고 빈 칸 아니면
							visit[nx][ny] = true; // 방문체크
							q.add(new Point(nx, ny)); // 큐에 넣기
						} // 조건문 종료
						if (!visit[nx][ny] && map[nx][ny] == '.') { // 방문 안했고 빈칸이면
							Rx = nx; // 빈 칸 행 갱신
							Ry = ny; // 빈 칸 열 갱신
							return; // 찾았으므로 빠져나가기
						} // 조건문 종료
					} // 조건문 종료
				} // 반복문 종료
			} else if (map[n.x][n.y] == '4') {
				for (int i = 1; i < 3; i++) { // 좌,하 탐색
					int nx = n.x + dx[i]; // 새로운 x
					int ny = n.y + dy[i]; // 새로운 y
					if (nx >= 0 && ny >= 0 && nx < R && ny < C) { // 맵 안에 있다면
						if (!visit[nx][ny] && map[nx][ny] != '.') { // 방문 안했고 빈 칸 아니면
							visit[nx][ny] = true; // 방문체크
							q.add(new Point(nx, ny)); // 큐에 넣기
						} // 조건문 종료
						if (!visit[nx][ny] && map[nx][ny] == '.') { // 방문 안했고 빈칸이면
							Rx = nx; // 빈 칸 행 갱신
							Ry = ny; // 빈 칸 열 갱신
							return; // 찾았으므로 빠져나가기
						} // 조건문 종료
					} // 조건문 종료
				} // 반복문 종료
			} // 조건문 종료

		} //반복문 종료

	} // bfs 함수 종료

	static class Point { // 행, 열을 담을 point 함수 선언
		int x; // 행 좌표
		int y; // 열 좌표

		public Point(int x, int y) { // Point 값 가져오기
			this.x = x; // 행 좌표
			this.y = y; // 열 좌표
		} // 종료

	} // 클래스 종료
} // 클래스 종료
