package gameData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 * XMLhandler handles loading and saving game information from files.
 * 
 * @author Marcin Szymkowiak
 */
public class XMLhandler {

	private static final String ACTIVITY = "Activity";
	private static final String ACTIVITYCHARISMA = "ActivityCharisma";
	private static final String ACTIVITYENDURANCE = "ActivityEndurance";
	private static final String ACTIVITYENERGY = "ActivityEnergy";
	private static final String ACTIVITYHAPPINESS = "ActivityHappiness";
	private static final String ACTIVITYINTELLIGENCE = "ActivityIntelligence";
	private static final String ACTIVITYKNOWLEDGE = "ActivityKnowledge";
	private static final String ACTIVITYMONEY = "ActivityMoney";
	private static final String ACTIVITYNAME = "ActivityName";
	private static final String ACTIVITYSCRUPULOUSNESS = "ActivityScrupulousness";
	private static final String ACTIVITYSTRESSRESISTANCE = "ActivityStressResistance";
	private static final String CALENDAR = "Calendar";
	private static final String CALENDARACTIVITY = "CalendarActivity";
	private static final String CHARISMA = "Charisma";
	private static final String COURSE = "Course";
	private static final String DAYTIME = "DayTime";
	private static final String DEGREE = "Degree";
	private static final String DESCRIPTION = "Description";
	private static final String DIFFICULTY = "Difficulty";
	private static final String DISTRIBUTIONNAME = "DistributionName";
	private static final String DURATION = "Duration";
	private static final String ECTS = "ECTS";
	private static final String ECTSDEF = "EctsDef";
	private static final String ENDURANCE = "Endurance";
	private static final String ENERGY = "Energy";
	private static final String EVENTENERGY = "EventEnergy";
	private static final String EVENTHAPPINESS = "EventHappiness";
	private static final String EVENTKNOWLEDGE = "EventKnowledge";
	private static final String EVENTMONEY = "EventMoney";
	private static final String EXAM = "Exam";
	private static final String EXAMROUND1 = "ExamRound1";
	private static final String EXAMROUND2 = "ExamRound2";
	private static final String EXAMROUND3 = "ExamRound3";
	private static final String FIELDOFSTUDY = "FieldOfStudy";
	private static final String GAMEPARAMETERS = "GameParameters";
	private static final String GRADE = "Grade";
	private static final String HAPPINESS = "Happiness";
	private static final String ID = "ID";
	private static final String INTELLIGENCE = "Intelligence";
	/**
	 * Strings used for searching elements and their parameters in XML files.
	 * Have to be static and final to be used in switch-case.
	 */
	private static final String ITEM = "Item";
	private static final String ITEMACTIVE = "ItemActive";
	private static final String ITEMCHARISMA = "ItemCharisma";
	private static final String ITEMENDURANCE = "ItemEndurance";
	private static final String ITEMENERGY = "ItemEnergy";
	private static final String ITEMHAPPINESS = "ItemHappiness";
	private static final String ITEMINTELLIGENCE = "ItemIntelligence";
	private static final String ITEMKNOWLEDGE = "ItemKnowledge";
	private static final String ITEMNAME = "ItemName";
	private static final String ITEMSCRUPULOUSNESS = "ItemScrupulousness";
	private static final String ITEMSTRESSRESISTANCE = "ItemStressResistance";
	private static final String KNOWLEDGE = "Knowledge";
	private static final String LECTURER = "Lecturer";
	private static final String LECTUREREXPECTATIONS = "LecturerExpectations";
	private static final String LECTURERID = "LecturerID";
	private static final String LECTURERNAIVETY = "LecturerNaivety";
	private static final String LECTURERNAME = "LecturerName";
	private static final String LECTURERSURNAME = "LecturerSurname";
	private static final String LOG = "Log";
	private static final String LOGELEMENT = "LogElement";
	private static final String MONEY = "Money";
	private static final String NAME = "Name";
	private static final String NUMBEROFCOLLOQUIUM = "NumberOfColloquium";
	private static final String NUMBEROFROUNDS = "NumberOfRounds";
	private static final String PLAYER = "Player";
	private static final String PRICE = "Price";
	private static final String PROBABILITY = "Probability";
	private static final String RANDOMEVENT = "RandomEvent";
	private static final String RETAKE = "Retake";
	private static final String ROUND = "Round";
	private static final String ROUNDSTAKEN = "RoundsTaken";
	private static final String SAVE = "Save";
	private static final String SCRUPULOUSNESS = "Scrupulousness";
	private static final String SEMESTER = "Semester";
	private static final String STARTROUND = "StartRound";
	private static final String STARTSACTIVITY = "StartsActivity";
	private static final String STRESSRESISTANCE = "StressResistance";
	private static final String SURNAME = "Surname";
	private static final String TIMEPARAMETER = "TimeParameter";
	private static final String TITLE = "Title";
	private InputStream ActivitiesIn;
	private XMLStreamReader ActivitiesStreamReader;
	private InputStream DistributionsIn;
	private XMLStreamReader DistributionsStreamReader;
	private InputStream EventsIn;
	private XMLStreamReader EventsStreamReader;
	/**
	 * XMLInput factory used to create XMLStreamReaders.
	 */
	private XMLInputFactory factory;
	/**
	 * ArrayList which contains all items.
	 */
	private ArrayList<Item> items;
	/**
	 * InputStreams for Items, Activities, Events and Distributions.
	 */
	private InputStream ItemsIn;
	/**
	 * XMLStreamReaders for Items,Activities, Events and Distributions
	 */
	private XMLStreamReader ItemsStreamReader;
	/**
	 * ArrayList containing loaded lecturers.
	 */
	private ArrayList<Lecturer> Lecturers;
	/**
	 * XMLStreamWriter used to save game in XML format.
	 */
	private XMLStreamWriter writer;

