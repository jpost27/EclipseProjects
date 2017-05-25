package stack;

/**
 * Interface defines a generic stack
 * 
 * @author jpost
 * @param <E>
 */
public interface IStack<E> {
	public boolean isEmpty();

	public void push(E item);

	public E pop();

	public E top();
}