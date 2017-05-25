package binarySearchTree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.lang.Comparable;

/**
 * Binary Search Tree data structure
 * @author Josh
 *
 * @param <E>
 */

public class BST<E extends Comparable<E>> implements Comparator<E>, IBST<E>,
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1779157132881793543L;
	// Instance Variables
	// ------------------------------------------------------------
	private Node<E> rootNode;
	// ------------------------------------------------------------
	private Node<E> currentNode;
	// ------------------------------------------------------------
	private int size;

	// ------------------------------------------------------------
	/**
	 * Creates a new empty BST
	 */
	public BST() {
		rootNode = null;
		currentNode = null;
		size = 0;
	}
	/**
	 * Creates a new BST with one element
	 * @param info - first element
	 */
	public BST(E info) {
		rootNode = new Node<E>(info, null);
		currentNode = rootNode;
		size = 1;
	}

	// -----------------------------------------------------------
	/**
	 * Returns true if the tree is empty
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	// ------------------------------------------------------------
	/**
	 * Return method
	 * @return current Node
	 */
	public Node<E> getCurrent() {
		return currentNode;
	}
	/**
	 * Return method
	 * @return root Node
	 */
	public Node<E> getRoot() {
		return rootNode;
	}
	/**
	 * Return method
	 * @return size of the tree
	 */
	public int getSize() {
		return size;
	}

	// _____________________________________________________________________________________________________________
	// add methods, first is inputting information and the second generates
	// random information to add
	/**
	 * Adds a given element to the tree
	 * @param info - element to be added to the tree
	 */
	public boolean add(E info) {
		if (isEmpty()) {
			rootNode = new Node<E>(info, null);
			currentNode = rootNode;
			size++;
			return true;
		} else if (contains(info))
			return false;
		// All other trees
		else {
			currentNode = rootNode;
			while (currentNode != null) {
				// info provided was found in the tree
				if (currentNode.getInfo() == info)
					return false;
				// info is greater than node
				else if (compare(currentNode.getInfo(), info) < 0) {
					if (currentNode.getRightLeaf() != null)
						currentNode = currentNode.getRightLeaf();
					else {
						currentNode
								.setRightLeaf(new Node<E>(info, currentNode));
						size++;
						return true;
					}
				}
				// info is less than node
				else {
					if (currentNode.getLeftLeaf() != null)
						currentNode = currentNode.getLeftLeaf();
					else {
						currentNode.setLeftLeaf(new Node<E>(info, currentNode));
						size++;
						return true;
					}
				}
			}
		}
		size++;
		return true;
	}
	/**
	 * Adds a random number from 1-100 to the tree
	 * BUG: can cause an infinite loop if all numbers 1-100 are in the tree
	 */
	@SuppressWarnings("unchecked")
	public boolean add() {
		Random r = new Random();
		Integer info = r.nextInt(100);
		// Make sure number generated is not in the tree already
		while (contains((E) info)) {
			info = r.nextInt(100);
		}
		add((E) info);
		return true;
	}

	// _____________________________________________________________________________________________________________
	/**
	 * Removes a given element from the tree
	 * @param info - element to remove
	 */
	public boolean remove(E info) {
		Node<E> keyNode = null;
		// Can't delete from an empty tree
		if (isEmpty() || !contains(info)) {
			System.out.println("Error, element not in tree");
			return false;
		}

		// Special case, tree has only one element so it is emptied
		else if (size == 1) {
			size--;
			rootNode = null;
			currentNode = null;
			return true;
		}
		// All other trees
		else {
			currentNode = rootNode;
			while (currentNode != null) {
				// info provided was found in the tree
				if (currentNode.getInfo() == info) {
					// No left child, only right
					if ((currentNode.getLeftLeaf() == null)
							&& (currentNode.getRightLeaf() != null)
							&& (currentNode.getRootNode() != null)) {
						if (currentNode.getRootNode().getLeftLeaf() == currentNode)
							currentNode.getRootNode().setLeftLeaf(
									currentNode.getRightLeaf());
						else
							currentNode.getRootNode().setRightLeaf(
									currentNode.getRightLeaf());
						currentNode.getRightLeaf().setRootNode(
								currentNode.getRootNode());
					}
					// No right child, only left
					else if ((currentNode.getLeftLeaf() != null)
							&& (currentNode.getRightLeaf() == null)
							&& (currentNode.getRootNode() != null)) {
						if (currentNode.getRootNode().getLeftLeaf() == currentNode)
							currentNode.getRootNode().setLeftLeaf(
									currentNode.getLeftLeaf());
						else
							currentNode.getRootNode().setRightLeaf(
									currentNode.getLeftLeaf());
						currentNode.getLeftLeaf().setRootNode(
								currentNode.getRootNode());
					}
					// Leaf, delete it
					else if ((currentNode.getLeftLeaf() == null)
							&& (currentNode.getRightLeaf() == null)
							&& (currentNode.getRootNode() != null)) {
						if (currentNode.getRootNode().getLeftLeaf() == currentNode)
							currentNode.getRootNode().setLeftLeaf(null);
						else
							currentNode.getRootNode().setRightLeaf(null);
					}
					// removing root branching right
					else if ((currentNode == rootNode)
							&& (currentNode.getLeftLeaf() == null)) {
						rootNode = currentNode.getRightLeaf();
						rootNode.setRootNode(null);
					}
					// Has to be replaced with predecessor
					else {
						keyNode = currentNode;
						// node has leaf predecessor
						currentNode = currentNode.getLeftLeaf();
						while (true) {
							if (currentNode.getRightLeaf() == null)
								break;
							else
								currentNode = currentNode.getRightLeaf();
						}
						// current node is now predecessor
						size++;
						remove(currentNode.getInfo());
						keyNode.setInfo(currentNode.getInfo());
					}
					// rootNode.setLevel(1);
					rootNode.updateLevel();
					size--;
					return true;
				}
				// info is greater than node
				else if (compare(currentNode.getInfo(), info) < 0)
					currentNode = currentNode.getRightLeaf();
				// info is less than node
				else
					currentNode = currentNode.getLeftLeaf();

			}
		}
		return false;
	}

	// _________________________________________________________________________________________________________________
	/**
	 *  Checks if the information is in the tree
	 */
	public boolean contains(E info) {
		if (isEmpty())
			return false;
		else {
			currentNode = rootNode;
			while (currentNode != null) {
				// info provided was found in the tree
				if (compare(currentNode.getInfo(), info) == 0)
					return true;
				// info is greater than node
				else if (compare(currentNode.getInfo(), info) < 0)
					currentNode = currentNode.getRightLeaf();
				// info is less than node
				else
					currentNode = currentNode.getLeftLeaf();
			}
		}
		// info was not found
		return false;
	}

	/**
	 *  recursively clears the tree
	 */
	public void clear() {
		clear(rootNode);
		rootNode = null;
		currentNode = null;
	}

	private void clear(Node<E> node) {
		if (node == null)
			;
		else {
			clear(node.getLeftLeaf());
			clear(node.getRightLeaf());
			node = null;
		}
	}

	/**
	 *  recursively reverses the tree
	 */
	public void reverse() {
		reverse(rootNode);
	}

	Node<E> right = new Node<E>(null, null);

	private void reverse(Node<E> node) {
		if (node == null)
			;
		else {
			reverse(node.getLeftLeaf());
			reverse(node.getRightLeaf());
			right = node.getLeftLeaf();
			node.setLeftLeaf(node.getRightLeaf());
			node.setRightLeaf(right);
		}
	}

	/*
	 *  Recursively creates and returns an array list of the tree in order
	 */
	public ArrayList<Node<E>> a;
	public ArrayList<Node<E>> inOrder() {
		a = new ArrayList<Node<E>>();
		inOrder(rootNode);
		return a;
	}

	public void inOrder(Node<E> node) {
		if (node != null) {
			inOrder(node.getLeftLeaf());
			a.add(node);
			inOrder(node.getRightLeaf());
		}
	}

	@Override
	public int compare(E a, E b) {
		return a.compareTo(b);
	}
}