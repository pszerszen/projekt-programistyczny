package gameData;

import java.util.ArrayList;

/**
 * The ExponentialDistribution class is extending of Distribution class, and
 * stores the data of distribution
 * 
 * @author Mateusz Szews
 * @see ArrayList
 */
public class ExponentialDistribution extends Distribution {

	/** private variable which contains ArrayList of double numbers */
	private ArrayList<Double> Parameters = new ArrayList<Double>();

	/**
	 * ExponentialDistribution constructor
	 * 
	 * @param Name
	 *            String: name of distribution
	 */
	ExponentialDistribution(String Name) {
		super(Name);
	}

	/**
	 * adding real number into Parameters
	 * 
	 * @param p
	 *            double
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
		double value;
		value = (double) (1 / (Math.pow(Math.E, 1 / Parameters.get(0))) * Math
				.exp(-Parameters.get(0) * x * this.A));
		// System.out.println(value);
		return value;
	}

	/**
	 * Overridden method from {@link gameData.Distribution} class.
	 */
	@Override
	protected void setTime(int t) {
		Parameters.set(0, (double) t);
		this.A = -1 / (t * Parameters.get(0));
	}

}