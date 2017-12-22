import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * A Field is a grid that contains animals and acts as the environment of the simulation.
 * 
 * @author Conor McAvoy
 *
 */
public class Field {
	Square[][] squares;
	int size;
	ArrayList<Bunny> bunnies;
	
	/**
	 * @param size
	 */
	public Field(int size) {
		// default initial population of each animal is size
		this(size, size, size);
	}

	/**
	 * @param size
	 * @param bunnies
	 * @param foxes
	 */
	public Field(int size, int bunnies, int foxes) {
		this.size = size;
		
		initializeSquares();
		
		ArrayList<Integer> positions = new ArrayList<>();
		for (int i = 0; i < size*size; i++) {
			positions.add(i);
		}
		Collections.shuffle(positions);
		
		for (int i = 0; i < bunnies; i++) {
			int index = positions.get(i);
			int x = index % size;
			int y = index / size;
			
			Bunny b = new Bunny(x, y);
			b.setPosition(squares[y][x]);
			squares[y][x].setAnimal(b);
		}
		
//		for (int i = bunnies; i < bunnies + foxes; i++) {
//			int index = positions.get(i);
//			int x = index % size;
//			int y = index / size;
//			
//			Fox f = new Fox();
//			squares[y][x].setAnimal(f);
//		}
		simulate(1);
	}
	
	private void initializeSquares() {
		squares = new Square[size][size];
		// initialize all squares
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				squares[y][x] = new Square(x, y);
			}
		}
		
		// set square neighbors
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Square s = squares[y][x];
				
				if (x == 0) {
					s.setWest(squares[y][size-1]);
				} else {
					s.setWest(squares[y][x-1]);
				}
				
				if (x == size-1) {
					s.setEast(squares[y][0]);
				} else {
					s.setWest(squares[y][x+1]);
				}
				
				if (y == 0) {
					s.setNorth(squares[size-1][x]);
				} else {
					s.setNorth(squares[y-1][x]);
				}
				
				if (y == size-1) {
					s.setSouth(squares[0][x]);
				} else {
					s.setSouth(squares[y+1][x]);
				}
			}
		}
	}
	
	public void simulate(int rounds) {
		for (int r = 0; r < rounds; r++) {
			getBunnies();
			for (Bunny b: bunnies) {
				b.update();
			}
			
			for (Bunny b: bunnies) {
				Bunny.move(b, squares);
			}
		}
	}
	
	public void getBunnies() {
		bunnies = new ArrayList<>();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (squares[y][x].getAnimal().isBunny()) {
					bunnies.add((Bunny) squares[y][x].getAnimal());
				}
			}
		}
	}
	
	public void display() {
		
	}
}
