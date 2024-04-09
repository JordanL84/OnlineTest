package onlineTest;

import java.io.*;
import java.util.*;

public final class SystemManager implements Manager, Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Exam> exams;
	private ArrayList<Student> students;
	String[] letterGrades;
	double[] cutoffs;
		
	public SystemManager() {
		this.exams = new ArrayList<Exam>();
		this.students = new ArrayList<Student>();
	}
	
	public boolean addExam(int examId, String title) {
		for (Exam exam : exams) {
			if (exam.getId() == examId)
				return false;
		}
		exams.add(new Exam(examId, title));
		return true;
	}
	
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, 
										double points, boolean answer) {
		String[] answerArr = new String[1];
		if (answer == true)
			answerArr[0] = "True";
		else
			answerArr[0] = "False";
		for (Exam exam : exams) {
			if (exam.getId() == examId) 
				exam.addQuestion(questionNumber, text, points, answerArr, 1);
		}
	}
	
	public void addMultipleChoiceQuestion(int examId, int questionNumber,String text, 
											double points, String[] answer) {
		for (Exam exam : exams) {
			if (exam.getId() == examId) 
				exam.addQuestion(questionNumber, text, points, answer, 2);
		}
	}
	
	public void addFillInTheBlanksQuestion(int examId, int questionNumber,
		    String text, double points, String[] answer) {
		for (Exam exam : exams) {
			if (exam.getId() == examId) 
				exam.addQuestion(questionNumber, text, points, answer, 3);
		}
	}
	
	public String getKey(int examId) {
		for (Exam exam : exams) {
			if (exam.getId() == examId)
				return exam.toString();
		}
		return "Exam not found";
	}
	
	public boolean addStudent(String name) {
		for (Student student : students) {
			if (student.getName().equals(name))
				return false;
		}
		students.add(new Student(name));
		return true;
	}
	
	public Exam getExam(int examId) {
		for (Exam exam : exams) {
			if (exam.getId() == examId)
				return exam;
		}
		return null;
	}
	
	public void answerTrueFalseQuestion(String studentName, int examId, 
			int questionNumber, boolean answer) {
		String[] answerArr = new String[1];
		if (answer == true)
			answerArr[0] = "True";
		else
			answerArr[0] = "False";
		for (Student student : students) {
			if (student.getName().equals(studentName))
				student.answerQuestion(getExam(examId), questionNumber, answerArr);
		}
	}
	
	public void answerMultipleChoiceQuestion(String studentName, int examId, 
			int questionNumber, String[] answer) {
		for (Student student : students) {
			if (student.getName().equals(studentName))
				student.answerQuestion(getExam(examId), questionNumber, answer);
		}
	}

	public void answerFillInTheBlanksQuestion(String studentName, int examId, 
			int questionNumber, String[] answer) {
		for (Student student : students) {
			if (student.getName().equals(studentName))
				student.answerQuestion(getExam(examId), questionNumber, answer);
		}
	}
	
	public double getExamScore(String studentName, int examId) {
		for (Student student : students) {
			if (student.getName().equals(studentName)) {
				return student.getExamScore(examId);
			}
		}
		return -1;
	}
	
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		this.letterGrades = letterGrades;
		this.cutoffs = cutoffs;
	}
	
	public double getCourseNumericGrade(String studentName) {
		for (Student student : students) {
			if (student.getName().equals(studentName))
				return student.getCourseNumericGrade();
		}
		return -1;
	}
	
	public String getCourseLetterGrade(String studentName) {
		Double grade = getCourseNumericGrade(studentName);
		for (int i = 0; i < cutoffs.length; i++) {
			if (grade >= cutoffs[i])
				return letterGrades[i];
		}
		return letterGrades[letterGrades.length-1];
	}
	
	public void sortStudents() { 
		for (int i = 0; i < students.size(); i++) {
			Student temp = students.get(i);
			int index = i;
			while (index > 0 && temp.getName().compareTo(students.get(i-1).getName()) < 0) {
				students.set(index, students.get(index-1));
				index--;
			}
			students.set(index, temp);
		}
	}
	
	public String getCourseGrades() {
		String grades = "";
		sortStudents();
		for (Student student : students) {
			grades += student.getName() + " " +
					getCourseNumericGrade(student.getName()) + " " +
					getCourseLetterGrade(student.getName()) + "\n";			
		}
		return grades;
	}
	
	public double getMaxScore(int examId) {
		double maxScore = 0;
		for (Student student : students) {
			if (student.getExamScore(examId) > maxScore)
				maxScore = student.getExamScore(examId);
		}
		return maxScore;
	}
	
	public double getMinScore(int examId) {
		double minScore = students.get(0).getExamScore(examId);
		for (Student student : students) {
			if (student.getExamScore(examId) < minScore)
				minScore = student.getExamScore(examId);
		}
		return minScore;
	}
	
	public double getAverageScore(int examId) {
		double total = 0;
		for (Student student : students) 
			total += student.getExamScore(examId);
		return total/students.size();
	}
	
	public String getGradingReport(String studentName, int examId) {
		for (Student student : students) {
			if (student.getName().equals(studentName)) 
				return student.gradingReport(examId);
		}
		return null;
	}
	
	public void saveManager(Manager manager, String fileName) {
		try {
			File file = new File(fileName);
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));

			output.writeObject(manager);
			output.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public Manager restoreManager(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			return new SystemManager();
		} else {
			ObjectInputStream input;
			SystemManager manager = null;
			try {
				input = new ObjectInputStream(new FileInputStream(file));
				//SystemManager manager;
				try {
					manager = (SystemManager) input.readObject();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				input.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return manager;
		}
	}
}
