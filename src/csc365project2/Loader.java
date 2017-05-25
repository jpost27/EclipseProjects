package csc365project2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

public class Loader {
	public static int load(HashTable table, String url) {
		String token = "ZGetoAoxfImQiLjzSQbcGYWTJdQBaBfI";
		String[] command = { "curl", "-L", "-H", "token:" + token, url };
		ProcessBuilder process = new ProcessBuilder(command);
		Process p;
		try {
			System.out.println("\nConnecting...\n");
			p = process.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			String result = builder.toString();
			System.out.println("Connection Successful!\n");
			if (table.search(url)) {
				if (table.getInfo(url).equals(result)) {
					table.insert(url, result);
					return 0;
				}
				table.delete(url);
				return 1;
			}
			table.insert(url, result);
		} catch (IOException e) {
			System.out.print("error");
			e.printStackTrace();
		}
		return 1;
	}

	public static void btree(BTree tree, HashTable table, Cluster[] c) {
		for (Integer x = 0; x < 20000; x += 1000) {
			String result = table
					.getInfo("https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset="
							+ Integer.toString(x));
			JSONObject obj = new JSONObject(result);
			JSONArray arr = obj.getJSONArray("results");
			for (int i = 0; i < arr.length(); i++) {
				String id = arr.getJSONObject(i).optString("elevation", null);
				Double el = null;
				if (id != null)
					el = Double.parseDouble(id);
				String name = arr.getJSONObject(i).getString("name");
				if (name.length() > 3)
					name = name.substring(0, name.length() - 3);
				if (el != null) {
					tree.add(name, el);
					addToCluster(c, new Station(name, el));
				}
			}
		}
	}

	public static void addToCluster(Cluster[] c, Station station) {
		double diff = 999999999;
		int closest = 0;
		for (int x = 0; x < c.length; x++) {
			if (diff > Math.abs(c[x].getValue() - station.getInfo())) {
				diff = Math.abs(c[x].getValue() - station.getInfo());
				closest = x;
			}
		}
		c[closest].add(station);
	}

}
