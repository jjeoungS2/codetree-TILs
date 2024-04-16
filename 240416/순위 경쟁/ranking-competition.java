import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		Map<Character, Integer> scores = new HashMap<>();
		scores.put('A', 0);
		scores.put('B', 0);
		scores.put('C', 0);
		
		String win = "";
		int res = 0;
		
		String[] input = br.readLine().split(" ");
		char who = input[0].charAt(0);
		int num = Integer.parseInt(input[1]);
		
		scores.put(who, scores.get(who) + num);
		
		String leader = findLeaders(scores);
		win = leader;
		int a = scores.get('A');
		boolean flag = true;
		for(int s : scores.values()) {
			if(a!=s) {
				flag = false;
				break;
			}
		}
		if(!flag)res++;
		
		
		for(int i = 1; i < n; i++) {
			input = br.readLine().split(" ");
			who = input[0].charAt(0);
			num = Integer.parseInt(input[1]);
			
			scores.put(who, scores.get(who) + num);
			
			leader = findLeaders(scores);
			
			if(!leader.equals(win)) {
				win = leader;
				res++;
			}
		}
		
		System.out.println(res);
    }

    private static String findLeaders(Map<Character, Integer> scores) {
        int maxScore = Integer.MIN_VALUE;
        StringBuilder leaders = new StringBuilder();
        
        // 최대 점수 찾기
        for (Integer score : scores.values()) {
            if (score > maxScore) {
                maxScore = score;
            }
        }
        
        // 최대 점수를 가진 학생 찾기
        for (Map.Entry<Character, Integer> entry : scores.entrySet()) {
            if (entry.getValue() == maxScore) {
                leaders.append(entry.getKey());
            }
        }
        
        return leaders.toString();
    }
}