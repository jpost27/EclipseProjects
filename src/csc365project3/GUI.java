package csc365project3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GUI {

	static final boolean DEBUG = false;

	public static final int XDIM = 1000;
	public static final int YDIM = 500;

	private JFrame frame = new JFrame("Josh Post's March Assignment");
	private JLabel inputLabel1 = new JLabel("Page1:");
	private JLabel inputLabel2 = new JLabel("Page2:");
	private JButton search = new JButton("Search");
	private JTextField inputSource1 = new JTextField(100);
	private JTextField inputSource2 = new JTextField(100);
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable t = new JTable();
	private JPanel URLPanel = new JPanel();
	private JButton refresh = new JButton("Refresh");
	private JButton quit = new JButton("Quit");
	private String columnNames[];
	private String dataValues[][];
	private JPanel buttonPanel = new JPanel();
	private String s, d;
	private Tree tree;

	public void layout() {

		// Load BTree
		try {
			FileInputStream fileIn = new FileInputStream(Tree.directory
					+ "000Tree.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			tree = (Tree) in.readObject();
			System.out.println("Tree loaded from btree.ser.\n");
			in.close();
			fileIn.close();
		} catch (IOException i) {
			System.out.println("Tree not found.\n");
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Tree not found");
			c.printStackTrace();
			return;
		}

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
		URLPanel.add(inputLabel1, b1);

		b1.fill = GridBagConstraints.HORIZONTAL;
		b1.gridx = 1;
		b1.gridy = 0;
		b1.gridwidth = 8;
		b1.weightx = .8;
		URLPanel.add(inputSource1, b1);

		b1.fill = GridBagConstraints.HORIZONTAL;
		b1.gridx = 9;
		b1.gridwidth = 1;
		b1.gridy = 0;
		b1.weightx = 0;
		URLPanel.add(search, b1);

		b1.fill = GridBagConstraints.HORIZONTAL;
		b1.gridx = 0;
		b1.gridy = 1;
		URLPanel.add(inputLabel2, b1);

		b1.fill = GridBagConstraints.HORIZONTAL;
		b1.gridx = 1;
		b1.gridy = 1;
		b1.gridwidth = 8;
		b1.weightx = .8;
		URLPanel.add(inputSource2, b1);

		columnNames = new String[2];
		columnNames[0] = "Title";
		columnNames[1] = "Title:";
		CreateData();
		// Create a new table instance
		model = new DefaultTableModel(dataValues, columnNames);
		t = new JTable(model);
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(refresh, BorderLayout.CENTER);
		refresh.setEnabled(false);
		buttonPanel.add(quit, BorderLayout.SOUTH);
		buttonPanel.setBackground(Color.DARK_GRAY);
		c1.add(URLPanel, BorderLayout.NORTH);
		scrollPane = new JScrollPane(t);
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
				String t1 = inputSource1.getText();
				String t2 = inputSource2.getText();
				LinkedList<String> list = tree.findShortestPath(t1, t2);
				String[] arg = new String[list.size() + 2];
				for (int x = 0; x < list.size(); x++) {
					arg[x] = tree.getTitle(list.get(x));
				}
				arg[arg.length - 2] = tree.getSimilar(arg[0]);
				arg[arg.length - 1] = tree.getSimilar(arg[arg.length - 3]);
				System.out.println(arg[arg.length - 2] + " "
						+ arg[arg.length - 1]);
				System.out.println(list.toString());
				if (list != null)
					Graph.main(arg);
			}
		});

		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent l) {
				Loader.load("https://en.wikipedia.org/wiki/Java_concurrency");
				try {
					FileInputStream fileIn = new FileInputStream(d + s
							+ "btree.ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					tree = (Tree) in.readObject();
					System.out.println("BTree loaded from btree.ser.\n");
					in.close();
					fileIn.close();
				} catch (IOException i) {
					System.out.println("BTree not found.\n");
					i.printStackTrace();
					return;
				} catch (ClassNotFoundException c) {
					System.out.println("BTree not found");
					c.printStackTrace();
					return;
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
		ArrayList<String> titles = tree.getTitles();
		// FIRST COLUMN ON TABLE
		for (int x = 0; x < titles.size() / 2; x += 2) {
			model.setValueAt(titles.get(x), x, 0);
			model.setValueAt(titles.get(x + 1), x, 1);
		}

		t.repaint();
	}

	public void CreateData() {
		ArrayList<String> titles = tree.getTitles();
		dataValues = new String[titles.size() / 2][2];
		for (int iY = 0; iY < titles.size() / 2; iY++) {
			dataValues[iY][0] = titles.get(iY * 2);
			dataValues[iY][1] = titles.get((iY * 2) + 1);
		}
	}

	// =============================================
	public static void main(String[] args) {
		new GUI().layout();
	}
}
