import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToe {
	
	private static Scanner scanner = new Scanner(System.in);
	private static String[][] board = {
							{" ", " "," "},
							{" ", " ", " "},
							{" ", " ", " "} 
						};
	
	private static ArrayList<Integer> playerPosition = new ArrayList<>();
	private static ArrayList<Integer> cpuPosition = new ArrayList<>();
	
	public static void main(String[] args) {
		
		int dice = -1;
		while(dice < 0) {
			dice = throwDice();
		}
		
		System.out.println(dice);
		
		printBoard(); //prints empty tictactoe board
		
		while(true) {
			
			if(dice == 1) {
				computersTurn();
				if(ifWon()) {
					System.out.println("Sorry, you lost!");
					break;
				} else if(isTie()) {
					System.out.println("It's a tie");
					break;
				}

				printBoard(); //prints the current state of the tictactoe board
				
				playersTurn();
				if(ifWon()) {
					System.out.println("Congrats, you won!");
					break;
				} else if(isTie()) {
					System.out.println("It's a tie");
					break;
				}
				
			} else if(dice == 2) {
				playersTurn();
				if(ifWon()) {
					System.out.println("Congrats, you won!");
					break;
				} else if(isTie()) {
					System.out.println("It's a tie");
					break;
				}
				
				
				computersTurn();
				if(ifWon()) {
					System.out.println("Sorry, you lost!");
					break;
				} else if(isTie()) {
					System.out.println("It's a tie");
					break;
				}

				printBoard(); //prints the current state of the tictactoe board
			}
			
			
		}
		
		printBoard(); //prints last state of the board

	}
	
	//prints tictactoe board
	public static void printBoard() {
		System.out.println();
		System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]); 
		System.out.println("-----");
		System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
		System.out.println("-----");
		System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
	}
	
	//plays the computers part
	public static void computersTurn() {
		Random random = new Random();
		int placement = random.nextInt(9) + 1;
		
		while(isFull(placement) == true) {
			placement = random.nextInt(9) + 1;
		}

		placeToken(placement, "cpu");
		cpuPosition.add(placement);
	}
	
	public static void playersTurn() {
		System.out.println("Where do you want to place your token? (1-9)");
		int placement = Integer.valueOf(scanner.nextLine());
		
		while(!String.valueOf(placement).matches("[1-9]")) {
			System.out.println("Please type a number from 1-9");
			placement = Integer.valueOf(scanner.nextLine());
		}
		
		//checks if that block is already filled. 
		while(isFull(placement) == true) {
			System.out.println("Space taken. Try again.");
			placement = Integer.valueOf(scanner.nextLine());
		}
		
		placeToken(placement, "player");
		playerPosition.add(placement);
	}
	
	//checks if anyone won
	public static boolean ifWon() {
		
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);
		List leftCol = Arrays.asList(1,4,7);
		List midCol = Arrays.asList(2,5,8); 
		List rightCol = Arrays.asList(3,6,9);
		List cross1 = Arrays.asList(1,5,9);
		List cross2 = Arrays.asList(3,5,7);
		
		
		List<List> winningPos = new ArrayList<>();
		winningPos.add(topRow);
		winningPos.add(midRow);
		winningPos.add(botRow);
		winningPos.add(leftCol);
		winningPos.add(midCol);
		winningPos.add(rightCol);
		winningPos.add(cross1);
		winningPos.add(cross2);
		
		for(List l: winningPos) {
			if(playerPosition.containsAll(l )) {
					return true;	
			} else if(cpuPosition.containsAll(l)) {
				 return true;
			}
		} 
		
		return false;
	}
	
	//places token on the board
	public static void placeToken(int placement, String user) {
		String token = " ";
		
		if(user == "player") {
			token = "X";
		} else if(user == "cpu") {
			token = "O";
		} else {
			System.out.println("invalid user");
		}
		
		switch(placement) {
		case 1: 
			board[0][0] = token;
			break;
		case 2:
			board[0][1] = token;
			break;
		case 3: 
			board[0][2] = token;
			break;
		case 4: 
			board[1][0] = token;
			break;
		case 5: 
			board[1][1] = token;
			break;
		case 6:
			board[1][2] = token;
			break;
		case 7: 
			board[2][0] = token;
			break;
		case 8:
			board[2][1] = token;
			break;
		case 9:
			board[2][2] = token;
			break;
		default:
			break; 
		}
		
	}
	
	//checks if the position is taken
	public static boolean isFull(int placement) {
		if(playerPosition.contains(placement) || cpuPosition.contains(placement)) {
			return true;
		}
		
		return false;
	}
	
	//if no one won, it checks if it's a tie
	public static boolean isTie() {
		if(playerPosition.size() + cpuPosition.size() == 9) {
			return true;
		}
		
		return false;
	}
	
	//checks which player starts (cpu or player)
	public static int throwDice() {
		Random random = new Random();
		int cpuDice = random.nextInt(6) + 1;
		int playerDice = random.nextInt(6) + 1;
		
		System.out.println("Throwing dice...");
		System.out.println("You got " + playerDice);
		System.out.println("CPU got " + cpuDice);
		
		if(cpuDice > playerDice) {
			System.out.println("CPU starts!");
			return 1;
		} else if (playerDice > cpuDice) {
			System.out.println("You start!");
			return 2;
		} else {
			System.out.println("It's a tie. Trying again...");
		}
			
		return	-1;
	}
}
