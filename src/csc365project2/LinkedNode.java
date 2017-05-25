package csc365project2;

import java.io.Serializable;

public class LinkedNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8667538873867266195L;
	// DoublyLinkedNode structure
	// ------------------------------------------------------------------
	private LinkedNode nextNode;
	// ------------------------------------------------------------------
	protected String info;
	// ------------------------------------------------------------------
	protected String key;

	// ------------------------------------------------------------------

	public LinkedNode(String k, String inf) {
		info = inf;
		key = k;
		nextNode = null;
	}

	public LinkedNode(String k, String inf, LinkedNode next) {
		info = inf;
		key = k;
		nextNode = next;
	}

	// Retriever methods---------------------------
	public LinkedNode getNextNode() {
		return nextNode;
	}

	public String getKey() {
		return key;
	}

	public String getInfo() {
		return info;
	}

	// --------------------------------------------

	// Mutator methods-----------------------------
	public void setNextNode(LinkedNode node) {
		nextNode = node;
	}

	public void setInfo(String inf) {
		info = inf;
	}

	// --------------------------------------------

	public String toString() {
		if (info != null)
			return info.toString();
		return null;
	}
}
