package gameData;

import java.util.ArrayList;

/**
 * Class represents shop, which is an ArrayList of Item and contains methods
 * 
 * @author Andrzej Tarnowski
 * @see ArrayList
 * @see Item
 */
public class Shop {
	/**
	 * private ArrayList of Items which are available in Shop
	 */
	private ArrayList<Item> Items;

	public Shop() {
		Items = new ArrayList<Item>();
	}

	/**
	 * Adds an Item to ArrayList
	 * 
	 * @param i
	 *            Item to be added to list
	 */
	public void AddItem(Item i) {
		Items.add(i);
	}

	/**
	 * Searches shop until it finds Item with the same name as parameter and
	 * returns that Item
	 * 
	 * @param name
	 *            Item's name
	 * @return Searched Item
	 */
	public Item getItem(String name) {
		int i = 0;
		while (!Items.get(i).getName().equals(name) && i < Items.size()) {
			i++;
		}
		return Items.get(i);
	}

	/**
	 * Returns ArrayList of Items
	 * 
	 * @return List of items in shop
	 */
	public ArrayList<Item> getItems() {
		return Items;
	}
}
