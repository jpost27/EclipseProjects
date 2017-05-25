package splayBST;

import java.util.Random;

public class Test_SplayBST {

	// Test methods for the SplayBST
	public static void main(String[] args) {
		RandomTest();
		Test1();
		Test2();
		Test3();
	}

	// One level insert
	private static void Test1() {
		SplayBST<Integer> tree = new SplayBST<Integer>();
		// Add left
		tree.add(5);
		tree.add(0);
		System.out.println("\n\nLeft added one level.");
		tree.displayMe();
		tree = new SplayBST<Integer>();
		// Add right
		tree.add(0);
		tree.add(5);
		System.out.println("\n\nRight added one level.");
		tree.displayMe();
	}

	// Two level insertion
	private static void Test2() {
		// Left left
		SplayBST<Integer> tree = new SplayBST<Integer>();
		tree.add(1);
		tree.add(2);
		tree.add(0);
		System.out.println("\n\nLeft Left added two levels.");
		tree.displayMe();
		// Left Right
		tree = new SplayBST<Integer>();
		tree.add(0);
		tree.add(2);
		tree.add(1);
		System.out.println("\n\nLeft Right added two levels.");
		tree.displayMe();
		// Right Left
		tree = new SplayBST<Integer>();
		tree.add(2);
		tree.add(0);
		tree.add(1);
		System.out.println("\n\nRight Left added two levels.");
		tree.displayMe();
		// Right Right
		tree = new SplayBST<Integer>();
		tree.add(1);
		tree.add(0);
		tree.add(2);
		System.out.println("\n\nRight Right added two levels.");
		tree.displayMe();
	}

	// Search Test
	private static void Test3() {
		SplayBST<Integer> tree = new SplayBST<Integer>();
		tree.add(2);
		tree.add(8);
		tree.add(5);
		tree.add(4);
		tree.add(7);
		tree.add(0);
		tree.add(1);
		tree.add(9);
		tree.add(3);
		tree.add(6);
		System.out.println("0 - 9 added, searching for 10 and 5.");
		int x = tree.search(10), y = tree.search(5);
		if (x == 10)
			System.out.println("\n10 found.");
		else
			System.out.println("\n10 not found, " + x + " returned instead.");
		if (y == 5)
			System.out.println("\n5 found.\n");
		else
			System.out.println("\n5 not found, " + y + "returned instead.\n");
		tree.displayMe();
	}

	// Adds random integers to the tree and then
	// searches to make sure they are all there
	private static void RandomTest() {
		int TestLength = 50;
		// Adds TestLength random integers to an array
		Random random = new Random();
		SplayBST<Integer> tree = new SplayBST<Integer>();
		int[] n = new int[TestLength];

		for (int i = 0; i < TestLength; i++) {
			n[i] = random.nextInt(100);
		}
		// Adds each number to the splay tree
		System.out.println("Adding:");
		for (int a : n) {
			System.out.println(a);
			tree.add(a);
		}
		// Searches to make sure each number is there
		System.out.println("\n\n\nSearch:");
		for (int i = 0; i < TestLength; i++) {
			System.out.println(tree.search(n[i]));
		}
		// Display the tree
		System.out.println("\n\nTree:");
		tree.displayMe();
	}
}
