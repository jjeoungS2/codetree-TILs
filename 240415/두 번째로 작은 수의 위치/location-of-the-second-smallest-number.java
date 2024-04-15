import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

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
        for(int i = 1; i < n; i++) {
        	if(num == list[i][0]) continue;
        	else {
                System.out.print(list[i][1]);
                break;
        	}
        }
    }
}