package csc365project1;

import hashTable.HashTable;
import hashTable.LinkedNode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JFrame frame = new JFrame("Josh Post's Assignment 1");
	private JLabel inputLabel = new JLabel("Enter City:");
	private JButton search = new JButton("Search");
	private JTextField inputSource = new JTextField(100);
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable t = new JTable();
	private JPanel URLPanel = new JPanel();
	private JLabel message = new JLabel(
			"Enter a location to see stations with similar elevations.");
	private JButton quit = new JButton("Quit");
	private String columnNames[];
	private String dataValues[][];
	private JPanel buttonPanel = new JPanel();

	public void layout() {
		final HashTable<String, Double> table = new HashTable<String, Double>();
		Loader.load(table,
				"https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=10000");
		System.out.println("1000 loaded.");
		Loader.load(table,
				"https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=11000");
		System.out.println("2000 loaded.");
		Loader.load(table,
				"https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=12000");
		System.out.println("3000 loaded.");
		Loader.load(table,
				"https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=13000");
		System.out.println("4000 loaded.");/*
											 * Loader.load(table,
											 * "https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=14000"
											 * );
											 * System.out.println("5000 loaded."
											 * ); Loader.load(table,
											 * "https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=15000"
											 * );
											 * System.out.println("6000 loaded."
											 * ); Loader.load(table,
											 * "https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=16000"
											 * );
											 * System.out.println("7000 loaded."
											 * ); Loader.load(table,
											 * "https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=17000"
											 * );
											 * System.out.println("8000 loaded."
											 * ); Loader.load(table,
											 * "https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=18000"
											 * );
											 * System.out.println("9000 loaded."
											 * ); Loader.load(table,
											 * "https://www.ncdc.noaa.gov/cdo-web/api/v2/stations?limit=1000&offset=19000"
											 * );
											 * System.out.println("10000 loaded."
											 * );
											 * System.out.println("Loading Done"
											 * );
											 */

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
		buttonPanel.add(quit, BorderLayout.CENTER);
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
				// CITY IS THE INPUT
				if (table.search(city) == false)
					resetChart();
				else {
					ArrayList<LinkedNode<String, Double>> diffs = table
							.compareInfo(table.getInfo(city));
					updateChart(diffs, table.getInfo(city));
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

	public void updateChart(ArrayList<LinkedNode<String, Double>> names,
			double primary) {
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
