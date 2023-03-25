import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long s1 = 0;
        long s2 = 0;
        for(int i=0; i<queue1.length; i++){
            q1.add(queue1[i]);
            s1 += queue1[i];
        }
        for(int i=0; i<queue2.length; i++){
            q2.add(queue2[i]);
            s2 += queue2[i];
        }
        //System.out.println(s1);
         //System.out.println(s2);
        int t = 0;
        int cnt =0;
        int l = 0;
        while(true){
            if(s1==s2){
                break;
            }
            else if(s1<s2){
                t = q2.poll();
                q1.add(t);
                s1 +=t;
                s2 -=t;
                cnt++;
            }else if(s1>s2){
                t = q1.poll();
                q2.add(t);
                s1 -=t;
                s2 +=t;
                cnt++;
            }
            l++;
             //System.out.println("s1:"+s1+", s2:"+s2);
            
            if(l >1000000){
                return -1;
            }
        }
        
        
        return cnt;
    }
}