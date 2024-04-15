import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        if(n == 1) {
        	System.out.println("-1");
        	return;
        }

        int[][] list = new int[n][2];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            list[i][0] = Integer.parseInt(st.nextToken());
            list[i][1] = i+1;
        }

        Arrays.sort(list,(o1,o2) -> {
            return o1[0] - o2[0];
        });
        
        int num = list[0][0];
        int cnt = 0;
        int i = 1;
        for(; i < n-1; i++) {
        	if(num == list[i][0]) continue;
        	else {
                cnt = list[i][0];
                break;
        	}
        }
        if(list[i][0] == list[i-1][0])System.out.println("-1");
        else if(i+1 >= n)System.out.println(list[i][1]);
        else if(list[i+1][0] == cnt) System.out.println("-1");
        else System.out.println(list[i][1]);
    }
}