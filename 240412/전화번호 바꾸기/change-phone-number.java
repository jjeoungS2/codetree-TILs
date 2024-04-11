import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] parts = s.split("-");

        System.out.print(parts[0]+"-"+parts[2]+"-"+parts[1]);

    }
}