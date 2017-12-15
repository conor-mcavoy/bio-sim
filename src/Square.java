import java.util.ArrayList;

public class Square {
	private Animal animal;
	private ArrayList<Square> neighbors;
	
	final int x;
	final int y;
	
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Animal getAnimal() {
		return this.animal;
	}
	
	public void setAnimal(Animal a) {
		this.animal = a;
	}
	
	public ArrayList<Square> getNeighbors() {
		return neighbors;
	}
	
	public void addNeighbor(Square n) {
		neighbors.add(n);
	}
}
