package csc365project2;

import java.io.Serializable;
import java.util.LinkedList;

public class List extends LinkedList<LinkedNode> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5798798163023218760L;

	// Instance Variables
	// ------------------------------------------------------------
	protected LinkedNode firstNode;

	// ------------------------------------------------------------
	protected LinkedNode lastNode;

	// ------------------------------------------------------------
	private LinkedNode currentNode;

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
	public LinkedNode getFirst() {
		return firstNode;
	}

	// ------------------------------------------------------------
	public LinkedNode getLast() {
		return lastNode;
	}

	// ------------------------------------------------------------
	public LinkedNode getCurrent() {
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
	public LinkedNode nextNode() {
		LinkedNode key = currentNode;
		currentNode = currentNode.getNextNode();
		return key;
	}

	// ------------------------------------------------------------
	public LinkedNode getCurrentNode() {
		LinkedNode key = currentNode;
		return key;
	}

	// ------------------------------------------------------------
	public void insert(LinkedNode linkedNode) {
		linkedNode.setNextNode(null);
		if (isEmpty()) {
			firstNode = linkedNode;
		} else {
			lastNode.setNextNode(linkedNode);
		}
		lastNode = linkedNode;
	}

	// ------------------------------------------------------------
	public void insert(String key, String info) {
		insert(new LinkedNode(key, info));
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
	public boolean member(String key) {
		LinkedNode thisNode = firstNode;
		while (thisNode != null) {
			String nodeKey = thisNode.getKey();
			if (nodeKey.equals(key))
				return true;
			thisNode = thisNode.getNextNode();
		}
		return false;
	}

	public String getInfo(String key) {
		LinkedNode thisNode = firstNode;
		while (thisNode != null) {
			String nodeKey = thisNode.getKey();
			if (nodeKey.equals(key))
				return thisNode.getInfo();
			thisNode = thisNode.getNextNode();
		}
		return null;
	}

	// ------------------------------------------------------------
	public boolean delete(String key) {
		LinkedNode thisNode = firstNode;
		LinkedNode prevNode = thisNode;
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
					LinkedNode nextNode = thisNode.getNextNode();
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
		LinkedNode nextNode = firstNode;
		while (nextNode != null) {
			result = result + nextNode.getKey() + "\n";
			nextNode = nextNode.getNextNode();
		}
		result = result + " ]";
		return result;
	}

}
