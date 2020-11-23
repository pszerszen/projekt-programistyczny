package gameData;

import java.util.ArrayList;

/**
 * The UniformDistribution class is extending of Distribution class, and stores
 * the data of distribution
 * 
 * @author Mateusz Szews
 * @see ArrayList
 */
public class UniformDistribution extends Distribution {

	/** private variable which contains ArrayList of double numbers */
	private ArrayList<Double> Parameters = new ArrayList<Double>();

	/**
	 * UniformDistribution constructor
	 * 
	 * @param Name
	 *            String: name of distribution
	 */
	UniformDistribution(String Name) {
		super(Name);
	}

	/**
	 * adding real number into Parameters
	 * 
	 * @param p
	 *            variable to be added
	 */
	@Override
	protected void addParameters(double p) {
		Parameters.add(p);
	}

	/**
	 * representing function of real variable x
	 * 
	 * @param x
	 *            double
	 * @return value double
	 */
	@Override
	protected double density(double x) {
		if (x <= Parameters.get(0))
			return this.A / Parameters.get(0);
		else
			return 0;
	}

	/**
	 * Overriden method from {@link gameData.Distribution} class.
	 */
	@Override
	protected void setTime(int t) {
		Parameters.set(0, (double) t);
		this.A = 1.4 * Parameters.get(0) - 1;
	}

}