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

//import project4.Critter.TestCritter;

public class FraidyCat extends Critter{
	
	@Override
	public String toString() { return "F"; }
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;

	
	public FraidyCat() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight(String not_used) { 
		int emptyDir = lookForEmpty();
		if (emptyDir >= 0) {
			dir = emptyDir;
		}
		run(dir);
		return false; 
	}
	
	private int lookForEmpty() {
		int direction = -1;
		for (int dir = 0; dir <= 7; dir++) {
			if (getEnergy() - Params.look_energy_cost > 0) 
				if (look2(dir) == null) {
					direction = dir;
					break;
				}
		}
		return direction;
	}

	@Override
	public void doTimeStep() {
		/* take one step forward */
		boolean myRandomBoolean = (Critter.getRandomInt(1) == 1);
		if (myRandomBoolean) {
			walk(dir);
		}
		
		if (getEnergy() > (Params.start_energy - Params.min_reproduce_energy)) {
			FraidyCat child = new FraidyCat();
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

	public static void runStats(java.util.List<Critter> FraidyCats) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : FraidyCats) {
			FraidyCat c = (FraidyCat) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + FraidyCats.size() + " total FraidyCats    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * FraidyCats.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * FraidyCats.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * FraidyCats.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * FraidyCats.size()) + "% left   ");
		System.out.println();
	}

	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return null;
	}
}