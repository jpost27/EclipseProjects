package csc365project3;

import java.util.Hashtable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WordList {
	private Hashtable<String, Integer> wordlist;

	public WordList(Page page) {
		wordlist = new Hashtable<String, Integer>();
		Document doc = Jsoup.parse(page.getHtml());
		Elements token = doc.select("p");
		for (Element t : token)
			cleanParse(t.text());
	}

	// ---------------------------------------------------------
	private void cleanParse(String t) {
		String[] split = t.split("\\s+");
		for (int x = 0; x < split.length; x++) {
			if (filter(split[x].toLowerCase()))
				add(split[x].toLowerCase());
		}
	}

	private boolean filter(String k) {
		if (k.length() < 3)
			;
		else if (k.equals("the"))
			;
		else if (k.equals("and"))
			;
		else if (k.equals("with"))
			;
		else if (k.equals("was"))
			;
		else if (k.equals("from"))
			;
		else if (k.equals("for"))
			;
		else if (k.equals("this"))
			;
		else if (k.equals("have"))
			;
		else if (k.equals("that"))
			;
		else if (k.equals("into"))
			;
		else if (k.equals("were"))
			;
		else if (k.equals("such"))
			;
		else if (k.equals("more"))
			;
		else if (k.equals("also"))
			;
		else if (k.equals("saw"))
			;
		else if (k.equals("use"))
			;
		else if (k.equals("its"))
			;
		else if (k.equals("all"))
			;
		else if (k.equals("are"))
			;
		else if (k.equals("not"))
			;
		else if (k.equals("many"))
			;
		else if (k.equals("of"))
			;
		else if (k.equals("a"))
			;
		else if (k.equals("in"))
			;
		else if (k.equals("^"))
			;
		else if (k.equals("on"))
			;
		else if (k.equals("as"))
			;
		else if (k.equals("is"))
			;
		else if (k.equals("there"))
			;
		else if (k.equals("new"))
			;
		else if (k.equals("which"))
			;
		else if (k.equals("by"))
			;
		else if (k.equals("an"))
			;
		else if (k.equals("-"))
			;
		else if (k.equals("b"))
			;
		else if (k.equals("it"))
			;
		else if (k.equals("or"))
			;
		else if (k.equals("film"))
			;
		else if (k.equals("he"))
			;
		else if (k.equals("original"))
			;
		else if (k.equals("best"))
			;
		else if (k.equals("of"))
			;
		else if (k.equals("not"))
			;
		else if (k.equals("at"))
			;
		else if (k.equals("his"))
			;
		else if (k.equals("film"))
			;
		else if (k.equals("films"))
			;
		else if (k.equals("has"))
			;
		else if (k.equals("about"))
			;
		else if (k.equals("page"))
			;
		else if (k.equals("show"))
			;
		else if (k.equals("they"))
			;
		else if (k.equals("who"))
			;
		else if (k.equals("first"))
			;
		else if (k.equals("one"))
			;
		else if (k.equals("but"))
			;
		else if (k.equals("movie"))
			;
		else if (k.equals("some"))
			;
		else if (k.equals("may"))
			;
		else if (k.equals("short"))
			;
		else if (k.equals("like"))
			;
		else if (k.equals("than"))
			;
		else if (k.equals("been"))
			;
		else if (k.equals("out"))
			;
		else if (k.equals("wikipedia"))
			;
		else if (k.equals("what"))
			;
		else if (k.equals("their"))
			;
		else if (k.equals("edit"))
			;
		else if (k.equals("other"))
			;
		else if (k.equals("big"))
			;
		else if (k.equals("retrieved"))
			;
		else if (k.equals("american"))
			;
		else if (k.equals("media"))
			;
		else if (k.equals("after"))
			;
		else if (k.equals("being"))
			;
		else if (k.equals("british"))
			;
		else if (k.equals("you"))
			;
		else if (k.equals("had"))
			;
		else if (k.equals("most"))
			;
		else if (k.equals("often"))
			;
		else if (k.equals("during"))
			;
		else if (k.equals("later"))
			;
		else if (k.equals("over"))
			;
		else if (k.equals("same"))
			;
		else if (k.equals("while"))
			;
		else if (k.equals("around"))
			;
		else if (k.equals("top"))
			;
		else if (k.equals("next"))
			;
		else if (k.equals("will"))
			;
		else if (k.equals("part"))
			;
		else if (k.equals("create"))
			;
		else if (k.equals("each"))
			;
		else if (k.equals("another"))
			;
		else if (k.equals("only"))
			;
		else if (k.equals("articles"))
			;
		else {
			return true;
		}
		return false;
	}

	public int get(String k) {
		return wordlist.get(k);
	}

	public void add(String k) {
		int x;
		if (wordlist.get(k) != null) {
			x = wordlist.get(k) + 1;
			wordlist.remove(k);
			wordlist.put(k, x);
		} else
			wordlist.put(k, 1);
	}

	public Hashtable<String, Integer> getTable() {
		return wordlist;
	}

	public int size() {
		return wordlist.size();
	}
}
