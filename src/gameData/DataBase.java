package gameData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.xml.stream.XMLStreamException;

/**
 * DataBase class that gives static access to important game data.
 * 
 * @author Marcin Szymkowiak
 * 
 */
public class DataBase {

	/**
	 * Represents XMLhandler object
	 */
	private static XMLhandler xmlhandler;
	/**
	 * Contains list of Activities
	 */
	public static ArrayList<Activity> activities = new ArrayList<>();
	/**
	 * Represents GameCalendar object
	 */
	public static GameCalendar cal = new GameCalendar();
	/**
	 * Contains list of Courses
	 */
	public static ArrayList<Course> courses = new ArrayList<>();
	/**
	 * Path to Current File
	 */
	public static String currentFileName = "";
	/**
	 * Contains current DayTime value
	 */
	public static Byte DayTime = 0;
	/**
	 * Contains list of Distributions
	 */
	public static ArrayList<Distribution> distributions = new ArrayList<>();
	/**
	 * Contains player's majority
	 */
	public static String FieldOfStudy = "";
	/**
	 * Contains list of Items
	 */
	public static ArrayList<Item> items = new ArrayList<>();
	/**
	 * Contains list of Lecturers
	 */
	public static ArrayList<Lecturer> lecturers = new ArrayList<>();
	/**
	 * Represents Log object
	 */
	public static Log log = new Log();
	/**
	 * Represents Player object
	 */
	public static Player player = new Player();
	/**
	 * Represents current round number
	 */
	public static int Round = 0;

	/**
	 * Represents Shop object
	 */
	public static Shop WallMart = new Shop();

	/**
	 * Drawing random number of lecturers and setting them to Courses.
	 * 
	 * @throws XMLStreamException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void drawLecturers() throws XMLStreamException,
			FileNotFoundException, IOException {
		xmlhandler = new XMLhandler();
		Random rand = new Random();
		int limit = rand.nextInt(courses.size() / 2) + 10;
		Lecturer tempL;
		String drawnNames[];
		for (int i = 0; i <= limit; i++) {
			tempL = new Lecturer();
			drawnNames = xmlhandler.drawName();
			tempL.setID(String.valueOf(i));
			tempL.setDegree(drawnNames[0]);
			tempL.setName(drawnNames[1]);
			tempL.setSurname(drawnNames[2]);
			tempL.setExpectations(rand.nextInt(101));
			tempL.setNaivety(rand.nextInt(101));
			lecturers.add(tempL);
		}
		for (int i = 0; i < courses.size(); i++) {
			courses.get(i).setLecturer(
					lecturers.get(rand.nextInt(lecturers.size())));
		}
	}

	/**
	 * Searches for Activity with given name.
	 * 
	 * @param name
	 *            Name of Activity to be found.
	 * 
	 * @return Searched Activity
	 */
	public static Activity getActivity(String name) {
		int i = 0;
		while (i < activities.size()
				&& !activities.get(i).getName().equals(name)) {
			i++;
		}
		return activities.get(i);
	}

	/**
	 * Searches for Distribution with given name.
	 * 
	 * @param distributionName
	 *            Name of Distribution to be found.
	 * 
	 * @return Searched Distribution
	 */
	public static Distribution getDistribution(String distributionName) {
		Distribution R = null;
		for (int i = 0; i < distributions.size(); i++) {
			if (distributions.get(i).getName().equals(distributionName)) {
				R = distributions.get(i);
			}
		}
		return R;
	}

	/**
	 * Searches for item with given name.
	 * 
	 * @param name
	 *            Name of Item to be found.
	 * 
	 * @return Searched Item
	 */
	public static Item getItem(String name) {
		int i = 0;
		while (!items.get(i).getName().equals(name) && i < items.size()) {
			i++;
		}
		return items.get(i);
	}

	/**
	 * Loads Activities from default file (res/Activities.xml). Calls method
	 * {@link gameData.XMLhandler#loadActivities}
	 * 
	 * @see gameData.XMLhandler
	 * @throws XMLStreamException
	 * @throws FileNotFoundException
	 */
	public static void loadActivities() throws XMLStreamException,
			FileNotFoundException {
		xmlhandler = new XMLhandler();
		activities = xmlhandler.loadActivities();
	}

	/**
	 * Loads Distributions from default file. (res/Distributions.xml). Calls
	 * method {@link gameData.XMLhandler#loadDistribution() }.
	 * 
	 * @see gameData.XMLhandler
	 * @throws XMLStreamException
	 * @throws FileNotFoundException
	 */
	public static void loadDistributions() throws XMLStreamException,
			FileNotFoundException {
		xmlhandler = new XMLhandler();
		distributions = xmlhandler.loadDistribution();
	}

	/**
	 * Loads Items from default file (res/Items.xml).
	 * {@link gameData.XMLhandler#loadItems(java.lang.String) }
	 * 
	 * @see gameData.XMLhandler
	 * @throws XMLStreamException
	 * @throws FileNotFoundException
	 */
	public static void loadItems() throws XMLStreamException,
			FileNotFoundException {
		xmlhandler = new XMLhandler();
		items = xmlhandler.loadItems("res/Items.xml");
	}

