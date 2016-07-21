/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Summer 2016
 */
package project4;
import java.util.Scanner;

public class Main {

	static int i = 0;
	static String input = "START";
	static String delim = "[ ]+";
	static String[] tokens = new String[5]; 
	static Scanner in = new Scanner(System.in);

	public static void main(String args[]) throws InvalidCritterException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		while (!(input.toUpperCase().equals("QUIT"))) {
			System.out.print("Critters>");
			input = in.nextLine();
			tokens = input.split(delim);
			switch (tokens[0].toUpperCase()){
			case "QUIT":
				input = tokens[0];
				break;
			case "SHOW":
				Critter.displayWorld();
				break;
			case "STEP":
				i = 1;
				if (tokens.length > 1){
					i = getNumber(tokens[1]);
				}
				for (int j = 1 ; j <= i; j++){
					Critter.worldTimeStep();
				}
				break;
			case "SEED":
				i = 1;
				if (tokens.length > 1){
					i = getNumber(tokens[1]);
				}
				i = getNumber(tokens[1]);
				Critter.setSeed(i);
				break;
			case "MAKE":
//				Critter.makeCritter("project4.Craig");
//				Critter.makeCritter("project4.Jitter");
//				Critter.makeCritter("project4.Bitter");
//				break;
				i = 1;
				if (tokens.length > 2){
					i = getNumber(tokens[2]);
				}
				for (int j = 1 ; j <= i; j++){
					Critter.makeCritter(tokens[1]);
				}
			case "STATS":
				if (!(tokens.length == 2)){
					System.out.println("Invalid Command - stats");
					break;
				}
				Critter.getInstances(tokens[1]);
				break;
			default:
				if (!(input.equals("START"))){
					System.out.println("Invalid command");
			}
				 break;
			}
		}
		System.out.println("Bye");
	}
	
	public static int getNumber(String str){
		int i = 0;
		if (str == null || str == "" || str == " "){
			return 1;
		}
		try { 
			i = Integer.parseInt(str);
		}
		 catch(NumberFormatException er) {
			 System.out.println("Invalid command");
			 return 0;
		}
		if (i < 1){
			 System.out.println("Invalid command");
			 return 0;
		}
		return i;
	}

}
