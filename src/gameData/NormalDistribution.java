package gameData;

import java.util.ArrayList;

/**
 * The NormalDistribution class is extending of Distribution class, and stores
 * the data of distribution
 * 
 * @author Mateusz Szews
 * @see ArrayList
 */
public class NormalDistribution extends Distribution {

	/** private variable which contains ArrayList of double numbers */
	private ArrayList<Double> Parameters = new ArrayList<Double>();

	/**
	 * NormalDistribution constructor
	 * 
	 * @param Name
	 *            String: name of distribution
	 */
	NormalDistribution(String Name) {
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
		double w = (this.A * (1 / Math.sqrt(2 * Math.PI * Parameters.get(1)) * Math
				.exp(-(Math.pow((x - Parameters.get(0)), 2) / (2 * Parameters
						.get(1))))));
		// System.out.println(w);
		return w;
	}

	/**
	 * Overridden method from {@link gameData.Distribution} class.
	 */
	@Override
	protected void setTime(int t) {
		Parameters.set(1, (double) t / 2);
		Parameters.set(0, (double) t / 2);
		this.A = t;
	}

}