package onlineTest;

import java.util.*;
import java.io.Serializable;

public class Exam implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Question> questions;
	private int examId;
	private String title;
	
	public Exam() {
		questions = new ArrayList<Question>();
	}
	
	public Exam(int examId, String title) {
		this.examId = examId;
		this.title = title;
		questions = new ArrayList<Question>();
	}
	
	public int getId() {
		return examId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	public int getType(int questionNum) {
		for (Question question : questions) {
			if (question.getQuestionNum() == questionNum)
				return question.getType();
		}
		return 0;
	}
	
	public void addQuestion(int questionNum, String text, double points, String[] answer, int type) {
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getQuestionNum() == questionNum) {
				questions.remove(questions.get(i));
				break;
			}
		}
		questions.add(new Question(questionNum, text, points, answer, type));
	}
	
	public String toString() {
		String str = "";
		for (Question question : questions)
			str += question.toString();
		return str;
	}
}