	/**
	 * Constructor for XMLhandler. Creates StreamReaders for: Activities, Events
	 * and Distributions.
	 * 
	 * @see javax.xml.stream.XMLStreamReader
	 * @see javax.xml.stream.XMLInputFactory
	 * @see java.io.FileInputStream
	 * @throws XMLStreamException
	 * @throws FileNotFoundException
	 */
	public XMLhandler() throws XMLStreamException, FileNotFoundException {
		factory = XMLInputFactory.newInstance();

		ActivitiesIn = new FileInputStream("res/Activities.xml");
		ActivitiesStreamReader = factory.createXMLStreamReader(ActivitiesIn);
		EventsIn = new FileInputStream("res/Events.xml");
		EventsStreamReader = factory.createXMLStreamReader(EventsIn);
		DistributionsIn = new FileInputStream("res/Distributions.xml");
		DistributionsStreamReader = factory
				.createXMLStreamReader(DistributionsIn);
	}

	/**
	 * Searches Events.xml file in order to find RandomEvents and saves them in
	 * ArrayList. XMLStreamReader is used. Each element is checked if it's name
	 * is 'RandomEvent" or one of RandomEvent's parameters. If it's
	 * "RandomEvent", new RandomEvent object is created, if it's one of
	 * parameters, it's value (parsed to other type than String if needed) is
	 * added with corresponding RandomEvent's setter to previously created
	 * object. If parameter's name is "ActivityName", ActivityList is searched
	 * for Activity with the same name as this parameter value and this
	 * RandomEvent object is added to this Activity's RandomEvent list. If value
	 * equals "ALL" this object is added to all Activities.
	 * 
	 * NOTE: Method doesn't check if variable temp is not null, so there is a
	 * possibility of NullPointerException if the XML file is not written
	 * correctly.
	 * 
	 * @param ActivityList
	 *            List of activities to which RandomEvents should be added.
	 * 
	 * @see javax.xml.stream.XMLStreamReader
	 * @throws XMLStreamException
	 * @throws NumberFormatException
	 * @throws NullPointerException
	 */
	private void loadRandomEvents(ArrayList<Activity> ActivityList)
			throws XMLStreamException, NumberFormatException,
			NullPointerException {
		RandomEvent tempE = null;
		Activity tempA;
		int i;
		String tempS, tempS2; // temporary Strings
		while (EventsStreamReader.hasNext()) {
			EventsStreamReader.next();
			if (EventsStreamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
				tempS = EventsStreamReader.getLocalName();

				switch (tempS) {
				case RANDOMEVENT:
					tempE = new RandomEvent();
					break;
				case NAME:
					tempE.setName(EventsStreamReader.getElementText());
					break;
				case DESCRIPTION:
					tempE.setDescription(EventsStreamReader.getElementText());
					break;
				case PROBABILITY:
					tempE.setProbability(Float.parseFloat(EventsStreamReader
							.getElementText()));
					break;
				case EVENTKNOWLEDGE:
					tempE.setEventKnowledge(Float.parseFloat(EventsStreamReader
							.getElementText()));
					break;
				case EVENTHAPPINESS:
					tempE.setEventHappiness(Float.parseFloat(EventsStreamReader
							.getElementText()));
					break;
				case EVENTENERGY:
					tempE.setEventEnergy(Float.parseFloat(EventsStreamReader
							.getElementText()));
					break;
				case EVENTMONEY:
					tempE.setEventMoney(Long.parseLong(EventsStreamReader
							.getElementText()));
					break;
				case ACTIVITYNAME:
					tempS2 = EventsStreamReader.getElementText();
					if (tempS2.equals("")) {
						break;
					}
					tempE.setActivityName(tempS2);
					int limit = ActivityList.size();
					boolean EventForAllActivities = false;
					if (tempS2.equals("ALL")) // if RandomEvent is meant for all
					// Activities
					{
						EventForAllActivities = true;
					}
					for (i = 0; i < limit; i++) {
						tempA = (Activity) ActivityList.get(i);
						if (EventForAllActivities
								|| tempA.getName().equals(tempS2)) {
							tempA.addRandomEvent(tempE);
							if (!EventForAllActivities) // if RandomEvent is
							// meant for only one
							// activity break here.
							{
								break;
							}
						}
					}
					break;
				case ROUNDSTAKEN:
					tempE.setRoundsTaken((byte) Short
							.parseShort(EventsStreamReader.getElementText()));
					break;
				case STARTSACTIVITY:
					tempS2 = EventsStreamReader.getElementText();
					if (tempS2.equals("")) {
						break;
					}
					tempE.setStartsActivity(tempS2);
					break;
				case NUMBEROFROUNDS:
					tempE.setNumberOfRounds((byte) Short
							.parseShort(EventsStreamReader.getElementText()));
					break;
				}
			}
		}
	}

	/**
	 * Draws random name from file. To set a pointer in RAF to start of random
	 * line calls {@link #searchLine(java.io.RandomAccessFile, int)} method. To
	 * get String value of line starting in drawn pointer calls
	 * {@link #readLine(java.io.RandomAccessFile)} method.
	 * 
	 * @param raf
	 *            RandomAccessFile related with file in which we want to draw
	 *            single line.
	 * 
	 * @return Drawn line.
	 * 
	 * @throws IOException
	 */
	private String randName(RandomAccessFile raf) throws IOException {
		raf.seek(searchLine(raf, 10));
		return readLine(raf);
	}

