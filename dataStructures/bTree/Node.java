package bTree;

import java.util.ArrayList;
import java.util.Comparator;
/**
 * Node Class for BTree data structure
 * @author Josh
 *
 * @param <E>
 */
public class Node<E extends Comparable<E>> {
	// Node Structure----------------------------------------------------
	protected ArrayList<E> keys;
	// ------------------------------------------------------------------
	protected ArrayList<Node<E>> links;
	// ------------------------------------------------------------------
	private int order, numLinks;
	// ------------------------------------------------------------------
	NaturalComparator<? super E> comp;

	// ------------------------------------------------------------------

	// Constructor ----------------------
	/**
	 * Constructs a Node of order n
	 * @param n - order of tree
	 */
	public Node(int n) {
		keys = new ArrayList<E>();
		links = new ArrayList<Node<E>>();
		numLinks = 0;
		order = n;
		comp = new NaturalComparator<E>();
	}

	// Get Methods ----------------------
	public int getNumLinks() {
		return numLinks;
	}

	public int getNumKeys() {
		return keys.size();
	}

	public E getKey(int x) {
		return keys.get(x);
	}

	public Node<E> getLink(int x) {
		return links.get(x);
	}

	// ----------------------------------

	// Node Info ------------------------
	public boolean isLeaf() {
		return links.size() == 0;
	}

	public boolean isFull() {
		return keys.size() >= order - 1;
	}

	public boolean contains(E x) {
		return keys.contains(x);
	}

	public boolean isEmpty() {
		return keys.size() == 0;
	}

	public boolean isMin() {
		return keys.size() <= ((order / 2) - 1);
	}

	public int indexOfKey(E key) {
		return keys.indexOf(key);
	}

	// ---------------------------------

	// Add/Remove Keys------------------
	public boolean addKey(int x, E key) {
		keys.add(x, key);
		return true;
	}

	public int addKey(E key) {
		for (int x = 0; x < keys.size(); x++) {
			if (comp.compare(keys.get(x), key) > 0) {
				keys.add(x, key);
				return x;
			}
		}
		keys.add(keys.size(), key);
		return keys.size() - 1;
	}

	public boolean removeKey(E key) {
		return keys.remove(key);
	}

	public boolean removeKeyAtIndex(int x) {
		keys.remove(keys.get(x));
		return true;
	}

	// -----------------------------------

	// Add/Remove Links ------------------
	public void addLink(int x, Node<E> link) {
		links.add(x, link);
		numLinks++;
	}

	public int indexOfLink(Node<E> link) {
		return links.indexOf(link);
	}

	public boolean removeLink(Node<E> link) {
		links.remove(link);
		numLinks--;
		return true;
	}

	public boolean removeLinkAtIndex(int x) {
		links.remove(links.get(x));
		numLinks--;
		return true;
	}

	// -----------------------------------------

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