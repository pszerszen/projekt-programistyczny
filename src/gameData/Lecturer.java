package gameData;

/**
 * The Lecturer class stores the data of lecturer
 * 
 * @author Wojciech Stokłosa
 */
public class Lecturer {
	/** private variable which contains lecturers academic degree */
	private String Degree;
	/** private variable which contains parameter of lecturer's expectations */
	private int Expectations;
	/** private variable which contains lecturers ID */
	private String ID;
	/** private variable which contains parameter of lecturer's naivety */
	private int Naivety;
	/** private variable which contains lecturer name */
	private String Name;
	/** private variable which contains lecturer surname */
	private String Surname;

	/**
	 * Lecturer constructor
	 */
	public Lecturer() {

	}

	/**
	 * Lecturer's constructor. Creates Lecturer's with assigned values to
	 * variables
	 * 
	 * @param Name
	 *            name of Lecturer
	 * @param Surname
	 *            surname of lecturer
	 * @param Expectations
	 *            level of expectations of this lecturer
	 * @param Naivety
	 *            level of naivety of this lecturer
	 * @param Degree
	 *            academic degree
	 */
	public Lecturer(String Name, String Surname, int Expectations, int Naivety,
			String Degree) {
		this.Name = Name;
		this.Surname = Surname;
		this.Expectations = Expectations;
		this.Naivety = Naivety;
		this.Degree = Degree;
	}

	/**
	 * Returns academic degree
	 * 
	 * @return academic degree
	 */
	public String getDegree() {
		return Degree;
	}

	/**
	 * Returns level of exceptations
	 * 
	 * @return level of expectations
	 */
	public int getExpectations() {
		return Expectations;
	}

	/**
	 * Returns ID of lecturer
	 * 
	 * @return ID of lecturer
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Returns level of naivety
	 * 
	 * @return level of naivety
	 */
	public int getNaivety() {
		return Naivety;
	}

	/**
	 * Returns name of lecturer
	 * 
	 * @return name of lecturer
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Returns surname of the lecturer
	 * 
	 * @return surname of the lecturer
	 */
	public String getSurname() {
		return Surname;
	}

	/**
	 * Changes value in Lecturer's private variable Degree into value from
	 * parameter
	 * 
	 * @param degree
	 *            academic degree
	 */
	public void setDegree(String degree) {
		Degree = degree;
	}

	/**
	 * Changes value in Lecturer's private variable Exceptations into value from
	 * parameter
	 * 
	 * @param expectations
	 *            level of exceptations of the lecturer
	 */
	public void setExpectations(int expectations) {
		Expectations = expectations;
	}

	/**
	 * Changes value in Lecturer's private variable ID into value from parameter
	 * 
	 * @param iD
	 *            ID of lecturer
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * Changes value in Lecturer's private variable Naivety into value from
	 * parameter
	 * 
	 * @param naivety
	 *            level of naivety
	 */
	public void setNaivety(int naivety) {
		Naivety = naivety;
	}

	/**
	 * Changes value in Lecturer's private variable Name into value from
	 * parameter
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * Changes value in Lecturer's private variable Surname into value from
	 * parameter
	 * 
	 * @param surname
	 *            surname of lecturer
	 */
	public void setSurname(String surname) {
		Surname = surname;
	}

}
