package gameData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import miniGames.Draughts.Draughts;
import miniGames.blockd.Blockd;
import miniGames.hangman.HangmanGame;
import miniGames.memory.Memory;
import miniGames.ships.Ships;

/**
 * The Activity class stores the data of activity
 * 
 * @author Mateusz Szews
 * @see gameData.Distribution
 * @see gameData.RandomEvent
 */
public class Activity {
	/**
	 * private variable which contains influence parameter of player's Charisma
	 * on Activity
	 */
	private double ActivityCharisma;
	/**
	 * Private variable which contains influence parameter of player's Endurance
	 * on Activity
	 */
	private double ActivityEndurance;
	/**
	 * private variable which contains parameter of changing player's Energy
	 */
	private double ActivityEnergy;
	/**
	 * private variable which contains parameter of changing player's Happiness
	 */
	private double ActivityHappiness;
	/**
	 * private variable which contains influence parameter of player's
	 * Intelligence on Activity
	 */
	private double ActivityIntelligence;
	/**
	 * private variable which contains parameter of changing player's Knowledge
	 */
	private double ActivityKnowledge;
	/**
	 * private variable which contains parameter of changing player's Money
	 */
	private double ActivityMoney;
	/**
	 * private variable which contains influence parameter of player's
	 * Scrupulousness on Activity
	 */
	private double ActivityScrupulousness;
	/**
	 * private variable which contains influence parameter of player's
	 * Resistance on Activity
	 */
	private double ActivityStressResistance;
	/** private variable which contains name of distribution */
	private String DistributionName;
	/** private variable which contains ArrayList of RandomEvent */
	private ArrayList<RandomEvent> EventList = new ArrayList<RandomEvent>();
	/** private variable which contains name of activity */
	private String Name;
	/** private variable which contains duration Activity */
	private double TimeParameter;

	/**
	 * Activity constructor
	 */
	public Activity() {
		this.Name = "";
		this.DistributionName = "Normal";
		this.ActivityEnergy = 0;
		this.ActivityHappiness = 0;
		this.ActivityKnowledge = 0;
		this.ActivityMoney = 0;
		this.ActivityStressResistance = 0;
		this.ActivityScrupulousness = 0;
		this.ActivityEndurance = 0;
		this.ActivityCharisma = 0;
		this.ActivityIntelligence = 0;
		this.TimeParameter = 0;
		this.EventList.clear();
	}

	/**
	 * Adding effects of Event to player
	 * 
	 * @param rEvent
	 *            RandomEvent
	 * @see gameData.DataBase
	 * @see gameData.RandomEvent
	 * @return number integer
	 */
	private int addEventEffects(RandomEvent rEvent) {
		DataBase.log
				.AddLine(rEvent.getName() + " - " + rEvent.getDescription());

		// System.out.println(DataBase.Round);
		DataBase.cal.setActivity(DataBase.Round, rEvent.getRoundsTaken(),
				rEvent.getName()); // Dodawanie do kalendarza eventu i czasu
									// trwania
		// Do poprawy-nie znam nazwy funkcji :D
		DataBase.player
				.setKnowledge((int) (DataBase.player.getKnowledge() * (1 + rEvent
						.getEventKnowledge())));
		DataBase.player
				.setHappiness((int) (DataBase.player.getHappiness() * (1 + rEvent
						.getEventHappiness())));
		DataBase.player
				.setEnergy((int) (DataBase.player.getEnergy() * (1 + rEvent
						.getEventEnergy())));

		DataBase.player.setMoney((int) (DataBase.player.getMoney() + rEvent
				.getEventMoney()));

		DataBase.Round = DataBase.Round + rEvent.getRoundsTaken();

		int number = 0;

		if (!rEvent.getStartsActivity().equals("")) {
			for (int i = 0; i < DataBase.activities.size(); i++)
				if (DataBase.activities.get(i).getName()
						.equals(rEvent.getStartsActivity()))
					number = DataBase.activities.get(i).Effects(
							rEvent.getNumberOfRounds());
		}
		number = number + rEvent.getRoundsTaken();

		return number;
	}

