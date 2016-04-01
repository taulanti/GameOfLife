import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameOfLife {
	
	public static int sum(int [][] a){
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				sum += a[i][j];
			}
		}
		return sum;
	}
	
	/*rules 
	 * Each cell with one or no neighbors dies, as if by solitude.
	 * Each cell with four or more neighbors dies, as if by overpopulation.
	 * Each cell with two or three neighbors survives.
	 * Each cell with three neighbors becomes populated.
	 */
	 
	// this function will walk through board and process every cell
	
	private static void liveORdieORbloom(int [][] a , int [][] temp){
		int neighborValues = 0;
		for(int row = 0; row < temp.length; row++){
			for(int col = 0; col < temp[row].length; col++){
				neighborValues = getNeighborValues(row,col,temp);
				if     (neighborValues < 2 || neighborValues > 3)			{a[row][col] = 0;}
				else if(neighborValues == 3 && a[row][col] == 0)			{a[row][col] = 1;}
			}
		}	
	}
	
	private static int getNeighborValues(int row, int col, int [][] temp){
		int sum = 0;
		sum += getCellValue(row,col+1,temp);		//right cell
		sum += getCellValue(row,col-1,temp);		//left cell
		sum += getCellValue(row-1,col,temp);		//top cell
		sum += getCellValue(row+1,col,temp);		//bottom cell
		sum += getCellValue(row+1,col+1,temp);		//right bottom cell
		sum += getCellValue(row+1,col-1,temp); 		//left bottom cell
		sum += getCellValue(row-1,col+1,temp); 		//top right cell
		sum += getCellValue(row-1,col-1,temp); 		//top left cell
		
		
		return sum;
	}
	
	private static int getCellValue(int row, int col,int [][] temp){
		if((row < 0 || col < 0) || (row >= temp.length || col >= temp[0].length)){
			return 0;
		}
		return temp[row][col];
	}
	
	//start game and update the board
	public static void  startGame(int [][] a){
		int [][] temp = new int[a.length][a[0].length];
		copyMatrix(a, temp);
		liveORdieORbloom(a, temp);
		copyMatrix(a, temp);
	}
	
	public static void printBoard(int [][] a){
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(" "+"["+a[i][j]+"]");
			}
			System.out.println();
		}
	}
	
	private static void copyMatrix(int [][] a, int [][] temp){
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				temp[i][j] = a[i][j];
			}
		}
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
