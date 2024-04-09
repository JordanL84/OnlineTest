package onlineTest;

import java.io.Serializable;

public class StudentExam extends Exam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Exam examKey;

	public StudentExam(Exam examKey) {
		super();
		this.examKey = examKey; 
	}
	
	public int getId() {
		return examKey.getId();
	}
	
	public void answerQuestion(int questionNum, String[] answer) {
		super.getQuestions().add(new Question(questionNum, answer,
				examKey.getType(questionNum)));
	}
	
	public Question getQuestion(int questionNum) {
		for (Question question : examKey.getQuestions()) {
			if (question.getQuestionNum() == questionNum) {
				return question;
			}
		}
		return null;
	}
	
	public double gradeQuestion(Question sAnswer, Question answer) {
		double score = 0;
		String[] temp = new String[sAnswer.getAnswer().length];
		for (int k = 0; k < temp.length; k++) {
			temp[k] = sAnswer.getAnswer()[k];
		}
		for (String str : answer.getAnswer()) {
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] != null && temp[i].equals(str)) {
					score++;
					temp[i] = null;
					continue;
				}
			}
		}
		if (answer.getType() < 3) {
			if (answer.getAnswer().length != temp.length)
				return 0;
			if (score == answer.getAnswer().length)
				return answer.getPoints();
			return 0;
		}
		return score * (answer.getPoints() / answer.getAnswer().length);
	}
	
	public double getExamScore() {
		double score = 0;
		for (Question sAnswer : super.getQuestions()) {
			score += gradeQuestion(sAnswer, getQuestion(sAnswer.getQuestionNum()));
		}
		return score;
	}
	
	public String gradingReport() {
		String str = "";
		double studentScore = 0;
		double totalPoints = 0;
		for (Question sAnswer : super.getQuestions()) {
			str += "Question #" + sAnswer.getQuestionNum() + " " +
					gradeQuestion(sAnswer, getQuestion(sAnswer.getQuestionNum())) +
					" points out of " + getQuestion(sAnswer.getQuestionNum()).getPoints() +
					"\n";
			studentScore += gradeQuestion(sAnswer, getQuestion(sAnswer.getQuestionNum()));
		}
		for (Question question : examKey.getQuestions()) {
			totalPoints += question.getPoints();
		}
		str += "Final Score: " + studentScore + " out of " + totalPoints;
		return str;
	}
	
	public double gradeExam() {
		double studentScore = 0;
		double totalPoints = 0;
		for (Question sAnswer : super.getQuestions())  {
			studentScore += gradeQuestion(sAnswer, getQuestion(sAnswer.getQuestionNum()));
		}
		for (Question question : examKey.getQuestions()) {
			totalPoints += question.getPoints();
		}
		return studentScore*100/totalPoints;
	}
}
