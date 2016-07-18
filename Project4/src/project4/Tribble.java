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
		// TODO Auto-generated method stub
		return false;
	}

}
