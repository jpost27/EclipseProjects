package queue;
/**
 * Interface for Queue
 * @author Josh
 *
 * @param <E>
 */
public interface IQueue<E> {
	public boolean isEmpty();
	public void insert(E item);
	public E pop();
	public E peek();
}