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

import project4.Critter.TestCritter;

public class Tribble extends TestCritter{
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	public Tribble() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}
	
	@Override
	public String toString() { return "T"; }
	
	@Override
	public void doTimeStep() {
		//walk(dir);
		
		Critter c = new Tribble();
		int mateDir = lookForMate();
		int emptyDir = lookForEmpty();
		if (mateDir >= 0) {
			int babyDirection = emptyDir < 0 ? 0: emptyDir;
			if (getEnergy() - Params.min_reproduce_energy > 0)
				reproduce(c, babyDirection);
		} else if (emptyDir >= 0) {
			if (getEnergy() - Params.walk_energy_cost > 0)
				walk(emptyDir);
		}
		
		int roll = Critter.getRandomInt(GENE_TOTAL);
		int turn = 0;
		while (genes[turn] <= roll) {
			roll = roll - genes[turn];
			turn = turn + 1;
		}
		assert(turn < 8);
		
		dir = (dir + turn) % 8;
		
	}
	
	private int lookForMate() {
		int direction = -1;
		for (int dir = 0; dir <= 7; dir++) {
			String neighbor = null;
			if (getEnergy() - Params.look_energy_cost > 0) 
				neighbor = look(dir);
			if (neighbor != null && neighbor.equals(this.toString()) ) {
				direction = dir;
				break;
			}
		}
		return direction;
	}
	
	private int lookForEmpty() {
		int direction = -1;
		for (int dir = 0; dir <= 7; dir++) {
			if (getEnergy() - Params.look_energy_cost > 0) 
				if (look(dir) == null) {
					direction = dir;
					break;
				}
		}
		return direction;
	}

	@Override
	public boolean fight(String oponent) {
		return true;
	}
	

	public static void runStats(java.util.List<Critter> Tribble) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : Tribble) {
			Tribble c = (Tribble) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + Tribble.size() + " total Tribble    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * Tribble.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * Tribble.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * Tribble.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * Tribble.size()) + "% left   ");
		System.out.println();
	}

	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return null;
	}

}
