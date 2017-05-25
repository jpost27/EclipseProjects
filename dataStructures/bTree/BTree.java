package bTree;

import java.lang.Comparable;
import java.util.Comparator;
/**
 * BTree data structure
 * @author Josh
 *
 * @param <E>
 * KNOWN BUGS: does not work for odd orders
 */
public class BTree<E extends Comparable<E>> {

	// BTree structure---------------------------------
	protected Node<E> root;
	// -------------------------------------------------
	private int order;
	// -------------------------------------------------
	NaturalComparator<? super E> comp;

	// -------------------------------------------------

	// Constructor ------------------------------------
	/**
	 * Constructs a tree of the given order
	 * @param o - order of the tree
	 */
	public BTree(int o) {
		order = o;
		root = new Node<E>(o);
		comp = new NaturalComparator<E>();
	}
	/**
	 * Returns true if the value is in the tree
	 * @param value
	 * @return boolean
	 */
	public boolean find(E value) {
		Node<E> r = root;
		// Find where element belongs
		for (int x = 0; x <= r.getNumKeys(); x++) {
			// Element is in current Node
			if (r.contains(value))
				return true;
			// Greater than all in Node
			// Branch to right if not leaf, not in tree if leaf
			if (x == r.getNumKeys()) {
				// Node is not leaf, keep searching
				if (!r.isLeaf()) {
					// DONT ENTER MINIMAL NODE
					r = r.getLink(x);
					x = -1;
					continue;
				}
				// Node is leaf and element not found
				else
					return false;
			}
			// Continue scanning if greater than current element
			else if (comp.compare(r.getKey(x), value) < 0)
				continue;
			// Element isn't here ifLeaf, or branch to leaf x !ifLeaf
			// Value is less than current element
			else {
				// Current Node is not a leaf
				if (!r.isLeaf()) {
					r = r.getLink(x);
					x = -1;
					continue;
				} else
					return false;
			}
		}
		return false;
	}

	// Insert Elements into tree-----------------------------------------
	// Insertion method
	/**
	 * Adds a value to the tree.
	 * Returns true if successful (element is not already in the tree).
	 * @param value
	 * @return boolean
	 */
	public boolean add(E value) {
		Node<E> r = root;
		// First element in tree
		if (root.isEmpty()) {
			root.addKey(0, value);
			return true;
		}
		// Find where element belongs
		for (int x = 0; x <= r.getNumKeys(); x++) {
			// Greater than all in Node
			// Branch to right if not leaf, insert right if leaf
			if (x == r.getNumKeys()) {
				if (!r.isLeaf()) {
					if (r.getLink(x).isFull())
						overflow(r, x);
					else
						r = r.getLink(x);
					x = -1;
					continue;
				} else {
					r.addKey(x, value);
					break;
				}
			}
			// Continue scanning if greater than current element
			else if (comp.compare(r.getKey(x), value) < 0)
				continue;
			// Return false if element is found
			else if (comp.compare(value, r.getKey(x)) == 0)
				return false;
			// Element belongs here if leaf or branch to leaf x if not leaf
			else {
				// Current Node is not a leaf
				if (!r.isLeaf()) {
					if (r.getLink(x).isFull())
						overflow(r, x);
					else
						r = r.getLink(x);
					x = -1;
					continue;
				} else {
					r.addKey(x, value);
					break;
				}
			}
		}
		if (root.isFull())
			rootOverflow();
		return true;
	}

	// ---------------------------------------------------------

	// Overflow methods ----------------------------------------
	// Breaks up the root if it overflowed
	private void rootOverflow() {
		Node<E> n = root;
		root = new Node<E>(order);
		root.addKey(middleOne(n));
		root.addLink(0, firstHalf(n));
		root.addLink(1, secondHalf(n));
	}

	// Handles Node overflow by breaking and shifting
	// middle element up
	private void overflow(Node<E> r, int link) {
		// r is parent, n is child
		Node<E> n = r.getLink(link);
		int x = r.addKey(middleOne(n));
		r.removeLinkAtIndex(x);
		r.addLink(x, firstHalf(n));
		r.addLink(x + 1, secondHalf(n));
	}

	// Returns node representing left branch of overflow Node
	public Node<E> firstHalf(Node<E> n) {
		Node<E> a = new Node<E>(order);
		for (int x = 0; x < (((n.getNumKeys() - 1) / 2)); x++)
			a.addKey(x, n.getKey(x));
		for (int y = 0; y < (n.getNumLinks() / 2); y++)
			a.addLink(y, n.getLink(y));
		return a;
	}

