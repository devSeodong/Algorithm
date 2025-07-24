import java.io.*;

public class Main {

	public static void main(String[] args) {
		int[][] arr = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
		int N = arr.length;

		int[][] mArr = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				mArr[i][j] = arr[j][N-i-1]; 
			}
		}
		
		System.out.println(Arrays.toString(mArr[0]));
		System.out.println(Arrays.toString(mArr[1]));
		System.out.println(Arrays.toString(mArr[2]));
	}
}
