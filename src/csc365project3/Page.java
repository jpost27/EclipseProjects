package csc365project3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Page implements Serializable {
	/**
	     * 
	     */
	private static final long serialVersionUID = 7211189615472925015L;
	// Page structure
	// ------------------------------------------------------------------
	private String html;
	// ------------------------------------------------------------------
	private String title;
	// ------------------------------------------------------------------
	private String url;
	// ------------------------------------------------------------------
	private ArrayList<String> links = new ArrayList<String>();
	// ------------------------------------------------------------------
	private Hashtable<String, Integer> edges;
	// ------------------------------------------------------------------
	private boolean visited;

	public Page(String u, String h) {
		visited = false;
		url = u;
		html = h;
		Document doc = Jsoup.parse(this.html);
		String t = doc.title();
		t = t.substring(0, t.length() - 12);
		title = t;
		setUp();
	}

	public Page(String u) {
		url = u;
		String[] command = { "curl", url };
		ProcessBuilder process = new ProcessBuilder(command);
		Process p;
		try {
			p = process.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			String result = builder.toString();
			html = result;
		} catch (IOException e) {
			System.out.print(url + ": error.");
			e.printStackTrace();
			html = null;
		}
		Document doc = Jsoup.parse(this.html);
		String t = doc.title();
		if (t.length() > 12)
			t = t.substring(0, t.length() - 12);
		title = t;
		setUp();
	}

	private void setUp() {
		Document doc = Jsoup.parse(this.html);
		Elements link = doc.select("a");
		String temp = null, comp = "/wiki/";
		for (Element l : link) {
			temp = l.attr("href");
			if ((temp.length() > 6)
					&& (temp.substring(0, 6).compareTo(comp) == 0)) {
				temp = "https://en.wikipedia.org" + temp;
				if ((temp.compareTo(url) != 0) && (!links.contains(temp))) {
					links.add(temp);
				}
			}
		}
	}

	public void determineWeights() {
		Integer x = 0;
		edges = new Hashtable<String, Integer>();
		for (int n = 0; n < links.size(); n++) {
			x = similarity(this, Load(links.get(n)));
			edges.put(links.get(n), x);
		}
		if (edges.size() != links.size()) {
			System.out.println(edges.size() + "  " + links.size());
		}
	}

	public Integer similarity(Page p1, Page p2) {
		WordList l1 = new WordList(p1);
		WordList l2 = new WordList(p2);
		Hashtable<String, Integer> h1 = l1.getTable();
		Hashtable<String, Integer> h2 = l2.getTable();
		Integer x = 0;
		String w;
		Set<String> keys = h1.keySet();
		Iterator<String> itr = keys.iterator();
		while (itr.hasNext()) {
			w = itr.next();
			if (h2.containsKey(w)) {
				if (h1.get(w) < h2.get(w))
					x += h1.get(w);
				else
					x += h2.get(w);
			}
		}
		return x;
	}

	public Page Load(String url) {
		Page page = null;
		try {
			String URL = url.replaceAll("/", "\\\\");
			FileInputStream fileIn = new FileInputStream(Tree.directory + URL
					+ ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			page = (Page) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			System.out.println(url + ": Page not found. Creating a new one");
			page = new Page(url);
		} catch (ClassNotFoundException c) {
			System.out.println("Page class not found");
		}
		return page;
	}

	public int getEdge(String s) {
		if (edges.containsKey(s))
			return edges.get(s);
		return -1;
	}

	public boolean isVisited() {
		return visited;
	}

	public void visit() {
		visited = true;
	}

	public void resetVisit() {
		visited = false;
	}

	// Retriever methods---------------------------
	public String getHtml() {
		return html;
	}

	public String getUrl() {
		return url;
	}

	public String getTitle() {
		return title;
	}

	public String getLink(int x) {
		if (links.size() > 0)
			return links.get(x);
		return null;
	}

	public ArrayList<String> getLinks() {
		return links;
	}

	// --------------------------------------------

	// Mutator methods-----------------------------

	public void setUrl(String u) {
		url = u;
	}

	public void setHtml(String h) {
		html = h;
	}

	public void removeLink(int x) {
		links.remove(x);
	}

	// --------------------------------------------

	public void printLinks() {
		for (int x = 0; x < links.size(); x++)
			System.out.println(links.get(x));
	}

	public void printTitle() {
		System.out.println(title);
	}

	public String toString() {
		if (title != null)
			return title.toString();
		return null;
	}

}
