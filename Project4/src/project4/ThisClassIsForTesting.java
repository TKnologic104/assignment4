package project4;

public class ThisClassIsForTesting extends Critter{
	
@Override
public String toString() { return "T"; }

public ThisClassIsForTesting(){
	super.setX(0);
	super.setY(4);
}
	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