	/**
	 * Drawing a RandomEvent from EventList
	 * 
	 * @param n
	 *            Parameter of probability of drawing an event, the bigger n
	 *            means the less chance of draw an event
	 * @return rEvent RandomEvent
	 * @see RandomEvent
	 */
	private RandomEvent drawEvent(int n) {
		RandomEvent rEvent = new RandomEvent();
		int Size = EventList.size();

		Random R = new Random();
		int N = R.nextInt(n * Size);

		if (N < Size) {
			double[] T = new double[Size + 1];
			T[0] = 0;

			for (int i = 0; i < Size; i++)
				T[i + 1] = T[i] + EventList.get(i).getProbability();

			if (T[Size] == 0)
				T[Size] = 1;
			for (int i = 0; i < Size + 1; i++)
				T[i] = T[i] / T[Size];

			R = new Random();
			double N2 = R.nextDouble();

			for (int i = 1; i < Size + 1; i++) {
				if (N2 <= T[i]) {

					rEvent = EventList.get(i - 1);
					break;
				}
			}
		}
		return rEvent;
	}

	/**
	 * Function of dependency between player's statistics, is using in
	 * timeInfluence function
	 * 
	 * @param Name
	 *            Name of player's statistic
	 * @return Average double
	 */
	@SuppressWarnings("unused")
	private double Function(String Name) {
		double average = 0;
		int l;
		if (Name == "Knowledge") {
			if (this.ActivityKnowledge > 0) {
				average = (DataBase.player.getHappiness() + 2 * DataBase.player
						.getEnergy()) / 750;

				average = average
						+ ((2 * DataBase.player.getInteligence()
								* (double) this.ActivityIntelligence + 3
								* DataBase.player.getEndurance()
								* (double) this.ActivityEndurance + DataBase.player
								.getScrupulousness()
								* (double) this.ActivityScrupulousness) / 10);
			} else {
				average = (2 * DataBase.player.getKnowledge()
						+ DataBase.player.getHappiness() + DataBase.player
						.getEnergy()) / 200;

				average = 60
						- average
						- ((2 * DataBase.player.getInteligence()
								* (double) this.ActivityIntelligence + 3
								* DataBase.player.getEndurance()
								* (double) this.ActivityEndurance + DataBase.player
								.getScrupulousness()
								* (double) this.ActivityScrupulousness) / 10);
			}
		}
		if (Name == "Happiness") {
			if (this.ActivityHappiness > 0) {
				average = (10 * DataBase.player.getKnowledge() + 8
						* DataBase.player.getEnergy() + Math.min(
						DataBase.player.getMoney(), 10000)) / 3000;

				average = average
						+ ((DataBase.player.getCharisma()
								* (double) this.ActivityCharisma + DataBase.player
								.getStressResistance()
								* (double) this.ActivityStressResistance) * 2);
			} else {
				average = (10 * DataBase.player.getKnowledge() + 8
						* DataBase.player.getEnergy() + Math.min(
						DataBase.player.getMoney(), 10000)) / 3000;

				average = 16
						- average
						- ((DataBase.player.getCharisma()
								* (double) this.ActivityCharisma + DataBase.player
								.getStressResistance()
								* (double) this.ActivityStressResistance) / 4);
			}
		}
		if (Name == "Energy") {
			if (this.ActivityEnergy > 0) {
				average = DataBase.player.getHappiness() / 100;

				average = 10
						+ average
						+ ((DataBase.player.getStressResistance() * (double) this.ActivityStressResistance) * 6);
			} else {
				average = -DataBase.player.getHappiness() / 40 + 100;

				average = average
						+ ((DataBase.player.getStressResistance() * (double) this.ActivityStressResistance) * (-1.5));
			}
		}
		if (Name == "Money") {
			if (this.ActivityMoney > 0) {
				average = Math.pow(
						((2 * DataBase.player.getKnowledge() + DataBase.player
								.getEnergy()) / 400), 2);

				average = average
						+ ((DataBase.player.getInteligence()
								* (double) this.ActivityIntelligence
								+ DataBase.player.getEndurance()
								* (double) this.ActivityEndurance
								+ DataBase.player.getScrupulousness()
								* (double) this.ActivityScrupulousness + DataBase.player
								.getCharisma() * (double) this.ActivityCharisma) * 2);
			} else {
				average = ((2 * DataBase.player.getKnowledge() + DataBase.player
						.getEnergy()) / 3);

				average = 2000
						- average
						- ((DataBase.player.getInteligence()
								* (double) this.ActivityIntelligence + DataBase.player
								.getCharisma() * (double) this.ActivityCharisma) * 40);
			}
		}
		if (average == 0)
			average = 1;

		return average;
	}

