package realEstateAgent;
import java.io.*;
import java.util.*;

/**
 * House List object
 * @author Josh
 *
 */
public class HouseList {
	//Houses stored in an ArrayList
	private ArrayList<House> houseList;

	/**
	 * HouseList constructor
	 * @param filename - name of the file containing house information
	 */
	public HouseList(String filename) {
		houseList = new ArrayList<House>();
		Scanner myFileIn = null;
		try {
			myFileIn = new Scanner(new File(filename));
		} catch (FileNotFoundException e2) {
			System.out.println("File: " + filename + " is not found");
			System.exit(0);
		}
		
		String address;
		int price;
		int area;
		int numberOfBedrooms;

		while (myFileIn.hasNext()) {
			address = myFileIn.next();
			price = myFileIn.nextInt();
			area = myFileIn.nextInt();
			numberOfBedrooms = myFileIn.nextInt();
			House nextHouse = new House(address, price, area, numberOfBedrooms);
			houseList.add(nextHouse);
		}
	}

	// ---------------------------------------------------------

	/**
	 * prints all of the houses that meet the criteria
	 * @param c - criteria houses need to meet
	 */
	public void printHouses(Criteria c) {
		for (int k = 0; k < houseList.size(); k++) {
			if (houseList.get(k).satisfies(c)) {
				System.out.println(houseList.get(k).toString());
			}
		}
	}
	/**
	 * returns a String containing all of the houses that meet the criteria
	 * @param c - criteria houses need to meet
	 * @return
	 */
	public String getHouses(Criteria c) {
		String res = "";
		for (int k = 0; k < houseList.size(); k++) {
			if (houseList.get(k).satisfies(c)) {
				res = res + houseList.get(k).toString() + "\n";
			}
		}
		return res;
	}
}
