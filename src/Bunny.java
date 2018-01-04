import java.util.Random;

/**
 * A Bunny is a prey Animal. It moves around a Field in Squares and is preyed
 * upon by Foxes.
 * 
 * @author Conor McAvoy
 *
 */
public class Bunny extends Animal {
	int x;
	int y;
	private Square position;
	
	/**
	 * Creates a Bunny inside a Square with specified x and y coordinates. The
	 * Square should be set separately since Bunnies cannot access the full Field,
	 * only the Square they occupy. This structure may be changed in the future:
	 * it may be easier to make it possible for Animals to have access to the entire
	 * Field in which they reside.
	 * 
	 * @param x The x position of the Bunny.
	 * @param y The y position of the Bunny.
	 */
	public Bunny(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return Square in which the Bunny resides.
	 */
	public Square getPosition() {
		return position;
	}
	
	/**
	 * @param s The Square in which the Bunny resides.
	 */
	public void setPosition(Square s) {
		position = s;
	}
	
	/* (non-Javadoc)
	 * @see Animal#isBunny()
	 */
	@Override
	public boolean isBunny() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see Animal#isFox()
	 */
	@Override
	public boolean isFox() {
		return false;
	}

	/**
	 * This updates where the Bunny is in the Field based on its surroundings
	 * and current state. The current implementation is extremely sparse.
	 * Motion is accomplished by recognizing a difference in the Bunny's
	 *  x and y variables and its position variable, and reconciling the position
	 *  accordingly. As mentioned above, this organization is liable to change.
	 */
	public void update() {		
		x = position.getNortheast().x;
		y = position.getNortheast().y;
		
//		Random rng = new Random();
//		int new_position = rng.nextInt(8);
//		
//		switch (new_position) {
//			case 0:
//				x = position.getNorth().x;
//				y = position.getNorth().y;
//				break;
//			case 1:
//				x = position.getNortheast().x;
//				y = position.getNortheast().y;
//				break;
//			case 2:
//				x = position.getEast().x;
//				y = position.getEast().y;
//				break;
//			case 3:
//				x = position.getSoutheast().x;
//				y = position.getSoutheast().y;
//				break;
//			case 4:
//				x = position.getSouth().x;
//				y = position.getSouth().y;
//				break;
//			case 5:
//				x = position.getSouthwest().x;
//				y = position.getSouthwest().y;
//				break;
//			case 6:
//				x = position.getWest().x;
//				y = position.getWest().y;
//				break;
//			case 7:
//				x = position.getNorthwest().x;
//				y = position.getNorthwest().y;
//				break;			
//		}
	}
	
	/**
	 * Accomplished Bunny motion. See comments in update method.
	 * 
	 * @param b Bunny to be moved.
	 * @param squares Full Square grid of the entire Field.
	 */
	public static void move(Bunny b, Square[][] squares) {
		if (b.x == b.position.x && b.y == b.position.y) {
			return;
		}
		
		Square newPosition = squares[b.y][b.x];
		if (newPosition.getAnimal() != null) { // collision detection
			// currently if a collision is detected no motion will occur
			b.x = b.position.x;
			b.y = b.position.y;		
			return;
		}
		
		b.position.removeAnimal();
		newPosition.setAnimal(b);
		b.position = newPosition;
	}
}