	// Returns node representing right branch of overflow Node
	public Node<E> secondHalf(Node<E> n) {
		Node<E> a = new Node<E>(order);
		for (int x = (((n.getNumKeys() - 1) / 2) + 1); x < n.getNumKeys(); x++)
			a.addKey((x - (((n.getNumKeys() - 1) / 2) + 1)), n.getKey(x));
		for (int y = (((n.getNumLinks() - 1) / 2) + 1); y < n.getNumLinks(); y++)
			a.addLink(y - (((n.getNumLinks() - 1) / 2) + 1), n.getLink(y));
		return a;
	}

	// Returns middle element of overflow Node
	public E middleOne(Node<E> n) {
		return n.getKey((n.getNumKeys() - 1) / 2);
	}

	// ------------------------------------------------------------

	// Delete elements from tree ----------------------------------
	/**
	 * Removes an element from the tree.
	 * Returns false if the element is not in the tree.
	 * @param value
	 * @return boolean
	 */
	public boolean remove(E value) {
		Node<E> r = root;
		// Element is in root
		if (r.contains(value)) {
			rootDelete(value);
			return true;
		}
		// Find where element belongs
		for (int x = 0; x <= r.getNumKeys(); x++) {
			// Element is in current Node
			if (r.contains(value)) {
				if (r.isLeaf())
					r.removeKey(value);
				else
					internalDelete(r, r.indexOfKey(value));
				return true;
			}
			// Greater than all in Node
			// Branch to right if not leaf, not in tree if leaf
			if (x == r.getNumKeys()) {
				// Node is not leaf, keep searching
				if (!r.isLeaf()) {
					// DONT ENTER MINIMAL NODE
					if (r.getLink(x).isMin())
						unMin(r, x);
					else
						r = r.getLink(x);
					x = -1;
					continue;
				}
				// Node is leaf and element not found
				else
					return false;
			}
			// Continue scanning if greater than current element
			else if (comp.compare(r.getKey(x), value) < 0)
				continue;
			// Element isn't here ifLeaf, or branch to leaf x !ifLeaf
			// Value is less than current element
			else {
				// Current Node is not a leaf
				if (!r.isLeaf()) {
					// DONT ENTER MINIMAL NODE
					if (r.getLink(x).isMin())
						unMin(r, x);
					else
						r = r.getLink(x);
					x = -1;
					continue;
				} else
					return false;
			}
		}
		return true;
	}

	// Input: E = value to delete in the root
	// Delete an element in the root
	private void rootDelete(E value) {
		int x = root.indexOfKey(value);
		// Root is a leaf, remove key
		if (root.isLeaf())
			root.removeKey(value);
		// Key can be removed from root
		else if (root.getNumKeys() > 1)
			internalDelete(root, x);
		// Root is gone
		else
			internalDelete(root, x);
	}

	// Input: r = Node the element is in, x = position of the element in r
	// Delete an element not in a leaf
	private void internalDelete(Node<E> r, int x) {
		// Left child is not minimal
		if (!r.getLink(x).isMin()) {
			r.removeKeyAtIndex(x);
			r.addKey(predecessor(r, x));
		}
		// Right child is not minimal
		else if (!r.getLink(x + 1).isMin()) {
			r.removeKeyAtIndex(x);
			r.addKey(x, successor(r, x));
		}
		// Both children are minimal
		else {
			// Key = value of key we are deleting
			E key = r.getKey(x);
			// Right Node
			Node<E> rt = r.getLink(x + 1);
			Node<E> l = r.getLink(x);
			// Add key to left child and remove it from parent
			l.addKey(key);
			r.removeKeyAtIndex(x);
			// Add keys and links from right to left
			for (int n = 0; n < rt.getNumKeys(); n++)
				l.addKey(rt.getKey(n));
			for (int n = 0; n < rt.getNumLinks(); n++)
				l.addLink(l.getNumLinks(), rt.getLink(n));
			// Get rid of right
			r.removeLinkAtIndex(x + 1);
			// Move down to combined node and remove from there
			r = l;
			if (r.isLeaf())
				remove(key);
			else
				internalDelete(l, l.indexOfKey(key));
		}

	}

