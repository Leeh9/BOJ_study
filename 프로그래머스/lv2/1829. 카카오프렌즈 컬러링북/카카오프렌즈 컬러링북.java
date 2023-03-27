import java.util.*;
class Solution {
    static boolean[][] visit;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public int[] solution(int m, int n, int[][] picture) {

        visit = new boolean[m][n];
//         for(int i=0; i<m; i++){
//             for(int j=0; j<n; j++){
                
//                 visit[i][j] = false;
//             }
//         }
        
        int[] answer = new int[2];
        
        int cnt =0;
        int max =0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(!visit[i][j] && picture[i][j] !=0){
                    int d = bfs(i,j,m,n,picture);
                    cnt++;
                    max = Math.max(max, d);
                }
            }
        }
        
        answer[0] = cnt;
        answer[1] = max;
        
        return answer;
    }
    
    static int bfs(int x, int y,int m, int n, int[][] p){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x,y));
        visit[x][y] = true;
        
        int c= 1;
        while(!q.isEmpty()){
            Point np = q.poll();
            
            for(int i=0; i<4; i++){
                int nx = np.x+dx[i];
                int ny = np.y+dy[i];
                
                if(nx>=0 && ny>=0 && nx<m && ny<n){
                    if(!visit[nx][ny] && p[nx][ny] == p[x][y]){
                        q.add(new Point(nx,ny));
                        visit[nx][ny] = true;
                        c++;
                    }
                }
            }
            
        }
        
        return c;
    }
    
    static class Point{
        int x;
        int y;
        
        public Point(int x, int y){
            this.x =  x;
            this.y =  y;
        }
    }
}