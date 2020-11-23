package gameData;

/**
 * The Distribution is an abstract class that stores the data of activity's
 * distribution, each Activity has it's own Distribution which is representing
 * activity's effects on player's statistics at depend on time influence
 * (rounds)
 * 
 * @author Mateusz Szews
 */
abstract class Distribution {

	/** private variable which contains name of distribution */
	private String Name;
	protected double A = 1;

	/**
	 * Distribution constructor
	 * 
	 * @param Name
	 *            String: name of distribution
	 */
	Distribution(String Name) {
		this.Name = Name;
	}

	/**
	 * Abstract function
	 * 
	 * @param p
	 *            double
	 */
	protected abstract void addParameters(double p);

	/**
	 * abstract function
	 * 
	 * @param x
	 *            double
	 */
	protected abstract double density(double x);

	/**
	 * Returns Distribution's name
	 * 
	 * @return Name String
	 */
	protected String getName() {
		return Name;
	}

	/**
	 * abstract function
	 * 
	 * @param t
	 *            time
	 */
	protected abstract void setTime(int t);
}
