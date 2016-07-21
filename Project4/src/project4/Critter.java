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
	protected void setEnergy(int energy) { this.energy = energy; }
	
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

	public int getX_coord() {
		return x_coord;
	}

	public int getY_coord() {
		return y_coord;
	}

	protected final void walk(int direction) {
		this.x_coord = this.x_coord + direction;
		if (this.x_coord > Params.world_width){
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
	}
	
	protected final void run(int direction) {
		
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		/* check if parent can have offspring */
		if (this.getEnergy() < Params.min_reproduce_energy) {
			return;
		}
		/* update energy of parent and offspring */
		offspring.setEnergy(this.getEnergy() / 2);
		this.setEnergy((int)Math.ceil(this.getEnergy() / 2.0));
		/* place baby in adjacent space to parent */
		//TODO add support for wrap around world
		switch (direction) {
		case 0:
			setx(getX_coord() + 1); // go straight right
			break;
		case 1:
			setx(getX_coord() + 1); // go upper right
			sety(getY_coord() - 1);
			break;
		case 2:
			sety(getY_coord() - 1); // go straight up
			break;
		case 3:
			setx(getX_coord() - 1); // go upper left
			sety(getY_coord() - 1);
			break;
		case 4:
			setx(getX_coord() - 1); // go straight left
			break;
		case 5:
			setx(getX_coord() - 1); // go lower left
			sety(getY_coord() + 1);
			break;
		case 6:
			sety(getY_coord() + 1); // go straight down
			break;
		case 7:
			setx(getX_coord() + 1); // go lower right
			sety(getY_coord() + 1);
			break;
		default:
			System.out.println("invalid direction");
			break;
		}
		/* stage babies */
		babies.add(offspring);
	}
	
	protected final static void resolveEncounter(Critter a, Critter b) {
		int a_AttackRoll = 0;
		int b_AttackRoll = 0;
		if (a.fight(b.toString())) {
			a_AttackRoll = Critter.getRandomInt(a.getEnergy());
		}
		if (b.fight(a.toString())) {
			b_AttackRoll = Critter.getRandomInt(b.getEnergy());
		}
		if (a_AttackRoll >= b_AttackRoll) {
			a.setEnergy(b.getEnergy() / 2);
			b.setEnergy(0);
		}
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
		/* move and reproduce (but don't add babies to population) */
		for (Critter c: population) {
			c.doTimeStep();
		}
		
		/* resolve encounters*/
		for (Critter a: population) {
			for (Critter b: population) {
				if (a.getX_coord() == b.getX_coord() && a.getY_coord() == b.getY_coord()) {
					resolveEncounter(a,b);
				}
			}
		}
		
		/* update rest energy */
		for (Critter c: population) {
			c.setEnergy(c.getEnergy() - Params.rest_energy_cost);
		}
		
		/* add algae */
		for (int i = 0; i < Params.refresh_algae_count; i += 1) {
			Algae a = new Algae();
			a.setx(Critter.getRandomInt(Params.world_width));
			a.sety(Critter.getRandomInt(Params.world_height));
			population.add(a);
		}
		
		/* remove dead critters from population */
		for (Critter c: population) {
			if (c.getEnergy() <= 0) {
				population.remove(c);
			}
		}
		
		/* add babies to population */
		for (Critter c: babies) {
			population.add(c);
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
	
}