	// Input: n = parent node, x = position of value in node
	// Deletes and returns predecessor of element at x
	private E predecessor(Node<E> n, int x) {
		if (!n.isLeaf()) {
			if (n.getLink(x).isMin())
				unMin(n, x);
			n = n.getLink(x);
			while (!n.isLeaf()) {
				if (n.getLink(n.getNumLinks() - 1).isMin())
					unMin(n, n.getNumLinks() - 1);
				n = n.getLink(n.getNumLinks() - 1);
			}
			E value = n.getKey(n.getNumKeys() - 1);
			n.removeKey(value);
			return value;
		} else
			return null;
	}

	// Input: n = parent node, x = position of value in node
	// Deletes and returns successor of element at x
	private E successor(Node<E> n, int x) {
		if (!n.isLeaf()) {
			if (n.getLink(x + 1).isMin())
				unMin(n, x + 1);
			n = n.getLink(x + 1);
			while (!n.isLeaf()) {
				if (n.getLink(0).isMin())
					unMin(n, 0);
				n = n.getLink(0);
			}
			E value = n.getKey(0);
			n.removeKey(value);
			return value;
		} else
			return null;
	}

	// Input: r = Parent of non-minimal node, x = pointer to non-minimal node
	// Node link is a minimum node, need to take care of this.
	private void unMin(Node<E> r, int x) {
		// c is child node that is minimum
		Node<E> c = r.getLink(x);
		// Left child is not minimal, rotate to borrow from it
		if ((x > 0) && (!r.getLink(x - 1).isMin())) {
			// l is left child to rotate and borrow from
			Node<E> l = r.getLink(x - 1);
			// Element from parent and link from left child added to c to make
			// it not minimal
			c.addKey(0, r.getKey(x - 1));
			if (!l.isLeaf())
				c.addLink(0, l.getLink(l.getNumLinks() - 1));
			// Remove element sent to c
			r.removeKeyAtIndex(x - 1);
			// Replace element with element from l
			r.addKey(l.getKey(l.getNumKeys() - 1));
			// Remove key and link used from l
			if (!l.isLeaf())
				l.removeLinkAtIndex(l.getNumLinks() - 1);
			l.removeKeyAtIndex(l.getNumKeys() - 1);
		}
		// Right child is not minimal
		else if ((x < r.getNumLinks() - 1) && (!r.getLink(x + 1).isMin())) {
			// r is right child to rotate and borrow from
			Node<E> rt = r.getLink(x + 1);
			// Element from parent and link from right child added to c to make
			// it not minimal
			c.addKey(c.getNumKeys(), r.getKey(x));
			if (!rt.isLeaf())
				c.addLink(c.getNumLinks(), rt.getLink(0));
			// Remove element sent to c
			r.removeKeyAtIndex(x);
			// Replace element with element from l
			r.addKey(rt.getKey(0));
			// Remove key and link used from l
			if (!rt.isLeaf())
				rt.removeLinkAtIndex(0);
			rt.removeKeyAtIndex(0);
		}
		// Children are minimal, combine node with left neighbor
		else if (x > 0) {
			// l is left child to rotate and borrow from
			Node<E> l = r.getLink(x - 1);
			// Move all information to left child
			l.addKey(r.getKey(x - 1));
			for (int n = 0; n < c.getNumKeys(); n++)
				l.addKey(c.getKey(n));
			if (!c.isLeaf()) {
				for (int n = 0; n < c.getNumLinks(); n++)
					l.addLink(l.getNumLinks(), c.getLink(n));
			}
			r.removeKeyAtIndex(x - 1);
			r.removeLinkAtIndex(x);
			if ((r.isEmpty()) && (r == root))
				root = l;
		}
		// No left child, right is minimal. Combine with right neighbor
		else {
			// r is right child to rotate and borrow from
			Node<E> rt = r.getLink(x + 1);
			c.addKey(r.getKey(x));
			for (int n = 0; n < rt.getNumKeys(); n++)
				c.addKey(rt.getKey(n));
			if (!rt.isLeaf()) {
				for (int n = 0; n < rt.getNumLinks(); n++)
					c.addLink(c.getNumLinks(), rt.getLink(n));
			}
			r.removeKeyAtIndex(x);
			r.removeLinkAtIndex(x + 1);
			if ((r.isEmpty()) && (r == root))
				root = c;
		}
	}// ----------------------------------------------------------------------------

	// Comparator
	@SuppressWarnings("hiding")
	public class NaturalComparator<E extends Comparable<E>> implements
			Comparator<E> {
		public int compare(E a, E b) {
			return a.compareTo(b);
		}

		public boolean equals(Object obj) {
			return (obj != null) && (obj instanceof NaturalComparator);
		}
	}
}