package hashTable;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class List<K, I> extends LinkedList<LinkedNode<K, I>> {

	// Instance Variables
	// ------------------------------------------------------------
	protected LinkedNode<K, I> firstNode;

	// ------------------------------------------------------------
	protected LinkedNode<K, I> lastNode;

	// ------------------------------------------------------------
	private LinkedNode<K, I> currentNode;

	// ------------------------------------------------------------
	private int length;

	// ------------------------------------------------------------
	// Constructor
	public List() {
		length = 0;
		firstNode = null;
		currentNode = null;
		lastNode = null;
	}

	// ------------------------------------------------------------
	public boolean isEmpty() {
		return (firstNode == null && lastNode == null);
	}

	// ------------------------------------------------------------
	public LinkedNode<K, I> getFirst() {
		return firstNode;
	}

	// ------------------------------------------------------------
	public LinkedNode<K, I> getLast() {
		return lastNode;
	}

	// ------------------------------------------------------------
	public LinkedNode<K, I> getCurrent() {
		return currentNode;
	}

	// ------------------------------------------------------------
	public int getLength() {
		return length;
	}

	// ------------------------------------------------------------
	/**
	 * Resets the currentNode cursor to the beginning of the list. This should
	 * be done before we scan the list for any reason
	 */
	public void resetCurrentElement() {
		currentNode = firstNode;
	}

	// ------------------------------------------------------------
	public boolean hasMoreNodes() {

		return (currentNode != null);
	}

	// ------------------------------------------------------------
	public LinkedNode<K, I> nextNode() {
		LinkedNode<K, I> key = currentNode;
		currentNode = currentNode.getNextNode();
		return key;
	}

	// ------------------------------------------------------------
	public LinkedNode<K, I> getCurrentNode() {
		LinkedNode<K, I> key = currentNode;
		return key;
	}

	// ------------------------------------------------------------
	public void insert(LinkedNode<K, I> linkedNode) {
		linkedNode.setNextNode(null);
		if (isEmpty()) {
			firstNode = linkedNode;
		} else {
			lastNode.setNextNode(linkedNode);
		}
		lastNode = linkedNode;
	}

	// ------------------------------------------------------------
	public void insert(K key, I info) {
		insert(new LinkedNode<K, I>(key, info));
	}

	// ------------------------------------------------------------
	/**
	 * Delete the first element of the list
	 */
	public void deleteFirst() {
		// Can't delete from an empty list
		if (isEmpty()) {
			System.out.println("Error in List.deleteFirst() "
					+ "Attempted delete from Empty List ");
		}

		// Special case: list has only one element
		else if (length == 1) {
			firstNode = null;
			lastNode = null;
			length = 0;
		}
		// All other lists: Only first pointer changes. Last doesn't
		else {
			firstNode = firstNode.getNextNode();
			length--;
		}
	}

	// ------------------------------------------------------------
	public boolean member(K key) {
		LinkedNode<K, I> thisNode = firstNode;
		while (thisNode != null) {
			K nodeKey = thisNode.getKey();
			if (nodeKey.equals(key))
				return true;
			thisNode = thisNode.getNextNode();
		}
		return false;
	}

	public I getInfo(K key) {
		LinkedNode<K, I> thisNode = firstNode;
		while (thisNode != null) {
			K nodeKey = thisNode.getKey();
			if (nodeKey.equals(key))
				return thisNode.getInfo();
			thisNode = thisNode.getNextNode();
		}
		return null;
	}

	// ------------------------------------------------------------
	public boolean delete(K key) {
		LinkedNode<K, I> thisNode = firstNode;
		LinkedNode<K, I> prevNode = thisNode;
		if (thisNode.getKey().equals(key)) {
			deleteFirst();
			return true;
		}
		while (thisNode.getNextNode() != null) {
			if (thisNode.getNextNode().getKey().equals(key)) {
				if (thisNode.getNextNode().getNextNode() == null) {
					thisNode.setNextNode(null);
				} else {
					// Get pointers to its next node
					LinkedNode<K, I> nextNode = thisNode.getNextNode();
					// Adjust pointers
					prevNode.setNextNode(nextNode);
					return true;
				}
			}

			// Increment the pointer to next node
			prevNode = thisNode;
			thisNode = thisNode.getNextNode();
		}
		System.out.println("Error in List.delete() " + "Token was not found");
		return false;
	}

	// ------------------------------------------------------------
	/**
	 * Convert list contents into a printable String
	 */
	public String toString() {
		String result = "[ ";
		LinkedNode<K, I> nextNode = firstNode;
		while (nextNode != null) {
			result = result + nextNode.getKey() + "\n";
			nextNode = nextNode.getNextNode();
		}
		result = result + " ]";
		return result;
	}

}
