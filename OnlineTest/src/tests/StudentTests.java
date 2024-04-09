package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import onlineTest.SystemManager;

/**
 * 
 * You need student tests if you are looking for help during office hours about
 * bugs in your code.
 * 
 * @author UMCP CS Department
 *
 */
public class StudentTests {

	@Test
	public void studentTest1() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		manager.addTrueFalseQuestion(10, 1, "Abstract classes cannot have constructors.", 2, false);
		manager.addTrueFalseQuestion(10, 2, "The equals method returns a boolean.", 4, true);
		String questionText = "Which of the following are valid ids?\n";
		questionText += "A: house   B: 674   C: age   D: &";
		manager.addMultipleChoiceQuestion(10, 3, questionText, 3, new String[]{"A","C"});
		questionText = "Name two types of initialization blocks.";
		manager.addFillInTheBlanksQuestion(10, 4, questionText, 4, new String[]{"static","non-static"});
		//System.out.println(manager.getKey(10));
		answer.append(manager.getKey(10));
		assertTrue(TestingSupport.isResultCorrect(testName, answer.toString(), true));
	}
	
	@Test
	public void studentTest2() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		//StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		manager.addTrueFalseQuestion(10, 1, "Abstract classes cannot have constructors.", 2, false);
		manager.addTrueFalseQuestion(10, 2, "The equals method returns a boolean.", 4, true);
		String questionText = "Which of the following are valid ids?\n";
		questionText += "A: house   B: 674   C: age   D: &";
		manager.addMultipleChoiceQuestion(10, 3, questionText, 3, new String[]{"A","C"});
		questionText = "Name two types of initialization blocks.";
		manager.addFillInTheBlanksQuestion(10, 4, questionText, 4, new String[]{"static","non-static"});
		
		String studentName1 = "Smith,John";
		manager.addStudent(studentName1);
		manager.answerTrueFalseQuestion(studentName1, 10, 1, false);
		manager.answerTrueFalseQuestion(studentName1, 10, 2, true);
		manager.answerMultipleChoiceQuestion(studentName1, 10, 3, new String[]{"A", "C"});
		manager.answerFillInTheBlanksQuestion(studentName1, 10, 4, new String[]{"static", "non-static"});
		
		String studentName2 = "Lin, Jordan";
		manager.addStudent(studentName2);
		manager.answerTrueFalseQuestion(studentName2, 10, 1, false);
		manager.answerTrueFalseQuestion(studentName2, 10, 2, false);
		manager.answerMultipleChoiceQuestion(studentName2, 10, 3, new String[]{"A", "C", "D"});
		manager.answerFillInTheBlanksQuestion(studentName2, 10, 4, new String[]{"static", "non-static"});
		
		manager.setLetterGradesCutoffs(new String[]{"A+","A", "B+", "B", "C", "D", "F"}, 
										new double[]{95,90,85,80,70,60,0});
		assertTrue(13.0 == manager.getExamScore(studentName1, 10));
		assertTrue(100.0 == manager.getCourseNumericGrade(studentName1));
		assertEquals("A+", manager.getCourseLetterGrade(studentName1));
		assertEquals("F", manager.getCourseLetterGrade(studentName2));
		assertTrue(13.0 == manager.getMaxScore(10));
		assertTrue(6.0 == manager.getMinScore(10));
		assertTrue(9.5 == manager.getAverageScore(10));
		assertTrue(TestingSupport.isResultCorrect(testName, manager.getGradingReport(studentName2, 10), true));
		//System.out.println(manager.getGradingReport(studentName2, 10));
	}
	
	@Test
	public void studentTest3() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		manager.addTrueFalseQuestion(10, 1, "Dogs are cute", 2, true);
		manager.addTrueFalseQuestion(10, 2, "Ice cream sucks", 4, false);
		String questionText = "Which of the following are football teams\n";
		questionText += "A: Packers   B: Dogs   C: Jets   D: Buccaneers";
		manager.addMultipleChoiceQuestion(10, 3, questionText, 3, new String[]{"A","C", "D"});
		questionText = "Name two types of initialization blocks.";
		manager.addFillInTheBlanksQuestion(10, 4, questionText, 4, new String[]{"static","non-static"});
		
		String fileName = "serializedManager.ser";
		manager.saveManager(manager, fileName);
		SystemManager restoredManager = (SystemManager) manager.restoreManager(fileName);	
		
		//answer.append("After restoring");
		answer.append(restoredManager.getKey(10));
		
		assertTrue(TestingSupport.isResultCorrect(testName, answer.toString(), true));
		System.out.println(answer.toString());
	}
}
