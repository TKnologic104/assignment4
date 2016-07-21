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
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	public final void setx(int x){
		this.x_coord = x;
		return;
	}
	
	protected final void sety(int y){
		this.y_coord = y;
		return;
	}
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
		
		if (x_coord > Params.world_width){
			this.x_coord = 1;
		}
		if (this.x_coord < 1){
			this.x_coord = Params.world_width + 1;
		}
		this.y_coord = this.y_coord + direction;
		if (this.y_coord > Params.world_height){
			this.y_coord = 1;
		}
		if (this.y_coord < 1){
			this.y_coord = Params.world_height + 1;
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
		
	}

	
	protected final void reproduce(Critter offspring, int direction) {
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/* create and initialize a Critter subclass
	 * critter_class_name must be the name of a concrete subclass of Critter, if not
	 * an InvalidCritterException must be thrown
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException{
		Class<?> cls = null;
		try {
			cls = Class.forName(critter_class_name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error while creating class");
		}
		Object ob = null;
		try {
			ob = cls.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error while creating class");

		} 
		population.add((Critter) ob);
	}
	
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		Object ob = null;
		try {
			Class cls = Class.forName(critter_class_name);
			try {
				ob = cls.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int k = 0; k < getPopulation().size(); k++){
			if ((getPopulation().get(k)).getClass().isInstance(ob)){
				result.add(getPopulation().get(k));
			}
		}
		return result;
	}
	
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
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
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
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
	
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
		
	public static void worldTimeStep() {
		for (int k = 0; k < getPopulation().size(); k++){
			getPopulation().get(k).walk(1);//change walk to doTimestep of Critter "k"
		}
	}
	
	public static void displayWorld() {
		String[][] out = new String[Params.world_height + 2][Params.world_width + 2];
		for (int i = 0; i <= Params.world_height + 1;i++){
			for (int j = 0; j <= Params.world_width + 1;j++){
			out[i][j] = " ";
			}
		}

		out[0][0] = "+";
		out[Params.world_height + 1][0] = "+";
		out[0][Params.world_width + 1] = "+";
		out[Params.world_height + 1][Params.world_width + 1] = "+";
		for (int i = 1; i <= Params.world_width;i++){
			out[0][i] = "-";
			out[Params.world_height + 1][i] = "-";
		}
		
		for (int i = 1; i <= Params.world_height;i++){
			out[i][0] = "|";
			out[i][Params.world_width + 1] = "|";
		}
		
		for (int i = 1; i <= Params.world_height;i++){
			for (int j = 1; j <= Params.world_width;j++){
				for (int k = 0; k < getPopulation().size(); k++){
					if (((getPopulation().get(k)).x_coord == j) && ((getPopulation().get(k)).y_coord == i)){ 
						out[i][j] = getPopulation().get(k).toString();
						break;
					}
				}
			}
		}
		
		for (int i = 0; i <= Params.world_height + 1;i++){
			for (int j = 0; j <= Params.world_width + 1;j++){
				System.out.print(out[i][j]);
			}
			System.out.println();
		}

	}

	public static List<Critter> getPopulation() {
		return population;
	}

	public static void setPopulation(List<Critter> population) {
		Critter.population = population;
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
