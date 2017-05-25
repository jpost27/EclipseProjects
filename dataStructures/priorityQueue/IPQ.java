package priorityQueue;
/**
 * Priority queue interface
 * @author Josh
 *
 * @param <E>
 */
public interface IPQ<E> {
	public boolean isEmpty();
	public void add(E e);
	public E remove();
}
