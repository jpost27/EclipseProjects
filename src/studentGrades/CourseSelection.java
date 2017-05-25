package studentGrades;

import linkedlist.*;
/**
 * Represents a Course
 * @author Josh
 *
 */
public class CourseSelection {
	private DoublyLinkedList<Student> studentList;
	private double highestAverage;
	private double lowestAverage;

	/**
	 * Constructs a course with students
	 */
	public CourseSelection() {
		studentList = new DoublyLinkedList<Student>();
		studentList.insertAtEnd(new Student("aaa", 60, 69, 79));
		studentList.insertAtEnd(new Student("aab", 70, 89, 59));
		studentList.insertAtEnd(new Student("aac", 80, 79, 89));
		studentList.insertAtEnd(new Student("aad", 90, 89, 99));
		studentList.insertAtEnd(new Student("aae", 40, 59, 89));
		studentList.insertAtEnd(new Student("aba", 60, 69, 79));
		studentList.insertAtEnd(new Student("abb", 30, 89, 59));
		studentList.insertAtEnd(new Student("abc", 80, 89, 69));
		studentList.insertAtEnd(new Student("abd", 90, 89, 79));
		studentList.insertAtEnd(new Student("abe", 40, 59, 69));
		studentList.insertAtEnd(new Student("aca", 70, 69, 79));
		studentList.insertAtEnd(new Student("acb", 70, 89, 59));
		studentList.insertAtEnd(new Student("acc", 80, 79, 69));
		studentList.insertAtEnd(new Student("acd", 90, 89, 99));
		studentList.insertAtEnd(new Student("ace", 40, 59, 89));
		studentList.insertAtEnd(new Student("ada", 60, 88, 79));
		studentList.insertAtEnd(new Student("adb", 70, 89, 79));
		studentList.insertAtEnd(new Student("adc", 80, 79, 89));
		studentList.insertAtEnd(new Student("add", 90, 79, 79));
		studentList.insertAtEnd(new Student("ade", 10, 59, 49));
		computeAndSetHighestAverage();
		computeAndSetLowestAverage();
	}
	/**
	 * Determines the highest average
	 */
	private void computeAndSetHighestAverage() {
		studentList.resetCurrentElement();
		highestAverage = 0;
		while (studentList.hasMoreElements())

		{
			double average = studentList.getCurrentElement().getAverage();
			if (average > highestAverage)
				highestAverage = average;
			studentList.nextElement();
		}
	}
	/**
	 * Determines the lowest average
	 */
	private void computeAndSetLowestAverage() {
		studentList.resetCurrentElement();
		lowestAverage = 100;
		while (studentList.hasMoreElements()) {
			double average = studentList.getCurrentElement().getAverage();
			if (average < lowestAverage)
				lowestAverage = average;
			studentList.nextElement();
		}

	}
	/**
	 * Creates a String of all the student names
	 * @return String of student names
	 */
	public String allStudents() {
		String studentNames = new String();
		studentList.resetCurrentElement();
		while (studentList.hasMoreElements()) {
			studentNames = studentNames + "\n"
					+ studentList.getCurrentElement().getName();
			studentList.nextElement();
		}
		return studentNames;
	}
	/**
	 * Creates String array of students with highest average
	 * @return String array of the student names
	 */
	public String[] studentsWithHighestAverage() {
		double average = 0;
		int k = 0;
		String[] studentNames = new String[studentList.length()];
		studentList.resetCurrentElement();
		while (studentList.hasMoreElements()) {
			average = studentList.getCurrentElement().getAverage();
			if (average == highestAverage) {
				studentNames[k] = studentList.getCurrentElement().getName();
				k++;
			}
			studentList.nextElement();
		}
		return studentNames;
	}
	/**
	 * Creates String array of students with lowest average
	 * @return String array of the student names
	 */
	public String[] studentsWithLowestAverage() {
		double average = 0;
		int k = 0;
		String[] studentNames = new String[studentList.length()];
		studentList.resetCurrentElement();
		while (studentList.hasMoreElements()) {
			average = studentList.getCurrentElement().getAverage();
			if (average == lowestAverage) {
				studentNames[k] = studentList.getCurrentElement().getName();
				k++;
			}
			studentList.nextElement();
		}
		return studentNames;
	}
	/**
	 * Creates String array of students with given grade
	 * @return String array of the student names
	 */
	public String[] studentsWithThisGrade(char g) {
		char grade = 'x';
		int k = 0;
		String[] studentNames = new String[studentList.length()];
		studentList.resetCurrentElement();
		while (studentList.hasMoreElements()) {
			grade = studentList.getCurrentElement().getLetterGrade();
			if (grade == g) {
				studentNames[k] = studentList.getCurrentElement().getName();
				k++;
			}
			studentList.nextElement();
		}
		return studentNames;
	}
}
