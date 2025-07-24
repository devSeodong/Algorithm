import java.io.*;

public class Main {

	public static void main(String[] args) {
		int[][] arr = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
		int[][] mArr = new int[3][3];
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				mArr[j][i] = arr[i][j]; 
			}
		}
		
		System.out.println(Arrays.toString(mArr[0]));
		System.out.println(Arrays.toString(mArr[1]));
		System.out.println(Arrays.toString(mArr[2]));
	}
}