	/**
	 * Update statistics of player from dependence of Activity's statistics and
	 * rounds
	 * 
	 * @param round
	 *            integer: quantity of turns for doing Activity
	 * @param D
	 *            Distribution
	 * @see gameData.DataBase
	 * @see gameData.Distribution
	 */
	private void timeInfluence(int round, Distribution D) {
		if (round <= TimeParameter) {
			DataBase.player
					.setKnowledge((int) (DataBase.player.getKnowledge() + Function("Knowledge")
							* ActivityKnowledge * D.density(round)));
			DataBase.player
					.setHappiness((int) (DataBase.player.getHappiness() + Function("Happiness")
							* ActivityHappiness * D.density(round)));
			DataBase.player
					.setEnergy((int) (DataBase.player.getEnergy() + Function("Energy")
							* ActivityEnergy * D.density(round)));
			DataBase.player
					.setMoney((int) (DataBase.player.getMoney() + Function("Money")
							* ActivityMoney * D.density(round)));
		} else {
			DataBase.player
					.setMoney((int) (DataBase.player.getMoney() + Function("Money")
							* ActivityMoney * D.density(round)));
		}

		if (round > 1.5 * TimeParameter) {
			DataBase.player
					.setKnowledge((int) (DataBase.player.getKnowledge() - Function("Knowledge")
							* ActivityKnowledge
					/**
					 * D.density((float) Math.max(2.5 * TimeParameter - round +
					 * 1, -1))
					 */
					));
			DataBase.player
					.setHappiness((int) (DataBase.player.getHappiness() - Function("Happiness")
							* ActivityHappiness
					/**
					 * D.density((float) Math.max(2.5 * TimeParameter - round +
					 * 1, -1))
					 */
					));
			DataBase.player
					.setEnergy((int) (DataBase.player.getEnergy() - Function("Energy")
							* Math.abs(ActivityEnergy)
					/**
					 * D.density((float) Math.max(2.5 * TimeParameter - round +
					 * 1, -1))
					 */
					));
		}
	}

	/**
	 * Adding RandomEvent into EventList
	 * 
	 * @param rEvent
	 *            RandomEvent
	 * @see gameData.RandomEvent
	 * @see ArrayList
	 */
	public void addRandomEvent(RandomEvent rEvent) {
		EventList.add(rEvent);
	}

