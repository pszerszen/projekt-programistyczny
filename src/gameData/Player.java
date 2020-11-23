package gameData;

import java.util.ArrayList;

/**
 * The Player class stores the data of player and methods that operate on
 * player's parameters
 * 
 * @author Wojciech Stokłosa
 * @see gameData.Course
 * @see gameData.Item
 */
public class Player {

	/**
	 * private variable which contains parameter of players's charisma
	 */
	private int Charisma;
	/**
	 * private arraylist of courses player has to done
	 */
	private ArrayList<Course> Courses = new ArrayList<Course>();
	/**
	 * private variable which contains how much ECTS player has
	 */
	private int ECTS;
	/**
	 * private variable which contains parameter of players's endurance
	 */
	private int Endurance;
	/**
	 * private variable which contains parameter of players's energy
	 */
	private int Energy;
	/**
	 * private variable which contains parameter of players's happiness
	 */
	private int Happiness;
	/**
	 * private list of items that player bought and didn't use
	 */
	private ArrayList<Item> InActiveItems = new ArrayList<>();
	/**
	 * private variable which contains parameter of players's intelligence
	 */
	private int Inteligence;
	/**
	 * private variable which contains parameter of items interference
	 */
	private double ItemParameter;
	/**
	 * private list of items that player bought
	 */
	private ArrayList<Item> Items = new ArrayList<Item>();
	/**
	 * private variable which contains parameter of players's knowledge
	 */
	private int Knowledge;
	/**
	 * last round player had 0 energy.
	 */
	private int lastRound0;
	/**
	 * private variable which contains player's cash
	 */
	private int Money;
	/**
	 * private variable which contains players's name
	 */
	private String Name;
	/**
	 * private variable which contains parameter of players's scrupulousness
	 */
	private int Scrupulousness;
	/**
	 * private variable which contains parameter of players's stress resistance
	 */
	private int StressResistance;
	/**
	 * private variable which contains players's surname
	 */
	private String Surname;

	/**
	 * Player constructor
	 */
	public Player() {
		Name = "";
		Surname = "";
		Inteligence = 0;
		Charisma = 0;
		Endurance = 0;
		Scrupulousness = 0;
		StressResistance = 0;
		Knowledge = 0;
		Happiness = 0;
		Energy = 0;
		Money = 0;
		ECTS = 0;
		Courses = new ArrayList<>();
		Items = new ArrayList<>();
		ItemParameter = 1;
		lastRound0 = -1;
	}

	/**
	 * Adding effects of items to player
	 * 
	 * @param i
	 *            Item which effects are to be added
	 * @see gameData.Item
	 */
	private void addItemEffect(Item i) {

		Inteligence = (int) (Inteligence + i.getItemIntelligence()
				* ItemParameter);
		Charisma = (int) (Charisma + i.getItemCharisma() * ItemParameter);
		Endurance = (int) (Endurance + i.getItemEndurance() * ItemParameter);
		Scrupulousness = (int) (Scrupulousness + i.getItemScrupulousness()
				* ItemParameter);
		StressResistance = (int) (StressResistance + i
				.getItemStressResistance() * ItemParameter);
		Knowledge = (int) (Knowledge + i.getItemKnowledge() * ItemParameter);
		Happiness = (int) (Happiness + i.getItemHappiness() * ItemParameter);
		Energy = (int) (Energy + i.getItemEnergy() * ItemParameter);
	}

	/**
	 * kills this player
	 */
	private void KillPlayer() {
		DataBase.lost();
	}

	/**
	 * Removing effects of items when duration reaches zero
	 * 
	 * @param i
	 *            Item which effects are to be removed
	 */
	private void removeItemEffect(Item i) {
		Inteligence = (int) (Inteligence - i.getItemIntelligence()
				* ItemParameter);
		Charisma = (int) (Charisma - i.getItemCharisma() * ItemParameter);
		Endurance = (int) (Endurance - i.getItemEndurance() * ItemParameter);
		Scrupulousness = (int) (Scrupulousness - i.getItemScrupulousness()
				* ItemParameter);
		StressResistance = (int) (StressResistance - i
				.getItemStressResistance() * ItemParameter);
		Knowledge = (int) (Knowledge - i.getItemKnowledge() * ItemParameter);
		Happiness = (int) (Happiness - i.getItemHappiness() * ItemParameter);
		Energy = (int) (Energy - i.getItemEnergy() * ItemParameter);
	}

	/**
	 * Add course to ArrayList
	 * 
	 * @param course
	 *            course which is to be added
	 * @see gameData.Course
	 */
	public void addCourse(Course course) {
		Courses.add(course);
	}

	/**
	 * Add item to ArrayList of items
	 * 
	 * @param item
	 *            Item which is to be added
	 * @see gameData.Item
	 */
	public void buyItem(Item item) {
		Items.add(item);
		InActiveItems.add(item);
	}

