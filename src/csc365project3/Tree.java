package csc365project3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class Tree implements Serializable {
	// Directory to save files
	private static String s = System.getProperty("file.separator");
	protected static String directory = "." + s + "src" + s + "csc365project3" + s
			+ "Pages2" + s;
	// SerialVersionUID
	private static final long serialVersionUID = 7211189615472925015L;
	// Page structure
	// ------------------------------------------------------------------
	private Hashtable<String, String> tree;
	// ------------------------------------------------------------------
	private int MAX = 700;

	// ------------------------------------------------------------------

	public Tree() {
		tree = new Hashtable<String, String>();
	}

	public Tree(String url) {
		tree = new Hashtable<String, String>();
		add(url);
	}

	// Load/Save Pages
	public Page Load(String url) {
		Page page = null;
		try {
			String URL = url.replaceAll("/", "\\\\");
			FileInputStream fileIn = new FileInputStream(directory + URL
					+ ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			page = (Page) in.readObject();
			in.close();
			fileIn.close();
			// System.out.println(url+" loaded.");
		} catch (IOException i) {
			System.out.println(url + ": Page not found. Creating a new one");
			page = new Page(url);
		} catch (ClassNotFoundException c) {
			System.out.println("Page class not found");
		}
		return page;
	}

	public void Save(Page page) {
		try {
			String URL = page.getUrl();
			URL = URL.replaceAll("/", "\\\\");
			FileOutputStream fileOut = new FileOutputStream(directory + URL
					+ ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(page);
			out.close();
			fileOut.close();
			// System.out.println("Page saved: "+URL+".ser\n"+tree.size()+"\n");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	// Retriever methods---------------------------
	public Page getPage(String url) {
		Page page = Load(url);
		return page;
	}

	public Page getPageByTitle(String title) {
		Page page = null;
		Set<String> keys = tree.keySet();
		Iterator<String> itr = keys.iterator();
		while (itr.hasNext()) {
			page = Load(itr.next());
			if (page.getTitle().compareTo(title) == 0)
				return page;
		}
		return null;
	}

	public int size() {
		return tree.size();
	}

	public Hashtable<String, String> getTable() {
		return tree;
	}

	// --------------------------------------------

	// Mutator methods-----------------------------

	public void add(String url) {
		add(Load(url));
	}

	public void add(Page page) {
		tree.put(page.getUrl(), page.getTitle());
		Save(page);
		if (tree.size() <= 1)
			BRANCH(page.getUrl());
	}

	public void remove(String url) {
		try {
			String URL = url.replaceAll("/", "\\\\");
			File file = new File(directory + URL + ".ser");
			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tree.remove(url);
	}

	public void remove(Page p) {
		try {
			String url = p.getUrl().replaceAll("/", "\\\\");
			File file = new File(directory + url + ".ser");
			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
				tree.remove(p.getUrl());
			} else {
				System.out.println("Delete operation failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------
	public void printTitles() {
		Set<String> keys = tree.keySet();
		Iterator<String> itr = keys.iterator();
		while (itr.hasNext()) {
			System.out.println(Load(itr.next()).getTitle());
		}
	}

	// --------------------------------------------
	public void BRANCH(String url) {
		System.out.println("Branching...\n");
		Page root = Load(url);
		ArrayList<String> links = root.getLinks();
		for (int x = 0; x < links.size(); x++) {
			if (!tree.containsKey(links.get(x)))
				add(links.get(x));
		}
		ArrayList<String> l = null;
		for (int x = 0; x < links.size(); x++) {
			l = Load(links.get(x)).getLinks();
			for (int y = 0; y < l.size(); y++) {
				if (!tree.containsKey(l.get(y)))
					add(l.get(y));
				if (tree.size() >= MAX)
					break;
			}
			if (tree.size() >= MAX)
				break;
		}
		System.out.println("Cleaning Links...\n");
		while (cleanLinks() != 0) {
		}
		System.out.println("Setting Weights...\n");
		setWeights();
	}

	public ArrayList<String> getTitles() {
		Set<String> keys = tree.keySet();
		Iterator<String> itr = keys.iterator();
		ArrayList<String> titles = new ArrayList<String>();
		while (itr.hasNext()) {
			titles.add(tree.get(itr.next()));
		}
		return titles;
	}

	protected int cleanLinks() {
		Page page = null;
		int flag = 0;
		Set<String> keys = tree.keySet();
		Iterator<String> itr = keys.iterator();
		ArrayList<String> links;
		ArrayList<String> r = new ArrayList<String>();
		while (itr.hasNext()) {
			page = Load(itr.next());
			links = page.getLinks();
			for (int x = 0; x < links.size(); x++) {
				if (!tree.containsKey(links.get(x))) {
					page.removeLink(x);

					x--;
				}
			}
			Save(page);
			if (page.getLink(0) == null)
				r.add(page.getUrl());
		}
		for (int x = 0; x < r.size(); x++) {
			remove(r.get(x));
			flag++;
		}
		return flag;
	}

	protected void setWeights() {
		Page page = null;
		Set<String> keys = tree.keySet();
		Iterator<String> itr = keys.iterator();
		while (itr.hasNext()) {
			page = Load(itr.next());
			page.determineWeights();
			Save(page);
		}
	}

	public boolean linksToEveryNode() {
		Page page = null;
		Set<String> keys = tree.keySet();
		Iterator<String> i = keys.iterator();
		while (i.hasNext()) {
			page = Load(i.next());
			page.resetVisit();
			Save(page);
		}
		linksToAllPages("https://en.wikipedia.org/wiki/Java_concurrency");
		Iterator<String> itr = keys.iterator();
		while (itr.hasNext()) {
			if (!Load(itr.next()).isVisited())
				return false;
		}
		return true;
	}

	private void linksToAllPages(String url) {
		Page p = Load(url);
		p.visit();
		Save(p);
		ArrayList<String> links = p.getLinks();
		if (links.size() > 0) {
			p = null;
			for (int x = 0; x < links.size(); x++) {
				if (!Load(links.get(x)).isVisited())
					linksToAllPages(links.get(x));
			}
		}
		return;
	}

	private boolean f = false;
	private LinkedList<LinkedList<String>> goodPaths = new LinkedList<LinkedList<String>>();

	protected LinkedList<String> findShortestPath(String p1, String p2) {
		return findShortestPath(getPageByTitle(p1), getPageByTitle(p2));
	}

	// P1 Start, P2 Destination
	protected LinkedList<String> findShortestPath(Page p1, Page p2) {
		goodPaths = new LinkedList<LinkedList<String>>();
		Page page = null;
		Set<String> keys = tree.keySet();
		Iterator<String> itr = keys.iterator();
		while (itr.hasNext()) {
			page = Load(itr.next());
			page.resetVisit();
			Save(page);
		}
		LinkedList<LinkedList<String>> paths = new LinkedList<LinkedList<String>>();
		LinkedList<String> list = new LinkedList<String>();
		list.add(p1.getUrl());
		p1.visit();
		Save(p1);
		paths.add(list);
		findShortestPath(p2, paths);
		paths = goodPaths;
		f = false;
		list = new LinkedList<String>();
		if (paths.size() > 0) {
			list = paths.get(0);
			for (int x = 1; x < paths.size(); x++) {
				if (paths.get(x).size() < list.size()) {
					list = paths.get(x);
				} else if (paths.get(x).size() == list.size()) {
					if (getWeight(paths.get(x)) > getWeight(list)) {
						list = paths.get(x);
					}
				}
			}
		}
		return list;
	}

	private void findShortestPath(Page p2, LinkedList<LinkedList<String>> list) {
		LinkedList<LinkedList<String>> l2 = new LinkedList<LinkedList<String>>();
		Page pp = null;
		LinkedList<String> newList = new LinkedList<String>();
		for (int x = 0; x < list.size(); x++) {
			String t = list.get(x).get(list.get(x).size() - 1);
			Page p = Load(t);
			for (int y = 0; y < p.getLinks().size(); y++) {
				pp = Load(p.getLink(y));
				// System.out.println(p.getTitle()+"-->"+pp.getTitle());
				if (!pp.isVisited()) {
					newList = new LinkedList<String>();
					for (int z = 0; z < list.get(x).size(); z++) {
						newList.add(list.get(x).get(z));
					}
					newList.add(pp.getUrl());
					l2.add(newList);
					if (p2 == null) {
						return;
					}
					if (pp.getTitle().compareTo(p2.getTitle()) == 0) {
						System.out
								.println(pp.getTitle() + "  " + p2.getTitle());
						goodPaths.add(newList);
						f = true;
					}
					pp.visit();
					Save(pp);
				}
			}
		}
		list = null;
		if (f == true)
			return;
		else {
			findShortestPath(p2, l2);
			return;
		}
	}

	private int getWeight(LinkedList<String> list) {
		int weight = 0;
		String current = null;
		String next = null;
		Page page = null;
		for (int x = 0; x < list.size() - 1; x++) {
			current = list.get(x);
			next = list.get(x + 1);
			page = Load(current);
			if (page.getEdge(next) >= 0)
				weight += page.getEdge(next);
			else
				return 999999999;
		}
		return weight;
	}

	public String getTitle(String string) {
		return tree.get(string);
	}

	public String getSimilar(String string) {
		Page p = getPageByTitle(string);
		ArrayList<String> links = p.getLinks();
		int m = 0;
		String max = "";
		for (int x = 0; x < links.size(); x++) {
			if (p.getEdge(links.get(x)) > m) {
				m = p.getEdge(links.get(x));
				max = links.get(x);
			}
		}
		return tree.get(max);
	}
}