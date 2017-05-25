package movieGenre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * WordList object. An ArrayList of Word objects.
 * @author Josh
 *
 */
public class WordList {
	//Store Word objects in ArrayList
	protected ArrayList<Word> wordlist;
	
	/**
	 * WordList constructor
	 * @param url - url of the page to retrieve words from
	 */
	public WordList(String url) {
		wordlist = new ArrayList<Word>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements token = doc.select("p");
			for (Element t : token)
				cleanParse(t.text());
			token = doc.select("h1");
			for (Element t : token)
				cleanParse(t.text());
			token = doc.select("h2");
			for (Element t : token)
				cleanParse(t.text());
			token = doc.select("h3");
			for (Element t : token)
				cleanParse(t.text());
			token = doc.select("h4");
			for (Element t : token)
				cleanParse(t.text());
			token = doc.select("h5");
			for (Element t : token)
				cleanParse(t.text());
			token = doc.select("li");
			for (Element t : token)
				cleanParse(t.text());
			token = doc.select("a");
			for (Element t : token)
				cleanParse(t.text());
			popCount();
			// ignore words with less than two references
			for (int y = 0; y < wordlist.size() - 1; y++) {
				int x = wordlist.get(y).getReferences();
				if (x < 2) {
					wordlist.remove(y);
					y--;
				}
			}
			sort();
			wordlist.remove(wordlist.size() - 1);
		} catch (IOException ex) {
			System.out.println("Website: " + url + " is not found");
			System.exit(0);
		}
	}

	// ---------------------------------------------------------
	/**
	 * parses the elements and adds them to the WordList
	 * @param t
	 */
	private void cleanParse(String t) {
		String[] split = t.split("\\s+");
		for (int x = 0; x < split.length; x++) {
			if (filter(split[x].toLowerCase()) == true)
				wordlist.add(new Word(split[x].toLowerCase()));
		}
	}

	/**
	 * Removes duplicates and increments references accordingly
	 */
	private void popCount() {
		String word1, word2;
		for (int k = 0; k < wordlist.size(); k++) {
			word1 = wordlist.get(k).getName();
			for (int j = k + 1; j < wordlist.size(); j++) {
				word2 = wordlist.get(j).getName();
				if (word1.equals(word2)) {
					wordlist.remove(j);
					wordlist.get(k).increment();
				}
			}
		}
	}

	/**
	 * sorts the WordList in order of references
	 */
	protected void sort() {
		int flag = 1;
		while (flag > 0) {
			flag = 0;
			for (int k = 0; k < wordlist.size() - 1; k++) {
				if (wordlist.get(k).getReferences() < wordlist.get(k + 1)
						.getReferences()) {
					flag++;
					Collections.swap(wordlist, k, k + 1);
				}
			}
		}
	}

	/**
	 * filters out common insignificant words
	 * @param k - word to be tested
	 * @return returns true if the the word passes the filter
	 */
	private boolean filter(String k) {
		if (k.length() < 3);
		else if (k.equals("the"));
		else if (k.equals("and"));
		else if (k.equals("with"));
		else if (k.equals("was"));
		else if (k.equals("from"));
		else if (k.equals("for"));
		else if (k.equals("this"));
		else if (k.equals("have"));
		else if (k.equals("that"));
		else if (k.equals("into"));
		else if (k.equals("were"));
		else if (k.equals("such"));
		else if (k.equals("more"));
		else if (k.equals("also"));
		else if (k.equals("saw"));
		else if (k.equals("use"));
		else if (k.equals("its"));
		else if (k.equals("all"));
		else if (k.equals("are"));
		else if (k.equals("not"));
		else if (k.equals("many"));
		else if (k.equals("of"));
		else if (k.equals("a"));
		else if (k.equals("in"));
		else if (k.equals("^"));
		else if (k.equals("on"));
		else if (k.equals("as"));
		else if (k.equals("is"));
		else if (k.equals("there"));
		else if (k.equals("new"));
		else if (k.equals("which"));
		else if (k.equals("by"));
		else if (k.equals("an"));
		else if (k.equals("-"));
		else if (k.equals("b"));
		else if (k.equals("it"));
		else if (k.equals("or"));
		else if (k.equals("film"));
		else if (k.equals("he"));
		else if (k.equals("original"));
		else if (k.equals("best"));
		else if (k.equals("of"));
		else if (k.equals("not"));
		else if (k.equals("at"));
		else if (k.equals("his"));
		else if (k.equals("film"));
		else if (k.equals("films"));
		else if (k.equals("has"));
		else if (k.equals("about"));
		else if (k.equals("page"));
		else if (k.equals("show"));
		else if (k.equals("they"));
		else if (k.equals("who"));
		else if (k.equals("first"));
		else if (k.equals("one"));
		else if (k.equals("but"));
		else if (k.equals("movie"));
		else if (k.equals("some"));
		else if (k.equals("may"));
		else if (k.equals("short"));
		else if (k.equals("like"));
		else if (k.equals("than"));
		else if (k.equals("been"));
		else if (k.equals("out"));
		else if (k.equals("wikipedia"));
		else if (k.equals("what"));
		else if (k.equals("their"));
		else if (k.equals("edit"));
		else if (k.equals("other"));
		else if (k.equals("big"));
		else if (k.equals("retrieved"));
		else if (k.equals("american"));
		else if (k.equals("media"));
		else if (k.equals("after"));
		else if (k.equals("being"));
		else if (k.equals("british"));
		else if (k.equals("you"));
		else if (k.equals("had"));
		else if (k.equals("most"));
		else if (k.equals("often"));
		else if (k.equals("during"));
		else if (k.equals("later"));
		else if (k.equals("over"));
		else if (k.equals("same"));
		else if (k.equals("while"));
		else if (k.equals("around"));
		else if (k.equals("top"));
		else if (k.equals("next"));
		else if (k.equals("will"));
		else if (k.equals("part"));
		else if (k.equals("create"));
		else if (k.equals("each"));
		else if (k.equals("another"));
		else if (k.equals("only"));
		else if (k.equals("articles"));
		else {
			return true;
		}
		return false;
	}

	/**
	 * prints each word and its reference count
	 */
	public void printWords() {
		for (int k = 0; k < wordlist.size(); k++) {
			System.out.println(wordlist.get(k).getName() + "  "
					+ wordlist.get(k).getReferences());
		}
	}

	/**
	 * returns a String containing all words
	 * @return String
	 */
	public String getWords() {
		String res = "";
		for (int k = 0; k < wordlist.size(); k++) {
			res = res + wordlist.get(k).getName() + "\n";
		}
		return res;
	}

	/**
	 * adds a word to WordList
	 * @param w - word to be added
	 */
	public void add(Word w) {
		wordlist.add(w);
	}

	/**
	 * returns the Word object of the given position
	 * @param p - position
	 * @return Word object
	 */
	public Word get(int p) {
		return wordlist.get(p);
	}

	/**
	 * Returns the size of the WordList
	 * @return size
	 */
	public int size() {
		return wordlist.size();
	}
}
