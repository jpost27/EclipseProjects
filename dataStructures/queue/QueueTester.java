package queue;

public class QueueTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Queue<String> sq = new Queue<String>();
		sq.insert("124345");
		sq.insert("123abc");
		sq.insert("abc123");
		sq.insert("joshpost");
		sq.insert("abc");
		sq.printQueue();
		sq.pop();
		sq.pop();
		sq.printQueue();
		sq.insert("some more");
		sq.insert("happyBirthday");
		sq.printQueue();
	}

}