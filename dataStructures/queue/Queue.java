package queue;
import java.util.*;

/**
 * Queue data structure
 * @author Josh
 *
 * @param <E>
 */
public class Queue<E> implements IQueue<E> {
	// Queue is stored in an ArrayList
	ArrayList<E> body;

	/**
	 * Constructor for the queue
	 */
	public Queue() {
		body = new ArrayList<E>();
	}

	/**
	 * Returns true is the queue is empty
	 */
	public boolean isEmpty() {
		return (body.size() == 0);
	}

	/**
	 * Adds the item to the end of the queue
	 * @param item - the item to insert
	 */
	public void insert(E item) {
		body.add(item);
	}

	/**
	 * Removes and returns the next of the queue
	 * @return E - next item in queue
	 */
	public E pop() {
		if (isEmpty())
			return null;
		E item = body.get(0);
		body.remove(0);
		return item;
	}

	/**
	 * Returns next item in queue
	 * @return E - next item in queue
	 */
	public E peek() {
		if (isEmpty())
			return null;
		return body.get(0);
	}

	/**
	 * Prints the queue as a string
	 */
	public void printQueue() {
		for (int i = 0; i < body.size(); i++) {
			System.out.print(body.get(i) + "    ");
		}
		System.out.println();
	}
}