package linkedlist;
/**
 * Interface for Lists
 * @author Josh
 *
 * @param <E>
 */
public interface IList<E> {
	public boolean isEmpty();
	public int length();
	public E elementAt(int loc);
	public void insertAt(E info, int loc);
	public void deleteNth(int loc);
	public boolean member(E info);
	public void deleteFirstOccurrence(E info);
	public void deleteAllOccurrences(E info);
	public String toString();
	public void resetCurrentElement();
	public E nextElement();
	public boolean hasMoreElements();
}
