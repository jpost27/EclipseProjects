package hashTable;

public class LinkedNode<K, I> {
	// DoublyLinkedNode structure
	// ------------------------------------------------------------------
	private LinkedNode<K, I> nextNode;
	// ------------------------------------------------------------------
	protected I info;
	// ------------------------------------------------------------------
	protected K key;

	// ------------------------------------------------------------------

	public LinkedNode(K k, I inf) {
		info = inf;
		key = k;
		nextNode = null;
	}

	public LinkedNode(K k, I inf, LinkedNode<K, I> next) {
		info = inf;
		key = k;
		nextNode = next;
	}

	// Retriever methods---------------------------
	public LinkedNode<K, I> getNextNode() {
		return nextNode;
	}

	public K getKey() {
		return key;
	}

	public I getInfo() {
		return info;
	}

	// --------------------------------------------

	// Mutator methods-----------------------------
	public void setNextNode(LinkedNode<K, I> node) {
		nextNode = node;
	}

	public void setInfo(I inf) {
		info = inf;
	}

	// --------------------------------------------

	public String toString() {
		if (info != null)
			return info.toString();
		return null;
	}
}
