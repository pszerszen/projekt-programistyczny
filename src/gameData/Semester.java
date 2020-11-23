package gameData;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Semester class stores the days
 * 
 * @author Wojciech Stokłosa
 * @see gameData.GameCalendar
 * @see gameData.Day
 */
public class Semester {

	/**
	 * List of days in this semester
	 */
	private ArrayList<Day> DayList;
	/**
	 * Array with days of week
	 */
	private final String DaysOfWeek[] = { "Poniedziałek", "Wtorek", "Środa",
			"Czwartek", "Piątek", "Sobota", "Niedziela" };
	/**
	 * ID (number) of semester
	 */
	private int ID;
	/**
	 * Number of days in semester
	 */
	private final int NumberOfDays = 120;

	/**
	 * Semester constructor
	 * 
	 * @param ID
	 *            Semester's ID
	 */
	public Semester(int ID) {
		DayList = new ArrayList<>();
		this.ID = ID;
		for (int i = 0; i < NumberOfDays; i++) {
			DayList.add(new Day(i, DaysOfWeek[i % 7]));
		}
	}

	/**
	 * Semester constructor, create days with list of activities
	 * 
	 * @param ID
	 *            ID
	 * @param ActivityList
	 *            list of activities
	 */
	public Semester(int ID, String[] ActivityList) {
		this.ID = ID;
		DayList = new ArrayList<>();
		for (int i = 0; i < NumberOfDays; i++) {

			DayList.add(new Day(i, DaysOfWeek[i % 7], Arrays.copyOfRange(
					ActivityList, i * 24, (i + 1) * 24)));
		}
	}

	/**
	 * Returns list consisting of lists of day's activities
	 * 
	 * @return List consisting of lists of day's activities
	 */
	public String[] getActivities() {
		int i, j;
		String[] Activities = new String[NumberOfDays * 24];
		String[] tempActivities;
		for (i = 0; i < NumberOfDays; i++) {
			tempActivities = DayList.get(i).getActivityList();
			for (j = 0; j < 24; j++) {
				Activities[i * 24 + j] = tempActivities[j];
			}
		}
		return Activities;
	}

	/**
	 * Returns day selected by index of parameter
	 * 
	 * @param i
	 *            Index of day
	 * @return Selected day
	 */
	public Day getDay(int i) {
		return DayList.get(i);
	}

	/**
	 * Returns semester's ID
	 * 
	 * @return Semester's ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Add exams and colloquiums of specified course to semester
	 * 
	 * @param course
	 *            course which exams and colloquiums are to be taken
	 */
	public void setExams(Course course) {
		if (course.isExam()) {
			for (int i = 0; i < course.getExamsRound().length; i++) {
				course.setExamsRound(
						i,
						(course.getExamsRound()[i] + DayList.get(
								(course.getExamsRound()[i] - 2) / 24).setExam(
								course.getExamsRound()[i], course)));
			}
		} else {
			for (int i = 0; i < course.getExamsRound().length; i++) {
				course.setExamsRound(i, (course.getExamsRound()[i] + DayList
						.get((course.getExamsRound()[i] - 2) / 24)
						.setColloquium(course.getExamsRound()[i], course)));
				;
			}
		}
	}

	/**
	 * Changes value in Semester's private variable ID into value from parameter
	 * 
	 * @param ID
	 *            Variable which is to be changed
	 */
	public void setID(int ID) {
		this.ID = ID;
	}
}
