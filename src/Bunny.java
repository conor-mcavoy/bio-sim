
public class Bunny implements Animal {
	int x;
	int y;
	Square position;
	
	public Bunny(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean isBunny() {
		return true;
	}

	@Override
	public boolean isFox() {
		return false;
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}
}
