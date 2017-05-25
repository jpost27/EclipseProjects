package csc365project3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Loader {
	/*
	 * WARNING!!!: THIS DELETES ALL CACHED FILES. THIS CREATES A BRAND NEW TREE.
	 * THIS COSTS ABOUT 2+ HOURS. THIS IS NOT FUN. THIS IS NOT WORTH WAITING
	 * FOR. JUST NEVER DO THIS. EVER.
	 */
	public static void load(String root) {
		Tree tree = null;
		File dir = new File(Tree.directory);
		purgeDirectory(dir);
		tree = new Tree(root);

		try {
			FileOutputStream fileOut = new FileOutputStream(Tree.directory
					+ "000Tree.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(tree);
			out.close();
			fileOut.close();
			System.out
					.println("Tree saved: 000Tree.ser\n" + tree.size() + "\n");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	static void purgeDirectory(File dir) {
		for (File file : dir.listFiles()) {
			if (file.isDirectory())
				purgeDirectory(file);
			file.delete();
		}
	}
}