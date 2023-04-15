class Solution {
    static int[][] map;
    static int min;
    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows][columns];
        
        int idx =1;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                map[i][j] = idx;
                idx++;
                //System.out.print(map[i][j]+" ");
            }
            //System.out.println();
        }
        int[] ans = new int[queries.length];
        int t = 0;
        for(int[] q : queries){
            play(rows,columns,q);
            
            // for(int i=0; i<rows; i++){
            //     for(int j=0; j<columns; j++){
            //         //System.out.print(map[i][j]+" ");
            //     }
            //     //System.out.println();
            // }
            
            ans[t] = min;
            t++;
        }
        
        return ans;
    }
    
    static void play(int rows, int columns, int[] q){
        min = Integer.MAX_VALUE;
        int x1 = q[0]-1;
        int y1 = q[1]-1;
        int x2 = q[2]-1;
        int y2 = q[3]-1;
        int temp = map[x1][y1];
        min = Math.min(min,temp);
        
        for(int i=x1; i<x2; i++){
            map[i][y1] = map[i+1][y1];
            min = Math.min(min,map[i][y1]);
        }
        for(int i=y1; i<y2; i++){
            map[x2][i] = map[x2][i+1];
            min = Math.min(min,map[x2][i]);
        }
        for(int i=x2; i>x1; i--){
            map[i][y2] = map[i-1][y2];
            min = Math.min(min,map[i][y2]);
        }
        for(int i=y2; i>y1; i--){
            map[x1][i] = map[x1][i-1];
            min = Math.min(min,map[x1][i]);
        }
        
        map[x1][y1+1] = temp;
    
    }
}