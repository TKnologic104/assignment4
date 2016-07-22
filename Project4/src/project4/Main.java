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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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
			switch (tokens[0]){
			case "quit":
				if (tokens.length > 1){
					System.out.println("Invalid Command: " + input);
					break;
				}
				input = tokens[0];
				break;
			case "show":
				if (tokens.length > 1){
					System.out.println("Invalid Command: " + input);
					break;
				}
				Critter.displayWorld();
				break;
			case "step":
				if (tokens.length > 2){
					System.out.println("Invalid Command: " + input);
					break;
				}
				i = 1;
				if (tokens.length > 1){
					i = getNumber(tokens[1]);
				}
				for (int j = 1 ; j <= i; j++){
					Critter.worldTimeStep();
				}
				break;
			case "seed":
				if (tokens.length > 2){
					System.out.println("Invalid Command: " + input);
					break;
				}
				i = 1;
				if (tokens.length > 1){
					i = getNumber(tokens[1]);
				}
				i = getNumber(tokens[1]);
				Critter.setSeed(i);
				break;
			case "make":
				if (tokens.length > 3){
					System.out.println("Invalid Command: " + input);
					break;
				}
				i = 1;
				if (tokens.length > 2){
					i = getNumber(tokens[2]);
				}
				for (int j = 1 ; j <= i; j++){
					Critter.makeCritter(tokens[1]);
				}
				break;
			case "stats":
				if (tokens.length > 2){
					System.out.println("Invalid Command: " + input);
					break;
				}
				if (!(tokens.length == 2)){
					System.out.println("Invalid Command: " + input);
					break;
				}
				List<Critter> statList = new ArrayList<Critter>();
				statList = Critter.getInstances(tokens[1]);
				try{
					if (statList.size() <= 0){
						System.out.println("No instances found");
						break;
					}
				}
				catch (NullPointerException e){
					break;
				}
				if (statList.equals(Critter.getPopulation())){
					Critter.runStats(statList);
					break;
				}
				Object ob = null;
				Method m1 = null; // super class to all methods of any particular class
				Class<?> cls = Class.forName(tokens[1]);
				ob = cls.newInstance();
				try {
					m1 = ob.getClass().getMethod("runStats", List.class);
				} catch (NoSuchMethodException | SecurityException e) {
					System.out.println("Invalid Command: " + input);
					return;
					//e.printStackTrace();
				}
				try {
					m1.invoke(ob, statList);
				} catch (IllegalArgumentException | InvocationTargetException e) {
					System.out.println("Invalid Command: " + input);
					return;
					//e.printStackTrace();
				}
				break;
			default:
				//if (!(input.equals("START"))){
					System.out.println("Invalid Command: " + input);
			//}
				 break;
			}
		}
		System.out.println("Bye");
	}
	
	/** converts string into an integer value, and also prints Invalid command if its not a valid integer.
	 * @param str
	 * @return
	 */
	public static int getNumber(String str){
		int i = 0;
		if (str == null || str == "" || str == " "){
			return 1;
		}
		try { 
			i = Integer.parseInt(str);
		}
		 catch(NumberFormatException er) {
			 System.out.println("Invalid Command: " + input);
			 return 0;
		}
		if (i < 1){
			 System.out.println("Invalid Command:" + input);
			 return 0;
		}
		return i;
	}

}
