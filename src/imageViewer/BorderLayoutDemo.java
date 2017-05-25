package imageViewer;

import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import linkedlist.DoublyLinkedList;

//----------------------------------------------------
public class BorderLayoutDemo {
	private int current = 0, max = 0;
	private DoublyLinkedList<String> myList;
	private int currentIndex;
	private JFrame frame = new JFrame("BroderLayoutDemo");
	private JPanel navigationPanel = new JPanel();
	private JLabel pictureLabel = new JLabel();
	private JComboBox<String> directoryNamesBox = new JComboBox<String>();
	private JButton first = new JButton("<<");
	private JButton prev = new JButton("<");
	private JLabel countLabel = new JLabel(current + " of " + max);
	private JButton next = new JButton(">");
	private JButton last = new JButton(">>");

	public void demo() {
		// --------------------------------------------
		// Get the container and set border layout
		Container c = frame.getContentPane();
		c.setLayout(new BorderLayout());
		// --------------------------------------------
		// buildGUI
		// North, West and East parts just have some
		// text in them. They are not functional.
		// In fact, you can comment out the three add
		// statements for them. We are only using the
		// south and the center parts
		navigationPanel.add(first, new BorderLayout());
		navigationPanel.add(prev, new BorderLayout());
		navigationPanel.add(countLabel, new BorderLayout());
		navigationPanel.add(next, new BorderLayout());
		navigationPanel.add(last, new BorderLayout());
		c.add(directoryNamesBox, BorderLayout.NORTH);
		c.add(pictureLabel, BorderLayout.CENTER);
		c.add(navigationPanel, BorderLayout.SOUTH);

		// --------------------------------------------
		// The south box has a "dropdown" list. We can add
		// items into it. It is a box containing strings as
		// items. Each string is a filename that contains a
		// picture. (.jpg file) You can copy/paste a jpg file
		// into the src directory
		directoryNamesBox.addItem("Choose a file name to load pictures");
		directoryNamesBox.addItem("Directory 1");
		directoryNamesBox.addItem("Directory 2");
		directoryNamesBox.addItem("Directory 3");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setVisible(true);

		// --------------------------------------------
		// Adding an actionListener to the southBox.
		// The dropdown list will display the three filenames
		// The user clicks on one of them. We get the
		// selectedItem from the list, which will be an Object,
		// and cast it into a String. We use this string to
		// create an image icon, and do a setIcon to the label.
		first.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current = 1;
				myList.resetCurrentElement();
				ImageIcon thisIcon = new ImageIcon(myList.getCurrentElement());
				pictureLabel.setIcon(thisIcon);
				countLabel.setText(current + " of " + max);
			}
		});
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (current > 1) {
					myList.prevElement();
					current--;
					ImageIcon thisIcon = new ImageIcon(myList
							.getCurrentElement());
					pictureLabel.setIcon(thisIcon);
					countLabel.setText(current + " of " + max);
				}
			}
		});
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (current < max) {
					myList.nextElement();
					current++;
					ImageIcon thisIcon = new ImageIcon(myList
							.getCurrentElement());
					pictureLabel.setIcon(thisIcon);
					countLabel.setText(current + " of " + max);
				}
			}
		});
		last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current = max;
				myList.resetCurrentElementToLast();
				ImageIcon thisIcon = new ImageIcon(myList.getCurrentElement());
				pictureLabel.setIcon(thisIcon);
				countLabel.setText(current + " of " + max);
			}
		});
		directoryNamesBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename = (String) (directoryNamesBox.getSelectedItem());
				if (filename != "Choose a file name to load pictures") {
					addPictures(filename);
					ImageIcon thisIcon = new ImageIcon(myList
							.getCurrentElement());
					pictureLabel.setIcon(thisIcon);
					current = 1;
					max = myList.length();
					myList.resetCurrentElement();
				}
			}
		});

	}

	// ==============================================
	public void displayData(JLabel label, String data) {
		ImageIcon thisIcon = new ImageIcon((java.lang.String) data);
		label.setIcon(thisIcon);
	}

	// ==============================================
	public void addPictures(String directoryName) {

		try {
			// Start with a brand new DoublyLinkedList
			myList = new DoublyLinkedList<String>();

			// Create a File object using the directory name
			File rootDirectory = new File("src/imageViewer/"+(java.lang.String) directoryName);
			System.out.println(rootDirectory.toString());
			// If it is not a directory send error message
			if (rootDirectory.isDirectory() == false) {
				JOptionPane
						.showMessageDialog(null,
								"ERROR: You must specify a directory from where to load pictures");
				System.out
						.println("ERROR: You must specify a directory from where to load pictures");
			} else
			// It is a directory. So process it further
			{
				try {

					// Create an array containig filenames that pass the filter
					String[] filesInDirectory = (String[]) rootDirectory
							.list(new MyFilter());

					// Add these file filenames to the DoublyLinkedList
					for (int cnt = 0; cnt < filesInDirectory.length; cnt++) {
						String nextFile = filesInDirectory[cnt];
						String fullFileName = (String) (rootDirectory
								.getCanonicalPath() + File.separator + nextFile);
						myList.insertAtEnd(fullFileName);
					}
					// Hopefully there are some images in the directory
					if (filesInDirectory.length > 0) {
						// Display the first picture on the screen
						myList.resetCurrentElement();
						currentIndex = 1;
						countLabel.setText(currentIndex + " of "
								+ myList.length());
						displayData(pictureLabel, myList.getCurrentElement());
					}
					// If not, inform the user
					else
						JOptionPane.showMessageDialog(null,
								"No Pictures in that folder."
										+ "Choose another folder");
				} catch (IOException ioe) {
					System.out.println("ERROR: IOException");
				}
			}
		} catch (SecurityException ex) {
			System.out.println("ERROR: Security Exception: " + ex.toString());
		}
	}

	// =============================================
	public static void main(String[] args) {
		new BorderLayoutDemo().demo();
	}
}