	/**
	 * Decreases Duration of active items and removes items when Duration reach
	 * 0. Removes effects of items and calls method
	 * {@link #removeItemEffect(gameData.Item)} when necessary
	 * 
	 * @see gameData.Item
	 */
	public void DecreaseItemsDuration() {
		for (int i = 0; i < Items.size(); i++) {
			if (Items.get(i).isActive()) {
				Items.get(i).reduceDuration();
			}
			if (Items.get(i).getDuration() == 0) {
				removeItemEffect(Items.get(i));
				Items.remove(i);
			}
		}
	}

	/**
	 * Returns Player's Charisma
	 * 
	 * @return charisma
	 */
	public int getCharisma() {
		return Charisma;
	}

	/**
	 * Returns all Player's Courses as ArrayList
	 * 
	 * @return ArrayList of courses
	 * @see ArrayList
	 */
	public ArrayList<Course> getCourses() {
		return Courses;
	}

	/**
	 * Returns Player's ECTS
	 * 
	 * @return ECTS
	 */
	public int getECTS() {
		return ECTS;
	}

	/**
	 * Returns Player's Endurance
	 * 
	 * @return Endurance
	 */
	public int getEndurance() {
		return Endurance;
	}

	/**
	 * Returns Player's Energy
	 * 
	 * @return Energy
	 */
	public int getEnergy() {
		return Energy;
	}

	/**
	 * Returns Player's Happiness
	 * 
	 * @return Happiness
	 */
	public int getHappiness() {
		return Happiness;
	}

	/**
	 * Returns list of Items which haven't been used yet
	 * 
	 * @return list containing unused items
	 */
	public ArrayList<Item> getInActiveItems() {
		return InActiveItems;
	}

	/**
	 * Returns Player's Inteligence
	 * 
	 * @return Inteligence
	 */
	public int getInteligence() {
		return Inteligence;
	}

	/**
	 * Returns Player's ItemParameter
	 * 
	 * @return ItemParameter
	 */
	public double getItemParameter() {
		return ItemParameter;
	}

	/**
	 * Return list of Items
	 * 
	 * @return ArrayList of Items
	 * @see ArrayList
	 */
	public ArrayList<Item> getItems() {
		return Items;
	}

	/**
	 * Returns Player's Knowledge
	 * 
	 * @return Knowledge
	 */
	public int getKnowledge() {
		return Knowledge;
	}

	/**
	 * Returns Player's Money
	 * 
	 * @return Money
	 */
	public int getMoney() {
		return Money;
	}

	/**
	 * Returns Player's Name
	 * 
	 * @return Name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Returns Player's Scrupulousness
	 * 
	 * @return Scrupulousness
	 */
	public int getScrupulousness() {
		return Scrupulousness;
	}

	/**
	 * Returns Player's StressResistance
	 * 
	 * @return StressResistance
	 */
	public int getStressResistance() {
		return StressResistance;
	}

	/**
	 * Returns Player's Surname
	 * 
	 * @return Surname
	 */
	public String getSurname() {
		return Surname;
	}

	public void makeRounds(int NumberOfRounds) {
		for (int i = 0; i < NumberOfRounds; i++) {
			DecreaseItemsDuration();
		}
	}

	/**
	 * Changes value in Player's private variable Charisma into value from
	 * parameter or 0 if value from parameter is lower than 0
	 * 
	 * @param Charisma
	 *            Variable which is to be changed
	 */
	public void setCharisma(int Charisma) {
		if (Charisma >= 0) {
			this.Charisma = Charisma;
		} else {
			this.Charisma = 0;
		}
	}

	/**
	 * Changes value in Player's private variable Courses into value from
	 * parameter
	 * 
	 * @param Courses
	 *            list of courses
	 */
	public void setCourses(ArrayList<Course> Courses) {
		this.Courses = Courses;
	}

	/**
	 * Changes value in Player's private variable ECTS into value from parameter
	 * 
	 * @param ECTS
	 *            Variable which is to be changed
	 */
	public void setECTS(int ECTS) {
		this.ECTS = ECTS;
	}

	/**
	 * Changes value in Player's private variable Endurance into value from
	 * parameter or 0 if value from parameter is lower than 0
	 * 
	 * @param Endurance
	 *            Variable which is to be changed
	 */
	public void setEndurance(int Endurance) {
		if (Endurance >= 0) {
			this.Endurance = Endurance;
		} else {
			this.Endurance = 0;
		}
	}

