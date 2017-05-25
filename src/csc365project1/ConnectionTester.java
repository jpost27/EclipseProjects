package csc365project1;

import hashTable.HashTable;

public class ConnectionTester {
	//
	// Token:ZGetoAoxfImQiLjzSQbcGYWTJdQBaBfI
	// curl -L -H token:ZGetoAoxfImQiLjzSQbcGYWTJdQBaBfI
	// http://www.ncdc.noaa.gov/cdo-web/api/v2/locations?locationcategoryid=ST&limit=52
	// "name": "Winter Precipitation", "id": "WIPRCP"
	//
	public static void main(String[] args) {
		HashTable<String, Double> table = new HashTable<String, Double>();
		Loader.load(table,
				"https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=10000");
		System.out.println("1000 loaded.");
		Loader.load(table,"https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=11000");
		System.out.println("2000 loaded."); 
		Loader.load(table,"https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=12000");
		System.out.println("3000 loaded."); 
		Loader.load(table,"https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=13000");
		System.out.println("4000 loaded."); 
		Loader.load(table,"https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=14000");
		System.out.println("5000 loaded."); 
		Loader.load(table,"https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=15000");
		System.out.println("6000 loaded."); 
		Loader.load(table,"https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=16000");
		System.out.println("7000 loaded.");
		Loader.load(table,"https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=17000");
		System.out.println("8000 loaded.");
		Loader.load(table,"https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=18000");
		System.out.println("9000 loaded.");
		Loader.load(table,"https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=19000");
		System.out.println("10000 loaded.");
	}
}
