package splayBST;

/**
 * Splay Binary Search Tree Data Structure
 * 
 * @author jpost
 * @param <E>
 */
public class SplayBST<E extends Comparable<E>> {
	// Root node and number of nodes is stored
	Node<E> root;
	int count;

	/**
	 * Constructor for an empty tree.
	 */
	public SplayBST() {
		root = null;
		count = 0;
	}

	/**
	 * Constructor for a tree with the first element assigned.
	 * @param r - the root element of the tree.
	 */
	public SplayBST(E r) {
		root = new Node<E>(r);
		count = 0;
	}

	/**
	 * Adds an element to the tree. Assumes no duplicates.
	 * @param x - element to add
	 */
	public void add(E x) {
		root = splayInsert(root, x);
		count++;
	}

	/**
	 * Moves node containing the closest value to x to the root and returns the
	 * value.
	 * @param x - requested value
	 * @return E - closest value
	 */
	public E search(E x) {
		root = splaySearch(root, x);
		return root.getValue();
	}

	/*
	 * There are two cases when both links in a double rotation are oriented in
	 * the same direction. Unlike the root-insertion method, splay insertion
	 * performs the higher rotation first.
	 * 
	 * 4 cases for two levels down in the search path from the root: left-left:
	 * rotate right at root twice left-right: rotate left at left child; rotate
	 * right at root right-right: rotate left at root twice right-left: rotate
	 * right at right child, rotate left at root
	 * 
	 * There are two cases when both links in a double rotation are oriented in
	 * the same direction. Unlike the root-insertion method, splay insertion
	 * performs the higher rotation first. r = root, x = new node info
	 */
	/**
	 * Shifts the tree around to insert the requested element in order
	 * @param r - root node
	 * @param x - value to be inserted
	 * @return Node - the new root node
	 */
	private Node<E> splayInsert(Node<E> r, E x) {
		// empty tree. create root
		if (r == null)
			return new Node<E>(x);
		// x < r value, go to left
		if (compare(x, r.getValue()) < 0) {

			// no node on left, insert here
			if (r.left == null) {
				r.left = new Node<E>(x);
				return rotateRight(r);
			}

			// left, then left again
			if (compare(x, r.left.getValue()) < 0) {
				r.left.left = splayInsert(r.left.left, x);
				r = rotateRight(r);
			}
			// left then right
			else {
				r.left.right = splayInsert(r.left.right, x);
				r.left = rotateLeft(r.left);
			}
			return rotateRight(r);
		}
		// x > r value, go to right
		else { // compare(x,r.value)>0
				// no node on right, insert here
			if (r.right == null) {
				r.right = new Node<E>(x);
				return rotateLeft(r);
			}
			// right then left
			if (compare(x, r.right.getValue()) < 0) {
				r.right.left = splayInsert(r.right.left, x);
				r.right = rotateRight(r.right);
			}
			// right then right again
			else {
				r.right.right = splayInsert(r.right.right, x);
				r.right = rotateLeft(r.right);
			}
			return rotateLeft(r);
		}
	}

	/**
	 * Shift the tree to find the desired value.
	 * @param r - current root node
	 * @param x - value to search for
	 * @return Node - the node closest to the desired value
	 */
	private Node<E> splaySearch(Node<E> r, E x) {
		if (r == null)
			;
		// x > r value, go to right
		else if (compare(x, r.getValue()) > 0) {
			// no node on right, insert here
			if (r.right == null)
				return r;
			// right then left
			if (compare(x, r.right.getValue()) < 0) {
				r.right.left = splaySearch(r.right.left, x);
				r.right = rotateRight(r.right);
			}
			// right then right again
			else if (compare(x, r.right.getValue()) > 0) {
				r.right.right = splaySearch(r.right.right, x);
				if (r.right.right != null)
					r.right = rotateLeft(r.right);
			}
			return rotateLeft(r);
		}
		// x < r value, go to left
		else if (compare(x, r.getValue()) < 0) {
			// no node on left, insert here
			if (r.left == null)
				return r;

			// left, then left again
			if (compare(x, r.left.getValue()) < 0) {
				r.left.left = splaySearch(r.left.left, x);
				r = rotateRight(r);
			}
			// left then right
			else if (compare(x, r.left.getValue()) > 0) {
				r.left.right = splaySearch(r.left.right, x);
				if (r.left.right != null)
					r.left = rotateLeft(r.left);
			}
			return rotateRight(r);
		} else
			return r;
		return r;
	}

	private Node<E> rotateRight(Node<E> a) {
		Node<E> b = a.left;
		a.left = b.right;
		b.right = a;
		return b;
	}

	private Node<E> rotateLeft(Node<E> a) {
		Node<E> c = a.right;
		a.right = c.left;
		c.left = a;
		return c;
	}

	/**
	 * Displays the contents of the tree
	 */
	public void displayMe() {
		displayMe(root);
	}
	public void displayMe(Node<E> n) {
		if (n != null)
			displayMe(n.left);
			System.out.println(n.getValue());
			displayMe(n.right);
	}

	public int compare(E a, E b) {
		return a.compareTo(b);
	}
}