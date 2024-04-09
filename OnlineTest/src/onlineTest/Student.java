package onlineTest;

import java.util.*;
import java.io.Serializable;

public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<StudentExam> studentExams;
	
	public Student(String name) {
		this.name = name;
		this.studentExams = new ArrayList<StudentExam>();
	}
	
	public String getName() {
		return name;
	}
	
	public void answerQuestion(Exam examKey, int questionNum, String[] answer) {
		for (StudentExam sExam : studentExams) {
			if (sExam.getId() == examKey.getId()) {
				sExam.answerQuestion(questionNum, answer);
				return;
			}
		}
		StudentExam newExam = new StudentExam(examKey);
		studentExams.add(newExam);
		newExam.answerQuestion(questionNum, answer);
	}
	
	public double getExamScore(int examId) {
		for (StudentExam sExam : studentExams) {
			if (sExam.getId() == examId)
				return sExam.getExamScore();
		}
		return -1;
	}
	
	public String gradingReport(int examId) {
		for (StudentExam sExam : studentExams) {
			if (sExam.getId() == examId)
				return sExam.gradingReport();
		}
		return null;
	}
	
	public double getCourseNumericGrade() {
		double score = 0;
		for (StudentExam exam : studentExams) {
			score += exam.gradeExam();
		}
		return score/studentExams.size();
	}
}
