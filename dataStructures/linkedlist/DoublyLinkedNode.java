package linkedlist;

public class DoublyLinkedNode<E> {
	// DoublyLinkedNode structure
	// ------------------------------------------------------------------
	private DoublyLinkedNode<E> nextNode;

	// ------------------------------------------------------------------
	private DoublyLinkedNode<E> prevNode;

	// ------------------------------------------------------------------
	protected E info;

	/**
	 * Constructs a node with only info
	 * @param inf - info
	 */
	public DoublyLinkedNode(E inf) {
		prevNode = null;
		info = inf;
		nextNode = null;
	}

	/**
	 * Constructs a DoublyLinkeNode with both links
	 * @param inf - information
	 * @param prev - previous Node
	 * @param next - next Node
	 */
	public DoublyLinkedNode(DoublyLinkedNode<E> prev, E inf,
			DoublyLinkedNode<E> next) {
		info = inf;
		prevNode = prev;
		nextNode = next;
	}

	// Retriever methods---------------------------
	/**
	 * Return method
	 * @return Next Node
	 */
	public DoublyLinkedNode<E> getNextNode() {
		return nextNode;
	}

	/**
	 * Return method
	 * @return Previous Node
	 */
	public DoublyLinkedNode<E> getPrevNode() {
		return prevNode;
	}

	/**
	 * Return method
	 * @return Node info
	 */
	public E getInfo() {
		return info;
	}

	// --------------------------------------------

	// Mutator methods-----------------------------
	/**
	 * Sets next Node
	 * @param node - next Node
	 */
	public void setNextNode(DoublyLinkedNode<E> node) {
		nextNode = node;
	}
	/**
	 * Sets prevNode
	 * @param node - previous Node
	 */
	public void setPrevNode(DoublyLinkedNode<E> node) {
		prevNode = node;
	}
	/**
	 * Sets info
	 * @param inf - info
	 */
	public void setInfo(E inf) {
		info = inf;
	}

	// --------------------------------------------
	/**
	 * Returns Node info as a String
	 */
	public String toString() {
		return info.toString();
	}
}
