class Solution {
    static int[] num;
    static int n;
    static int max_cnt=0;
    static int max_value = 0;
    public int[] solution(int[][] users, int[] emoticons) {
        n = emoticons.length;
        num = new int[n];
        
        int[] answer = new int[2];
        
        perm(0,users,emoticons);
        answer[0] = max_cnt;
        answer[1] = max_value;
        return answer;
    }
    
    private static void perm(int cnt, int[][] users, int[] emoticons) {
		if (cnt == emoticons.length) {
            int cost = 0;
            int cost_sum =0;
            int eu = 0;
            for(int j=0; j<users.length; j++){
                cost = 0;
                for(int i=0; i<cnt; i++){
                    //System.out.print(num[i]);
                    // 만약 유저1번이 원하는게 이모티콘 할인율보다 낮으면
                    if(users[j][0]<=num[i]){
                        //4200원
                        cost += emoticons[i] * (100-num[i])/100;
                    }
                }
                
                if(cost >= users[j][1]){
                    eu++;
                }else{
                    cost_sum +=cost;
                }
            }
            
            
            //System.out.println();
            if(eu>max_cnt){
                max_cnt = eu;
                max_value = cost_sum;
            }else if(max_cnt ==eu){
                if(cost_sum>max_value){
                    max_value = cost_sum;
                }
            }
            //System.out.println(max_cnt+" ㅎㅎ " + max_value);
			return;
		}
		// 가능한 모든 수 시도 ( 주사위 1~6)
		for (int i = 1; i <= 4; i++) {
			// 수의 중복선택이 가능하므로 중복체크 필요없음
			// 해당 수 선택
			num[cnt] = i*10;
			perm(cnt + 1,users,emoticons);
		}
	}
}
