package binarySearchTree;

import java.util.ArrayList;

//tester class for the BST. not required but helpful for testing
public class Tester {
	public static void main(String[] args) {
		BST<String> bst = new BST<String>();
		bst.add("5");
		bst.add("3");
		bst.add("1");
		bst.add("2");
		bst.add("8");
		bst.add("9");
		bst.add("7");
		bst.add("6");
		bst.add("4");
		bst.add("10");
		System.out.println(bst.contains("4"));
		ArrayList<Node<String>> a = bst.inOrder();
		for (int y = 0; y < a.size(); y++) {
			System.out.println(a.get(y).getInfo());
		}
		bst.remove("5");
		ArrayList<Node<String>> b = bst.inOrder();
		for (int y = 0; y < b.size(); y++) {
			System.out.println(b.get(y).getInfo());
		}
	}
}