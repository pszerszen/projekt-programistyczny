package gameData;

/**
 * This class consists all parameters, which represents item and consists public
 * method that operate on items or return item's parameter
 * 
 * @author Andrzej Tarnowski
 * @author Piotr Szerszeń
 * 
 */
public class Item {
	/**
	 * private variable depositing information is item active in current round
	 */
	private boolean active;
	/**
	 * private variable depositing amount of rounds in which item may be active
	 */
	private short Duration;
	/**
	 * private variable depositing item's influence on player's Charisma
	 */
	private float ItemCharisma;
	/**
	 * private variable depositing item's influence on player's Endurance
	 */
	private float ItemEndurance;
	/**
	 * private variable depositing item's influence on player's Energy
	 */
	private float ItemEnergy;
	/**
	 * private variable depositing item's influence on player's Happiness
	 */
	private float ItemHappiness;
	/**
	 * private variable depositing item's influence on player's Intelligence
	 */
	private float ItemIntelligence;
	/**
	 * private variable depositing item's influence on player's Knowledge
	 */
	private float ItemKnowledge;
	/**
	 * private variable depositing item's influence on player's Scrupulousness
	 */
	private float ItemScrupulousness;
	/**
	 * private variable depositing item's influence on player's resistance to
	 * stress
	 */
	private float ItemStressResistance;
	/**
	 * private variable which contains item's name
	 */
	private String Name;
	/**
	 * private variable depositing price of item
	 */
	private int Price;

	public Item() {

	}

	/**
	 * Item's constructor
	 */
	public Item(String Name, byte Duration, float ItemKnowledge,
			float ItemEnergy, float ItemHappiness, int Price,
			float ItemIntelligence, float ItemCharisma, float ItemEndurance,
			float ItemStressResistance, float ItemScrupulousness) {
		this.Name = Name;
		this.Duration = Duration;
		this.ItemCharisma = ItemCharisma;
		this.ItemEndurance = ItemEndurance;
		this.ItemEnergy = ItemEnergy;
		this.ItemHappiness = ItemHappiness;
		this.ItemIntelligence = ItemIntelligence;
		this.ItemKnowledge = ItemKnowledge;
		this.ItemScrupulousness = ItemScrupulousness;
		this.ItemStressResistance = ItemStressResistance;
	}

	/** This method activates the item */
	public void activate() {
		this.active = true;
	}

	/**
	 * Returns item's variable Duration
	 * 
	 * @return Item's Duration
	 */
	public short getDuration() {
		return Duration;
	}

	/**
	 * Returns item's variable ItemCharisma
	 * 
	 * @return Item's ItemCharisma
	 */
	public float getItemCharisma() {
		return ItemCharisma;
	}

	/**
	 * Returns item's variable ItemEndurance
	 * 
	 * @return Item's ItemEndurance
	 */
	public float getItemEndurance() {
		return ItemEndurance;
	}

	/**
	 * Returns item's variable ItemEnergy
	 * 
	 * @return Item's ItemEnergy
	 */
	public float getItemEnergy() {
		return ItemEnergy;
	}

	/**
	 * Returns item's variable ItemHappiness
	 * 
	 * @return Item's ItemHappiness
	 */
	public float getItemHappiness() {
		return ItemHappiness;
	}

	/**
	 * Returns item's variable ItemIntelligence
	 * 
	 * @return Item's ItemIntelligence
	 */
	public float getItemIntelligence() {
		return ItemIntelligence;
	}

	/**
	 * Returns item's variable ItemKnowledge
	 * 
	 * @return Item's ItemKnowledge
	 */
	public float getItemKnowledge() {
		return ItemKnowledge;
	}

	/**
	 * Returns item's variable ItemScrupulousness
	 * 
	 * @return Item's ItemScrupulousness
	 */
	public float getItemScrupulousness() {
		return ItemScrupulousness;
	}

	/**
	 * Returns item's variable StressResistance
	 * 
	 * @return Item's StressResistance
	 */
	public float getItemStressResistance() {
		return ItemStressResistance;
	}

	/**
	 * Returns item's variable Name
	 * 
	 * @return Item's Name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Returns item's variable Price
	 * 
	 * @return Item's Price
	 */
	public int getPrice() {
		return Price;
	}

	/**
	 * Checks is item active in current round
	 * 
	 * @return Information is item active in current round
	 */
	public boolean isActive() {
		return active;
	}

	/** Decreases Duration by one */
	public void reduceDuration() {
		this.Duration--;

	}

	/**
	 * Changes value in item's private variable Duration into value from
	 * parameter
	 * 
	 * @param Duration
	 *            Variable which is to be changed
	 */
	public void setDuration(short Duration) {
		this.Duration = Duration;
	}

	/**
	 * Changes value in item's private variable ItemCharisma into value from
	 * parameter
	 * 
	 * @param ItemCharisma
	 *            Variable which is to be changed
	 */
	public void setItemCharisma(float ItemCharisma) {
		this.ItemCharisma = ItemCharisma;
	}

	/**
	 * Changes value in item's private variable ItemEndurance into value from
	 * parameter
	 * 
	 * @param ItemEndurance
	 *            Variable which is to be changed
	 */
	public void setItemEndurance(float ItemEndurance) {
		this.ItemEndurance = ItemEndurance;
	}

	/**
	 * Changes value in item's private variable ItemEnergy into value from
	 * parameter
	 * 
	 * @param ItemEnergy
	 *            Variable which is to be changed
	 */
	public void setItemEnergy(float ItemEnergy) {
		this.ItemEnergy = ItemEnergy;
	}

	/**
	 * Changes value in item's private variable ItemHappiness into value from
	 * parameter
	 * 
	 * @param ItemHappiness
	 *            Variable which is to be changed
	 */
	public void setItemHappiness(float ItemHappiness) {
		this.ItemHappiness = ItemHappiness;
	}

	/**
	 * Changes value in item's private variable ItemIntelligence into value from
	 * parameter
	 * 
	 * @param ItemIntelligence
	 *            Variable which is to be changed
	 */
	public void setItemIntelligence(float ItemIntelligence) {
		this.ItemIntelligence = ItemIntelligence;
	}

	/**
	 * Changes value in item's private variable ItemKnowledge into value from
	 * parameter
	 * 
	 * @param ItemKnowledge
	 *            Variable which is to be changed
	 */
	public void setItemKnowledge(float ItemKnowledge) {
		this.ItemKnowledge = ItemKnowledge;
	}

	/**
	 * Changes value in item's private variable ItemScrupulousness into value
	 * from parameter
	 * 
	 * @param ItemScrupulousness
	 *            Variable which is to be changed
	 */
	public void setItemScrupulousness(float ItemScrupulousness) {
		this.ItemScrupulousness = ItemScrupulousness;
	}

	/**
	 * Changes value in item's private variable ItemStressResistance into value
	 * from parameter
	 * 
	 * @param ItemStressResistance
	 *            Variable which is to be changed
	 */
	public void setItemStressResistance(float ItemStressResistance) {
		this.ItemStressResistance = ItemStressResistance;
	}

	/**
	 * Changes value in item's private variable Name into value from parameter
	 * 
	 * @param Name
	 *            Variable which is to be changed
	 */
	public void setName(String Name) {
		this.Name = Name;
	}

	/**
	 * Changes value in item's private variable Price into value from parameter
	 * 
	 * @param Price
	 *            Variable which is to be changed
	 */
	public void setPrice(int Price) {
		this.Price = Price;
	}

}
