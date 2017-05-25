package binarySearchTree;

import java.io.Serializable;
import java.util.Comparator;

//Node object for each element in the tree

public class Node<E extends Comparable<E>> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8111676796191020645L;
	// Node structure
	private Node<E> rootNode;
	// ------------------------------------------------------------------
	private Node<E> leftNode;
	// ------------------------------------------------------------------
	private Node<E> rightNode;
	// ------------------------------------------------------------------
	protected E info;
	// ------------------------------------------------------------------
	private int level;
	// ------------------------------------------------------------------
	NaturalComparator<? super E> comp;

	// ------------------------------------------------------------------
	// Constructor Method
	public Node(E inf, Node<E> root) {
		rootNode = root;
		rightNode = null;
		leftNode = null;
		info = inf;
		if (rootNode == null)
			level = 1;
		else
			level = rootNode.getLevel() + 1;
	}

	// Retriever methods---------------------------
	public Node<E> getRightLeaf() {
		return rightNode;
	}

	public Node<E> getLeftLeaf() {
		return leftNode;
	}

	public Node<E> getRootNode() {
		return rootNode;
	}

	public E getInfo() {
		return info;
	}

	public int getLevel() {
		return level;
	}

	// --------------------------------------------

	// Mutator methods-----------------------------
	public void setRightLeaf(Node<E> node) {
		rightNode = node;
	}

	public void setLeftLeaf(Node<E> node) {
		leftNode = node;
	}

	public void setRootNode(Node<E> node) {
		rootNode = node;
	}

	public void setInfo(E inf) {
		info = inf;
	}

	public void setLevel(int x) {
		level = x;
	}

	public void updateLevel() {
		if (rootNode != null)
			level = rootNode.getLevel() + 1;
		else
			level = 1;
		if (leftNode != null)
			leftNode.updateLevel();
		if (rightNode != null)
			rightNode.updateLevel();
	}

	// --------------------------------------------

	// Comparator
	// ------------------------------------------------------------------
	@SuppressWarnings("hiding")
	public class NaturalComparator<E extends Comparable<E>> implements
			Comparator<E> {
		public int compare(E a, E b) {
			return a.compareTo(b);
		}

		public boolean equals(Object obj) {
			return (obj != null) && (obj instanceof Comparator);
		}
	}
}