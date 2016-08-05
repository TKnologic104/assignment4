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

public class Dumbo extends Critter {
	
	@Override
	public String toString() { return "D"; }
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	public Dumbo() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight(String not_used) { return true; }

	@Override
	public void doTimeStep() {
		/* take one step forward */
		walk(0);
		
		if (getEnergy() > 150) {
			Dumbo child = new Dumbo();
			for (int k = 0; k < 8; k += 1) {
				child.genes[k] = this.genes[k];
			}
			int g = Critter.getRandomInt(8);
			while (child.genes[g] == 0) {
				g = Critter.getRandomInt(8);
			}
			child.genes[g] -= 1;
			g = Critter.getRandomInt(8);
			child.genes[g] += 1;
			//reproduce(child, Critter.getRandomInt(8));
		}
		
		/* pick a new direction based on our genes */
		int roll = Critter.getRandomInt(GENE_TOTAL);
		int turn = 0;
		while (genes[turn] <= roll) {
			roll = roll - genes[turn];
			turn = turn + 1;
		}
		assert(turn < 8);
		dir = (dir + turn) % 8;
	}

	public static void runStats(java.util.List<Critter> Dumbo) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : Dumbo) {
			Dumbo c = (Dumbo) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + Dumbo.size() + " total Dumbo    ");
		System.out.print("" + ("100% straight   "));
		System.out.print("" + ("0% right   "));
		System.out.print("" + ("0% back   "));
		System.out.print("" + ("0% left   "));
		System.out.println();
	}

	public static String runStatsString(java.util.List<Critter> Dumbo) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : Dumbo) {
			Dumbo c = (Dumbo) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		String str = Dumbo.size() + " total Dumbo    ";
		str = str + "100% straight   ";
		str = str + "0% right   ";
		str = str + "0% back   ";
		str = str + "0% left   ";
		return str;
	}

	@Override 
	public CritterShape viewShape() { 
		return CritterShape.SQUARE; 
	}
	
	@Override 
	public javafx.scene.paint.Color viewOutlineColor() { 
		return javafx.scene.paint.Color.RED; 
		}

	@Override 
	public javafx.scene.paint.Color viewFillColor() { 
		return javafx.scene.paint.Color.RED; 
	}
}
