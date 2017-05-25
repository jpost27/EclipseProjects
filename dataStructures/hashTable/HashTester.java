package hashTable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("unused")
public class HashTester {
	public static void main(String[] args) {
		// *
		HashTable<Integer, Integer> test = new HashTable<Integer, Integer>();
		for (int x = 0; x < 1000; x++)
			test.insert(x, x + 2);
		System.out.println("DONE");
		for (int x = 0; x < 1000; x++)
			System.out.print(test.search(x) + "\n");
		System.out.println("DONE");
		for (int x = 0; x < 1000; x++)
			System.out.print(test.delete(x) + "\n");
		System.out.println("DONE");
		for (int x = 0; x < 1000; x++)
			System.out.print(test.search(x) + "\n");
		// */
		/*
		 * String fileName = "words.txt"; String line = null;
		 * HashTable<String,Integer> tesTree = new HashTable<String,Integer>();
		 * try{ FileReader fileReader = new FileReader(fileName); BufferedReader
		 * bufferedReader = new BufferedReader(fileReader);
		 * 
		 * while((line = bufferedReader.readLine()) != null) {
		 * if(tesTree.insert(line,0)){ System.out.println("Added: " + line); }
		 * else{ System.out.println("Unable to add: " + line); break; } }
		 * bufferedReader.close(); } catch(FileNotFoundException ex) {
		 * System.out.println( "Unable to open file '" + fileName + "'"); }
		 * catch(IOException ex) { System.out.println( "Error reading file '" +
		 * fileName + "'"); } try{ FileReader fileReader = new
		 * FileReader(fileName); BufferedReader bufferedReader = new
		 * BufferedReader(fileReader);
		 * 
		 * while((line = bufferedReader.readLine()) != null) {
		 * if(tesTree.delete(line)){ System.out.println("Removed: " + line); }
		 * else{ System.out.println("Could Not Remove: " + line); break; } }
		 * bufferedReader.close(); } catch(FileNotFoundException ex) {
		 * System.out.println( "Unable to open file '" + fileName + "'"); }
		 * catch(IOException ex) { System.out.println( "Error reading file '" +
		 * fileName + "'"); }
		 */
	}
}