	/**
	 * Changes value in Player's private variable Energy into value from
	 * parameter or 0 if value from parameter is lower than 0
	 * 
	 * @param Energy
	 *            Variable which is to be changed
	 */
	public void setEnergy(int Energy) {
		if (Energy >= 0 && Energy <= 1000) {
			this.Energy = Energy;
			this.lastRound0 = -1;
		} else if (Energy < 0) {
			if (this.lastRound0 != -1) {
				if (DataBase.Round - lastRound0 >= 24) {
					KillPlayer();
				}
			} else {
				this.lastRound0 = DataBase.Round;
			}
			this.Energy = 0;
		} else {
			this.Energy = 1000;
			this.lastRound0 = -1;
		}
	}

	/**
	 * Changes value in Player's private variable Happiness into value from
	 * parameter or 0 if value from parameter is lower than 0
	 * 
	 * @param Happiness
	 *            Variable which is to be changed
	 */
	public void setHappiness(int Happiness) {
		if (Happiness >= 0 && Happiness <= 1000) {
			this.Happiness = Happiness;
		} else if (Happiness < 0) {
			this.Happiness = 0;
		} else {
			this.Happiness = 1000;
		}
	}

	/**
	 * Replaces list of items which haven't been used with list from parameter
	 * 
	 * @param InActiveItems
	 *            List which is to be replaced
	 */
	public void setInActiveItems(ArrayList<Item> InActiveItems) {
		this.InActiveItems = InActiveItems;
	}

	/**
	 * Adds item from parameter to list of items which haven't been used
	 * 
	 * @param item
	 *            Item which is to be added
	 */
	public void setInActiveItems(Item item) {
		InActiveItems.add(item);
	}

	/**
	 * Changes value in Player's private variable Endurance into value from
	 * parameter or 0 if value from parameter is lower than 0
	 * 
	 * @param Inteligence
	 *            Variable which is to be changed
	 */
	public void setInteligence(int Inteligence) {
		if (Inteligence >= 0) {
			this.Inteligence = Inteligence;
		} else {
			this.Inteligence = 0;
		}
	}

	/**
	 * Changes value in Player's private variable ItemParameter into value from
	 * parameter
	 * 
	 * @param ItemParameter
	 *            ItemParameter
	 */
	public void setItemParameter(double ItemParameter) {
		this.ItemParameter = ItemParameter;
	}

	/**
	 * Replaces list of items with list from parameter
	 * 
	 * @param itemlist
	 *            list which is to be replaced
	 */
	public void setItems(ArrayList<Item> itemlist) {
		this.Items = itemlist;
	}

	/**
	 * Adds item from parameter to list of items
	 * 
	 * @param item
	 *            item list which is to be added
	 */
	public void setItems(Item item) {
		this.Items.add(item);
	}

	/**
	 * Changes value in Player's private variable Knowledge into value from
	 * parameter or 0 if value from parameter is lower than 0
	 * 
	 * @param Knowledge
	 *            Variable which is to be changed
	 */
	public void setKnowledge(int Knowledge) {
		if (Knowledge >= 0 && Knowledge <= 1000) {
			this.Knowledge = Knowledge;
		} else if (Knowledge < 0) {
			this.Knowledge = 0;
		} else {
			this.Knowledge = 1000;
		}
	}

	/**
	 * Changes value in Player's private variable Money into value from
	 * parameter or 0 if value from parameter is lower than 0
	 * 
	 * @param Money
	 *            Variable which is to be changed
	 */
	public void setMoney(int Money) {
		if (Money >= 0) {
			this.Money = Money;
		} else {
			this.Money = 0;
		}
	}

	/**
	 * Changes value in Player's private variable Name into value from parameter
	 * 
	 * @param Name
	 *            Name
	 */
	public void setName(String Name) {
		this.Name = Name;
	}

	/**
	 * Changes value in Player's private variable Scrupulousness into value from
	 * parameter
	 * 
	 * @param Scrupulousness
	 *            Variable which is to be changed
	 */
	public void setScrupulousness(int Scrupulousness) {
		this.Scrupulousness = Scrupulousness;
	}

	/**
	 * Changes value in Player's private variable StressResistance into value
	 * from parameter
	 * 
	 * @param StressResistance
	 *            Variable which is to be changed
	 */
	public void setStressResistance(int StressResistance) {
		this.StressResistance = StressResistance;
	}

	/**
	 * Changes value in Player's private variable Surname into value from
	 * parameter
	 * 
	 * @param Surname
	 *            Surname
	 */
	public void setSurname(String Surname) {
		this.Surname = Surname;
	}

	/**
	 * Activates effects of items. Uses {@link #addItemEffect(gameData.Item) }
	 * 
	 * @param item
	 *            Item which effects are to be activated
	 */
	public void useItem(Item item) {
		int i = Items.indexOf(item);
		Items.get(i).activate();
		addItemEffect(Items.get(i));
		InActiveItems.remove(item);
	}
}
