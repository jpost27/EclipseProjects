package studentGrades;

import javax.swing.*;

import java.awt.event.*;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;


public class CourseSectionGUI extends JFrame {
	private static final long serialVersionUID = -642512330209871846L;
	// Declare GUI Components
	private CourseSelection myList;
	private JLabel results;
	private JTextArea result;
	private JButton all;
	private JButton spec;
	private JButton high;
	private JButton low;
	private JButton quit;
	private JTextField inputGrade;
	private JScrollPane scrollingResult;

	// Constructor calls: instantiateGUIComponents(), buildGUI(), and
	// addListeners(); setDefaultCloseOperation, setSize(...) and
	// setVisible(true)
	public CourseSectionGUI(CourseSelection list) {
		myList = list;
		instantiateGUIComponents();
		buildGUI();
		addListeners();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 400);
		setVisible(true);
	}

	private void instantiateGUIComponents() {
		results = new JLabel("Results");
		inputGrade = new JTextField(1);
		all = new JButton("Show all students");
		spec = new JButton("Students with a spec. grade");
		high = new JButton("Students with highest average");
		low = new JButton("Students with lowest average");
		quit = new JButton("QUIT");
		result = new JTextArea("", 10, 80);
		scrollingResult = new JScrollPane(result);
		result.setEditable(false);
	}

	private void buildGUI() {
		// --------------Get Content Pane to add components
		Container c = getContentPane();
		c.setLayout(new GridLayout(2, 4, 5, 5));
		c.add(results);
		c.add(all);
		c.add(spec);
		c.add(inputGrade);
		c.add(scrollingResult);
		c.add(high);
		c.add(low);
		c.add(quit);
	}

	// -----------------
	private void addListeners() {
		// listener for the calculate button
		all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String res = myList.allStudents();
				result.setText(res);
			}
		});
		spec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String specGrade = inputGrade.getText();
				char grade = specGrade.charAt(0);
				String[] names = myList.studentsWithThisGrade(grade);
				String nameList = "";
				for (int x = 0; x < names.length; x++) {
					if (names[x] != null)
						nameList = nameList + "\n" + names[x];
				}
				result.setText(nameList);
			}
		});
		high.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] names = myList.studentsWithHighestAverage();
				String nameList = "";
				for (int x = 0; x < names.length; x++) {
					if (names[x] != null)
						nameList = nameList + "\n" + names[x];
				}
				result.setText(nameList);
			}
		});
		low.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseSelection c4 = new CourseSelection();
				String[] names = c4.studentsWithLowestAverage();
				String nameList = "";
				for (int x = 0; x < names.length; x++) {
					if (names[x] != null)
						nameList = nameList + "\n" + names[x];
				}
				result.setText(nameList);
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
		CourseSelection myList = new CourseSelection();
		new CourseSectionGUI(myList);
	}

}
