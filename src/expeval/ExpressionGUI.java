package expeval;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class ExpressionGUI extends JFrame {
	// Declare GUI Components
	private ExpressionEvaluator myList;
	private JLabel results;
	private JTextArea input;
	private JTextArea result;
	private JButton execute;
	private JButton quit;
	private JTextField inputExpression;
	private JScrollPane scrollingResult;

	// Constructor calls: instantiateGUIComponents(), buildGUI(), and
	// addListeners(); setDefaultCloseOperation, setSize(...) and
	// setVisible(true)
	public ExpressionGUI(ExpressionEvaluator list) {
		myList = list;
		instantiateGUIComponents();
		buildGUI();
		addListeners();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 300);
		setVisible(true);
	}

	private void instantiateGUIComponents() {
		results = new JLabel("Value of Expression");
		inputExpression = new JTextField(10);
		execute = new JButton("Calculate");
		quit = new JButton("Exit");
		input = new JTextArea("Enter Infix Expression");
		input.setEditable(false);
		result = new JTextArea("", 5, 5);
		scrollingResult = new JScrollPane(result);
		result.setEditable(false);
	}

	private void buildGUI() {
		// --------------Get Content Pane to add components
		Container c = getContentPane();
		c.setLayout(new GridLayout(3, 2, 5, 5));
		c.add(input);
		c.add(inputExpression);
		c.add(results);
		c.add(scrollingResult);
		c.add(execute);
		c.add(quit);
	}

	// -----------------
	private void addListeners() {
		// listener for the calculate button
		execute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String in = inputExpression.getText();
				Expression e1 = new Expression(in);
				int res = myList.evaluate(e1);
				String res1 = Integer.toString(res);
				result.setText(res1);
			}
		});

		// Listener for exit button
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		ExpressionEvaluator myList = new ExpressionEvaluator();
		new ExpressionGUI(myList);
	}

}
