package csc365project2;

import java.io.Serializable;
import java.util.ArrayList;

public class BTree implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7979777001329915677L;
	// BTree structure---------------------------------
	protected Node root;
	// -------------------------------------------------
	private int order;

	// -------------------------------------------------

	// Constructor ------------------------------------
	public BTree(int o) {
		order = o;
		root = new Node(o);
	}

	public Station find(String value) {
		Node r = root;
		// Find where element belongs
		for (int x = 0; x <= r.getNumKeys(); x++) {
			// Element is in current Node
			if (r.contains(value))
				return r.getK(r.indexOfKey(value));
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
					return null;
			}
			// Continue scanning if greater than current element
			else if (r.getKey(x).compareTo(value) < 0)
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
					return null;
			}
		}
		return null;
	}

	// Insert Elements into tree-----------------------------------------
	// Insertion method
	public boolean add(String value, Double info) {
		Node r = root;
		// First element in tree
		if (root.isEmpty()) {
			root.addKey(0, value, info);
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
					r.addKey(x, value, info);
					break;
				}
			}
			// Continue scanning if greater than current element
			else if (r.getKey(x).compareTo(value) < 0)
				continue;
			// Return false if element is found
			else if (value.compareTo(r.getKey(x)) == 0)
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
					r.addKey(x, value, info);
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
		Node n = root;
		root = new Node(order);
		root.addKey(middleOne(n));
		root.addLink(0, firstHalf(n));
		root.addLink(1, secondHalf(n));
	}

	// Handles Node overflow by breaking and shifting
	// middle element up
	private void overflow(Node r, int link) {
		// r is parent, n is child
		Node n = r.getLink(link);
		int x = r.addKey(middleOne(n));
		r.removeLinkAtIndex(x);
		r.addLink(x, firstHalf(n));
		r.addLink(x + 1, secondHalf(n));
	}

	// Returns node representing left branch of overflow Node
	public Node firstHalf(Node n) {
		Node a = new Node(order);
		for (int x = 0; x < (((n.getNumKeys() - 1) / 2)); x++)
			a.addKey(x, n.getK(x));
		for (int y = 0; y < (n.getNumLinks() / 2); y++)
			a.addLink(y, n.getLink(y));
		return a;
	}

	// Returns node representing right branch of overflow Node
	public Node secondHalf(Node n) {
		Node a = new Node(order);
		for (int x = (((n.getNumKeys() - 1) / 2) + 1); x < n.getNumKeys(); x++)
			a.addKey((x - (((n.getNumKeys() - 1) / 2) + 1)), n.getK(x));
		for (int y = (((n.getNumLinks() - 1) / 2) + 1); y < n.getNumLinks(); y++)
			a.addLink(y - (((n.getNumLinks() - 1) / 2) + 1), n.getLink(y));
		return a;
	}

	// Returns middle element of overflow Node
	public Station middleOne(Node n) {
		return n.getK((n.getNumKeys() - 1) / 2);
	}

	// ------------------------------------------------------------

	// Delete elements from tree ----------------------------------

	public boolean remove(String value) {
		Node r = root;
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
			else if (r.getKey(x).compareTo(value) < 0)
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
	private void rootDelete(String value) {
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
	private void internalDelete(Node r, int x) {
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
			Station key = r.getK(x);
			// Right Node
			Node rt = r.getLink(x + 1);
			Node l = r.getLink(x);
			// Add key to left child and remove it from parent
			l.addKey(key);
			r.removeKeyAtIndex(x);
			// Add keys and links from right to left
			for (int n = 0; n < rt.getNumKeys(); n++)
				l.addKey(rt.getK(n));
			for (int n = 0; n < rt.getNumLinks(); n++)
				l.addLink(l.getNumLinks(), rt.getLink(n));
			// Get rid of right
			r.removeLinkAtIndex(x + 1);
			// Move down to combined node and remove from there
			r = l;
			if (r.isLeaf())
				remove(key.getKey());
			else
				internalDelete(l, l.indexOfKey(key.getKey()));
		}

	}

	// Input: n = parent node, x = position of value in node
	// Deletes and returns predecessor of element at x
	private Station predecessor(Node n, int x) {
		if (!n.isLeaf()) {
			if (n.getLink(x).isMin())
				unMin(n, x);
			n = n.getLink(x);
			while (!n.isLeaf()) {
				if (n.getLink(n.getNumLinks() - 1).isMin())
					unMin(n, n.getNumLinks() - 1);
				n = n.getLink(n.getNumLinks() - 1);
			}
			Station value = n.getK(n.getNumKeys() - 1);
			n.removeKey(value);
			return value;
		} else
			return null;
	}

	// Input: n = parent node, x = position of value in node
	// Deletes and returns successor of element at x
	private Station successor(Node n, int x) {
		if (!n.isLeaf()) {
			if (n.getLink(x + 1).isMin())
				unMin(n, x + 1);
			n = n.getLink(x + 1);
			while (!n.isLeaf()) {
				if (n.getLink(0).isMin())
					unMin(n, 0);
				n = n.getLink(0);
			}
			Station value = n.getK(0);
			n.removeKey(value);
			return value;
		} else
			return null;
	}

	// Input: r = Parent of non-minimal node, x = pointer to non-minimal node
	// Node link is a minimum node, need to take care of this.
	private void unMin(Node r, int x) {
		// c is child node that is minimum
		Node c = r.getLink(x);
		// Left child is not minimal, rotate to borrow from it
		if ((x > 0) && (!r.getLink(x - 1).isMin())) {
			// l is left child to rotate and borrow from
			Node l = r.getLink(x - 1);
			// Element from parent and link from left child added to c to make
			// it not minimal
			c.addKey(0, r.getK(x - 1));
			if (!l.isLeaf())
				c.addLink(0, l.getLink(l.getNumLinks() - 1));
			// Remove element sent to c
			r.removeKeyAtIndex(x - 1);
			// Replace element with element from l
			r.addKey(l.getK(l.getNumKeys() - 1));
			// Remove key and link used from l
			if (!l.isLeaf())
				l.removeLinkAtIndex(l.getNumLinks() - 1);
			l.removeKeyAtIndex(l.getNumKeys() - 1);
		}
		// Right child is not minimal
		else if ((x < r.getNumLinks() - 1) && (!r.getLink(x + 1).isMin())) {
			// r is right child to rotate and borrow from
			Node rt = r.getLink(x + 1);
			// Element from parent and link from right child added to c to make
			// it not minimal
			c.addKey(c.getNumKeys(), r.getK(x));
			if (!rt.isLeaf())
				c.addLink(c.getNumLinks(), rt.getLink(0));
			// Remove element sent to c
			r.removeKeyAtIndex(x);
			// Replace element with element from l
			r.addKey(rt.getK(0));
			// Remove key and link used from l
			if (!rt.isLeaf())
				rt.removeLinkAtIndex(0);
			rt.removeKeyAtIndex(0);
		}
		// Children are minimal, combine node with left neighbor
		else if (x > 0) {
			// l is left child to rotate and borrow from
			Node l = r.getLink(x - 1);
			// Move all information to left child
			l.addKey(r.getK(x - 1));
			for (int n = 0; n < c.getNumKeys(); n++)
				l.addKey(c.getK(n));
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
			Node rt = r.getLink(x + 1);
			c.addKey(r.getK(x));
			for (int n = 0; n < rt.getNumKeys(); n++)
				c.addKey(rt.getK(n));
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

	public ArrayList<Station> compareInfo(Double i) {
		ArrayList<Station> s = new ArrayList<Station>();
		Node n = root;
		compareInfo(n, i, s);
		return s;
	}

	public void compareInfo(Node n, Double i, ArrayList<Station> s) {
		for (int x = 0; x < n.getNumKeys(); x++) {
			insertInOrder(i, s, n.getK(x));
			if (s.size() > 100)
				s.remove(s.size() - 1);
		}
		if (!n.isLeaf()) {
			for (int y = 0; y < n.getNumLinks(); y++) {
				compareInfo(n.getLink(y), i, s);
			}
		}
	}

	public void insertInOrder(Double info, ArrayList<Station> name, Station node) {
		for (int x = 0; x < name.size(); x++) {
			double a = Math.abs((info - (Double) name.get(x).getInfo()));
			double b = Math.abs((info - (Double) node.getInfo()));
			if (a < b) {
				continue;
			} else {
				name.add(x, node);
				return;
			}
		}
		name.add(name.size(), node);
	}
}