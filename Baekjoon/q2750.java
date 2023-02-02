import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class q2750 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N  = Integer.parseInt(br.readLine());
        LinkedList<Integer> nums = new LinkedList<>();
        
        boolean isAdd;

        for(int i = 0; i < N; i++) {
        	isAdd = false;
            int num = Integer.parseInt(br.readLine());
            if(i == 0) {
                nums.add(num);
                continue;
            }
            for(int j = 0; j < nums.size(); j++) {
                if(nums.get(j) > num) {
                    nums.add(j, num);
                    isAdd = true;
                    break;
                }
            }
            if(!isAdd) {
            	nums.add(num);
            }
        }

        if (!nums.isEmpty()) {
            for(int n : nums) {
                System.out.println(n);
            }
        }
    }
}

/**
 * 수 정렬하기 / 브론즈 2 / 30분
 * */
