/* CRITTERS Critter.java
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

import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */
public abstract class Critter {

	public static List<Critter> critterWorld = new java.util.ArrayList<>();

	/*
	
	public static void displayWorld1() {
		for (int i = 1; i <= Params.world_height; i++) {
			for (int j = 1; j <= Params.world_width; j++) {
				if (i == 1 || i == Params.world_height) {
					if (j == 1) {
						System.out.print("+");
						continue;
					}
					if (j == Params.world_width) {
						System.out.println("+");
						continue;
					}
					System.out.print("-");
					continue;
				} else {
					if (j == 1) {
						System.out.print("|");
						continue;
					}
					if (j == Params.world_width) {
						System.out.println("|");
						continue;
					}
				}
				for (int k = 0; k < critterWorld.size(); k++) {
					if (((critterWorld.get(k)).getX_coord() == j) && ((critterWorld.get(k)).y_coord == i)) {
						System.out.print(critterWorld.get(k).toString());
					} else {
						System.out.print("-");
					}
				}
			}
		}

	}

	*/
	
	public static void displayWorld2(){
		int y = Params.world_height;
		int x = Params.world_width;

		String[][] b = new String[y][x];

		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				for (int k = 0; k < critterWorld.size(); k++) {
					if(((critterWorld.get(k)).getX_coord() == j) && ((critterWorld.get(k)).getY_coord() == i)) {
						b[i][j] = critterWorld.get(k).toString();
					} 
					else {
						b[i][j] = "-";
					}
				}
			}
			b[i][0] = "|";
			b[i][x-1] = "|";
		}
		b[0][x-1] = "+";
		b[0][0] = "+";
		b[y-1][x-1] = "+";
		b[y-1][0] = "+";
		
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				System.out.print((b[i][j]));
			}
			System.out.println();
		}
	}

	private static java.util.Random rand = new java.util.Random();

	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}

	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}

	/*
	 * a one-character long string that visually depicts your critter in the
	 * ASCII interface
	 */
	public String toString() {
		return "";
	}

	private int energy = 0;

	protected int getEnergy() {
		return energy;
	}

	private int x_coord;
	private int y_coord;

	protected final void walk(int direction) {
		switch (direction) {
		case 0:
			setX_coord(getX_coord() + 1); // go straight right
			break;
		case 1:
			setX_coord(getX_coord() + 1); // go upper right
			setY_coord(getY_coord() - 1);
			break;
		case 2:
			setY_coord(getY_coord() - 1); // go straight up
			break;
		case 3:
			setX_coord(getX_coord() - 1); // go upper left
			setY_coord(getY_coord() - 1);
			break;
		case 4:
			setX_coord(getX_coord() - 1); // go straight left
			break;
		case 5:
			setX_coord(getX_coord() - 1); // go lower left
			setY_coord(getY_coord() + 1);
			break;
		case 6:
			setY_coord(getY_coord() + 1); // go straight down
			break;
		case 7:
			setX_coord(getX_coord() + 1); // go lower right
			setY_coord(getY_coord() + 1);
			break;
		default:
			System.out.println("invalid direction");
			break;
		}
		energy -= Params.walk_energy_cost;
	}

	protected final void run(int direction) {
		switch (direction) {
		case 0:
			setX_coord(getX_coord() + 2); // go straight right
			break;
		case 1:
			setX_coord(getX_coord() + 2); // go upper right
			setY_coord(getY_coord() - 2);
			break;
		case 2:
			setY_coord(getY_coord() - 2); // go straight up
			break;
		case 3:
			setX_coord(getX_coord() - 2); // go upper left
			setY_coord(getY_coord() - 2);
			break;
		case 4:
			setX_coord(getX_coord() - 2); // go straight left
			break;
		case 5:
			setX_coord(getX_coord() - 2); // go lower left
			setY_coord(getY_coord() + 2);
			break;
		case 6:
			setY_coord(getY_coord() + 2); // go straight down
			break;
		case 7:
			setX_coord(getX_coord() + 2); // go lower right
			setY_coord(getY_coord() + 2);
			break;
		default:
			System.out.println("invalid direction");
			break;
		}
		energy -= Params.run_energy_cost;
	}

	protected final void reproduce(Critter offspring, int direction) {
	}

	public abstract void doTimeStep();

	public abstract boolean fight(String oponent);

	/*
	 * create and initialize a Critter subclass critter_class_name must be the
	 * name of a concrete subclass of Critter, if not an InvalidCritterException
	 * must be thrown
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
	}

	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();

		return result;
	}

	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string, 1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();
	}

	/*
	 * the TestCritter class allows some critters to "cheat". If you want to
	 * create tests of your Critter model, you can create subclasses of this
	 * class and then use the setter functions contained here.
	 * 
	 * NOTE: you must make sure that the setter functions work with your
	 * implementation of Critter. That means, if you're recording the positions
	 * of your critters using some sort of external grid or some other data
	 * structure in addition to the x_coord and y_coord functions, then you MUST
	 * update these setter functions so that they correctly update your
	 * grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setXCoord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setYCoord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
	}

	private static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	public static void worldTimeStep() {
		for (Critter c : population) {
			c.doTimeStep();
		}
	}

	public static void displayWorld() {
	}
	
	
	//I created these
	public void setX(int x){
		this.setX_coord(x);
		return;
	}
	
	public void setY(int y){
		this.setY_coord(y);
		return;
	}

	public int getX_coord() {
		return x_coord;
	}

	public void setX_coord(int x_coord) {
		this.x_coord = x_coord;
	}

	public int getY_coord() {
		return y_coord;
	}

	public void setY_coord(int y_coord) {
		this.y_coord = y_coord;
	}
	
	
	
}
