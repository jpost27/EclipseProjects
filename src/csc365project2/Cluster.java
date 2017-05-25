package csc365project2;

import java.io.Serializable;
import java.util.LinkedList;

public class Cluster implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7034836756712088782L;

	private double value;
	protected LinkedList<Station> stations;

	public Cluster() {
		value = 0;
		stations = new LinkedList<Station>();
	}

	public Cluster(double v) {
		value = v;
		stations = new LinkedList<Station>();
	}

	public double average() {
		double av = 0;
		for (int x = 0; x < stations.size(); x++) {
			av += stations.get(x).getInfo();
		}
		av = av / stations.size();
		return av;
	}

	public boolean contains(Station s) {
		for (int x = 0; x < stations.size(); x++) {
			if (stations.get(x).getKey().equals(s.getKey()))
				return true;
		}
		return false;
	}

	public int indexOf(Station s) {
		for (int x = 0; x < stations.size(); x++) {
			if (stations.get(x).getKey().equals(s.getKey()))
				return x;
		}
		return -1;
	}

	public double getValue() {
		return value;
	}

	public boolean setValue(double x) {
		if (value != x) {
			value = x;
			return true;
		}
		return false;
	}

	public void add(Station s) {
		stations.add(s);
	}
}
