package gameData;

import java.util.ArrayList;

/**
 * The Day class stores the data of day
 * 
 * @author Wojciech Stokłosa
 * @see gameData.GameCalendar
 * @see gameData.Semester
 */
public class Day {

	/**
	 * List of activities player did / have to do at this day
	 */
	private String[] ActivityList;
	/**
	 * String with day's name
	 */
	private String DayOfWeek;
	/**
	 * Day's ID (number)
	 */
	private int ID;

	/**
	 * Day's constructor
	 * 
	 * @param ID
	 *            Day's ID
	 * @param DayOfWeek
	 *            Name of the day
	 */
	public Day(int ID, String DayOfWeek) {
		this.ID = ID;
		this.DayOfWeek = DayOfWeek;
		this.ActivityList = new String[24];
		for (int i = 0; i < 24; i++) {
			ActivityList[i] = "";
		}
	}

	/**
	 * Day constructor
	 * 
	 * @param ID
	 *            Day's ID
	 * @param DayOfWeek
	 *            Name of day
	 * @param ActivityList
	 *            List of activities player did / have to do at this day
	 */
	public Day(int ID, String DayOfWeek, String[] ActivityList) {
		this.ID = ID;
		this.DayOfWeek = DayOfWeek;
		this.ActivityList = ActivityList;
	}

	/**
	 * Returns table which contains hours and Activity's names that happened at
	 * this day
	 * 
	 * @return Object[][] contains hours and names of activities that happened
	 *         will happen this day
	 */
	public Object[][] getActivities() {
		ArrayList<String> activity = new ArrayList<String>();
		ArrayList<Integer> activityStart = new ArrayList<Integer>();
		ArrayList<Integer> activityLength = new ArrayList<Integer>();
		String LastActivity = "", hours;
		int i;
		Integer lastHour;
		for (i = 0; i < 24; i++) {
			if (!ActivityList[i].equals("")) {
				if (!ActivityList[i].equals(LastActivity)) {
					activity.add(ActivityList[i]);
					activityStart.add(i);
					activityLength.add(1);
				} else {
					activityLength.set(activityLength.size() - 1,
							activityLength.get(activityLength.size() - 1) + 1);
				}
			}
		}
		Object[][] Table = new Object[activity.size()][2];
		for (i = 0; i < activity.size(); i++) {
			lastHour = activityStart.get(i) + activityLength.get(i);
			hours = activityStart.get(i).toString() + ".00 - "
					+ lastHour.toString() + ".00";
			Table[i][0] = hours;
			Table[i][1] = activity.get(i);
		}
		return Table;
	}

	public String getActivity(int Round) {
		return ActivityList[Round];
	}

	/**
	 * Returns activity list
	 * 
	 * @return activity list
	 */
	public String[] getActivityList() {
		return ActivityList;
	}

	/**
	 * Returns Day's name
	 * 
	 * @return Name of the day
	 */
	public String getDayOfWeek() {
		return DayOfWeek;
	}

	/**
	 * Returns Day's ID
	 * 
	 * @return Day's ID
	 */
	public int getID() {
		return ID;
	}

	public boolean isExam(int Round) {
		if (ActivityList[Round].indexOf("Kolokwium") == -1
				&& ActivityList[Round].indexOf("Egzamin") == -1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Saves activity at this day
	 * 
	 * @param round
	 *            Round when activity happen
	 * @param activity
	 *            Activity's name
	 */
	public void setActivity(int round, int numberOfRounds, String activity) {
		for (int i = round; i < round + numberOfRounds; i++) {
			ActivityList[i] = activity;
		}
	}

	/**
	 * Changes value in Day's private variable ActivityList into value from
	 * parameter
	 * 
	 * @param ActivityList
	 *            activity list
	 */
	public void setActivityList(String[] ActivityList) {
		this.ActivityList = ActivityList;
	}

	/**
	 * Add colloquium to list of activities
	 * 
	 * @param round
	 *            Round when colloquim is to be happened
	 * @param course
	 *            Course which colloquim is to be taken
	 */
	public int setColloquium(int round, Course course) {
		int i = 0;
		while (ActivityList[(round % 24) - 1 + i].indexOf("Egzamin") != -1
				|| ActivityList[(round % 24) - 1 + i].indexOf("Kolokwium") != -1) {
			i++;
			if (i + round % 24 - 1 > 20)
				i -= 13;
		}
		ActivityList[(round % 24) - 1 + i] = "Kolokwium: " + course.getTitle();
		return i;
	}

	/**
	 * Add exam to list of activities
	 * 
	 * @param round
	 *            Round when exam is to be happened
	 * @param course
	 *            Course which exam is to be taken
	 */
	public int setExam(int round, Course course) {
		int i = 0;
		while (ActivityList[(round % 24) - 1 + i].indexOf("Egzamin") != -1
				|| ActivityList[(round % 24) - 1 + i].indexOf("Kolokwium") != -1) {
			i++;
			if (i + round % 24 - 1 > 20)
				i -= 13;
		}
		ActivityList[(round % 24) - 1 + i] = "Egzamin: " + course.getTitle();
		return i;
	}
}
