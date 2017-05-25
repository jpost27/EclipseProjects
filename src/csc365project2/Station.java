package csc365project2;

import java.io.Serializable;

public class Station implements Serializable {
	/**
	     * 
	     */
	private static final long serialVersionUID = 7211189615472925015L;
	// DoublyLinkedNode structure
	// ------------------------------------------------------------------
	protected Double info;
	// ------------------------------------------------------------------
	protected String key;

	// ------------------------------------------------------------------

	public Station(String k, Double inf) {
		key = k;
		info = inf;
	}

	// Retriever methods---------------------------
	public String getKey() {
		return key;
	}

	public Double getInfo() {
		return info;
	}

	// --------------------------------------------

	// Mutator methods-----------------------------

	public void setInfo(Double inf) {
		info = inf;
	}

	// --------------------------------------------

	public String toString() {
		if (info != null)
			return info.toString();
		return null;
	}

}
