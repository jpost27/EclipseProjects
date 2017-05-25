package csc365project2;

import java.util.ArrayList;
import java.io.Serializable;

public class Node implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3866117384840355076L;
	// Node Structure----------------------------------------------------
	protected ArrayList<Station> keys;
	// ------------------------------------------------------------------
	protected ArrayList<Node> links;
	// ------------------------------------------------------------------
	private int order, numLinks;

	// ------------------------------------------------------------------

	// Constructor ----------------------
	public Node(int n) {
		keys = new ArrayList<Station>();
		links = new ArrayList<Node>();
		numLinks = 0;
		order = n;
	}

	// Get Methods ----------------------
	public int getNumLinks() {
		return numLinks;
	}

	public int getNumKeys() {
		return keys.size();
	}

	public String getKey(int x) {
		return keys.get(x).getKey();
	}

	public Station getK(int x) {
		return keys.get(x);
	}

	public Node getLink(int x) {
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

	public boolean contains(String s) {
		for (int x = 0; x < keys.size(); x++) {
			if (keys.get(x).getKey().equals(s))
				return true;
		}
		return false;
	}

	public boolean isEmpty() {
		return keys.size() == 0;
	}

	public boolean isMin() {
		return keys.size() <= ((order / 2) - 1);
	}

	public int indexOfKey(String key) {
		for (int x = 0; x < keys.size(); x++) {
			if (keys.get(x).getKey().equals(key))
				return x;
		}
		return -1;
	}

	// ---------------------------------

	// Add/Remove Keys------------------
	public boolean addKey(int x, Station station) {
		keys.add(x, station);
		return true;
	}

	public int addKey(Station station) {
		return addKey(station.getKey(), station.getInfo());
	}

	public boolean addKey(int x, String key, Double info) {
		keys.add(x, new Station(key, info));
		return true;
	}

	public int addKey(String key, Double info) {
		for (int x = 0; x < keys.size(); x++) {
			if (keys.get(x).getKey().compareTo(key) > 0) {
				keys.add(x, new Station(key, info));
				return x;
			}
		}
		keys.add(keys.size(), new Station(key, info));
		return keys.size() - 1;
	}

	public boolean removeKey(Station station) {
		int x = indexOfKey(station.getKey());
		if (x >= 0) {
			keys.remove(x);
			return true;
		} else
			return false;
	}

	public boolean removeKey(String key) {
		int x = indexOfKey(key);
		if (x >= 0) {
			keys.remove(x);
			return true;
		} else
			return false;
	}

	public boolean removeKeyAtIndex(int x) {
		keys.remove(keys.get(x));
		return true;
	}

	// -----------------------------------

	// Add/Remove Links ------------------
	public void addLink(int x, Node link) {
		links.add(x, link);
		numLinks++;
	}

	public int indexOfLink(Node link) {
		return links.indexOf(link);
	}

	public boolean removeLink(Node link) {
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

}