import java.io.*;
import java.util.*;
public class Main {
    static int A = 0;
	static int B = 0;
	static String win = "";
	static int res = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String s = "";
		int num = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		s = st.nextToken();
		num = Integer.parseInt(st.nextToken());
		if(s.equals("A")) {
			A += num;
		} else {
			B += num;
		}
		if(A < B) win = "B";
		else win = "A";
		res++;
		
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			s = st.nextToken();
			num = Integer.parseInt(st.nextToken());
			if(s.equals("A")) A+=num;
			else B+=num;
			
			if(A == B) {
				if(!win.equals("AB")) {
					res++;
					win = "AB";
				}
			} else if(A > B) {
				if(!win.equals("A")) {
					res++;
					win = "A";
				}
			} else {
				if(!win.equals("B")) {
					res++;
					win = "B";
				}
			}
		}
		System.out.println(res);
    }
}