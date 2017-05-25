package FLRAF;

import java.io.BufferedReader;
import java.io.FileReader;

public class Tester {
	public static void main(String[] args) throws Exception {

		FLRAF flraf = new FLRAF(32);
		String fileName = "words.txt";
		String line = null;
		String word;

		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		while ((line = bufferedReader.readLine()) != null) {
			word = line;
			System.out.println("Added " + word);
			flraf.writeBlock(word.getBytes("UTF-8"));
		}
		flraf.writeBlock("".getBytes("UTF-8"));
		bufferedReader.close();

		String w;
		for (int x = 0; x < (flraf.length() / 32); x++) {
			w = new String(flraf.getBlock(x), "UTF-8");
			System.out.println("Word found: " + w);
		}
		flraf.close();
	}
}