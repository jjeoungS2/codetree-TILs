import java.util.*;
import java.io.*;
public class Main {

	static int N,M,K;
	static int[][] map;
	static int[][] turn_map;
	static int[][] player;
	static int[] exit = new int[2];
	static int res = 0;
	static int bx = 0, by = 0, bsize = 0;
	
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		turn_map = new int[N+1][N+1];
		player = new int[M][2];

        for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			player[i][0] = x;
			player[i][1] = y;
		}
		
	
		
		st = new StringTokenizer(br.readLine());
		exit[0] = Integer.parseInt(st.nextToken());
		exit[1] = Integer.parseInt(st.nextToken());


        while(K-- > 0) {
			move_player();
			
			boolean escaped = true;
			for(int i = 0; i < M; i++) {
				if(!(player[i][0] == exit[0] && player[i][1] == exit[1])) escaped = false;
			}
			if(escaped) break;
			
			findSquare();
			rotateBox();
			rotatePlayer();
		}
		System.out.println(res);
		System.out.println(bx+" "+by);


    }

    static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void move_player() {
		
		for(int t = 0; t < M; t++) {
			
			int a = player[t][0];
			int b = player[t][1];
			
			
			int before = Math.abs(a-exit[0]) + Math.abs(b - exit[1]);
			for(int i = 0; i < 4; i++) {
				if(a + dx[i] <= 0 || a+dx[i] > N || b + dy[i] <= 0 || b+dy[i] > N || map[a+dx[i]][b+dy[i]]!=0)continue;
				int num = Math.abs((a+dx[i]) - exit[0]) + Math.abs((b+dy[i]) - exit[1]);
				if(before <= num) continue;
				else {
					res++;
					player[t][0] = a + dx[i];
					player[t][1] = b + dy[i];
					break;
				}
			}
		}	
	}

    public static void findSquare() {
		Queue<int[]> q = new ArrayDeque<>();
		int max = Integer.MAX_VALUE;
		for(int i = 0; i < M; i++) {
			if(player[i][0] == exit[0] && player[i][1] == exit[1]) {
                continue;
            }
			int num = Math.abs(player[i][0] - exit[0]) + Math.abs(player[i][1]-exit[1]);
			if(max == num) {
				q.add(new int[] {player[i][0], player[i][1]});
			}
			else if(max > num) {
				max = num;
				q.clear();
				q.add(new int[] {player[i][0], player[i][1]});
			}
		}
		
		int max_len = Integer.MAX_VALUE;
		if(q.size() >= 2) {
			int[] num = q.poll();
			int a = Math.abs(num[0] - exit[0]) + 1;
			int b = Math.abs(num[1] - exit[1]) + 1;
			if(a >= b) {
				if(max_len > a) max_len = a;
			} else {
				if(max_len > b) max_len = b;
			}
		} else {
			int[] num = q.poll();
			int a = Math.abs(num[0] - exit[0]) + 1;
			int b = Math.abs(num[1] - exit[1]) + 1;
			if(a >= b) {
				if(max_len > a) max_len = a;
			} else {
				if(max_len > b) max_len = b;
			}
		}
		
		for(int i = 1; i <= max_len; i++) {
			for(int j = 1; j <= max_len; j++) {
				int x = i + max_len - 1;
				int y = j + max_len - 1;
				// 출구 있는지 없는지 확인
				if(!(i <= exit[0] && exit[0] <= x && j <= exit[1] && exit[1] <= y)) {
                    continue;
                }
				
				boolean person = false;
				for(int k = 0; k < M; k++) {
					if( i <= player[k][0] && player[k][0] <= x && j <= player[k][1] && player[k][1] <= y) {
						if(!(player[k][0] == exit[0] && player[k][1] == exit[1])) {
							person = true;
						}
					}
				}
				
				if(person) {
					bx = i;
					by = j;
					bsize = max_len;
					return;
				}
			}
		}
		
	}

	public static void rotateBox() {
		for(int i = bx; i < bx + bsize; i++) {
			for(int j = by; j < by+bsize; j++) {
				if(map[i][j] > 0) map[i][j]--;
			}
		}
		
		for(int i = bx; i < bx + bsize; i++) {
			for(int j = by; j < by+bsize; j++) {
				int ox = i - bx;
				int oy = j - by;
				int rx = oy;
				int ry = bsize - ox - 1;
				turn_map[rx+bx][ry+by] = map[i][j];
			}	
		}
		
		for(int i = bx; i < bx + bsize; i++) {
			for(int j = by; j < by + bsize; j++) {
				map[i][j] = turn_map[i][j];
			}
		}
	}
	
	public static void rotatePlayer() {
		for(int i  = 0; i < M; i++) {
			int x = player[i][0];
			int y = player[i][1];
			
			if(bx <= x && x < bx+bsize && by <= y && y < by + bsize) {
				int ox = x - bx;
				int oy = y - by;
				int rx = oy;
				int ry = bsize - ox - 1;
				player[i][0] = rx + bx;
				player[i][1] = ry + by;
			}
		}
		
		int x = exit[0];
		int y = exit[1];
		if(bx <= x && x < bx+bsize && by <= y && y < bx + bsize) {
			int ox = x - bx;
			int oy = y - by;
			int rx = oy;
			int ry = bsize - ox - 1;
			exit[0] = rx + bx;
			exit[1] = ry + by;
		}
	}

}