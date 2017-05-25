package FLRAF;

import java.net.*;
import java.io.*;

import bTree.BTree;

public class SimpleService {
	static final int PORT = 2327; // use the 2-digit number you selected in
									// class for "47"

	public static void main(String[] args) {
		String fileName = "words.txt";
		String line = null;
		BTree<String> tree = new BTree<String>(8);
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				if (tree.add(line)) {
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
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(PORT);

			for (;;) {
				Socket client = serverSocket.accept();

				PrintWriter out = new PrintWriter(client.getOutputStream(),
						true);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						client.getInputStream()));

				String cmd = in.readLine();
				cmd = cmd.substring(11, cmd.length() - 9);
				String output = "";
				if (!cmd.startsWith("-")) {
					if (tree.find(cmd))
						output = cmd + " found!";
					else {
						tree.add(cmd);
						output = cmd + " not found. Added to " + cmd
								+ " to the dictionary.";
					}
				} else {
					cmd = cmd.substring(1, cmd.length());
					if (tree.find(cmd)) {
						tree.remove(cmd);
						output = cmd
								+ " found and removed from the dictionary.";
					} else
						output = cmd
								+ " not found. Unable to remove from dictionary.";
				}
				String reply = "<!DOCTYPE html>\n"
						+ "<html>\n"
						+ "<head><title>Testing</title></head>\n"
						+ "<body><h1>Josh's Dictionary</h1>\n"
						+ "<div>\n"
						+ "<h1 style=\"color:#000000;\">Look up a word:</h1>\n"
						+ "<form action=\"http://127.0.0.1:2327\" method=\"GET\" style=\"width:100px;\"><fieldset>"
						+ "<label>Enter word:</label><input name=\"word\" style=\"margin-top:30px;width:200px;\" type=\"text\" required=\"required\"></input><br></br>"
						+ "<input type=\"submit\" value=\"Go\" style=\"margin-top:30px;\"></input>"
						+ "</fieldset></form>" + "Got request:<br>\n " + output
						+ "\n</body>\n</html>\n";

				int len = reply.length();

				out.println("HTTP/1.0 200 OK");
				out.println("Content-Length: " + len);
				out.println("Content-Type: text/html\n");
				out.println(reply);

				out.close();
				in.close();
				client.close();
			}

		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

	}
}