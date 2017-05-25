package hashTable;

import java.util.ArrayList;

public class HashTable<K, I> {
	// ------------------------------------------------------------------
	protected List<K, I>[] data;
	protected LinkedNode<K, I> currentNode;
	protected int currentList;
	protected int size;
	protected int buckets = 10;

	// ------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public HashTable() {
		size = 0;
		data = new List[buckets];
		currentList = 0;
		currentNode = null;
	}

	@SuppressWarnings("unchecked")
	public HashTable(int numElements) {
		buckets = (numElements / 3) * 4;
		data = new List[buckets];
		currentList = 0;
		currentNode = null;
	}

	public boolean insert(K key, I info) {
		if (data[hash(key)] == null)
			data[hash(key)] = new List<K, I>();
		if (data[hash(key)].member(key) == false) {
			data[hash(key)].insert(key, info);
			size++;
			checkSize();
			return true;
		}
		return false;
	}

	public void checkSize() {
		if (size > buckets) {
			HashTable<K, I> n = new HashTable<K, I>(size);
			for (int x = 0; x < data.length; x++) {
				if (data[x] != null) {
					data[x].resetCurrentElement();
					while (data[x].hasMoreNodes()) {
						LinkedNode<K, I> t = data[x].nextNode();
						n.insert(t.getKey(), t.getInfo());
					}
				}
			}
			buckets = (size / 3) * 4;
			data = n.getList();
		}
	}

	public List<K, I>[] getList() {
		return data;

	}

	public ArrayList<LinkedNode<K, I>> compareInfo(Double i) {
		ArrayList<LinkedNode<K, I>> name = new ArrayList<LinkedNode<K, I>>();
		for (int x = 0; x < data.length; x++) {
			if (data[x] != null) {
				data[x].resetCurrentElement();
				while (data[x].hasMoreNodes()) {
					LinkedNode<K, I> t = data[x].nextNode();
					insertInOrder(i, name, t);
					if (name.size() > 100)
						name.remove(name.size() - 1);
				}
			}
		}
		return name;
	}

	public void insertInOrder(Double info, ArrayList<LinkedNode<K, I>> name,
			LinkedNode<K, I> node) {
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

	public void printFirst() {
		System.out.println(data[0].toString());
	}

	public I getInfo(K key) {
		return data[hash(key)].getInfo(key);
	}

	public boolean delete(K key) {
		boolean x = data[hash(key)].delete(key);
		if (x)
			size--;
		return x;
	}

	public boolean search(K key) {
		if (data[hash(key)] != null)
			return data[hash(key)].member(key);
		return false;
	}

	public int hash(K key) {
		int x = Math.abs(key.hashCode() % buckets);
		return x;
	}
}
