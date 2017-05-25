package realEstateAgent;

public class Tester_HouseList {

	public static void main(String[] args) {
		String s = System.getProperty("file.separator");
		
		HouseList availableHouses = new HouseList("."+s+"src"+s+"realEstateAgent"+s+"houses.txt");

		Criteria c = new Criteria(1000, 500000, 100, 5000, 0, 10);
		Criteria d = new Criteria(1000, 100000, 500, 1200, 0, 3);
		Criteria e = new Criteria(100000, 200000, 1000, 2000, 2, 3);
		Criteria f = new Criteria(200000, 300000, 1500, 4000, 3, 6);
		Criteria g = new Criteria(100000, 500000, 2500, 5000, 3, 6);

		System.out.println("Results of the first criteria");

		availableHouses.printHouses(c);
		System.out.println("");
		System.out.println("Results of the second criteria");
		availableHouses.printHouses(d);
		System.out.println("");
		System.out.println("Results of the third criteria");
		availableHouses.printHouses(e);
		System.out.println("");
		System.out.println("Results of the fourth criteria");
		availableHouses.printHouses(f);
		System.out.println("");
		System.out.println("Results of the fifth criteria");
		availableHouses.printHouses(g);
		System.out.println("");
	}
}
