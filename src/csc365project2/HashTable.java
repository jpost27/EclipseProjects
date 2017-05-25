package csc365project2;

import java.io.Serializable;

public class HashTable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4256943329099576296L;
	// ------------------------------------------------------------------
	protected List[] data;
	protected LinkedNode currentNode;
	protected int currentList;
	protected int size;
	protected int buckets = 10;

	// ------------------------------------------------------------------

	public HashTable() {
		size = 0;
		data = new List[buckets];
		currentList = 0;
		currentNode = null;
	}

	public HashTable(int numElements) {
		buckets = (numElements / 3) * 4;
		data = new List[buckets];
		currentList = 0;
		currentNode = null;
	}

	public boolean insert(String key, String info) {
		if (data[hash(key)] == null)
			data[hash(key)] = new List();
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
			HashTable n = new HashTable(size);
			for (int x = 0; x < data.length; x++) {
				if (data[x] != null) {
					data[x].resetCurrentElement();
					while (data[x].hasMoreNodes()) {
						LinkedNode t = data[x].nextNode();
						n.insert(t.getKey(), t.getInfo());
					}
				}
			}
			buckets = (size / 3) * 4;
			data = n.getList();
		}
	}

	public List[] getList() {
		return data;
	}

	public void printFirst() {
		System.out.println(data[0].toString());
	}

	public String getInfo(String key) {
		return data[hash(key)].getInfo(key);
	}

	public boolean delete(String key) {
		boolean x = data[hash(key)].delete(key);
		if (x)
			size--;
		return x;
	}

	public boolean search(String key) {
		if (data[hash(key)] != null)
			return data[hash(key)].member(key);
		return false;
	}

	public int hash(String key) {
		int x = Math.abs(key.hashCode() % buckets);
		return x;
	}
}
