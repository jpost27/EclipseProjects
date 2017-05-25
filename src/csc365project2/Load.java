package csc365project2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Load {

	public static void main(String[] args) {
		String s, d;
		BTree tree = new BTree(8);
		s = System.getProperty("file.separator");
		d = System.getProperty("user.home");
		int flag = 0;
		HashTable table = new HashTable();
		try {
			FileInputStream fileIn = new FileInputStream(d + s + "cache.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			table = (HashTable) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Cache loaded.");
		} catch (IOException i) {
			System.out.println("File not found. Starting with new HashTable");
		} catch (ClassNotFoundException c) {
			System.out.println("HashTable not found");
			return;
		}
		for (Integer x = 0; x < 20000; x += 1000) {
			flag += Loader.load(table,
					"https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset="
							+ x.toString());
			System.out.println((x + 1000) + " loaded.");
		}
		System.out.println("Loading Done");
		// Info has changed. Create a new BTree
		if (flag > 0) {
			// Save cache
			try {
				FileOutputStream fileOut = new FileOutputStream(d + s
						+ "cache.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(table);
				out.close();
				fileOut.close();
				System.out.printf("Serialized cache data is saved in " + d + s
						+ "cache.ser\n");
			} catch (IOException i) {
				i.printStackTrace();
			}
			// Create new BTree and cluster
			Cluster[] c = new Cluster[10];
			for (double x = 0; x < 500; x += 50) {
				c[(int) (x / 50)] = new Cluster(x);
			}
			Loader.btree(tree, table, c);
			kmean(c);
			// Save BTree
			try {
				FileOutputStream fileOut = new FileOutputStream(d + s
						+ "btree.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(tree);
				out.close();
				fileOut.close();
				System.out.printf("Serialized tree data is saved in " + d + s
						+ "btree.ser\n");
			} catch (IOException i) {
				i.printStackTrace();
			}
			for (int x = 0; x < c.length; x++) {
				System.out.println(c[x].getValue());
				try {
					FileOutputStream fileOut = new FileOutputStream(d + s
							+ "cluster" + x + ".ser");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(c[x]);
					out.close();
					fileOut.close();
					System.out.printf("Cluster " + x + " saved in " + d + s
							+ "cluster" + x + ".ser\n");
				} catch (IOException i) {
					i.printStackTrace();
				}
			}
		} else {
			System.out.println("Data has not changed.");
		}

	}

	public static void kmean(Cluster[] c) {
		boolean t = true;
		int flag = 0;
		for (int x = 0; x < c.length; x++) {
			t = c[x].setValue(c[x].average());
			if (t)
				flag++;
		}
		if (flag == 0)
			return;
		Cluster[] d = new Cluster[10];
		for (int x = 0; x < c.length; x++)
			d[x] = new Cluster(c[x].getValue());
		Station s;
		for (int x = 0; x < c.length; x++) {
			for (int y = 0; y < c[x].stations.size(); y++) {
				s = c[x].stations.get(y);
				Loader.addToCluster(d, s);
			}
		}
		kmean(d);
	}

}
