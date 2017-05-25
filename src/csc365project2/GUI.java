package csc365project2;

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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GUI {
	private JFrame frame = new JFrame("Josh Post's March Assignment");
	private JLabel inputLabel = new JLabel("Enter City:");
	private JButton search = new JButton("Search");
	private JTextField inputSource = new JTextField(100);
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable t = new JTable();
	private JPanel URLPanel = new JPanel();
	private JLabel message = new JLabel(
			"Enter a location to see stations with similar elevations.");
	private JButton refresh = new JButton("Refresh");
	private JButton cluster = new JButton("Cluster");
	private JButton quit = new JButton("Quit");
	private String columnNames[];
	private String dataValues[][];
	private JPanel buttonPanel = new JPanel();

	private String s, d;
	private BTree tree = new BTree(8);
	private Cluster c = new Cluster();

	public void layout() {
		s = System.getProperty("file.separator");
		d = System.getProperty("user.home");

		// Load BTree
		try {
			FileInputStream fileIn = new FileInputStream(d + s + "btree.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			tree = (BTree) in.readObject();
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
		URLPanel.add(message, b1);
		URLPanel.setBackground(Color.DARK_GRAY);
		inputLabel.setForeground(Color.green);
		message.setForeground(Color.green);

		columnNames = new String[3];
		columnNames[0] = "City";
		columnNames[1] = "Difference:";
		columnNames[2] = "Elevation:";
		CreateData("-");
		// Create a new table instance
		model = new DefaultTableModel(dataValues, columnNames);
		t = new JTable(model);
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(cluster, BorderLayout.NORTH);
		buttonPanel.add(refresh, BorderLayout.CENTER);
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
				String city = inputSource.getText();
				city = city.toUpperCase();
				Station s = tree.find(city);
				// CITY IS THE INPUT
				if (s == null)
					resetChart();
				else {
					ArrayList<Station> diffs = tree.compareInfo(s.getInfo());
					diffs.remove(s);
					diffs.add(0, s);
					updateChart(diffs, s.getInfo());
				}
			}
		});

		cluster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent l) {
				resetChart();
				String city = inputSource.getText();
				city = city.toUpperCase();
				Station st = tree.find(city);
				if (st != null) {
					for (int x = 0; x < 10; x++) {
						try {
							FileInputStream fileIn = new FileInputStream(d + s
									+ "cluster" + x + ".ser");
							ObjectInputStream in = new ObjectInputStream(fileIn);
							c = (Cluster) in.readObject();
							in.close();
							fileIn.close();
						} catch (IOException i) {
							System.out.println("cluster" + x + " not found.\n");
							i.printStackTrace();
						} catch (ClassNotFoundException c) {
							System.out.println("Cluster class not found");
							c.printStackTrace();
						}
						if (c.contains(st)) {
							System.out.println("Found in cluster " + x);
							break;
						}
					}
					int i = c.indexOf(st);
					ArrayList<Station> n = new ArrayList<Station>();
					for (int x = 0; x < 100; x++) {
						n.add(c.stations.get((x + i) % c.stations.size()));
					}
					updateChart(n, st.getInfo());
				}
			}
		});

		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent l) {
				Load.main(new String[0]);
				try {
					FileInputStream fileIn = new FileInputStream(d + s
							+ "btree.ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					tree = (BTree) in.readObject();
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
		for (int iY = 0; iY < 100; iY++) {
			for (int iX = 0; iX < 3; iX++) {
				model.setValueAt("-", iY, iX);
			}
		}
		t.repaint();
	}

	public void updateChart(ArrayList<Station> names, Double primary) {
		// FIRST COLUMN ON TABLE
		for (int x = 0; x < names.size(); x++)
			model.setValueAt(names.get(x).getKey(), x, 0);

		// SECOND COLUMN ON TABLE
		for (int x = 0; x < names.size(); x++)
			model.setValueAt((double) (Math
					.round(((names.get(x).getInfo() - primary)) * 100)) / 100,
					x, 1);

		// THIRD COLUMN ON TABLE
		for (int x = 0; x < names.size(); x++)
			model.setValueAt((names.get(x).getInfo()), x, 2);

		t.repaint();
	}

	public void CreateData(String j) {
		dataValues = new String[100][3];

		for (int iY = 0; iY < 100; iY++) {
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
