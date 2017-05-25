package bstGraphApplication;

import javax.swing.*;

import binarySearchTree.BST;
import binarySearchTree.Node;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class BTSwingApp<E extends Comparable<E>> extends JFrame implements
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
	public BST<E> bst = new BST<E>();
	public ArrayList<Node<E>> a = new ArrayList<Node<E>>();
	public String s, d;

	// define GUI objects (widgets) that will comprise this GUI app

	JButton clearButton;
	JButton removeButton;
	JButton addButton;
	JButton rremoveButton;
	JButton raddButton;
	JButton reflectButton;
	JButton saveButton;
	JButton loadButton;
	JTextField inputField;

	JPanel buttonPanel;
	TreePanel<E> treePanel;

	// always define GUI constructor

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BTSwingApp() {

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
		addButton = new JButton("  Add  ");
		raddButton = new JButton("  Random Add  ");
		removeButton = new JButton("  Remove  ");
		reflectButton = new JButton("Reflect");
		clearButton = new JButton(" Clear ");
		saveButton = new JButton("  Save  ");
		loadButton = new JButton(" Load ");
		inputField = new JTextField(10);
		inputField.setEditable(true);

		// create the panels
		buttonPanel = new JPanel();
		treePanel = new TreePanel();

		// set up the layout for the panels if we're putting widgets in them
		buttonPanel.setLayout(new FlowLayout());

		// add widget(s) to panel(s)

		buttonPanel.add(addButton);
		buttonPanel.add(raddButton);
		buttonPanel.add(inputField);
		buttonPanel.add(removeButton);
		buttonPanel.add(reflectButton);
		buttonPanel.add(clearButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(loadButton);

		// add panels to our frame

		add(treePanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		addButton.addActionListener(this);
		raddButton.addActionListener(this);
		removeButton.addActionListener(this);
		reflectButton.addActionListener(this);
		clearButton.addActionListener(this);
		saveButton.addActionListener(this);
		loadButton.addActionListener(this);

		// pack the widgets and make frame visible
		setSize(XDIM, YDIM);
		pack();
		// setResizable(true); //if you want the user to be able to resize
		setResizable(false);
		setVisible(true); // make frame visible

	} // end constructor

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == clearButton) {
			clearFlag = true;
			reflectFlag = false;
			treePanel.removeAll();
			treePanel.updateUI();
			bst = new BST<E>();
		}
		if (e.getSource() == saveButton) {
			clearFlag = false;
			reflectFlag = false;
			try {
				FileOutputStream fileOut = new FileOutputStream(d + s
						+ "bst.bstrc");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(bst);
				out.close();
				fileOut.close();
				System.out.printf("Serialized data is saved in " + d + s
						+ "bst.ser");
			} catch (IOException i) {
				i.printStackTrace();
			}
		}
		if (e.getSource() == loadButton) {
			clearFlag = false;
			reflectFlag = false;
			try {
				FileInputStream fileIn = new FileInputStream(d + s
						+ "bst.bstrc");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				bst = (BST<E>) in.readObject();
				in.close();
				fileIn.close();
			} catch (IOException i) {
				System.out.println("File not found");
				bst = new BST<E>();
				return;
			} catch (ClassNotFoundException c) {
				System.out.println("BST not found");
				c.printStackTrace();
				bst = new BST<E>();
				return;
			}

			treePanel.repaint();
		}
		if (e.getSource() == reflectButton) {
			reflectFlag = true;
			clearFlag = false;
			treePanel.repaint();
		}
		if (e.getSource() == removeButton) {
			String text = inputField.getText();
			System.out.print("Removing " + text + "... ");
			if (bst.remove((E) (Integer) Integer.parseInt(text))) {
				System.out.println("Successful");
				reflectFlag = false;
				clearFlag = false;
				treePanel.removeAll();
				treePanel.updateUI();
				treePanel.repaint();
			} else
				System.out.println("Remove Failed");
		}
		if (e.getSource() == raddButton) {
			System.out.print("Random Adding... ");
			if (bst.add()) {
				System.out.println("Successful");
				reflectFlag = false;
				clearFlag = false;
				treePanel.repaint();
			} else
				System.out.println("Random Add Failed");
		}
		if (e.getSource() == addButton) {
			String text = inputField.getText();
			System.out.print("Adding " + text + "... ");
			if (bst.add((E) (Integer) Integer.parseInt(text))) {
				System.out.println("Successful");
				reflectFlag = false;
				clearFlag = false;
				treePanel.repaint();
			} else
				System.out.println("Add Failed");
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
		BTSwingApp bts = new BTSwingApp();
	}

	// ALWAYS WRITE THIS
	public static void main(String[] args) {
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
				bst.clear();
				return;
			}
			a = bst.inOrder();
			for (int x = 0; x < a.size(); x++) {
				g.drawString(a.get(x).getInfo().toString(), 30 * (x + 1),
						30 * a.get(x).getLevel());
				if (a.get(x).getLeftLeaf() != null) {
					g.drawLine(30 * (x + 1) - 2, 30 * a.get(x).getLevel() + 2,
							30 * (a.indexOf(a.get(x).getLeftLeaf()) + 1) + 12,
							30 * a.get(x).getLevel() + 18);
				}
				if (a.get(x).getRightLeaf() != null) {
					g.drawLine(30 * (x + 1) + 12, 30 * a.get(x).getLevel() + 2,
							30 * (a.indexOf(a.get(x).getRightLeaf()) + 1) - 2,
							30 * a.get(x).getLevel() + 18);
				}
			}
			if (reflectFlag) {
				bst.reverse();
				a = bst.inOrder();
				for (int x = a.size() - 1; x >= 0; x--) {
					g.drawString(a.get(x).getInfo().toString(), 30 * (x + 1)
							+ (30 * (a.size() + 1)), 30 * a.get(x).getLevel());
					if (a.get(x).getLeftLeaf() != null) {
						g.drawLine(30 * (x + 1) - 2 + (30 * (a.size() + 1)),
								30 * a.get(x).getLevel() + 2,
								30 * (a.indexOf(a.get(x).getLeftLeaf()) + 1)
										+ 12 + (30 * (a.size() + 1)), 30 * a
										.get(x).getLevel() + 18);
					}
					if (a.get(x).getRightLeaf() != null) {
						g.drawLine(30 * (x + 1) + 12 + (30 * (a.size() + 1)),
								30 * a.get(x).getLevel() + 2,
								30 * (a.indexOf(a.get(x).getRightLeaf()) + 1)
										- 2 + (30 * (a.size() + 1)), 30 * a
										.get(x).getLevel() + 18);
					}
				}
				bst.reverse();
			}
		}
	}

}