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

public class Ent extends Critter {
	
	@Override
	public String toString() { return "E"; }
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	public Ent() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight(String not_used) { return true; }

	@Override
	public void doTimeStep() {

		
		if (getEnergy() > Params.min_reproduce_energy + Params.start_energy) {
			Ent child = new Ent();
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
			reproduce(child, Critter.getRandomInt(8));
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

	public static void runStats(java.util.List<Critter> Ents) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : Ents) {
			Ent c = (Ent) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + Ents.size() + " total Ents    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * Ents.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * Ents.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * Ents.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * Ents.size()) + "% left   ");
		System.out.println();
	}

	public static String runStatsString(java.util.List<Critter> Ents) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : Ents) {
			Ent c = (Ent) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		String str = Ents.size() + " total Ent's    ";
		str = str + total_straight / (GENE_TOTAL * 0.01 * Ents.size()) + "% straight   ";
		str = str + total_back / (GENE_TOTAL * 0.01 * Ents.size()) + "% back   ";
		str = str + total_right / (GENE_TOTAL * 0.01 * Ents.size()) + "% right   ";
		str = str + total_left / (GENE_TOTAL * 0.01 * Ents.size()) + "% left   ";
		return str;
	}

	@Override 
	public CritterShape viewShape() { 
		return CritterShape.TRIANGLE; 
	}
	
	@Override 
	public javafx.scene.paint.Color viewOutlineColor() { 
		return javafx.scene.paint.Color.GREEN; 
		}

	@Override 
	public javafx.scene.paint.Color viewFillColor() { 
		return javafx.scene.paint.Color.GREEN; 
		}
}
