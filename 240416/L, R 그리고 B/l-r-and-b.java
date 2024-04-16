import java.io.*;
import java.util.*;
public class Main {
    static char[][] map = new char[10][10];
	static int start_x, start_y, end_x, end_y;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		for(int i = 0; i < 10; i++) {
			s = br.readLine();
			for(int j = 0; j < 10; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'L') {
					start_x = i;
					start_y = j;
				}
				if(map[i][j] == 'B') {
					end_x = i;
					end_y = j;
				}
			}
		}
        bfs(start_x, start_y);

    }

    static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visit;
	public static void bfs(int x, int y) {
		visit = new boolean[10][10];
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {x,y,0});
		
		while(!q.isEmpty()) {
			int[] index = q.poll();
			visit[index[0]][index[1]] = true;
			
			for(int i = 0; i < 4; i++) {
				int a = index[0] + dx[i];
				int b = index[1] + dy[i];
				
				if( a < 0 || a >= 10 || b < 0 || b >= 10 || visit[a][b] || map[a][b] == 'R')continue;
				if( a == end_x && b == end_y) {
					System.out.println(index[2]);
					return;
				}
				q.add(new int[] {a,b,index[2]+1});
			}
		}
	}

}