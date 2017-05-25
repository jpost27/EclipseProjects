package movieGenre;
/**
 * Word object
 * @author Josh
 *
 */
public class Word {
	private String name;
	private int references;

	/**
	 * Word constructor
	 * @param s - the word
	 */
	public Word(String s) {
		name = s;
		references = 1;
	}

	/**
	 * increments the number of references by x
	 * @param x - number to increment by
	 */
	public void addReferences(int x) {
		references = references + x;
	}

	/**
	 * returns the value of the word
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * returns the number of references
	 * @return references
	 */
	public int getReferences() {
		return references;
	}

	/**
	 * increments references
	 */
	public void increment() {
		references++;
		return;
	}
}