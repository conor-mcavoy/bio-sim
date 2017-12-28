import java.util.Random;

public class Bunny implements Animal {
	int x;
	int y;
	private Square position;
	
	public Bunny(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Square getPosition() {
		return position;
	}
	
	public void setPosition(Square s) {
		position = s;
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
		// currently all bunnies move northeast
		
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
