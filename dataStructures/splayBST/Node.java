package splayBST;

/**
 * Node class for SplayBST
 * 
 * @author: jpost
 * @param: E
 */
public class Node<E extends Comparable<E>> {
	// Value of the node
	private E value;
	// Left and right are children of the node
	public Node<E> left;
	public Node<E> right;

	/**
	 * Constructs a node. Assigns a value and creates pointers to possible
	 * future children.
	 * 
	 * @param x - value of the node
	 */
	public Node(E x) {
		left = null;
		right = null;
		value = x;
	}

	/**
	 * Returns the value of the node
	 * 
	 * @return Value of the node
	 */
	public E getValue() {
		return value;
	}

	/**
	 * compares the value of this node to another value.
	 * 
	 * @param x
	 * @return A negative integer, zero, or a positive integer as this object is
	 *         less than, equal to, or greater than the specified object.
	 */
	public int compare(E x) {
		return this.value.compareTo(x);
	}
}