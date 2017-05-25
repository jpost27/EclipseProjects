package csc365project1;

import hashTable.HashTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

public class Loader {
	public static void load(HashTable<String, Double> table, String url) {
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
			JSONObject obj = new JSONObject(result);
			JSONArray arr = obj.getJSONArray("results");
			for (int i = 0; i < arr.length(); i++) {
				String id = arr.getJSONObject(i).optString("elevation", null);
				Double el = null;
				if (id != null)
					el = Double.parseDouble(id);
				String name = arr.getJSONObject(i).getString("name");
				name = name.substring(0, name.length() - 3);
				if (el != null)
					table.insert(name, el);
			}
		} catch (IOException e) {
			System.out.print("error");
			e.printStackTrace();
		}

	}

}
