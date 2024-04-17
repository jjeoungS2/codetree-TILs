import java.io.*;
import java.util.*;
public class Main {
    static int[] list;
    static int[] dp;
    static int n;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new int[n];
        dp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }
        
        int max = 0;
        for(int i = 0; i < n ; i++) {
        	dp[i] = list[i];
        	for(int j = 0; j < n; j++) {
        		if(list[j] > list[i] && dp[j] + list[i] > dp[i]) {
        			dp[i] = dp[j] + list[i];
        		}
        	}
        	max = Math.max(max, dp[i]);
        }
        System.out.println(max);
	}
}