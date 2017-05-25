package priorityQueue;

import java.util.Random;

public class Tester {

	public static void main(String[] args) {

		int TestLength = 50;

		Random random = new Random();
		CompleteHeap<Integer> h = new CompleteHeap<Integer>();
		int[] n = new int[TestLength];

		for (int i = 0; i < TestLength; i++) {
			n[i] = random.nextInt(100);
		}

		System.out.println("Adding:");
		for (int a : n) {
			System.out.println(a);
			h.add(a);
		}

		System.out.println("\n\n\nRemoval:");
		for (int i = 0; i < TestLength; i++) {
			System.out.println(h.remove());
		}
	}
}