	/**
	 * Reads line and returns it as String.
	 * 
	 * @param raf
	 *            RandomAccessFile
	 * 
	 * @return Read line.
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	private String readLine(RandomAccessFile raf)
			throws UnsupportedEncodingException, IOException {
		byte b = 56;
		int i = 0;
		byte[] bytes = new byte[256];
		while (b != 10 && b != 35) {
			b = raf.readByte();
			if (b != 10) {
				bytes[i] = b;
			}
			i++;
		}
		String returnS = new String(bytes, "UTF-8");
		returnS = returnS.trim();
		return returnS; // returns new String object. Array of bytes is given to
		// it's constructor.
	}

	/**
	 * Search local list of lecturers in order to find one with same ID
	 * parameter as one given.
	 * 
	 * @param id
	 *            Lecturer's ID to be found.
	 * 
	 * @return Searched Lecturer or null if list doesn't contain Lecturer with
	 *         ID from parameter
	 */
	private Lecturer searchLecturer(String id) {
		int i;
		Lecturer tempL;
		for (i = 0; i < Lecturers.size(); i++) {
			tempL = (Lecturer) Lecturers.get(i);
			if (tempL.getID().equals(id)) {
				return tempL;
			}
		}
		return null;
	}

	/**
	 * Draws random pointer position. (setting it on the start of line)
	 * 
	 * @param raf
	 *            RandomAccessFile
	 * @param znak
	 *            Sign to be searched. (integer value UTF-8)
	 * 
	 * @return Pointer position.
	 * 
	 * @throws IOException
	 */
	private long searchLine(RandomAccessFile raf, int znak) throws IOException {
		Random rand = new Random();
		long rLong = rand.nextLong();
		if (rLong < 0) {
			rLong = rLong * (-1);
		}

		rLong = (rLong % (raf.length() - 80)) + 40; // drawn value shouldn't be
		// bigger than file size

		raf.seek(rLong);
		if (raf.read() != znak) // if drawn position in file is not the sign we
		// are searching for...
		{
			int i = 0;
			int a = raf.read();
			// searches it going backwards byte by byte
			while (a != znak && a != 35) {
				raf.seek(rLong - i);
				a = raf.read();
				i++;
			}
		}
		return raf.getFilePointer();
	}

	/**
	 * Writes single element with two tabs before start element.
	 * 
	 * @param Element
	 *            Name of element <Element></Element>
	 * @param value
	 *            Value of element <Element>value</Element>
	 * 
	 * @throws XMLStreamException
	 */
	private void write(String Element, String value) throws XMLStreamException {
		String doubleTab = "\n \t \t"; // enter + double tab

		writer.writeCharacters(doubleTab);
		writer.writeStartElement(Element);
		writer.writeCharacters(value);
		writer.writeEndElement();
	}

	/**
	 * Draws gender, degree, name and surname. Creates three instances of
	 * RandomAccessFile and calls {@link #randName(java.io.RandomAccessFile)}
	 * method for each string to be drawn.
	 * 
	 * @see java.io.RandomAccessFile
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @return Array with 3 elements. 0 - degree, 1 - name, 2 - surname
	 * 
	 */
	public String[] drawName() throws FileNotFoundException, IOException {
		String[] wylosowany = new String[3]; // arraylist to be returned
		RandomAccessFile RafImiona, RafNazwiska, RafTytul;
		File FileImiona, FileNazwiska, FileTytul;
		Random rand = new Random();
		if (rand.nextBoolean()) {
			FileImiona = new File("res/MImiona.txt");
			FileNazwiska = new File("res/MNazwiska.txt");
		} else {
			FileImiona = new File("res/ZImiona.txt");
			FileNazwiska = new File("res/ZNazwiska.txt");
		}
		FileTytul = new File("res/Degrees.txt");
		RafImiona = new RandomAccessFile(FileImiona, "r");
		RafNazwiska = new RandomAccessFile(FileNazwiska, "r");
		RafTytul = new RandomAccessFile(FileTytul, "r");
		wylosowany[0] = randName(RafTytul);
		wylosowany[1] = randName(RafImiona);
		wylosowany[2] = randName(RafNazwiska);
		return wylosowany;
	}

