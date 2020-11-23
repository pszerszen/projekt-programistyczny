package gameData;

import java.security.SecureRandom;

/**
 * Stores the data of course
 * 
 * @author Wociech Stokłosa
 */
public class Course {

	/**
	 * Contains parameter of difficulty of the course
	 */
	private int Difficulty;
	/**
	 * Contains Ects value
	 */
	private byte ECTS;
	/**
	 * Contains True - exams, false - colloquiums
	 */
	private boolean Exam;
	/**
	 * Contains dates of exams and colloquiums
	 */
	private int[] ExamsRound;
	/**
	 * Contains grade which player gets in Course
	 */
	private double Grade;
	/**
	 * Contains lecturer of this course
	 * 
	 * @see gameData.Lecturer
	 */
	private Lecturer Lecturer;
	/**
	 * Contains amount of colloquiums/exams
	 */
	private byte NumberOfColloquium;
	/**
	 * Contains information is this course realized again
	 */
	private boolean Retake;
	/**
	 * Contains number of semester when this course is to be realized
	 */
	private byte Semester;
	/**
	 * Contains round when course is to be started
	 */
	private int StartRound;
	/**
	 * Contains title of the course
	 */
	private String Title;

	/**
	 * Course Constructor
	 */
	public Course() {
	}

	/**
	 * Course Constructor
	 * 
	 * @param Title
	 *            Title of the Course
	 * @param Difficulty
	 *            Difficulty of the course, integer value
	 * @param Semester
	 *            Number of semester in wchich this course is
	 * @param Lecturer
	 *            Course leader lecturer
	 * @param NumberOfColloquium
	 *            number of colloquiums that the player will have to write
	 * @param StartRound
	 *            round in which the course starts
	 * @param ECTS
	 *            Value of course
	 * @see gameData.Lecturer
	 */
	public Course(String Title, int Difficulty, byte Semester,
			Lecturer Lecturer, byte NumberOfColloquium, int StartRound,
			byte ECTS) {
		this.Title = Title;
		this.Difficulty = Difficulty;
		this.Semester = Semester;
		this.Lecturer = Lecturer;
		this.NumberOfColloquium = NumberOfColloquium;
		this.StartRound = StartRound;
		this.ECTS = ECTS;
	}

	/**
	 * Returns Difficulty of course
	 * 
	 * @return Difficulty of the course
	 */
	public int getDifficulty() {
		return Difficulty;
	}

	/**
	 * Returns number of ECTS
	 * 
	 * @return number of ECTS
	 */
	public byte getECTS() {
		return ECTS;
	}

	/**
	 * Returns numbers of round when exams are to be taken
	 * 
	 * @return exam rounds
	 */
	public int[] getExamsRound() {
		return ExamsRound;
	}

	/**
	 * Returns Grade from Course
	 * 
	 * @return student's grade
	 */
	public double getGrade() {
		return Grade;
	}

	/**
	 * Returns Course's Lecturer
	 * 
	 * @return lecturer
	 */
	public Lecturer getLecturer() {
		return Lecturer;
	}

	/**
	 * Returns number of colloquiums
	 * 
	 * @return number of colloquiums
	 */
	public byte getNumberOfColloquium() {
		return NumberOfColloquium;
	}

	/**
	 * Returns number of semester when Course is pursued
	 * 
	 * @return Number of semester when this course is pursued
	 */
	public byte getSemester() {
		return Semester;
	}

	/**
	 * Returns index of round when Course is started
	 * 
	 * @return start round
	 */
	public int getStartRound() {
		return StartRound;
	}

	/**
	 * Returns Title of this Course
	 * 
	 * @return title of this course
	 */
	public String getTitle() {
		return Title;
	}

	/**
	 * Returns information must student write an exam
	 * 
	 * @return true if student will have to write an exam
	 */
	public boolean isExam() {
		return Exam;
	}

	/**
	 * Returns information is course pursued again
	 * 
	 * @return true if the course is pursued again
	 */
	public boolean isRetake() {
		return Retake;
	}

