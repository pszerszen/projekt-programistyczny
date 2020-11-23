package gameData;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The GameCalendar class stores the semesters
 * 
 * @author Wojciech Stokłosa
 * @see gameData.Semester
 * @see gameData.Day
 */
public class GameCalendar {
	/**
	 * Number of days in semester
	 */
	private final int numberOfDays = 120;
	/**
	 * Number of semesters
	 */
	private final int numberOfSemesters = 7;
	/**
	 * List of semesters
	 */
	private ArrayList<Semester> SemesterList;

	/**
	 * Calendar constructor, creates empty list of activities and filled list of
	 * semesters
	 */
	public GameCalendar() {
		SemesterList = new ArrayList<Semester>();
		for (int i = 1; i <= numberOfSemesters; i++) {
			SemesterList.add(new Semester(i));
		}
	}

	/**
	 * Calendar constructor, creates calendar with list of activities
	 * 
	 * @param ActivityList
	 *            list of activities
	 */
	public GameCalendar(String[] ActivityList) {
		SemesterList = new ArrayList<Semester>();
		int i, numberOfActivitiesAtSemester = 120 * 24;
		for (i = 1; i <= 7; i++) {
			SemesterList.add(new Semester(i, Arrays.copyOfRange(ActivityList,
					(i - 1) * numberOfActivitiesAtSemester, i
							* numberOfActivitiesAtSemester)));
		}
	}

	public String getActivity(int round) {
		int Semester = round / (120 * 24);
		int Day = ((round - (Semester) * 120 * 24) / 24);
		return SemesterList.get(Semester).getDay(Day).getActivity(round % 24);
	}

	/**
	 * Returns list of day's activities in whole calendar as array of Strings
	 * 
	 * @return list of day's activities in whole calendar
	 */
	public String[] getActivityList() {
		int i, j, length = 120 * 24;
		String[] Activities = new String[numberOfSemesters * 120 * 24];
		String[] tempActivities;
		for (i = 0; i < numberOfSemesters; i++) {
			tempActivities = SemesterList.get(i).getActivities();
			for (j = 0; j < length; j++) {
				Activities[i * length + j] = tempActivities[j];
			}
		}
		return Activities;
	}

	/**
	 * Returns table which contains hours and Activity's names that happened at
	 * specified day. Using {@link gameData.Day #getActivities()}
	 * 
	 * @param numberOfSemester
	 *            number of specified semester
	 * @param numberOfDay
	 *            number of specified day
	 * @return object[][] of activities at specified day
	 */
	public Object[][] getDayActivities(int numberOfSemester, int numberOfDay) {
		Object[][] Table = SemesterList.get(numberOfSemester)
				.getDay(numberOfDay).getActivities();
		return Table;
	}

	/**
	 * Returns name of specified name
	 * 
	 * @param NumberOfSemester
	 *            number of semester
	 * @param NumberOfDay
	 *            number of day
	 * @return name of specified day
	 */
	public String getDayName(int NumberOfSemester, int NumberOfDay) {
		return SemesterList.get(NumberOfSemester).getDay(NumberOfDay)
				.getDayOfWeek();
	}

	/**
	 * Returns number of days at each semester
	 * 
	 * @return number of days at each semester
	 */
	public int getNumberOfDays() {
		return numberOfDays;
	}

	/**
	 * Returns number of days at in calendar
	 * 
	 * @return number of semesters in calendar
	 */
	public int getNumberOfSemesters() {
		return numberOfSemesters;
	}

	public boolean IsExamNow() {
		int Semester = DataBase.Round / (120 * 24);
		int Day = ((DataBase.Round - (Semester) * 120 * 24) / 24);
		return SemesterList.get(Semester).getDay(Day)
				.isExam(DataBase.Round % 24);
	}

	/**
	 * Sets activity in proper place in calendar
	 * 
	 * @param numberOfRounds
	 *            number of rounds
	 * @param round
	 *            round number
	 * @param activity
	 */
	public void setActivity(int round, int numberOfRounds, String activity) {
		int nextDayRounds, amountOfDays, i;
		int Semester = DataBase.Round / (120 * 24);
		int Day = ((DataBase.Round - (Semester) * 120 * 24) / 24);
		if (round % 24 + numberOfRounds < 24) {
			SemesterList.get(Semester).getDay(Day)
					.setActivity(round % 24, numberOfRounds, activity);
		} else {
			amountOfDays = (round % 24 + numberOfRounds) / 24;
			nextDayRounds = (round % 24 + numberOfRounds) % 24;
			SemesterList
					.get(Semester)
					.getDay(Day)
					.setActivity(round % 24, numberOfRounds - nextDayRounds,
							activity);
			if (amountOfDays >= 3) {
				for (i = 0; i < amountOfDays - 1; i++) {
					SemesterList.get(Semester).getDay(Day + i + 1)
							.setActivity(0, 24, activity);
				}
				SemesterList.get(Semester).getDay(Day + i)
						.setActivity(0, nextDayRounds, activity);
			} else
				SemesterList.get(Semester).getDay(Day + 1)
						.setActivity(0, nextDayRounds, activity);
		}

	}

	/**
	 * Fills dates of exams and colloquiums into calendar
	 * 
	 * @param course
	 *            course
	 */
	public void setExams(Course course) {
		SemesterList.get(course.getSemester() - 1).setExams(course);
	}

	public boolean WasExam(int round) {
		int Semester = round / (120 * 24);
		int Day = ((round - (Semester) * 120 * 24) / 24);
		return SemesterList.get(Semester).getDay(Day).isExam(round % 24);
	}
}