	/**
	 * Searches Activities.xml file in order to find Activities and saves them
	 * in ArrayList. XMLStreamReader is used. Each element is checked if it's
	 * name is 'Activity" or one of Activity's parameters. If it's "Item" new
	 * Activity object is created, if it's one of parameters, it's value (parsed
	 * to other type than String if needed) is added with corresponding
	 * Activity's setter to previously created object.
	 * 
	 * NOTE: Method doesn't check if variable temp is not null, so there is a
	 * possibility of NullPointerException if the XML file is not written
	 * correctly.
	 * 
	 * @see gameData.Activity
	 * @see javax.xml.stream.XMLStreamReader
	 * @throws NullPointerException
	 * @throws XMLStreamException
	 * @throws NumberFormatException
	 * @return ArrayList containing Activities
	 */
	public ArrayList<Activity> loadActivities() throws XMLStreamException,
			NumberFormatException, NullPointerException {
		int i = 0;
		Activity temp = null;
		String tempS;
		ArrayList<Activity> ActivityList = new ArrayList<>(); // ArrayList to be
		// returned
		while (ActivitiesStreamReader.hasNext()) {
			ActivitiesStreamReader.next();
			if (ActivitiesStreamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
				tempS = ActivitiesStreamReader.getLocalName();
				switch (tempS) {
				case ACTIVITY:
					ActivityList.add(new Activity());
					temp = (Activity) ActivityList.get(i);
					i++;
					break;
				case NAME:
					temp.setName(ActivitiesStreamReader.getElementText());
					break;
				case TIMEPARAMETER:
					temp.setTimeParameter(Float
							.parseFloat(ActivitiesStreamReader.getElementText()));
					break;
				case DISTRIBUTIONNAME:
					temp.setDistributionName(ActivitiesStreamReader
							.getElementText());
					break;
				case ACTIVITYENERGY:
					temp.setActivityEnergy(Float
							.parseFloat(ActivitiesStreamReader.getElementText()));
					break;
				case ACTIVITYHAPPINESS:
					temp.setActivityHappiness(Float
							.parseFloat(ActivitiesStreamReader.getElementText()));
					break;
				case ACTIVITYKNOWLEDGE:
					temp.setActivityKnowledge(Float
							.parseFloat(ActivitiesStreamReader.getElementText()));
					break;
				case ACTIVITYMONEY:
					temp.setActivityMoney(Float
							.parseFloat(ActivitiesStreamReader.getElementText()));
					break;
				case ACTIVITYCHARISMA:
					temp.setActivityCharisma(Double
							.parseDouble(ActivitiesStreamReader
									.getElementText()));
					break;
				case ACTIVITYENDURANCE:
					temp.setActivityEndurance(Double
							.parseDouble(ActivitiesStreamReader
									.getElementText()));
					break;
				case ACTIVITYINTELLIGENCE:
					temp.setActivityIntelligence(Double
							.parseDouble(ActivitiesStreamReader
									.getElementText()));
					break;
				case ACTIVITYSCRUPULOUSNESS:
					temp.setActivityScrupulousness(Double
							.parseDouble(ActivitiesStreamReader
									.getElementText()));
					break;
				case ACTIVITYSTRESSRESISTANCE:
					temp.setActivityStressResistance(Double
							.parseDouble(ActivitiesStreamReader
									.getElementText()));
					break;
				}

			}
		}
		loadRandomEvents(ActivityList);
		return ActivityList;
	}

	/**
	 * Loads GameCalendar object from given file. XMLStreamReader is used. Each
	 * element is checked if it's name is "CalendarActivity". All values of
	 * elements which has correct name are written to array CalendarActivities.
	 * After loading all strings to array, the array is used as a parameter for
	 * GameCalendar constructor.
	 * 
	 * @param path
	 *            Path to saved game file.
	 * 
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 * 
	 */
	public GameCalendar loadCalendar(String path) throws FileNotFoundException,
			XMLStreamException {
		XMLStreamReader CalendarStreamReader;
		FileInputStream CalendarIn = new FileInputStream(path);
		CalendarStreamReader = factory.createXMLStreamReader(CalendarIn);
		String tempS; // temporary String
		String[] CalendarActivities = new String[20160]; // Used for
		// re-constructing
		// GameCalendar
		// object.
		int i = 0;
		while (CalendarStreamReader.hasNext()) {
			CalendarStreamReader.next();
			if (CalendarStreamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
				tempS = CalendarStreamReader.getLocalName();
				if (tempS.equals(CALENDARACTIVITY)) {
					CalendarActivities[i] = CalendarStreamReader
							.getElementText();
					i++;
				}
			}
		}
		for (int j = i; j < 20160; j++) {
			CalendarActivities[j] = "";
		}
		return new GameCalendar(CalendarActivities);

	}

	/**
	 * Searches given file in order to find Courses and saves them in ArrayList.
	 * XMLStreamReader is used. Each element is checked if it's name is 'Course"
	 * or one of Course's parameters. If it's "Course" new Course object is
	 * created, if it's one of parameters, it's value (parsed to other type than
	 * String if needed) is added with corresponding Course's setter to
	 * previously created object. If parameter's name is "LecturerID" Method
	 * searchLecturer is called to find and add lecturer with this ID.
	 * 
	 * 
	 * @param path
	 *            Path to file from which Courses should be loaded.
	 * 
	 * @see javax.xml.stream.XMLStreamReader
	 * @throws NullPointerException
	 * @throws NumberFormatException
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 * @return ArrayList containing Courses
	 */
	public ArrayList<Course> loadCourses(String path)
			throws FileNotFoundException, XMLStreamException,
			NumberFormatException {
		XMLStreamReader CoursesStreamReader;
		FileInputStream CoursesIn = new FileInputStream(path);
		CoursesStreamReader = factory.createXMLStreamReader(CoursesIn);
		Course tempC = null;
		String tempS; // temporary String
		int[] ExamsRounds = null; // array with ExamRounds. When Course object
		// is created this array is added and filled
		// later.
		ArrayList<Course> CoursesList = new ArrayList<>();
		int i = 0, k = 0;
		while (CoursesStreamReader.hasNext()) {
			CoursesStreamReader.next();
			if (CoursesStreamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
				tempS = CoursesStreamReader.getLocalName();
				switch (tempS) {
				case COURSE:
					if (tempC != null) {
						for (int j = 0; j < 3; j++) {
							if (ExamsRounds[j] != 0) {
								k++;
							}
						}
						int[] tempArray = new int[k];
						System.arraycopy(ExamsRounds, 0, tempArray, 0, k);
						tempC.setExamsRound(tempArray);
					}
					k = 0;
					CoursesList.add(new Course());
					tempC = CoursesList.get(i);
					ExamsRounds = new int[3];
					tempC.setExamsRound(ExamsRounds);
					i++;
					break;
				case TITLE:
					tempC.setTitle(CoursesStreamReader.getElementText());
					break;
				case DIFFICULTY:
					tempC.setDifficulty(Integer.parseInt(CoursesStreamReader
							.getElementText()));
					break;
				case SEMESTER:
					tempC.setSemester(Byte.parseByte(CoursesStreamReader
							.getElementText()));
					break;
				case RETAKE:
					if (CoursesStreamReader.getElementText().equals("true")) {
						tempC.setRetake(true);
					} else {
						tempC.setRetake(false);
					}
					break;
				case EXAM:
					if (CoursesStreamReader.getElementText().equals("true")) {
						tempC.setExam(true);
					} else {
						tempC.setExam(false);
					}
					break;
				case LECTURERID:
					Lecturer tempL = searchLecturer(CoursesStreamReader
							.getElementText());
					if (tempL != null) {
						tempC.setLecturer(tempL);
					}
					break;
				case NUMBEROFCOLLOQUIUM:
					tempC.setNumberOfColloquium(Byte
							.parseByte(CoursesStreamReader.getElementText()));
					break;
				case GRADE:
					tempC.setGrade(Float.parseFloat(CoursesStreamReader
							.getElementText()));
					break;
				case STARTROUND:
					tempC.setStartRound(Integer.parseInt(CoursesStreamReader
							.getElementText()));
					break;
				case ECTS:
					tempC.setECTS(Byte.valueOf(CoursesStreamReader
							.getElementText()));
					break;
				case EXAMROUND1:
					ExamsRounds[0] = Integer.parseInt(CoursesStreamReader
							.getElementText());
					break;
				case EXAMROUND2:
					ExamsRounds[1] = Integer.parseInt(CoursesStreamReader
							.getElementText());
					break;
				case EXAMROUND3:
					ExamsRounds[2] = Integer.parseInt(CoursesStreamReader
							.getElementText());
					break;
				}
			}
		}
		if (tempC != null) {
			for (int j = 0; i < 3; i++) {
				if (ExamsRounds[j] != 0) {
					k++;
				}
			}
			int[] tempArray = new int[k];
			for (int j = 0; i < k; i++) {
				tempArray[j] = ExamsRounds[j];
			}
			tempC.setExamsRound(tempArray);
		}
		return CoursesList;
	}

