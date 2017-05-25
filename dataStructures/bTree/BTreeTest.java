package bTree;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

/**
 * Inserts a list of 45k+ words into the tree
 * and then removes each word
 * @author Josh
 *
 */
public class BTreeTest {
	public static void main(String[] args) {
		String fileName = "."+File.separator+"dataStructures"+File.separator+"bTree"+File.separator+"words.txt";
		String line = null;
		BTree<String> tesTree = new BTree<String>(8);
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				if (tesTree.add(line)) {
					System.out.println("Added: " + line);
				} else {
					System.out.println("Unable to add: " + line);
					break;
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				if (tesTree.remove(line)) {
					System.out.println("Removed: " + line);
				} else {
					System.out.println("Could Not Remove: " + line);
					break;
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}
}