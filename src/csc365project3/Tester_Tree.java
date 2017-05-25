package csc365project3;

import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;

public class Tester_Tree {
	public static void main(String args[]) {
		Tree tree = null;
		try {
			String path = Tree.directory + "000tree.ser";
			System.out.println(path);
			FileInputStream fileIn = new FileInputStream(Tree.directory
					+ "000tree.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			tree = (Tree) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			System.out.println("Tree not found. Creating a new one");
			tree = new Tree("https://en.wikipedia.org/wiki/Java_concurrency");
		} catch (ClassNotFoundException c) {
			System.out.println("Page class not found");
		}
		System.out.println("Figuring it out..........................@@@@@");
		System.out.println(tree.linksToEveryNode());
		try {
			FileOutputStream fileOut = new FileOutputStream(Tree.directory
					+ "000Tree.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(tree);
			out.close();
			fileOut.close();
			System.out.println("Tree saved: 000Tree.ser\n");
		} catch (IOException i) {
			i.printStackTrace();
		}
		for (int x = 0; x < 20; x++) {
			Toolkit.getDefaultToolkit().beep();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
