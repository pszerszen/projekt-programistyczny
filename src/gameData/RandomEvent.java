package gameData;

/**
 * The RandomEvent class stores the data of event
 * 
 * @author Mateusz Szews
 */
public class RandomEvent {
	/** private variable which contains name of activity, which runs this event */
	private String ActivityName;
	/** private variable which contains description of this event */
	private String Description;
	/** private variable which contains parameter of changing player's Energy */
	private double EventEnergy;
	/** private variable which contains parameter of changing player's Happiness */
	private double EventHappiness;
	/** private variable which contains parameter of changing player's Knowledge */
	private double EventKnowledge;
	/** private variable which contains parameter of changing player's Money */
	private double EventMoney;
	/** private variable which contains name of event */
	private String Name;
	/** private variable which contains number of rounds for activity */
	private short NumberOfRounds;
	/** private variable which contains probability of selecting this event */
	private double Probability;
	/** private variable which contains number of rounds on doing this event */
	private short RoundsTaken;
	/**
	 * private variable which contains name of activity, which this event can
	 * start
	 */
	private String StartsActivity;

	/**
	 * RandomEvent constructor
	 */
	RandomEvent() {
		this.Name = "";
		this.Probability = 0;
		this.EventKnowledge = 0;
		this.EventHappiness = 0;
		this.EventEnergy = 0;
		this.EventMoney = 0;
		this.Description = "";
		this.ActivityName = "";
		this.RoundsTaken = 0;
		this.StartsActivity = "";
		this.NumberOfRounds = 0;
	}

	/**
	 * Returns name of Activity
	 * 
	 * @return Name of Activity
	 */
	public String getActivityName() {
		return ActivityName;
	}

	/**
	 * Returns event's description
	 * 
	 * @return Event's description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * Returns Event's EventEnergy
	 * 
	 * @return Event's EventEnergy
	 */
	public double getEventEnergy() {
		return EventEnergy;
	}

	/**
	 * Returns Event's EventHappiness
	 * 
	 * @return Event's EventHappiness
	 */
	public double getEventHappiness() {
		return EventHappiness;
	}

	/**
	 * Returns Event's Eventknowledge
	 * 
	 * @return Event's EventKnowledge
	 */
	public double getEventKnowledge() {
		return EventKnowledge;
	}

	/**
	 * Returns Event's EventMoney
	 * 
	 * @return Event's EventMoney
	 */
	public double getEventMoney() {
		return EventMoney;
	}

	/**
	 * Returns Event's Name
	 * 
	 * @return Event's Name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Returns Event's NumberOfRounds
	 * 
	 * @return Event's NumberOfRounds
	 */
	public short getNumberOfRounds() {
		return NumberOfRounds;
	}

	/**
	 * Returns Event's Probability
	 * 
	 * @return Event's Probability
	 */
	public double getProbability() {
		return Probability;
	}

	/**
	 * Returns Event's RoundTaken
	 * 
	 * @return Event's RoundsTaken
	 */
	public short getRoundsTaken() {
		return RoundsTaken;
	}

	/**
	 * Returns Event's StartsActivity
	 * 
	 * @return Event's StartsActivity
	 */
	public String getStartsActivity() {
		return StartsActivity;
	}

	/**
	 * Changes value in Event's private variable ActivityName into value from
	 * parameter
	 * 
	 * @param ActivityName
	 *            Variable which is to be changed
	 */
	public void setActivityName(String ActivityName) {
		this.ActivityName = ActivityName;
	}

	/**
	 * Changes value in Event's private variable Description into value from
	 * parameter
	 * 
	 * @param Description
	 *            Variable which is to be changed
	 */
	public void setDescription(String Description) {
		this.Description = Description;
	}

	/**
	 * Changes value in Event's private variable EventEnergy into value from
	 * parameter
	 * 
	 * @param EventEnergy
	 *            Variable which is to be changed
	 */
	public void setEventEnergy(double EventEnergy) {
		this.EventEnergy = EventEnergy;
	}

	/**
	 * Changes value in Event's private variable EventHappiness into value from
	 * parameter
	 * 
	 * @param EventHappiness
	 *            Variable which is to be changed
	 */
	public void setEventHappiness(double EventHappiness) {
		this.EventHappiness = EventHappiness;
	}

	/**
	 * Changes value in Event's private variable EventKnowledge into value from
	 * parameter
	 * 
	 * @param EventKnowledge
	 *            Variable which is to be changed
	 */
	public void setEventKnowledge(double EventKnowledge) {
		this.EventKnowledge = EventKnowledge;
	}

	/**
	 * Changes value in Event's private variable EventMoney into value from
	 * parameter
	 * 
	 * @param EventMoney
	 *            Variable which is to be changed
	 */
	public void setEventMoney(double EventMoney) {
		this.EventMoney = EventMoney;
	}

	/**
	 * Changes value in Event's private variable Name into value from parameter
	 * 
	 * @param Name
	 *            Variable which is to be changed
	 */
	public void setName(String Name) {
		this.Name = Name;
	}

	/**
	 * Changes value in Event's private variable NumberOfRounds into value from
	 * parameter
	 * 
	 * @param NumberOfRounds
	 *            Variable which is to be changed
	 */
	public void setNumberOfRounds(short NumberOfRounds) {
		this.NumberOfRounds = NumberOfRounds;
	}

	/**
	 * Changes value in Event's private variable Probability into value from
	 * parameter
	 * 
	 * @param Probability
	 *            Variable which is to be changed
	 */
	public void setProbability(double Probability) {
		this.Probability = Probability;
	}

	/**
	 * Changes value in Event's private variable RoundsTaken into value from
	 * parameter
	 * 
	 * @param RoundsTaken
	 *            Variable which is to be changed
	 */
	public void setRoundsTaken(short RoundsTaken) {
		this.RoundsTaken = RoundsTaken;
	}

	/**
	 * Returns Event's StartsActivity
	 * 
	 * @param StartsActivity
	 *            Variable which is to be changed
	 */
	public void setStartsActivity(String StartsActivity) {
		this.StartsActivity = StartsActivity;
	}

}
