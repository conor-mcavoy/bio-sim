import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

/**
 * A Field is a grid that contains animals and acts as the environment of the simulation.
 * 
 * @author Conor McAvoy
 *
 */
public class Field {
	private Square[][] squares;
	private int size;

	private HashSet<Bunny> bunnies;


	public Field(int size) {
		// default initial population of each animal is size
		this(size, size, size);
	}

	public Field(int size, int numOfBunnies, int numOfFoxes) {
		this.size = size;

		initializeSquares();
		initializeDisplay();

		ArrayList<Integer> positions = new ArrayList<>();
		for (int i = 0; i < size*size; i++) {
			positions.add(i);
		}
		Collections.shuffle(positions);

		bunnies =  new HashSet<>();
		for (int i = 0; i < numOfBunnies; i++) {
			int index = positions.get(i);
			int x = index % size;
			int y = index / size;

			Bunny b = new Bunny(x, y);
			b.setPosition(squares[y][x]);

			bunnies.add(b);

			squares[y][x].setAnimal(b);
		}

		simulate(10);
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
					s.setEast(squares[y][x+1]);
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

	private void initializeDisplay() {
		Display.show();

		Display.setXScale(-0.5, size-0.5);
		Display.setYScale(-0.5, size-0.5);
		Display.clear();
		
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Display.filledSquare(x, y, .45);
			}
		}
		
	}

	public void simulate(int rounds) {
		display();
		for (int r = 0; r < rounds; r++) {
			// getBunnies();
			// ^may not be necessary as long as bunnies are added and removed
			// to and from the HashSet bunnies properly
			
			for (Bunny b: bunnies) {
				b.update();
			}

			for (Bunny b: bunnies) {
				Bunny.move(b, squares);
			}
			display();
		}
	}

	public void getBunnies() {
		bunnies = new HashSet<>();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (squares[y][x].getAnimal().isBunny()) {
					bunnies.add((Bunny) squares[y][x].getAnimal());
				}
			}
		}
	}

	public void display() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (squares[y][x].containsAnimal()) {
					if (squares[y][x].getAnimal().isBunny()) {
						Display.setPenColor(Display.BLUE);
					}
				} else {
					Display.setPenColor(Display.GRAY);
				}
				Display.filledSquare(x, size-y-1, .45);
			}
		}
		
		Display.show(100);
	}

	public static void main(String[] args) {
		Field f = new Field(10);

	}
}
