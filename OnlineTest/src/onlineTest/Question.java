package onlineTest;

import java.io.Serializable;

public class Question implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int questionNum;
	private String text;
	private double points;
	private String[] answer;
	private int type;
	
	public Question(int questionNum, String[] answer, int type) {
		this.questionNum = questionNum;
		this.answer = answer;
		this.type = type;
	}
	
	public Question(int questionNum, String text, double points, String[] answer, int type) {
		this.questionNum = questionNum;
		this.text = text;
		this.points = points;
		this.answer = answer;
		this.type = type;
	}
	
	public int getQuestionNum() {
		return questionNum;
	}
	
	public String[] getAnswer() {
		return answer;
	}
	
	public int getType() {
		return type;
	}
	
	public double getPoints() {
		return points;
	}
	
	public String answerToString() {
		if (type == 1) 
			return answer[0];
		String answ = "[";
		if (type == 2) {
			for (int i = 0; i < answer.length-1; i ++) 
				answ += answer[i] + ", ";
			answ += answer[answer.length-1] + "]";
			return answ;
		}
		for (int i = answer.length-1; i > 0; i--) 
			answ += answer[i] + ", ";
		answ += answer[0] + "]";
		return answ;
	}
	
	public String toString() {
		return "Question Text: " + text + "\nPoints: " + points +
				"\nCorrect Answer: " + answerToString() + "\n";		
	}
}
