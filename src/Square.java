public class Square {
	private Animal animal;

	private Square north;
	private Square east;
	private Square south;
	private Square west;

	final int x;
	final int y;

	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Animal getAnimal() {
		return this.animal;
	}

	public boolean containsAnimal() {
		return this.animal != null;
	}

	public void setAnimal(Animal a) {
		this.animal = a;
	}

	public void removeAnimal() {
		this.animal = null;
	}

	public Square getNorth() {
		return north;
	}

	public void setNorth(Square s) {
		this.north = s;
	}

	public Square getNortheast() {
		return north.getEast();
	}

	public Square getEast() {
		return east;
	}

	public void setEast(Square s) {
		this.east = s;
	}

	public Square getSoutheast() {
		return south.getEast();
	}

	public Square getSouth() {
		return south;
	}

	public void setSouth(Square s) {
		this.south = s;
	}

	public Square getSouthwest() {
		return south.getWest();
	}

	public Square getWest() {
		return west;
	}

	public void setWest(Square s) {
		this.west = s;
	}

	public Square getNorthwest() {
		return north.getWest();
	}

	public Square getNeighbor(int pos) {
		switch (pos) {
		case 0:
			return getNorth();
		case 1:
			return getNortheast();
		case 2:
			return getEast();
		case 3:
			return getSoutheast();
		case 4:
			return getSouth();
		case 5:
			return getSouthwest();
		case 6:
			return getWest();
		case 7:
			return getNorthwest();
		default:
			throw new IllegalArgumentException("No valid neighbor with index " + pos);
		}
	}
}