	/**
	 * Searches Distributions.xml file in order to find Distributions and saves
	 * them in ArrayList. XMLStreamReader is used. Each element is checked if
	 * it's name is 'Distribution" or Distribution parameter. If it's
	 * "Distribution" new Distribution object is created, if it's parameter,
	 * it's value is parsed to Integer and added with corresponding
	 * Distribution's setter to previously created object.
	 * 
	 * NOTE: Method doesn't check if variable temp is not null, so there is a
	 * possibility of NullPointerException if the XML file is not written
	 * correctly.
	 * 
	 * @see javax.xml.stream.XMLStreamReader
	 * 
	 * @throws XMLStreamException
	 * @throws NumberFormatException
	 * @throws NullPointerException
	 * 
	 * @return ArrayList containing Distributions
	 */
	public ArrayList<Distribution> loadDistribution()
			throws XMLStreamException, NumberFormatException {
		String tempS, tempS2;
		ArrayList<Distribution> dis = new ArrayList<>(); // List to be returned.
		Distribution d = null;
		while (DistributionsStreamReader.hasNext()) {
			DistributionsStreamReader.next();
			if (DistributionsStreamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
				tempS = DistributionsStreamReader.getLocalName();
				switch (tempS) {
				case NAME:
					tempS2 = DistributionsStreamReader.getElementText();
					switch (tempS2) {
					case "Normal":
						d = new NormalDistribution("Normal");
						break;
					case "Exponential":
						d = new ExponentialDistribution("Exponential");
						break;
					case "Uniform":
						d = new UniformDistribution("Uniform");
						break;
					}
					dis.add(d);
					break;
				case "Parameter":
					d.addParameters(Integer.parseInt(DistributionsStreamReader
							.getElementText()));
					break;
				}
			}
		}
		return dis;
	}

	/**
	 * Loads game parameters from given file.
	 * 
	 * @param path
	 *            Path to file where game parameters are stored.
	 * 
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 */
	public void loadGameParameters(String path) throws FileNotFoundException,
			XMLStreamException {
		XMLStreamReader ParametersStreamReader;
		FileInputStream ParametersIn = new FileInputStream(path);
		ParametersStreamReader = factory.createXMLStreamReader(ParametersIn);
		String tempS; // temporary String
		while (ParametersStreamReader.hasNext()) {
			ParametersStreamReader.next();
			if (ParametersStreamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
				tempS = ParametersStreamReader.getLocalName();
				switch (tempS) {
				case ROUND:
					DataBase.Round = Integer.parseInt(ParametersStreamReader
							.getElementText());
					break;
				case DAYTIME:
					DataBase.DayTime = Byte.parseByte(ParametersStreamReader
							.getElementText());
					break;
				case FIELDOFSTUDY:
					DataBase.FieldOfStudy = ParametersStreamReader
							.getElementText();
				}
			}
		}
	}

