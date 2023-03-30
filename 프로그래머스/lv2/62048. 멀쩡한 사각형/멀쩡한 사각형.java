class Solution {
      public long solution(long w, long h) {
        long answer = 1;
        
        long tmp = Math.max(w,h);
        long tmp2 = Math.min(w,h);
        
        w = tmp2;
        h = tmp;
        answer = w * h - (w+h-gcd(h,w));
        return answer;
    }

    public long gcd(long x, long y){
        long max = Math.max(x, y);
        long min = Math.min(x, y);

        while(min != 0){
            long temp = max % min;
            max = min;
            min = temp;
        }
        return max;
    }
}