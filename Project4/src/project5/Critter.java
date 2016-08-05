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
package project5;

import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */

public abstract class Critter {
	/* NEW FOR PROJECT 5 */ 

	public enum CritterShape { CIRCLE, SQUARE, TRIANGLE, DIAMOND, STAR
	} 
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background  
	 * *  * critters must override at least one of the following three methods, it is not
	 * proper for critters to remain invisible in the view  *  
	 * * If a critter only overrides the outline color, then it will look like a non-filled
	 * shape, at least, that's the intent. You can edit these default methods however you
	 * need to, but please preserve that intent as you implement them.  * 
	 * */ 
	public javafx.scene.paint.Color viewColor() {
		return javafx.scene.paint.Color.WHITE;
	}

	public javafx.scene.paint.Color viewOutlineColor() { 
		return viewColor(); 
	} 
	
	public javafx.scene.paint.Color viewFillColor() { 
		return viewColor(); 
	}
	
	public abstract CritterShape viewShape(); 
	protected String look(int direction, boolean steps) {
		return null;
	}
	
	
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
				peekX = this.getX_coord() + speed;	// look straight right
				peekY = this.getY_coord();
				break;
			case 1:
				peekX = this.getX_coord() + speed;	// look upper right
				peekY = this.getY_coord() - speed;	
				break;
			case 2:
				peekX = this.getX_coord();
				peekY = this.getY_coord() - speed; 	// look straight up
				break;
			case 3:
				peekX = this.getX_coord() - speed;	// look upper left
				peekY = this.getY_coord() - speed;
				break;
			case 4:
				peekX = this.getX_coord() - speed;	// look straight left
				peekY = this.getY_coord();
				break;
			case 5:
				peekX = this.getX_coord() - speed;	// look lower left
				peekY = this.getY_coord() + speed;
				break;
			case 6:
				peekX = this.getX_coord();
				peekY = this.getY_coord() + speed;	// look straight down
				break;
			case 7:
				peekX = this.getX_coord() + speed;	// look lower right
				peekY = this.getY_coord() + speed;
				break;
			default:
				System.out.println("invalid direction");
				break;
			}	
			
			for(Critter c: population) {
				if (this != c && c.getX_coord() == peekX && c.getY_coord() == peekY) {
					return;
				}
			}
		}
		
		switch (direction) {
		case 0:
			this.setX_coord(this.getX_coord() + speed);	// go straight right
			break;
		case 1:
			this.setX_coord(this.getX_coord() + speed);	// go upper right
			this.setY_coord(this.getY_coord() - speed);	
			break;
		case 2:
			this.setY_coord(this.getY_coord() - speed); 	// go straight up
			break;
		case 3:
			this.setX_coord(this.getX_coord() - speed);	// go upper left
			this.setY_coord(this.getY_coord() - speed);
			break;
		case 4:
			this.setX_coord(this.getX_coord() - speed);	// go straight left
			break;
		case 5:
			this.setX_coord(this.getX_coord() - speed);	// go lower left
			this.setY_coord(this.getY_coord() + speed);
			break;
		case 6:
			this.setY_coord(this.getY_coord() + speed);	// go straight down
			break;
		case 7:
			this.setX_coord(this.getX_coord() + speed);	// go lower right
			this.setY_coord(this.getY_coord() + speed);
			break;
		default:
			System.out.println("invalid direction");
			break;
		}
		if (getX_coord() == Params.world_width+1){
			setX_coord(getX_coord() - Params.world_width);
		}
		if (getX_coord() == Params.world_width+2){
			setX_coord(getX_coord() - (Params.world_width));
		}
		if (getX_coord() == 0){
			setX_coord(Params.world_width);
		}
		if (getX_coord() < 0){
			setX_coord(Params.world_width+1);
		}
		if (getY_coord() == Params.world_height+1){
			setY_coord(getY_coord() - Params.world_height);
		}
		if (getY_coord() == Params.world_height+2){
			setY_coord(getY_coord() - (Params.world_height));
		}
		if (getY_coord() == 0){
			setY_coord(Params.world_height);
		}
		if (getY_coord() < 0){
			setY_coord(Params.world_height+1);
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
		offspring.setX_coord(this.getX_coord());
		offspring.setY_coord(this.getY_coord());
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
		if (a.getX_coord() == b.getX_coord() && a.getY_coord() == b.getY_coord()) {
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
		c.setX_coord(Critter.getRandomInt(Params.world_width-1)+1);
		c.setY_coord(Critter.getRandomInt(Params.world_height-1)+1);
		c.energy = Params.start_energy;
		population.add(c);
		System.out.println("make called");
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

	public static String runStatsString(List<Critter> critters) {
		String str = "" + critters.size() + " critters -> ";
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
			str = str + prefix + s + ":" + critter_count.get(s);
			prefix = ", ";
		}
		return str;
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
			return super.getX_coord();
		}
		
		protected int getYCoord(){
			return super.getY_coord();
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
				if (a != b && a.getX_coord() == b.getX_coord() && a.getY_coord() == b.getY_coord()) {
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

	
	public static void displayWorld(Canvas canvas,Label lb, String str) {
		//adds the facility to be able to add graphic contents to the canvas, via the gc
		//later we will use gc. commands to add text and graphics
	    	GraphicsContext gc = canvas.getGraphicsContext2D();
	        // Clear the canvas, by painting an empty rectangle at the starting corner
	        gc.clearRect(0, 0, Params.world_width * 5, Params.world_height * 5);
	 		 gc.setFill(Color.WHITE);
	        gc.fillRect(0, 0, Params.world_width * 5, Params.world_height * 5);
	      //this method will find which critters at what location and write  a string.image at that location on the canvas
	        drawShapes(gc);
				String str2 = "project5." + str;
	//sets the text in the label with the runstats message returned   				
				try {
					if (str.equals("Critter")){
						lb.setText(  Critter.runStatsString(Critter.getInstances(str2)));
					}
					if (str.equals("Dumbo")){
						lb.setText(  Dumbo.runStatsString(Critter.getInstances(str2)));
					}
					if (str.equals("Ent")){
						lb.setText(  Ent.runStatsString(Critter.getInstances(str2)));
					}
					if (str.equals("FraidyCat")){
						lb.setText(  FraidyCat.runStatsString(Critter.getInstances(str2)));
					}
					if (str.equals("Tribble")){
						lb.setText(  Tribble.runStatsString(Critter.getInstances(str2)));
					}
			} 	catch (InvalidCritterException e1) {
	//this create a pop up window to display . it could have been error, warning or information box
				Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("ERROR");
		    	alert.setContentText("Invalid Critter Name");
		    	alert.showAndWait();
				}
			
		}
		
	private static void drawShapes(GraphicsContext gc) {
//		gc.setFont(new Font(STYLESHEET_CASPIAN,5));
		int p = Params.pixel_scale;
		int q = Params.icon_scale;
			for (int i = 1; i <= Params.world_height;i++){
				for (int j = 1; j <= Params.world_width;j++){
					for (int k = 0; k < Critter.getPopulation().size(); k++){
						if (((Critter.getPopulation().get(k)).getX_coord() == j) && ((Critter.getPopulation().get(k)).getY_coord() == i)){ 
							gc.setFill(Critter.getPopulation().get(k).viewFillColor());
//trying to experiment with images instead of the string
//							Image img = new Image(getClass().getResourceAsStream("Dumbo.jpg"));
//							Image img = new Image("Beetle.png");
//							gc.drawImage(img, 10 + j * 5, i * 5, 5, 5);
							String str = Critter.getPopulation().get(k).viewShape().toString();
							if (str.equals("CIRCLE")){
								gc.fillOval(j * p, i * p, q ,q);
							}
							if (str.equals("SQUARE")){
								gc.fillRect(j * p, i * p, q, q);
							}
							if (str.equals("TRIANGLE")){
								 gc.fillPolygon(new double[]{j*p,j*p+q/2,j*p+q},
					                       		new double[]{i*p+q,i*p,i*p+q}, 
					                       		3);
							}
							if (str.equals("DIAMOND")){
								 gc.fillPolygon(new double[]{j*p,j*p+q/2,j*p+q/2,j*p+q},
				                       		new double[]{i*p+q/2,i*p,i*p+q,i*p+q/2}, 
				                       		4);
							}
							if (str.equals("STAR")){
								 gc.fillPolygon(new double[]{j*p,j*p+q/2,j*p+q},
				                       		new double[]{i*p+3*q/4,i*p,i*p+3*q/4}, 
				                       		3);
								 gc.fillPolygon(new double[]{j*p,j*p+q/2,j*p+q},
				                       		new double[]{i*p+1*q/4,i*p+p,i*p+1*q/4}, 
				                       		3);
							}
							break;
						}
					}
				}
			}
		}
	
	public static void displayWorldOld() {
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
					if (((getPopulation().get(k)).getX_coord() == j) && ((getPopulation().get(k)).getY_coord() == i)){ 
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

	static List<Critter> getPopulation() {
		return population;
	}
	
	private static void setPopulation(Critter c) {
		population.add(c);
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
