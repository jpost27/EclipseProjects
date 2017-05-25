package binarySearchTree;

//My interface for a Binary Search Tree
public interface IBST<E extends Comparable<E>> {
	public boolean isEmpty();

	public boolean contains(E info);

	public boolean add();

	public boolean add(E info);

	public boolean remove(E info);

	public void clear();

	public void reverse();

	public String toString();
}
