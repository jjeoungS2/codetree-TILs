import java.io.*;
import java.util.*;
public class Main {
    static int n;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int[] list = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n ;i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(list);
		
		int max1 = list[n-1]*list[n-2]*list[n-3];
		int max2 = list[0]*list[1]*list[n-1];
		
		int max = Math.max(max1, max2);
		System.out.println(max);
    }
}