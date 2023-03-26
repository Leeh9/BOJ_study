import java.util.*;
class Solution {
    static HashSet<String> hs = new HashSet<>();
    static ArrayList<String>[] arr;
    public int[] solution(int[] fees, String[] records) {
        //차량 번호 개수 파악
        for(int i=0; i<records.length; i++){
            String t= records[i].substring(6,10);
            hs.add(t);
        }      
        
        //2차원 리스트 선언
        arr = new ArrayList[hs.size()];
        for(int i=0; i<hs.size(); i++){
            arr[i] = new ArrayList<>();
        }
        
        for(int i=0; i<records.length; i++){
            String t= records[i].substring(6,10);
            for(int j=0; j<hs.size(); j++){
                if(arr[j].size()==0){
                    arr[j].add(t);
                    arr[j].add(records[i].substring(0,2)+records[i].substring(3,5));
                    break;
                }else{
                    if(arr[j].get(0).equals(t)){
                        arr[j].add(records[i].substring(0,2)+records[i].substring(3,5));
                        break;
                    }
                }
                
            }
        }
        
        // out 시간이 없으면 2359 마지막에 넣기
        for(int i=0; i<hs.size(); i++){
            if(arr[i].size() %2 ==0){
                arr[i].add("2359");
            }   
        }
        
        // 첫번째 요소를 기준으로 정렬
        Collections.sort(Arrays.asList(arr), (o1,o2) ->{
            return o1.get(0).compareTo(o2.get(0));
        });
        
        // 답 배열 선언
        int[] res = new int[hs.size()];
        
        for(int i=0; i<hs.size(); i++){
            int time =0;
            for(int j=1; j<arr[i].size(); j=j+2){
                String t1 = arr[i].get(j);
                String t2 = arr[i].get(j+1);
                
                time += (Integer.parseInt(t2.substring(0,2)) * 60 + Integer.parseInt(t2.substring(2))) -  
                    (Integer.parseInt(t1.substring(0,2)) * 60 + Integer.parseInt(t1.substring(2)));
            }
            //System.out.println(time);
            int r =0;
            if(time<=fees[0]){
                r = fees[1];
            }else{
                if((time-fees[0]) % fees[2] ==0){
                     r = fees[1] + ((time-fees[0])/fees[2]) * fees[3];
                }else{
                     r = fees[1] + ((time-fees[0])/fees[2]) * fees[3] + fees[3];
                }
            }
                     
            res[i] = r;
        }
        
        
        // for(int i=0; i<hs.size(); i++){
        //     System.out.println(arr[i]);
        // }
        return res;
    }
}