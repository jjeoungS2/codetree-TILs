import java.io.*;
import java.util.*;
public class Main {
    static int[] list = new int[3];
    static int n;
    static Set<String> before = new HashSet<>();
    static Set<String> after = new HashSet<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 3; i++) {
			String s = st.nextToken();
			String[] a = s.split(":");
			int x = Integer.parseInt(a[0]);
			int y = Integer.parseInt(a[1]);
			list[i] += (x*60) + y;
//			System.out.println(list[i]);
		}
		n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			String[] a = s.split(":");
			int x = Integer.parseInt(a[0]);
			int y = Integer.parseInt(a[1]);
			int time = (x*60) + y;
			String name = st.nextToken();
			
			if(time <= list[0]) {
				before.add(name);
			}
			else if(time >= list[1] && time <= list[2]) {
				after.add(name);
			}
		}
		
		before.retainAll(after);
		System.out.println(before.size());
		
    }
}