	/**
	 * Function controls the course of the Activity
	 * 
	 * @param time
	 *            quantity of turns for doing Activity
	 * @see gameData.DataBase
	 * @see gameData.Distribution
	 * @return number integer
	 */
	@SuppressWarnings("unused")
	public int Effects(int time) {
		int number = 1;
		if (!Name.equals("Kolokwium/Egzamin")) {
			// DataBase.log.AddLine(this.Name);
			Distribution D = DataBase.getDistribution(DistributionName);
			D.setTime(time);

			RandomEvent rEvent = new RandomEvent();
			int P = 12; // parametr odpowiedzialny za czestosc wypadanai
						// eventow,
						// im mniejszy tym wieksze szanse
			int roundof = 0;
			int round = 0;
			int number1 = 0;
			int number2 = 0;
			boolean Break = false;

			while (time > 0) {
				rEvent = drawEvent(time + P);

				if (!rEvent.getName().equals("")
						&& ((rEvent.getEventMoney() < 0 && Math.abs(rEvent
								.getEventMoney()) < DataBase.player.getMoney()) || rEvent
								.getEventMoney() > 0)) {
					DataBase.log.AddLine(this.Name);

					number2 = addEventEffects(rEvent);
					number1 = number1 + number2;
					roundof = 0;

					if (time <= number2)
						Break = true;
					else
						time = time - number2;

				} else {
					DataBase.log.AddLine(this.Name);
					time--;
					round++;
					roundof++;
					timeInfluence(round, D);
					DataBase.Round = DataBase.Round + 1;
					DataBase.cal.setActivity(DataBase.Round, 1, this.Name);
				}
				if (Break)
					break;
			}
			number = number1 + round;

			return number;
		} else {
			Random R = new Random();
			String nazwa = new String();
			if (!((DataBase.Round % 2880 <= 2492 && DataBase.Round % 2880 >= 2353) || (DataBase.Round % 2880 <= 2660 && DataBase.Round % 2880 >= 2521))) {
				int r = R.nextInt(3);
				if (r == 0) {
					try {
						String[] passwordSet = DataBase.randHangmanQuestion();

						new HangmanGame(passwordSet[0], passwordSet[1]);
					} catch (ArithmeticException e) {
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					nazwa = "Hangman";

				} else if (r == 1) {
					new Memory();
					nazwa = "Memory";
				} else {
					Blockd bl = new Blockd();
					bl.create();
					nazwa = "Blockd";
				}
				// DataBase.cal.setActivity(DataBase.Round, 1, "Kolokwium : " +
				// nazwa);
				DataBase.Round = DataBase.Round + 1;
			} else {
				if (R.nextBoolean()) {
					new Ships();
					nazwa = "Ships";
				} else {
					new Draughts();
					nazwa = "Draughts";
				}
				// DataBase.cal.setActivity(DataBase.Round, 1, "Egzamin : " +
				// nazwa);
				DataBase.Round = DataBase.Round + 1;
			}

			DataBase.log.AddLine(this.Name);
			return number;
		}

	}

	/**
	 * Returns Activity's variable ActivityCharisma
	 * 
	 * @return ActivityCharisma
	 */
	public double getActivityCharisma() {
		return ActivityCharisma;
	}

	/**
	 * Returns Activity's variable ActivityEndurance
	 * 
	 * @return ActivityEndurance double
	 */
	public double getActivityEndurance() {
		return ActivityEndurance;
	}

	/**
	 * Returns Activity's variable ActivityEnergy
	 * 
	 * @return ActivityEnergy double
	 */
	public double getActivityEnergy() {
		return ActivityEnergy;
	}

	/**
	 * Returns Activity's variable ActivityHappiness
	 * 
	 * @return ActivityHappiness double
	 */
	public double getActivityHappiness() {
		return ActivityHappiness;
	}

	/**
	 * Returns Activity's variable ActivityIntelligence
	 * 
	 * @return ActivityIntelligence double
	 */
	public double getActivityIntelligence() {
		return ActivityIntelligence;
	}

	/**
	 * Returns Activity's variable ActivityKnowledge
	 * 
	 * @return ActivityKnowledge double
	 */
	public double getActivityKnowledge() {
		return ActivityKnowledge;
	}

	/**
	 * Returns Activity's variable ActivityMoney
	 * 
	 * @return ActivityMoney double
	 */
	public double getActivityMoney() {
		return ActivityMoney;
	}

	/**
	 * Returns Activity's variable ActivityScrupulousness
	 * 
	 * @return ActivityScrupulousness double
	 */
	public double getActivityScrupulousness() {
		return ActivityScrupulousness;
	}

	/**
	 * Returns Activity's variable ActivityStressResistance
	 * 
	 * @return ActivityStressResistance double
	 */
	public double getActivityStressResistance() {
		return ActivityStressResistance;
	}

	/**
	 * Returns name of Distribution
	 * 
	 * @return DistributionName String
	 */
	public String getDistributionName() {
		return DistributionName;
	}

	/**
	 * Returns Name of Activity
	 * 
	 * @return Name String
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Returns Activity's duration
	 * 
	 * @return TimeParameter double
	 */
	public double getTimeParameter() {
		return TimeParameter;
	}

	/**
	 * Remove rEvent from EventList
	 * 
	 * @param rEvent
	 *            RandomEvent
	 * @see gameData.RandomEvent
	 * @see ArrayList
	 */
	public void removeRandomEvent(RandomEvent rEvent) {
		EventList.remove(rEvent);
	}

	/**
	 * Changes value in Activity's private variable ActivityCharisma into value
	 * from parameter
	 * 
	 * @param ActivityCharisma
	 *            double which is to be changed
	 */
	public void setActivityCharisma(double ActivityCharisma) {
		this.ActivityCharisma = ActivityCharisma;
	}

	/**
	 * Changes value in Activity's private variable ActivityEndurance into value
	 * from parameter
	 * 
	 * @param ActivityEndurance
	 *            double which is to be changed
	 */
	public void setActivityEndurance(double ActivityEndurance) {
		this.ActivityEndurance = ActivityEndurance;
	}

	/**
	 * Changes value in Activity's private variable ActivityEnergy into value
	 * from parameter
	 * 
	 * @param ActivityEnergy
	 *            double which is to be changed
	 */
	public void setActivityEnergy(double ActivityEnergy) {
		this.ActivityEnergy = ActivityEnergy;
	}

	/**
	 * Changes value in Activity's private variable ActivityHappiness into value
	 * from parameter
	 * 
	 * @param ActivityHappiness
	 *            double which is to be changed
	 */
	public void setActivityHappiness(double ActivityHappiness) {
		this.ActivityHappiness = ActivityHappiness;
	}

	/**
	 * Changes value in Activity's private variable ActivityIntelligence into
	 * value from parameter
	 * 
	 * @param ActivityIntelligence
	 *            double which is to be changed
	 */
	public void setActivityIntelligence(double ActivityIntelligence) {
		this.ActivityIntelligence = ActivityIntelligence;
	}

	/**
	 * Changes value in Activity's private variable ActivityKnowledge into value
	 * from parameter
	 * 
	 * @param ActivityKnowledge
	 *            double which is to be changed
	 */
	public void setActivityKnowledge(double ActivityKnowledge) {
		this.ActivityKnowledge = ActivityKnowledge;
	}

	/**
	 * Changes value in Activity's private variable ActivityMoney into value
	 * from parameter
	 * 
	 * @param ActivityMoney
	 *            double which is to be changed
	 */
	public void setActivityMoney(double ActivityMoney) {
		this.ActivityMoney = ActivityMoney;
	}

	/**
	 * Changes value in Activity's private variable ActivityScrupulousness into
	 * value from parameter
	 * 
	 * @param ActivityScrupulousness
	 *            double which is to be changed
	 */
	public void setActivityScrupulousness(double ActivityScrupulousness) {
		this.ActivityScrupulousness = ActivityScrupulousness;
	}

	/**
	 * Changes value in Activity's private variable ActivityStressResistance
	 * into value from parameter
	 * 
	 * @param ActivityStressResistance
	 *            double which is to be changed
	 */
	public void setActivityStressResistance(double ActivityStressResistance) {
		this.ActivityStressResistance = ActivityStressResistance;
	}

	/**
	 * Changes name of Distribution into value from parameter
	 * 
	 * @param DistributionName
	 *            String which is to be changed
	 */
	public void setDistributionName(String DistributionName) {
		this.DistributionName = DistributionName;
	}

	/**
	 * Changes name of Activity into value from parameter
	 * 
	 * @param Name
	 *            String which is to be changed
	 */
	public void setName(String Name) {
		this.Name = Name;
	}

	/**
	 * Changes Activity's duration into value from parameter
	 * 
	 * @param TimeParameter
	 *            double which is to be changed
	 */
	public void setTimeParameter(double TimeParameter) {
		this.TimeParameter = TimeParameter;
	}
}
