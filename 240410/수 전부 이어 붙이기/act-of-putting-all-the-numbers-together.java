import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        Queue<Character> q = new ArrayDeque<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in()));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < n; i++){
            String s = st.nextToken();
            int len = s.length();
            for(int j = 0; j < len; j++){
                if(q.size() == 5){
                    while(!q.isEmpty()){
                        System.out.print(q.pollFirst());
                    }
                }
                char c = s.charAt(j);
                q.add(s.charAt(j));
            }
        }
                
        while(!q.isEmpty()) {
            System.out.print(q.pollFirst());
        }
    }
}