	/**
	 * Loads courses from given file. Calls method
	 * {@link gameData.XMLhandler#loadCourses(java.lang.String) }
	 * 
	 * @param filename
	 *            Path to file.
	 * 
	 * @see gameData.XMLhandler
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws XMLStreamException
	 */
	public static void loadKursy(String filename) throws FileNotFoundException,
			IOException, XMLStreamException {
		xmlhandler = new XMLhandler();
		courses = new ArrayList<>();
		courses = xmlhandler.loadCourses(filename);
	}

	/**
	 * Loads game from given file. Calls methods:
	 * {@link gameData.XMLhandler#loadGameParameters(java.lang.String)} ,
	 * {@link gameData.XMLhandler#loadPlayer(java.lang.String)} ,
	 * {@link gameData.XMLhandler#loadLecturers(java.lang.String)},
	 * {@link gameData.XMLhandler#loadCourses(java.lang.String) } and
	 * {@link gameData.XMLhandler#loadCalendar(java.lang.String) } from Warning!
	 * Order of loading is important! Do not load Courses before Lecturers!
	 * 
	 * @param filename
	 *            Path to file. (file name included)
	 * 
	 * @see gameData.XMLhandler
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 */
	public static void loadSavedGame(String filename)
			throws FileNotFoundException, XMLStreamException {
		xmlhandler = new XMLhandler();
		xmlhandler.loadGameParameters(filename);
		activities = xmlhandler.loadActivities();
		items = xmlhandler.loadItems("res/Items.xml");
		player = new Player();
		player = xmlhandler.loadPlayer(filename);
		distributions = xmlhandler.loadDistribution();
		lecturers = new ArrayList<>();
		lecturers = xmlhandler.loadLecturers(filename);
		courses = new ArrayList<>();
		courses = xmlhandler.loadCourses(filename);
		cal = xmlhandler.loadCalendar(filename);
		log = xmlhandler.loadLog(filename);
		currentFileName = filename;
	}

	/**
	 * Shows a window showing disgusted Star Treck character and a comment that
	 * player has just lost the game
	 */
	public static void lost() {
		DataBase.theEnd();
		JOptionPane
				.showMessageDialog(
						null,
						"Nie jesteś jeszcze gotów/gotowa na prawdziwe studenckie życie",
						"KONIEC GRY", JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon("res/lost.jpg"));
	}

	/**
	 * Calls the static method from {@link gameData.XMLhandler } with the same
	 * name
	 * 
	 * @return a password and definition set as a String array with size 2
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String[] randHangmanQuestion() throws FileNotFoundException,
			IOException {
		return xmlhandler.randHangmanQuestion("res/hangman/" + FieldOfStudy
				+ ".txt");
	}

	/**
	 * Saves game to given path. Calls method
	 * {@link gameData.XMLhandler#saveGame(java.lang.String, gameData.Player, gameData.GameCalendar, java.util.ArrayList, java.util.ArrayList, gameData.Log) }
	 * 
	 * @param filename
	 *            Path to file (file name includeds).
	 * 
	 * @see gameData.XMLhandler
	 * @throws XMLStreamException
	 * @throws FileNotFoundException
	 */
	public static void saveGame(String filename) throws IOException,
			XMLStreamException {
		xmlhandler = new XMLhandler();
		xmlhandler.saveGame(filename, player, cal, lecturers, courses, log);
		currentFileName = filename;
	}

	/**
	 * Sets the players majority
	 * 
	 * @param string
	 *            name of majority to be set
	 */
	public static void setFieldOfStudy(String string) {
		FieldOfStudy = string;
	}

	/**
	 * Calculates grade.
	 * 
	 * @param gameGrade
	 */
	public static void setGrade(double gameGrade) {
		Course tempC = null;
		String tempS = cal.getActivity(Round - 1);
		tempS = tempS.split(":")[1];
		tempS = tempS.trim();
		for (int i = 0; i < courses.size(); i++) {
			tempC = courses.get(i);
			if (tempC.getTitle().equals(tempS)) {
				break;
			}
		}
		double tempD = (((double) (player.getKnowledge()
				+ player.getHappiness() + player.getEnergy())
				/ 2000
				+ (double) player.getInteligence()
				/ 15
				+ (double) (2 * player.getCharisma())
				/ 20
				+ (double) player.getStressResistance() / 15 - (double) (tempC
				.getLecturer().getExpectations()) / 80) + (0.5 - (double) tempC
				.getDifficulty() / 100))
				* gameGrade;
		if (tempD > 5.5) {
			tempD = 5.5;
		} else if (tempD < 2.5) {
			tempD = 2.0;
		} else if (tempD < 3.0) {
			tempD = 3.0;
		}
		tempD = Math.floor(tempD * 2) / 2;
		tempC.setGrade(tempC.getGrade() + tempD);
	}

	/**
	 * Flushes all data in {@link DataBase}
	 */
	public static void theEnd() {
		Round = 0;
		cal = new GameCalendar();
		log = new Log();
		player = new Player();
		courses = new ArrayList<>();
		activities = new ArrayList<>();
		items = new ArrayList<>();
	}

	/**
	 * Shows a window informing that player has just won the game
	 */
	public static void won() {
		JOptionPane
				.showMessageDialog(
						null,
						"GRATULACJE!! Studia I-go stopnia ukończone, tytuł inżyniera masz w kieszeni.",
						"KONIEC GRY", JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon("res/won.jpg"));
	}
}