	/**
	 * Searches given file in order to find Items and saves them in ArrayList.
	 * XMLStreamReader is used. Each element is checked if it's name is 'Item"
	 * or one of Item's parameters. If it's "Item" new Item object is created,
	 * if it's one of parameters, it's value (parsed to other type than String
	 * if needed) is added with corresponding Item's setter to previously
	 * created object. NOTE: Method doesn't check if variable temp is not null,
	 * so there is a possibility of NullPointerException if the XML file is not
	 * written correctly.
	 * 
	 * @see gameData.Item
	 * @see javax.xml.stream.XMLStreamReader
	 * @throws XMLStreamException
	 * @throws NumberFormatException
	 * @throws NullPointerException
	 * @return ArrayList containing Items
	 */
	public ArrayList<Item> loadItems(String path) throws XMLStreamException,
			NumberFormatException, NullPointerException, FileNotFoundException {
		ItemsIn = new FileInputStream(path);
		ItemsStreamReader = factory.createXMLStreamReader(ItemsIn);
		int i = 0;
		Item temp = null;
		String tempS;
		items = new ArrayList<>();
		while (ItemsStreamReader.hasNext()) {
			ItemsStreamReader.next();

			if (ItemsStreamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
				tempS = ItemsStreamReader.getLocalName();
				switch (tempS) {
				case ITEM:
					items.add(new Item());
					temp = items.get(i);
					i++;
					break;
				case ITEMNAME:
					temp.setName(ItemsStreamReader.getElementText());
					break;
				case DURATION:
					temp.setDuration(Short.parseShort(ItemsStreamReader
							.getElementText()));
					break;
				case ITEMKNOWLEDGE:
					temp.setItemKnowledge(Float.parseFloat(ItemsStreamReader
							.getElementText()));
					break;
				case ITEMENERGY:
					temp.setItemEnergy(Float.parseFloat(ItemsStreamReader
							.getElementText()));
					break;
				case ITEMHAPPINESS:
					temp.setItemHappiness(Float.parseFloat(ItemsStreamReader
							.getElementText()));
					break;
				case PRICE:
					temp.setPrice(Integer.parseInt(ItemsStreamReader
							.getElementText()));
					break;
				case ITEMINTELLIGENCE:
					temp.setItemIntelligence(Float.parseFloat(ItemsStreamReader
							.getElementText()));
					break;
				case ITEMCHARISMA:
					temp.setItemCharisma(Float.parseFloat(ItemsStreamReader
							.getElementText()));
					break;
				case ITEMENDURANCE:
					temp.setItemEndurance(Float.parseFloat(ItemsStreamReader
							.getElementText()));
					break;
				case ITEMSTRESSRESISTANCE:
					temp.setItemStressResistance(Float
							.parseFloat(ItemsStreamReader.getElementText()));
					break;
				case ITEMSCRUPULOUSNESS:
					temp.setItemScrupulousness(Float
							.parseFloat(ItemsStreamReader.getElementText()));
					break;
				case ITEMACTIVE:
					String tempS2 = ItemsStreamReader.getElementText();
					if (tempS2.equals("true")) {
						temp.activate();
					}
				}
			}
		}
		return items;
	}

	/**
	 * Searches given file in order to find Lecturers and saves them in
	 * ArrayList. XMLStreamReader is used. Each element is checked if it's name
	 * is 'Lecturer" or one of Lecturer's parameters. If it's "Lecturer" new
	 * Lecturer object is created, if it's one of parameters, it's value (parsed
	 * to other type than String if needed) is added with corresponding
	 * Lecturer's setter to previously created object.
	 * 
	 * @param path
	 *            Path to file from which Lecturers should be loaded.
	 * 
	 * @see javax.xml.stream.XMLStreamReader
	 * @see gameData.Lecturer
	 * @throws FileNotFoundEsception
	 * @throws XMLStreamException
	 * @throws NumberFormatException
	 * @throws NullPointerException
	 * @return ArrayList containing loaded Lecturers
	 */
	public ArrayList<Lecturer> loadLecturers(String path)
			throws FileNotFoundException, XMLStreamException,
			NumberFormatException, NullPointerException {
		XMLStreamReader LecturersStreamReader;
		FileInputStream LecturersIn = new FileInputStream(path);
		LecturersStreamReader = factory.createXMLStreamReader(LecturersIn);
		Lecturer tempL = null;
		int i = 0;
		String tempS; // temporary String
		Lecturers = new ArrayList<>(); // List to be returned.

		while (LecturersStreamReader.hasNext()) {
			LecturersStreamReader.next();
			if (LecturersStreamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
				tempS = LecturersStreamReader.getLocalName();
				switch (tempS) {
				case LECTURER:
					Lecturers.add(new Lecturer());
					tempL = Lecturers.get(i);
					i++;
					break;
				case ID:
					tempL.setID(LecturersStreamReader.getElementText());
					break;
				case LECTURERNAME:
					tempL.setName(LecturersStreamReader.getElementText());
					break;
				case LECTURERSURNAME:
					tempL.setSurname(LecturersStreamReader.getElementText());
					break;
				case DEGREE:
					tempL.setDegree(LecturersStreamReader.getElementText());
					break;
				case LECTURERNAIVETY:
					tempL.setNaivety(Integer.parseInt(LecturersStreamReader
							.getElementText()));
					break;
				case LECTUREREXPECTATIONS:
					tempL.setExpectations(Integer
							.parseInt(LecturersStreamReader.getElementText()));
					break;
				}
			}
		}

		return Lecturers;
	}

	/**
	 * Loads Log object from given file. XMLStreamReader is used. Each element
	 * is checked if it's name is 'LOGELEMENT". If so, it's value is added to
	 * already created Log object in this method.
	 * 
	 * @param path
	 *            Path to file from which Lecturers should be loaded.
	 * @return Log which contains log elements from save
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 */
	public Log loadLog(String path) throws FileNotFoundException,
			XMLStreamException {
		XMLStreamReader LogStreamReader;
		FileInputStream LogIn = new FileInputStream(path);
		LogStreamReader = factory.createXMLStreamReader(LogIn);
		String tempS;
		Log tempL = new Log();
		while (LogStreamReader.hasNext()) {
			LogStreamReader.next();
			if (LogStreamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
				tempS = LogStreamReader.getLocalName();
				if (tempS.equals(LOGELEMENT)) {
					tempL.AddLine(LogStreamReader.getElementText());
				}
			}
		}
		return tempL;
	}

