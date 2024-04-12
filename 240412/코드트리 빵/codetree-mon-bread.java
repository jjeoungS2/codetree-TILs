import java.io.*;
import java.util.*;
public class Main {
    static int n,m;
	static int[][] map;
	static int[][] people_bread;
	static int[][] people_location;
	static int t = 1;
	static int goal = 0;
    static ArrayList<int[]> baseCamp = new ArrayList<>();

    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n+1][n+1];
		people_bread = new int[m][2];
		people_location = new int[m][2];
		
		for(int i = 0; i < m; i++) {
			people_location[i][0] = -1;
			people_location[i][1] = -1;			
		}
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n ;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) baseCamp.add(new int[] {i,j});
			}
		}
		
//		for(int[] i : map) {
//			for(int j : i) {
//				System.out.print(j+" ");
//			}
//			System.out.println();
//		}
		
		for(int i = 0; i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			people_bread[i][0] = Integer.parseInt(st.nextToken());
			people_bread[i][1] = Integer.parseInt(st.nextToken());
		}
		
		while(goal!=m){
			if(t <= m) {
				find_close_basecamp(t-1);
				// System.out.println("베이스캠프찾는중----------");
				// for(int i = 0; i < 3; i++) {
				// 	System.out.println(people_location[i][0] + " " +people_location[i][1]);		
				// }
			}
			if(t == 1) {
				t++;
				continue;
			}
			if( t > 1 && t-1 < m) {
				for(int i =0; i < t-1; i++) {
					if(people_location[i][0] == -1 && people_location[i][1] == -1) continue;
					move_people(i);
				}
				// System.out.println("움직이는중----------");
				// for(int i = 0; i < 3; i++) {
				// 	System.out.println(people_location[i][0] + " " +people_location[i][1]);		
				// }
			}
			else {
				for(int i = 0; i < 3; i++) {
					if(people_location[i][0] == -1 && people_location[i][1] == -1) continue;
					else {
						move_people(i);
					}
				}
				// System.out.println("움직이는중----------");
				// for(int i = 0; i < 3; i++) {
				// 	System.out.println(people_location[i][0] + " " +people_location[i][1]);		
				// }
			}
			// for(int i = 1; i <= n; i++) {
			// 	for(int j = 1; j <=n; j++) {
			// 		System.out.print(map[i][j]+" ");
			// 	}
			// 	System.out.println();
			// }
			
			t++;
		}
		System.out.println(t-1);
    }
    static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};
	public static void move_people(int idx) {
		int x = people_location[idx][0];
		int y = people_location[idx][1];
		int near = Integer.MAX_VALUE;
		int res_x = 0;
		int res_y = 0;
		for(int i = 0; i < 4; i++) {
			int a = x + dx[i];
			int b = y + dy[i];
			if(a <= 0 || a > n || b <= 0 || b >n || map[a][b]==-1)continue;
			visit = new boolean[n+1][n+1];
			visit[x][y] = true;
			int num = bfs(a,b,idx);
			if(a == people_bread[idx][0] && b == people_bread[idx][1]) {
				goal++;
				people_location[idx][0] = -1;
				people_location[idx][1] = -1;
				map[a][b] = -1;
				return;
			}
			if(num!=-1 && near > num) {
				near = num;
				res_x = a;
				res_y = b;
			}
		}
		people_location[idx][0] = res_x;
		people_location[idx][1] = res_y;
	}
	
	static boolean[][] visit;
	public static int bfs(int a,int b, int idx) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {a,b,0});
		if(people_bread[idx][0] == a && people_bread[idx][1] == b) {
			return 0;
		}
		
		while(!q.isEmpty()) {
			int[] index = q.poll();
			visit[index[0]][index[1]] = true;
			for(int i = 0; i < 4; i++) {
				int x = index[0] + dx[i];
				int y = index[1] + dy[i];
				int cnt = index[2];
				if(x <= 0 || x > n || y <= 0 || y > n || map[x][y] == -1 || visit[x][y]) continue;
				if(people_bread[idx][0] == x && people_bread[idx][1] == y) {
					return cnt+1;
				} else {
					q.add(new int[] {x,y,cnt+1});
				}
			}
		}
		return -1;
	}
	
	public static void find_close_basecamp(int idx) {
		int len = baseCamp.size();
		int x = -1;
		int y = -1;
		int min = Integer.MAX_VALUE;
		// 2,3
		for(int i = 0; i < len; i++) {
			int[] a = baseCamp.get(i);
			int num = Math.abs(people_bread[idx][0] - a[0]) + Math.abs(people_bread[idx][1] - a[1]);
			if(min > num && map[a[0]][a[1]] != -1) {
				min = num;
				x = a[0];
				y = a[1];
			}
		}
		people_location[idx][0] = x;
		people_location[idx][1] = y;
		map[x][y] = -1;
//		System.out.println(x+" "+y);
	}
}