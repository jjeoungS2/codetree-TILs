import java.io.*;
public class Main {
    public static void main(String[] args) {
        int a = 5;
        int b= 6; int c = 7;
        int temp = a;
        a = c;
        c = b;
        b = temp;
        System.out.print(a+"\n"+b+"\n"+c);
    }
}