/**
 * An Animal is a single animal in the simulation. Planned capabilities include
 * moving, eating, reproducing, and dying.
 * 
 * @author Conor McAvoy
 *
 */
public abstract class Animal {
	/**
	 * @return Whether the animal is a bunny or not.
	 */
	public abstract boolean isBunny();
	
	/**
	 * @return Whether the animal is a fox or not.
	 */
	public abstract boolean isFox();
	
	public abstract boolean isUpdated();

	public abstract void reset();
}
