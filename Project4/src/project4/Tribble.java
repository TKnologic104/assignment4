package project4;

//import project4.Critter.TestCritter;

public class Tribble extends Critter{
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
		walk(dir);
		
		if (getEnergy() > Params.min_reproduce_energy) { 
			Tribble child = new Tribble();
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
		
		int roll = Critter.getRandomInt(GENE_TOTAL);
		int turn = 0;
		while (genes[turn] <= roll) {
			roll = roll - genes[turn];
			turn = turn + 1;
		}
		assert(turn < 8);
		
		dir = (dir + turn) % 8;
		
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

}
