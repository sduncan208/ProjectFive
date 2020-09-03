/**
 * @author Sophia Duncan
 * UNL
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SudokuVerifier {	
	
	/* Checks if the 3x3 squares are valid in the input 2D array
	 * @param sudokuProblem 
	 * @return a boolean
	 */
	public static boolean checkSquares(ArrayList<ArrayList<Integer>> sudokuProblem)
	{
		int counter = 0;
		//Loops through the rows and columns
		for (int i = 0; i < 9; i+=3) {
			for (int j = 0; j < 9; j+=3) {
				//Creates an ArrayList that holds the square values
				ArrayList<Integer> squareContents = new ArrayList<>();
				
				squareContents.add(sudokuProblem.get(i).get(j));
				squareContents.add(sudokuProblem.get(i+1).get(j));
				squareContents.add(sudokuProblem.get(i+2).get(j));
				squareContents.add(sudokuProblem.get(i).get(j+1));
				squareContents.add(sudokuProblem.get(i+1).get(j+1));
				squareContents.add(sudokuProblem.get(i+2).get(j+1));
				squareContents.add(sudokuProblem.get(i).get(j+2));
				squareContents.add(sudokuProblem.get(i+1).get(j+2));
				squareContents.add(sudokuProblem.get(i+2).get(j+2));
				
				//Counts what square you are at
				counter ++;
				for (int k = 1; k < 10; k++) {
					//Will return false if the square does not contain a number
					if (!squareContents.contains(k))
					{
						System.out.println("Square " + counter + " is invalid");
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/* Checks if the columns are valid in the input 2D array
	 * @param sudokuProblem 
	 * @return a boolean
	 */
	public static boolean checkColumns(ArrayList<ArrayList<Integer>> sudokuProblem)
	{
		int counter = 0;
		// Loops through all the column
		for (int i = 0; i < sudokuProblem.size(); i++) {
			// Creates an ArrayList that holds the row values
			ArrayList<Integer> columnContents = new ArrayList<>();
			for (int j = 0; j < sudokuProblem.get(i).size(); j++) {
				columnContents.add(sudokuProblem.get(j).get(i));
			}

			//Checks what column you are at
			counter ++;
			// Checks if the numbers 1-9 are in the row
			for (int j = 1; j < 10; j++) {
				// Will return false if the column does not contain a number
				if (!columnContents.contains(j)) {
					System.out.println("Column " + counter + " is invalid");
					return false;
				}
			}
		}
		return true;
	}
	
	/* Checks if the rows are valid in the input 2D array
	 * @param sudokuProblem 
	 * @return a boolean
	 */
	public static boolean checkRows(ArrayList<ArrayList<Integer>> sudokuProblem)
	{
		int counter = 0;
		// Loops through all the rows
		for (int i = 0; i < sudokuProblem.size(); i++) {
			//Counts what row you are at
			counter ++;
			// Checks if the numbers 1-9 are in the column
			for (int j = 1; j < 10; j++) {
				// Will return false if the row does not contain a number
				if (!sudokuProblem.get(i).contains(j)) { 
					System.out.println("Row " + counter + " is invalid");
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args)
	{
		//2D list to hold all the numbers in the input file
		ArrayList<ArrayList<Integer>> inputNums = new ArrayList<>();
		
		Scanner s;
		try {
			s = new Scanner(new File("src/" + args[args.length - 1]));
			//counter counts which index in inputNums the program is at
			int counter = 0;
			while(s.hasNext())
			{
				 String[] tempArray = s.nextLine().split(" ");
				 int[] numList = new int[9];
				 inputNums.add(new ArrayList());
				 for(int i=0; i<9; i++)
				 {
					 numList[i] = Integer.parseInt(tempArray[i]);
					 inputNums.get(counter).add(numList[i]);
				 }
				 counter ++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		
		
		//Checks the rows
		boolean rowsCorrect = checkRows(inputNums);
		//Checks columns
		boolean columnsCorrect = checkColumns(inputNums);
		//checks 3x3 squares
		boolean squaresCorrect = checkSquares(inputNums);
		
		if(rowsCorrect && columnsCorrect && squaresCorrect)
			System.out.println("Valid solution");
		else
			System.out.println("Invalid solution");
	}
}