	/**
	 * Sets a random dates of Colloquium and exams
	 */
	public void RandomDates() {
		SecureRandom r = new SecureRandom();
		int randomweeks;
		int randomdays;
		int randomhours;
		if (Exam) {
			randomdays = r.nextInt(5) + 1;
			randomhours = r.nextInt(12) + 8;
			NumberOfColloquium = 2;
			ExamsRound = new int[2];
			ExamsRound[0] = 14 * 7 * 24 + randomdays * 24 + randomhours + 1;
			ExamsRound[1] = 15 * 7 * 24 + randomdays * 24 + randomhours + 1;
		} else {
			NumberOfColloquium = (byte) (1 + r.nextInt(3));
			ExamsRound = new int[NumberOfColloquium];
			for (int i = 0; i < NumberOfColloquium; i++) {
				randomweeks = r.nextInt((17 / (NumberOfColloquium + 1))) + 1;
				randomdays = r.nextInt(5) + 1;
				randomhours = r.nextInt(12) + 8;
				ExamsRound[i] = (i + 1)
						* (17 / (NumberOfColloquium + 1) * 7 * 24)
						+ randomweeks * 7 * 24 + +randomdays * 24 + randomhours;
			}
		}
		DataBase.cal.setExams(this);
	}

	/**
	 * Changes value in Course's private variable Difficulty into value from
	 * parameter
	 * 
	 * @param Difficulty
	 *            difficulty of the course
	 */
	public void setDifficulty(int Difficulty) {
		this.Difficulty = Difficulty;
	}

	/**
	 * Changes value in Course's private variable ECTS into value from parameter
	 * 
	 * @param ECTS
	 *            number of ECTS
	 */
	public void setECTS(byte ECTS) {
		this.ECTS = ECTS;
	}

	/**
	 * Changes value in Course's private variable Exam into value from parameter
	 * 
	 * @param exam
	 *            set as true if student will have to write an exam
	 */
	public void setExam(boolean exam) {
		Exam = exam;
	}

	public void setExamsRound(int number, int round) {
		this.ExamsRound[number] = round;
	}

	/**
	 * Changes value in Course's private variable ExamsRound into value from
	 * parameter
	 * 
	 * @param ExamsRound
	 *            int array with exams rounds
	 */
	public void setExamsRound(int[] ExamsRound) {
		this.ExamsRound = ExamsRound;
	}

	/**
	 * Changes value in Course's private variable Grade into value from
	 * parameter
	 * 
	 * @param Grade
	 *            student's grade
	 */
	public void setGrade(double Grade) {
		this.Grade = Grade;
	}

	/**
	 * Changes value in Course's private variable Lecturer into value from
	 * parameter
	 * 
	 * @param Lecturer
	 *            set course leader lecturer
	 */
	public void setLecturer(Lecturer Lecturer) {
		this.Lecturer = Lecturer;
	}

	/**
	 * Changes value in Course's private variable NumberOfColloquium into value
	 * from parameter
	 * 
	 * @param NumberOfColloquium
	 *            Number of colloquiums
	 */
	public void setNumberOfColloquium(byte NumberOfColloquium) {
		this.NumberOfColloquium = NumberOfColloquium;
	}

	/**
	 * Changes value in Course's private variable Retake into value from
	 * parameter
	 * 
	 * @param Retake
	 *            set as true if course is realized second time
	 */
	public void setRetake(boolean Retake) {
		this.Retake = Retake;
	}

	/**
	 * Changes value in Course's private variable Semester into value from
	 * parameter
	 * 
	 * @param Semester
	 *            semester
	 */
	public void setSemester(byte Semester) {
		this.Semester = Semester;
	}

	/**
	 * Changes value in Course's private variable StartRound into value from
	 * parameter
	 * 
	 * @param StartRound
	 *            start round
	 */
	public void setStartRound(int StartRound) {
		this.StartRound = StartRound;
	}

	/**
	 * Changes value in Course's private variable Title into value from
	 * parameter
	 * 
	 * @param title
	 *            title of the course
	 */
	public void setTitle(String title) {
		Title = title;
	}
}
