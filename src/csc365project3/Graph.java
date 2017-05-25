package csc365project3;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Graph<E extends Comparable<E>> extends JFrame implements
		ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4954150695451203268L;

	static final boolean DEBUG = false;

	public static final int XDIM = 1000;
	public static final int YDIM = 500;

	// define non-GUI objects;
	// GraphicalBinarySearchTree bst;
	boolean clearFlag;
	boolean reflectFlag;
	Random r;

	public String s, d;

	// define GUI objects (widgets) that will comprise this GUI app

	JButton saveButton;
	JButton loadButton;

	JPanel buttonPanel;
	TreePanel<E> treePanel;
	public static String[] t;

	// always define GUI constructor

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Graph() {
		// set up non-GUI objects
		clearFlag = true;
		reflectFlag = false;
		r = new Random();
		s = System.getProperty("file.separator");
		d = System.getProperty("user.home");
		// set up our frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // center the frame on the monitor
		setLayout(new BorderLayout()); // set Layout Manager for this frame

		// create widgets
		saveButton = new JButton("  Quit  ");
		loadButton = new JButton(" Load ");

		// create the panels
		buttonPanel = new JPanel();
		treePanel = new TreePanel();

		// set up the layout for the panels if we're putting widgets in them
		buttonPanel.setLayout(new FlowLayout());

		// add widget(s) to panel(s)

		buttonPanel.add(saveButton);
		buttonPanel.add(loadButton);

		// add panels to our frame

		add(treePanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		saveButton.addActionListener(this);
		loadButton.addActionListener(this);

		// pack the widgets and make frame visible
		setSize(XDIM, YDIM);
		pack();
		// setResizable(true); //if you want the user to be able to resize
		setResizable(false);
		setVisible(true); // make frame visible

	} // end constructor

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == saveButton) {
			clearFlag = false;
			reflectFlag = false;

		}
		if (e.getSource() == loadButton) {
			clearFlag = false;
			reflectFlag = false;

			treePanel.repaint();
		}
	}

	// ALWAYS WRITE THIS

	@SuppressWarnings("rawtypes")
	public static void createAndShowGUI() {

		// optional to set look and feel
		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		}

		// ALWAYS have this statement
		// Launch the GUI
		@SuppressWarnings("unused")
		Graph bts = new Graph();
	}

	// ALWAYS WRITE THIS
	public static void main(String[] args) {
		t = args;
		// schedule a job for the event dispatch thread,
		// creating and showing this application's GUI
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	} // end main

	@SuppressWarnings("hiding")
	class TreePanel<E extends Comparable<E>> extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 8097066626138661999L;

		public TreePanel() {
			setBorder(BorderFactory.createLineBorder(Color.blue));
		}

		public Dimension getPreferredSize() {
			return new Dimension(XDIM, YDIM);
		}

		// calling a panel's repaint() method will call paintComponent
		protected void paintComponent(Graphics g) {
			// call JPanel's paintComponent
			super.paintComponent(g);
			if (clearFlag) {
				return;
			}
			for (int x = 0; x < t.length; x++) {
				g.drawString(t[x], 30 * (x + 1), 30 * (x + 1));
			}

		}
	}

}