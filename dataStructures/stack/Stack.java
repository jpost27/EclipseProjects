package stack;

import java.util.ArrayList;

/**
 * Stack Data Structure
 * 
 * @author jpost
 * @param <E>
 */
public class Stack<E> implements IStack<E> {
	// --------------------------------------------------
	// Body of the stack
	private ArrayList<E> stack;

	// --------------------------------------------------
	/**
	 * Constructor
	 */
	public Stack() {
		stack = new ArrayList<E>();
	}

	// --------------------------------------------------
	/**
	 * Returns true if the stack is empty
	 */
	public boolean isEmpty() {
		return (stack.size() == 0);
	}

	// --------------------------------------------------
	/**
	 * Push an item into the stack
	 */
	public void push(E item) {
		stack.add(item);
	}

	// --------------------------------------------------
	/**
	 * Returns and deletes the first element. Return null if stack is empty.
	 */
	public E pop() {
		if (isEmpty())
			return null;
		else {
			E e = stack.get(stack.size() - 1);
			stack.remove(stack.size() - 1);
			return e;
		}
	}

	// --------------------------------------------------
	/**
	 * Peeks at the top element without removal. Return null if stack is empty.
	 */
	public E top() {
		if (isEmpty()) {
			return null;
		} else {
			E e = stack.get(stack.size() - 1);
			return e;
		}
	}
}