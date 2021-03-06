/* CRITTERS <MyClass.java>
 * https://github.com/MattDaumas/assignment4.git
 * EE422C Project 4 submission by
 * Tarang Khandpur
 * tk8435
 * 76595
 * Matthew Daumas
 * md32789
 * 76595
 * Slip days used: <1>
 * Summer 2016
 */
package project4;

import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

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
	
	/* added */
	private void setEnergy(int energy) { this.energy = energy; }
	
	private int x_coord;
	private int y_coord;
	private boolean hasMoved;
	private boolean inFight;
	
	protected final void walk(int direction) {
		if (hasMoved == true) {
			energy -= Params.walk_energy_cost;
			return;
		}
		move(direction, 1, Params.walk_energy_cost);
	}
	
	protected final void run(int direction) {
		if (hasMoved == true) {
			energy -= Params.run_energy_cost;
			return;
		}
		move(direction, 2, Params.run_energy_cost);
	}

	
	private void move(int direction, int speed, int cost) {
		this.energy -= cost;
		if (this.energy <= 0) {
			return;
		}
		int peekX = -1;
		int peekY = -1;
		if (this.inFight) {
			switch (direction) {
			case 0:
				peekX = this.x_coord + speed;	// look straight right
				peekY = this.y_coord;
				break;
			case 1:
				peekX = this.x_coord + speed;	// look upper right
				peekY = this.y_coord - speed;	
				break;
			case 2:
				peekX = this.x_coord;
				peekY = this.y_coord - speed; 	// look straight up
				break;
			case 3:
				peekX = this.x_coord - speed;	// look upper left
				peekY = this.y_coord - speed;
				break;
			case 4:
				peekX = this.x_coord - speed;	// look straight left
				peekY = this.y_coord;
				break;
			case 5:
				peekX = this.x_coord - speed;	// look lower left
				peekY = this.y_coord + speed;
				break;
			case 6:
				peekX = this.x_coord;
				peekY = this.y_coord + speed;	// look straight down
				break;
			case 7:
				peekX = this.x_coord + speed;	// look lower right
				peekY = this.y_coord + speed;
				break;
			default:
				System.out.println("invalid direction");
				break;
			}	
			
			for(Critter c: population) {
				if (this != c && c.x_coord == peekX && c.y_coord == peekY) {
					return;
				}
			}
		}
		
		switch (direction) {
		case 0:
			this.x_coord += speed;	// go straight right
			break;
		case 1:
			this.x_coord += speed;	// go upper right
			this.y_coord -= speed;	
			break;
		case 2:
			this.y_coord -= speed; 	// go straight up
			break;
		case 3:
			this.x_coord -= speed;	// go upper left
			this.y_coord -= speed;
			break;
		case 4:
			this.x_coord -= speed;	// go straight left
			break;
		case 5:
			this.x_coord -= speed;	// go lower left
			this.y_coord += speed;
			break;
		case 6:
			this.y_coord += speed;	// go straight down
			break;
		case 7:
			this.x_coord += speed;	// go lower right
			this.y_coord += speed;
			break;
		default:
			System.out.println("invalid direction");
			break;
		}
		if (x_coord == Params.world_width+1){
			x_coord = x_coord - Params.world_width;
		}
		if (x_coord == Params.world_width+2){
			x_coord = x_coord - (Params.world_width);
		}
		if (x_coord == 0){
			x_coord = Params.world_width;
		}
		if (x_coord < 0){
			x_coord = Params.world_width+1;
		}
		if (y_coord == Params.world_height+1){
			y_coord = y_coord - Params.world_height;
		}
		if (y_coord == Params.world_height+2){
			y_coord = y_coord - (Params.world_height);
		}
		if (y_coord == 0){
			y_coord = Params.world_height;
		}
		if (y_coord < 0){
			y_coord = Params.world_height+1;
		}
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
		offspring.x_coord = this.x_coord;
		offspring.y_coord = this.y_coord;
		offspring.move(direction, 1, 0);
		/* stage babies */
		babies.add(offspring);
	
	}
	
	private static void resolveEncounter(Critter a, Critter b) {
		if (a.getEnergy() <= 0 || b.getEnergy() <= 0) {
			return;
		}
		
		a.inFight = true;
		b.inFight = true;
		int a_AttackRoll = 0;
		int b_AttackRoll = 0;
		
		if (a.fight(b.toString())) {
			if(a.getEnergy() <= 0) {
				return;
			}
			a_AttackRoll = Critter.getRandomInt(a.getEnergy());
		}
		if (b.fight(a.toString())) {
			if(b.getEnergy() <= 0) {
				return;
			}
			b_AttackRoll = Critter.getRandomInt(b.getEnergy());
		}
		if (a.x_coord == b.x_coord && a.y_coord == b.y_coord) {
			if (a_AttackRoll >= b_AttackRoll) {
				a.setEnergy(b.getEnergy() / 2);
				b.setEnergy(0);
			} else {
				b.setEnergy(a.getEnergy() / 2);
				a.setEnergy(0);
			}
		} 
		return;
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
			//e.printStackTrace();
			System.out.println("Error while creating class");
			return;
		}
		Object ob = null;
		try {
			ob = cls.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			//e.printStackTrace();
			System.out.println("Error while creating class");
			return;

		} 
		Critter c = (Critter) ob;
		c.x_coord = Critter.getRandomInt(Params.world_width-1)+1;
		c.y_coord = Critter.getRandomInt(Params.world_height-1)+1;
		c.energy = Params.start_energy;
		population.add(c);
	}
	
	/** gets the list of all the subclasses as specified in the argument
	 * @param critter_class_name
	 * @return list of one specified critter subclass
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		String delim = "[.]";
		String[] tokens = new String[2];
		tokens = critter_class_name.split(delim);
		
		if (!critter_class_name.contains(".")){
			System.out.println("Invalid Command: stats " + critter_class_name);
			return null;
		}
		
		if (tokens[1].equals("Critter")){
			return population;
		}
		List<Critter> result = new java.util.ArrayList<Critter>();
		Object ob = null;
		try {
			Class<?> cls = Class.forName(critter_class_name);
			try {
				ob = cls.newInstance();
			} catch (InstantiationException e) {
				System.out.println("Invalid Command: stats " + critter_class_name);
				return null;
				//e.printStackTrace();
			} catch (IllegalAccessException e) {
				System.out.println("Invalid Command: stats " + critter_class_name);
				return null;
				//e.printStackTrace();
			} 
		} catch (ClassNotFoundException e) {
			System.out.println("Invalid Command: stats " + critter_class_name);
			return null;
			//e.printStackTrace();
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
		
		protected int getXCoord(){
			return super.x_coord;
		}
		
		protected int getYCoord(){
			return super.y_coord;
		}
		
        public static List<Critter> getPopulation() {
            return Critter.getPopulation();
        }
        
        public static void setPopulation(Critter c) {
        	Critter.setPopulation(c);
        }
        
        public static void resolveEncounter(Critter a, Critter b) {
        	Critter.resolveEncounter(a, b);
        }
        
        public static void cullDead() {
    		java.util.Iterator<Critter> itr = population.iterator();
    		while(itr.hasNext()){
    			Critter c = itr.next();
    			if (c.getEnergy() <= 0) {
    				itr.remove();
    			}
    		}	
        }
	}
	
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();	
	
	/**
	 * runs "doTimeStep" of each critter "k" in the population. 
	 */
	public static void worldTimeStep() {
		/* move and reproduce (but don't add babies to population) */
		for (Critter c: population) {
			c.inFight = false;
			c.hasMoved = false;
			c.doTimeStep();
		}
		/* resolve encounters*/				
		for (Critter a: population) {
			for (Critter b: population) {
				if (a != b && a.x_coord == b.x_coord && a.y_coord == b.y_coord) {
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
			a.setEnergy(Params.start_energy);
			a.setXCoord(Critter.getRandomInt(Params.world_width-1)+1); //fixed for border
			a.setYCoord(Critter.getRandomInt(Params.world_height-1)+1); //fixed for border
			population.add(a);
		}
		/* remove dead critters from population */
		java.util.Iterator<Critter> itr = population.iterator();
		while(itr.hasNext()){
			Critter c = itr.next();
			if (c.getEnergy() <= 0) {
				itr.remove();
			}
		}	
		/* add babies to population */
		for (Critter c: babies) {
			population.add(c);
		}
	}

	
	public static void displayWorld() {
		//fills whole grid with " ".
		String[][] out = new String[Params.world_height + 2][Params.world_width + 2]; // adds room for border.
		for (int i = 0; i <= Params.world_height + 1;i++){
			for (int j = 0; j <= Params.world_width + 1;j++){
			out[i][j] = " ";
			}
		}
		//sets corners to "+"
		out[0][0] = "+";
		out[Params.world_height + 1][0] = "+";
		out[0][Params.world_width + 1] = "+";
		out[Params.world_height + 1][Params.world_width + 1] = "+";
		//makes first and last row "-" 		
		for (int i = 1; i <= Params.world_width;i++){
			out[0][i] = "-";
			out[Params.world_height + 1][i] = "-";
		}
		//makes first and last column "|"		
		for (int i = 1; i <= Params.world_height;i++){
			out[i][0] = "|";
			out[i][Params.world_width + 1] = "|";
		}
		//places critters in population on board using their toString return characters
		//limits the placement to the playable area.
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
		//prints out the board
		for (int i = 0; i <= Params.world_height + 1;i++){
			for (int j = 0; j <= Params.world_width + 1;j++){
				System.out.print(out[i][j]);
			}
			System.out.println();
		}

	}

	private static List<Critter> getPopulation() {
		return population;
	}
	
	private static void setPopulation(Critter c) {
		population.add(c);
	}

}
