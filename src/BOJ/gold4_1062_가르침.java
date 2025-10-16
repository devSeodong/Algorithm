package BOJ;

import java.io.*;
import java.util.*;

public class gold4_1062_가르침 {
	public static void swap (int a, int b) {
		int temp = a;
		a = b;
		b = temp;
	}
	
	public static void main(String[] args) throws IOException {
		int a = 11;
		int b = 19;
		
		swap(a, b);
		System.out.println(a);
		System.out.println(b);
		
		switch(a) {
		case 1 : 
			b += 1;
		case 11 : 
			b+= 2;
		default : 
			b+= 3;
			break;
		}
		
		System.out.println(a-b);
	}
}
