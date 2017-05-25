package realEstateAgent;

/**
 * House object
 * @author Josh
 * 
 */
class House {
	String address;
	int price;
	int area;
	int numberOfBedrooms;

	/**
	 * House constructor
	 * @param add - address
	 * @param pr - price
	 * @param ar - area
	 * @param numBeds numberOfBedrooms
	 */
	public House(String add, int pr, int ar, int numBeds) {
		address = add;
		price = pr;
		area = ar;
		numberOfBedrooms = numBeds;
	}

	/**
	 * Determines if the house satisfies the given criteria
	 * @param c - Criteria
	 * @return returns true if the criteria is satisfied
	 */
	public boolean satisfies(Criteria c) {
		if (price >= c.getMinimumPrice() && price <= c.getMaximumPrice()){
			if (area >= c.getMinimumArea() && area <= c.getMaximumArea()){
				if (numberOfBedrooms >= c.getMinimumNumberOfBedrooms()
						&& numberOfBedrooms <= c.getMaximumNumberOfBedrooms())
					return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @return number of bedrooms in the house
	 */
	public int getNumberofBedrooms() {
		return numberOfBedrooms;
	}

	/**
	 * 
	 * @return area of the house
	 */
	public double getArea() {
		return area;
	}

	/**
	 * 
	 * @return price of the house
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @return String containing the house's information
	 */
	public String toString() {
		return address + "," + price + "," + area + "," + numberOfBedrooms;
	}

}