	/**
	 * Loads Player object from given file. XMLStreamReader is used. Each
	 * element is checked if it's name is 'Player" or one of Player's
	 * parameters. If it's "Player", new Player object is created, if it's one
	 * of parameters, it's value (parsed to other type than String if needed) is
	 * added with corresponding Player's setter to previously created object.
	 * 
	 * NOTE: Method doesn't check if variable temp is not null, so there is a
	 * possibility of NullPointerException if the XML file is not written
	 * correctly.
	 * 
	 * @param path
	 *            Path to file where the game is saved.
	 * 
	 * @see javax.xml.stream.XMLStreamReader
	 * @see gameData.Player
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 * @throws NumberFormatException
	 * @throws NullPointerException
	 * @return Loaded Player object.
	 */
	public Player loadPlayer(String path) throws FileNotFoundException,
			XMLStreamException, NumberFormatException, NullPointerException {
		XMLStreamReader SaveStreamReader;
		FileInputStream SaveIn = new FileInputStream(path);
		SaveStreamReader = factory.createXMLStreamReader(SaveIn);
		Player tempP = null;
		String tempS; // temporary String

		while (SaveStreamReader.hasNext()) {
			SaveStreamReader.next();
			if (SaveStreamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
				tempS = SaveStreamReader.getLocalName();
				switch (tempS) {
				case PLAYER:
					tempP = new Player();
					break;
				case NAME:
					tempP.setName(SaveStreamReader.getElementText());
					break;
				case SURNAME:
					tempP.setSurname(SaveStreamReader.getElementText());
					break;
				case INTELLIGENCE:
					tempP.setInteligence(Integer.parseInt(SaveStreamReader
							.getElementText()));
					break;
				case CHARISMA:
					tempP.setCharisma(Integer.parseInt(SaveStreamReader
							.getElementText()));
					break;
				case ENDURANCE:
					tempP.setEndurance(Integer.parseInt(SaveStreamReader
							.getElementText()));
					break;
				case SCRUPULOUSNESS:
					tempP.setScrupulousness(Integer.parseInt(SaveStreamReader
							.getElementText()));
					break;
				case STRESSRESISTANCE:
					tempP.setStressResistance(Integer.parseInt(SaveStreamReader
							.getElementText()));
					break;
				case KNOWLEDGE:
					tempP.setKnowledge(Integer.parseInt(SaveStreamReader
							.getElementText()));
					break;
				case HAPPINESS:
					tempP.setHappiness(Integer.parseInt(SaveStreamReader
							.getElementText()));
					break;
				case ENERGY:
					tempP.setEnergy(Integer.parseInt(SaveStreamReader
							.getElementText()));
					break;
				case MONEY:
					tempP.setMoney(Integer.parseInt(SaveStreamReader
							.getElementText()));
					break;
				case ECTSDEF:
					tempP.setECTS(Short.parseShort(SaveStreamReader
							.getElementText()));
					break;
				}
			}
		}
		tempP.setItems(loadItems(path));
		ArrayList<Item> tempItems = tempP.getItems();
		ArrayList<Item> inactiveItems = new ArrayList<>();
		Item tempI;
		for (int i = 0; i < tempItems.size(); i++) {
			tempI = tempItems.get(i);
			if (!tempI.isActive()) {
				inactiveItems.add(tempI);
			}
		}
		tempP.setInActiveItems(inactiveItems);
		return tempP;
	}

/**
	 * Draws password and it's description for game: Hangman. Passwords are kept
	 * in /res/Hangman.txt Calls {@link #searchLine(java.io.RandomAccessFile, int)}  method to 
     * draw a pointer. Calls {@link #readLine(java.io.RandomAccessFile)}  method to get String value of drawn line.
	 * 
	 * @return String array with two elements. 0 - password, 1 - description
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String[] randHangmanQuestion(String path)
			throws FileNotFoundException, IOException {
		String[] wylosowany = new String[2];
		File file = new File(path);
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		raf.seek(searchLine(raf, 42));
		wylosowany[0] = readLine(raf);
		wylosowany[1] = readLine(raf);
		return wylosowany;

	}

	/**
	 * Saves games to given path. XMLStreamWriter is used.
	 * 
	 * @param path
	 *            Path where game should be saved. For example:
	 *            "C:/Games/StudentSimulator2K13/Save.xml"
	 * @param player
	 *            Player object to be saved.
	 * @param calendar
	 *            GameCalendar object to be saved.
	 * @param lecturers
	 *            ArrayList containing Lecturers to be saved.
	 * @param courses
	 *            ArrayList containing Courses to be saved.
	 * 
	 * @see javax.xml.stream.XMLStreamWriter
	 * @see gameData.Player
	 * @see gameData.GameCalendar
	 * @see gameData.Lecturer
	 * @see gameData.Course
	 * @throws XMLStreamException
	 * @throws IOException
	 */
	public void saveGame(String path, Player player, GameCalendar calendar,
			ArrayList<Lecturer> lecturers, ArrayList<Course> courses, Log log)
			throws XMLStreamException, IOException, NullPointerException {
		XMLOutputFactory SaveFactory = XMLOutputFactory.newInstance();
		writer = SaveFactory.createXMLStreamWriter(new FileWriter(path));
		String enter = "\n";
		String singleTab = "\n \t";
		writer.writeStartDocument("UTF-8", "1.0");
		writer.writeCharacters(enter);
		writer.writeStartElement(SAVE);

		// GameParameters

		writer.writeCharacters(singleTab);
		writer.writeStartElement(GAMEPARAMETERS);
		write(ROUND, String.valueOf(DataBase.Round));
		write(DAYTIME, String.valueOf(DataBase.DayTime));
		write(FIELDOFSTUDY, DataBase.FieldOfStudy);
		writer.writeCharacters(singleTab);
		writer.writeEndElement();

		// Player

		writer.writeCharacters(singleTab);
		writer.writeStartElement(PLAYER);
		write(NAME, player.getName());
		write(SURNAME, player.getSurname());
		write(INTELLIGENCE, String.valueOf(player.getInteligence()));
		write(CHARISMA, String.valueOf(player.getCharisma()));
		write(ENDURANCE, String.valueOf(player.getEndurance()));
		write(SCRUPULOUSNESS, String.valueOf(player.getScrupulousness()));
		write(STRESSRESISTANCE, String.valueOf(player.getStressResistance()));
		write(KNOWLEDGE, String.valueOf(player.getKnowledge()));
		write(HAPPINESS, String.valueOf(player.getHappiness()));
		write(ENERGY, String.valueOf(player.getEnergy()));
		write(MONEY, String.valueOf(player.getMoney()));
		write(ECTSDEF, String.valueOf(player.getECTS()));
		writer.writeCharacters(singleTab);
		writer.writeEndElement();
		writer.writeCharacters(singleTab);

		// Courses

		int limit = courses.size();
		int tempArray[];
		int i, j;
		Course tempC;
		Lecturer tempL;
		for (i = 0; i < limit; i++) {
			writer.writeStartElement(COURSE);
			tempC = (Course) courses.get(i);
			write(TITLE, tempC.getTitle());
			write(DIFFICULTY, String.valueOf(tempC.getDifficulty()));
			write(SEMESTER, String.valueOf(tempC.getSemester()));
			if (tempC.isRetake()) {
				write(RETAKE, "true");
			} else {
				write(RETAKE, "false");
			}
			if (tempC.isExam()) {
				write(EXAM, "true");
			} else {
				write(EXAM, "false");
			}
			tempL = tempC.getLecturer();
			write(LECTURERID, tempL.getID());
			write(NUMBEROFCOLLOQUIUM,
					String.valueOf(tempC.getNumberOfColloquium()));
			write(GRADE, String.valueOf(tempC.getGrade()));
			write(STARTROUND, String.valueOf(tempC.getStartRound()));
			write(ECTS, String.valueOf(tempC.getECTS()));
			tempArray = tempC.getExamsRound();
			for (j = 0; j < tempArray.length; j++) {
				write("ExamRound" + (j + 1), String.valueOf(tempArray[j]));
			}
			writer.writeCharacters(singleTab);
			writer.writeEndElement();
			writer.writeCharacters(singleTab);
		}

		// Lecturers

		limit = lecturers.size();
		for (i = 0; i < limit; i++) {

			writer.writeStartElement(LECTURER);
			tempL = (Lecturer) lecturers.get(i);
			write(LECTURERNAME, tempL.getName());
			write(LECTURERSURNAME, tempL.getSurname());
			write(DEGREE, tempL.getDegree());
			write(ID, tempL.getID());
			write(LECTUREREXPECTATIONS, String.valueOf(tempL.getExpectations()));
			write(LECTURERNAIVETY, String.valueOf(tempL.getNaivety()));
			writer.writeCharacters(singleTab);
			writer.writeEndElement();
			writer.writeCharacters(singleTab);
		}

		// Items

		ArrayList<Item> tempItems = player.getItems();
		limit = tempItems.size();
		Item tempI;
		for (i = 0; i < limit; i++) {
			writer.writeStartElement(ITEM);
			tempI = tempItems.get(i);
			write(ITEMNAME, tempI.getName());
			write(DURATION, String.valueOf(tempI.getDuration()));
			write(ITEMCHARISMA, String.valueOf(tempI.getItemCharisma()));
			write(ITEMKNOWLEDGE, String.valueOf(tempI.getItemKnowledge()));
			write(ITEMENDURANCE, String.valueOf(tempI.getItemEndurance()));
			write(ITEMINTELLIGENCE, String.valueOf(tempI.getItemIntelligence()));
			write(ITEMSCRUPULOUSNESS,
					String.valueOf(tempI.getItemScrupulousness()));
			write(ITEMENERGY, String.valueOf(tempI.getItemEnergy()));
			write(ITEMHAPPINESS, String.valueOf(tempI.getItemHappiness()));
			write(ITEMSTRESSRESISTANCE,
					String.valueOf(tempI.getItemStressResistance()));
			write(ITEMACTIVE, String.valueOf(tempI.isActive()));
			writer.writeCharacters(singleTab);
			writer.writeEndElement();
			writer.writeCharacters(singleTab);
		}

		// Log

		writer.writeStartElement(LOG);
		ArrayList<String> tempLog = log.getLog();
		for (int k = 0; k < tempLog.size(); k++) {
			write(LOGELEMENT, tempLog.get(k));
		}
		writer.writeEndElement();
		writer.writeCharacters(singleTab);

		// Calendar

		String[] calAct = calendar.getActivityList();
		limit = calAct.length;
		writer.writeStartElement(CALENDAR);
		for (i = 0; i < limit; i++) {
			write(CALENDARACTIVITY, calAct[i]);
		}
		writer.writeCharacters(singleTab);
		writer.writeEndElement();
		writer.writeCharacters(singleTab);
		writer.writeCharacters(enter);
		writer.writeEndElement();
		writer.flush();
		writer.close();
	}
}