package project4;

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

}
