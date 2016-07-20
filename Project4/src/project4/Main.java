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
import java.util.Collection;
import java.util.Scanner;



public class Main {

	public static void main(String[] args) {
		System.out.println("GLHF");
		Craig test1 = new Craig();
		test1.setX(5);
		test1.setY(4);
		//ThisClassIsForTesting test2 = new ThisClassIsForTesting();
		Critter.getPopulation().add(test1);
		//Critter.population.add(test2);
		
		//Critter.displayWorld1(); //displays world
		Critter.displayWorld2();
		Critter.worldTimeStep();
		
		System.out.println();
		Critter.displayWorld2();
		Critter.worldTimeStep();

		System.out.println();
		Critter.displayWorld2();
		Critter.worldTimeStep();
		

		System.out.println();
		Critter.displayWorld2();
		Critter.worldTimeStep();
	}
}
