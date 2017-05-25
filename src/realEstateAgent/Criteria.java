package realEstateAgent;

/**
 * Criteria object
 * @author Josh
 *
 */
public class Criteria {

	private int minimumPrice;
	private int maximumPrice;
	private int minimumArea;
	private int maximumArea;
	private int minimumNumberOfBedrooms;
	private int maximumNumberOfBedrooms;

	/**
	 * Criteria constructor
	 * @param minP - minimum price
	 * @param maxP - maximum price
	 * @param minA - minimum area
	 * @param maxA - maximum area
	 * @param minNumOfBeds - minimum number of beds
	 * @param maxNumOfBeds - maximum number of beds
	 */
	public Criteria(int minP, int maxP, int minA, int maxA, int minNumOfBeds, int maxNumOfBeds) {
		minimumPrice = minP;
		maximumPrice = maxP;
		minimumArea = minA;
		maximumArea = maxA;
		minimumNumberOfBedrooms = minNumOfBeds;
		maximumNumberOfBedrooms = maxNumOfBeds;
	}

	// Retriever methods
	/**
	 * 
	 * @return minimum price
	 */
	public int getMinimumPrice() {
		return minimumPrice;
	}

	/**
	 * 
	 * @return maximum price
	 */
	public int getMaximumPrice() {
		return maximumPrice;
	}

	/**
	 * 
	 * @return minimum area
	 */
	public int getMinimumArea() {
		return minimumArea;
	}

	/**
	 * 
	 * @return maximum area
	 */
	public int getMaximumArea() {
		return maximumArea;
	}

	/**
	 * 
	 * @return minimum number of bedrooms
	 */
	public int getMinimumNumberOfBedrooms() {
		return minimumNumberOfBedrooms;
	}

	/**
	 * 
	 * @return maximum number of bedrooms
	 */
	public int getMaximumNumberOfBedrooms() {
		return maximumNumberOfBedrooms;
	}

}