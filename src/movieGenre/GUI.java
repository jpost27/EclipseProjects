package movieGenre;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//----------------------------------------------------
public class GUI {

	private JFrame frame = new JFrame("Josh Post's Assignment 1");
	private JLabel inputLabel = new JLabel("Enter URL:");
	private JButton search = new JButton("Search");
	private JTextField inputSource = new JTextField(100);
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable table = new JTable();
	private JPanel URLPanel = new JPanel();
	private JLabel websiteType = new JLabel(
			"Please enter the URL to any movie page.");
	private JButton quit = new JButton("Quit");
	private String columnNames[];
	private String dataValues[][];
	private JPanel buttonPanel = new JPanel();

	public void layout() {
		final WordList a = new WordList(
				"https://en.wikipedia.org/wiki/Horror_film");
		final WordList b = new WordList(
				"https://en.wikipedia.org/wiki/Comedy_film");
		final WordList c = new WordList(
				"https://en.wikipedia.org/wiki/Drama_(genre)");
		final WordList d = new WordList(
				"https://en.wikipedia.org/wiki/Thriller_(genre)");
		final WordList e = new WordList(
				"https://en.wikipedia.org/wiki/Adventure_fiction");
		final WordList f = new WordList(
				"https://en.wikipedia.org/wiki/Animation");
		final WordList g = new WordList(
				"https://en.wikipedia.org/wiki/Fantasy_television");
		final WordList h = new WordList(
				"https://en.wikipedia.org/wiki/Science_fiction");
		final WordList i = new WordList(
				"https://en.wikipedia.org/wiki/Documentary_film");
		final WordList j = new WordList(
				"https://en.wikipedia.org/wiki/Reality_television");
		// --------------------------------------------
		// Get the container and set border layout
		Container c1 = frame.getContentPane();
		c1.setLayout(new BorderLayout());
		// --------------------------------------------
		URLPanel.setLayout(new GridBagLayout());

		GridBagConstraints b1 = new GridBagConstraints();

		b1.fill = GridBagConstraints.HORIZONTAL;
		b1.gridx = 0;
		b1.gridy = 0;
		URLPanel.add(inputLabel, b1);

		b1.fill = GridBagConstraints.HORIZONTAL;
		b1.gridx = 1;
		b1.gridy = 0;
		b1.gridwidth = 8;
		b1.weightx = .8;
		URLPanel.add(inputSource, b1);

		b1.fill = GridBagConstraints.HORIZONTAL;
		b1.gridx = 9;
		b1.gridwidth = 1;
		b1.gridy = 0;
		b1.weightx = 0;
		URLPanel.add(search, b1);

		b1.fill = GridBagConstraints.HORIZONTAL;
		b1.gridx = 0;
		b1.gridwidth = 10;
		b1.weightx = 1;
		b1.gridy = 1;
		URLPanel.add(websiteType, b1);
		URLPanel.setBackground(Color.DARK_GRAY);
		inputLabel.setForeground(Color.white);
		websiteType.setForeground(Color.yellow);

		columnNames = new String[3];
		columnNames[0] = "Word";
		columnNames[1] = "Website Match:";
		columnNames[2] = "User Input Website:";
		CreateData("-");
		// Create a new table instance
		model = new DefaultTableModel(dataValues, columnNames);
		table = new JTable(model);
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(quit, BorderLayout.CENTER);
		buttonPanel.setBackground(Color.DARK_GRAY);
		c1.add(URLPanel, BorderLayout.NORTH);
		scrollPane = new JScrollPane(table);
		c1.add(scrollPane, BorderLayout.CENTER);
		c1.add(buttonPanel, BorderLayout.SOUTH);

		// --------------------------------------------
		frame.setBackground(Color.black);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setVisible(true);

		// --------------------------------------------

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent l) {
				resetChart();
				String source = inputSource.getText();
				WordList k = new WordList(source);
				WordList a1 = (combine(source, a));
				WordList b1 = (combine(source, b));
				WordList c1 = (combine(source, c));
				WordList d1 = (combine(source, d));
				WordList e1 = (combine(source, e));
				WordList f1 = (combine(source, f));
				WordList g1 = (combine(source, g));
				WordList h1 = (combine(source, h));
				WordList i1 = (combine(source, i));
				WordList j1 = (combine(source, j));
				int[] scores = new int[] { score(a, k), score(b, k),
						score(c, k), score(d, k), score(e, k), score(f, k),
						score(g, k), score(h, k), score(i, k), score(j, k) };
				Arrays.sort(scores);
				if (score(a, k) == scores[9]) {
					websiteType
							.setText("This movie is classified as a Horror film.");
					updateChart(a1, a, k);
				} else if (score(b, k) == scores[9]) {
					websiteType
							.setText("This movie is classified as a Comedy.");
					updateChart(b1, b, k);
				} else if (score(c, k) == scores[9]) {
					websiteType.setText("This movie is classified as a Drama.");
					updateChart(c1, c, k);
				} else if (score(d, k) == scores[9]) {
					websiteType
							.setText("This movie is classified as a Thriller.");
					updateChart(d1, d, k);
				} else if (score(e, k) == scores[9]) {
					websiteType
							.setText("This movie is classified as an Adventure.");
					updateChart(e1, e, k);
				} else if (score(f, k) == scores[9]) {
					websiteType
							.setText("This movie is classified as an Animation Film.");
					updateChart(f1, f, k);
				} else if (score(g, k) == scores[9]) {
					websiteType.setText("This movie is classified as Fantasy.");
					updateChart(g1, g, k);
				} else if (score(h, k) == scores[9]) {
					websiteType
							.setText("This movie is classified as Science Fiction.");
					updateChart(h1, h, k);
				} else if (score(i, k) == scores[9]) {
					websiteType
							.setText("This movie is classified as a Documentary.");
					updateChart(i1, i, k);
				} else if (score(j, k) == scores[9]) {
					websiteType.setText("This is a Reality TV Show.");
					updateChart(j1, j, k);
				} else {
				}
			}
		});

		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public void resetChart() {
		for (int iY = 0; iY < 200; iY++) {
			for (int iX = 0; iX < 3; iX++) {
				model.setValueAt("-", iY, iX);
			}
		}
		table.repaint();
	}

	public void updateChart(WordList n, WordList d, WordList u) {
		for (int k = 0; k < n.size(); k++) {
			if (k < 500)
				model.setValueAt(n.get(k).getName(), k, 0);
			else
				break;
		}
		for (int k = 0; k < n.size(); k++) {
			if (k < 500) {
				for (int l = 0; l < d.size(); l++) {
					if (n.get(k).getName().equals(d.get(l).getName())) {
						model.setValueAt(d.get(l).getReferences(), k, 1);
						break;
					}
				}
			} else
				break;
		}
		for (int k = 0; k < n.size(); k++) {
			if (k < 500) {
				for (int l = 0; l < u.size(); l++) {
					if (n.get(k).getName().equals(u.get(l).getName())) {
						model.setValueAt(u.get(l).getReferences(), k, 2);
						break;
					}
				}
			} else
				break;
		}
		table.repaint();
	}

	public WordList combine(String source, WordList d) {
		WordList out = new WordList(source);
		for (int k = 0; k < d.size(); k++) {
			int flag = 0;
			for (int l = 0; l < out.size(); l++) {
				if (out.get(l).getName().equals(d.get(k).getName())) {
					out.get(l).addReferences(d.get(k).getReferences());
					flag++;
					break;
				}
			}
			if (flag == 0)
				out.add(d.get(k));
		}
		out.sort();
		return out;
	}

	private int score(WordList d, WordList u) {
		int s = 0;
		for (int k = 0; k < d.size(); k++) {
			for (int l = 0; l < u.size(); l++) {
				if (d.get(k).getName().equals(u.get(l).getName())) {
					if (d.get(k).getName().equals("horror")
							|| d.get(k).getName().equals("comedy")
							|| d.get(k).getName().equals("drama")
							|| d.get(k).getName().equals("thriller")
							|| d.get(k).getName().equals("adventure")
							|| d.get(k).getName().equals("animation")
							|| d.get(k).getName().equals("fantasy")
							|| d.get(k).getName().equals("fiction")
							|| d.get(k).getName().equals("documentary")
							|| d.get(k).getName().equals("reality"))
						s = s
								+ ((d.get(k).getReferences() * u.get(l)
										.getReferences()) * 10);
					else
						s = s
								+ (d.get(k).getReferences() * u.get(l)
										.getReferences());
				}
			}
		}
		return s;
	}

	public void CreateData(String j) {
		dataValues = new String[500][3];

		for (int iY = 0; iY < 500; iY++) {
			for (int iX = 0; iX < 3; iX++) {
				dataValues[iY][iX] = j;
			}
		}
	}

	// =============================================
	public static void main(String[] args) {
		new GUI().layout();
	}
}