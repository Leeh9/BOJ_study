class Solution {
    static int[] num;
    static int n;
    static boolean[] visit;
    static int[][] dun;
    static int max=0;
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        n = dungeons.length;
        visit = new boolean[n];
        num = new int[n];
        dun = new int[n][2];
        dun = dungeons;
        perm(0,k);
        return max;
    }
    
    static void perm(int cnt,int k){
        if(cnt == n){
            int res = 0;
            int kk = k;
            for(int i=0; i<cnt; i++){
                //System.out.print(num[i]+" ");
                if(dun[num[i]][0]<=kk){
                    if(dun[num[i]][1]<=kk){
                        kk -= dun[num[i]][1];
                        
                        res++;
                    }
                }
            }
            System.out.println();
            max = Math.max(max,res);
            System.out.println();
            return;
        }
        
        for(int i=0; i<n; i++){
            
            if(visit[i]){
                continue;
            }
            num[cnt] =i; 
            visit[i] = true;
            perm(cnt+1,k);
            visit[i] = false;
        }
    }
}