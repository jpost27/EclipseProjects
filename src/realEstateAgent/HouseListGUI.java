package realEstateAgent;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

/**
 * GUI to search for houses
 * @author Josh
 *
 */
public class HouseListGUI extends JFrame {

	private static final long serialVersionUID = 369898866855651815L;
	// Declare GUI Components
	private HouseList myList;
	private JLabel criteria;
	private JLabel min;
	private JLabel max;
	private JLabel price;
	private JLabel area;
	private JLabel bedrooms;
	private JTextField inputMinimumPrice;
	private JTextField inputMaximumPrice;
	private JTextField inputMinimumArea;
	private JTextField inputMaximumArea;
	private JTextField inputMinimumNumberOfBedrooms;
	private JTextField inputMaximumNumberOfBedrooms;
	private JButton search;
	private JButton quit;
	private JTextArea result;
	private JScrollPane results;

	public HouseListGUI(HouseList list) {
		myList = list;
		instantiateGUIComponents();
		buildGUI();
		addListeners();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 400);
		setVisible(true);
	}

	private void instantiateGUIComponents() {
		criteria = new JLabel("Criteria");
		min = new JLabel("Min");
		max = new JLabel("Max");
		price = new JLabel("Price");
		area = new JLabel("Area");
		bedrooms = new JLabel("Number Of Bedrooms");
		inputMinimumPrice = new JTextField(10);
		inputMaximumPrice = new JTextField(10);
		inputMinimumArea = new JTextField(10);
		inputMaximumArea = new JTextField(10);
		inputMinimumNumberOfBedrooms = new JTextField(10);
		inputMaximumNumberOfBedrooms = new JTextField(10);
		search = new JButton("Search");
		quit = new JButton("QUIT");
		result = new JTextArea(" ");
		results = new JScrollPane(result);
	}

	private void buildGUI() {
		// --------------Get Content Pane to add components
		Container c = getContentPane();
		c.setLayout(new GridLayout(5, 3, 5, 5));
		c.add(criteria);
		c.add(min);
		c.add(max);
		c.add(price);
		c.add(inputMinimumPrice);
		c.add(inputMaximumPrice);
		c.add(area);
		c.add(inputMinimumArea);
		c.add(inputMaximumArea);
		c.add(bedrooms);
		c.add(inputMinimumNumberOfBedrooms);
		c.add(inputMaximumNumberOfBedrooms);
		c.add(search);
		c.add(results);
		c.add(quit);
	}

	// -----------------
	private void addListeners() {
		// listener for the calculate button
		search.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String minPrice = inputMinimumPrice.getText();
				String maxPrice = inputMaximumPrice.getText();
				String minArea = inputMinimumArea.getText();
				String maxArea = inputMaximumArea.getText();
				String minNumberOfBedrooms = inputMinimumNumberOfBedrooms
						.getText();
				String maxNumberOfBedrooms = inputMaximumNumberOfBedrooms
						.getText();

				int minP = Integer.parseInt(minPrice);
				int maxP = Integer.parseInt(maxPrice);
				int minA = Integer.parseInt(minArea);
				int maxA = Integer.parseInt(maxArea);
				int maxB = Integer.parseInt(maxNumberOfBedrooms);
				int minB = Integer.parseInt(minNumberOfBedrooms);

				Criteria c1 = new Criteria(minP, maxP, minA, maxA, minB, maxB);

				String res = myList.getHouses(c1);

				result.setText(res);
			}
		});

		// Listener for quit button
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {

		HouseList myList = new HouseList("src\\Project1\\houses.txt");
		new HouseListGUI(myList);
	}

}
