package priorityQueue;

import java.util.ArrayList;

/**
 * Data structure for a priority queue
 * @author Josh
 *
 * @param <E>
 */
public class CompleteHeap<E extends Comparable<E>> implements IPQ<E> {

	// Structure of the Heap
	public ArrayList<E> heap;

	// ----------------------------------

	/**
	 * Creates an empty heap
	 */
	public CompleteHeap() {
		heap = new ArrayList<E>();
	}
	/**
	 * Creates a heap with the given element
	 * @param e - first element to add to the heap
	 */
	public CompleteHeap(E e) {
		heap = new ArrayList<E>();
		heap.add(e);
	}

	/**
	 * Returns true if the heap is empty
	 */
	public boolean isEmpty() {
		return (heap.size() == 0);
	}

	/*
	 * SCRATCH WORK:
	 * Node at p
	 * left child is 2p+1
	 * right child is 2p+2
	 * Node at 5 left
	 * child is 11 
	 * right child is 12
	 * Node at 6
	 * left child is 13
	 * right child is 14 parent
	 * parent = ((child+1)/2)-1
	 */
	
	/**
	 * prints the elements in the heap
	 */
	public void print() {
		for (int x = 0; x < heap.size(); x++) {
			System.out.println(heap.get(x));
		}
	}

	/**
	 * Adds an element to the heap
	 * @param e - element to add
	 */
	public void add(E e) {
		int c = heap.size();
		int p = ((c + 1) / 2) - 1;
		heap.add(heap.size(), e);
		while ((p >= 0) && (compare(heap.get(p), heap.get(c)) > 0)) {
			E t = heap.get(p);
			heap.set(p, heap.get(c));
			heap.set(c, t);
			c = p;
			p = ((c + 1) / 2) - 1;
		}
	}

	/**
	 * Removes and returns the next element in the queue
	 */
	public E remove() {
		if (heap.isEmpty())
			return null;
		E pop = heap.get(0);
		if (heap.size() == 1)
			heap.remove(0);
		else {
			int p = 0, l = (2 * p) + 1, r = (2 * p) + 2;
			heap.set(0, heap.remove(heap.size() - 1));
			while (l < heap.size()) {
				int min = l;
				if (r < heap.size() && compare(heap.get(l), heap.get(r)) > 0)
					min = r;
				if (compare(heap.get(p), heap.get(min)) > 0) {
					E t = heap.get(p);
					heap.set(p, heap.get(min));
					heap.set(min, t);
					p = min;
					l = (2 * p) + 1;
					r = (2 * p) + 2;
				} else
					break;
			}
		}
		return pop;
	}

	// Comparator -----------------------------------------------------------
	public int compare(E a, E b) {
		return b.compareTo(a);
	}
}
