import java.util.*;

class Solution {

    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int a = board[0].length;
        int cnt = moves.length;
        Stack<Integer> s = new Stack<>();
        
        for(int i=0; i<moves.length; i++){
            int temp = moves[i]-1;
            for(int j=0; j<a; j++){
                if(board[j][temp] !=0){
                    if(s.size() !=0 && s.peek() == board[j][temp]){
                        answer +=2;
                        s.pop();
                    }else{                  
                        s.push(board[j][temp]);
                    }
                    board[j][temp] = 0;
                    break;  
                }
            }
            //System.out.println(s);
        }
       
        
        
        return answer;
    }
}