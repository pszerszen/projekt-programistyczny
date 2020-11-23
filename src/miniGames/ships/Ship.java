package miniGames.ships;

/**
 * class ships represents a ship, stores the data of ship and provides methods
 * to check state of ship after shoot
 * 
 * @see ShipsBoard
 * @see ShipsField
 * @author Wojciech Stokłosa
 */
public class Ship {

	/**
	 * state of ship; true == ship is destroyed, false == ship is still
	 * functional
	 */
	private boolean destroyed;
	/**
	 * boolean array stores datas about state of parts of this ship. true ==
	 * destroyed part, flase == undamaged part
	 */
	private boolean destroyedParts[];
	/**
	 * number of hits
	 */
	private int hitCount;
	// stores plane of ship, horizontal == false, vertical == true
	private boolean plane;
	/**
	 * size of ship, between 2 and 5 (include)
	 */
	private int size;
	/**
	 * coordinate of beginning of the ship on the x-axis
	 */
	private int x1;
	/**
	 * coordinate of ending of the ship on the x-axis
	 */
	private int x2;
	/**
	 * coordinate of beginning of the ship on the y-axis
	 */
	private int y1;
	/**
	 * coordinate of beginning of the ship on the y-axis
	 */
	private int y2;

	/**
	 * constructor of the ship
	 * 
	 * @param size
	 *            size of ship, between 2 and 5 (include)
	 * @param x1
	 *            coordinate of beginning of the ship on the x-axis
	 * @param y1
	 *            coordinate of beginning of the ship on the y-axis
	 * @param x2
	 *            coordinate of ending of the ship on the x-axis
	 * @param y2
	 *            coordinate of ending of the ship on the y-axis
	 */
	@SuppressWarnings("unused")
	public Ship(int size, int x1, int y1, int x2, int y2) {
		this.size = size;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.destroyed = false;
		this.hitCount = 0;
		if (x1 < x2) {
			plane = true;
		} else {
			plane = false;
		}
		this.destroyedParts = new boolean[size];
		for (boolean p : destroyedParts) {
			p = false;
		}
	}

	/**
	 * method called after hit the ship
	 * 
	 * @param x
	 *            coordinate of shoot on the x-axis
	 * @param y
	 *            coordinate of shoot on the y-axis
	 * @return true if ship is destroyed, false in other case
	 */
	public boolean Destroy(int x, int y) {
		int i;
		if (plane) {
			i = x - x1;
		} else {
			i = y - y1;
		}
		destroyedParts[i] = true;
		hitCount++;
		if (hitCount == size) {
			destroyed = true;
		}
		return destroyed;
	}

	/**
	 * get state of parts of ship
	 * 
	 * @return boolean array of states of the part of ships. if state === true
	 *         part of ship is destroyer, false in other case
	 */
	public boolean[] getDestroyedParts() {
		return destroyedParts;
	}

	/**
	 * get number of hits
	 * 
	 * @return number of hits
	 */
	public int getHitCount() {
		return hitCount;
	}

	/**
	 * get size of the sheep, between 2 and 5
	 * 
	 * @return size of the ship
	 */
	public int getSize() {
		return size;
	}

	/**
	 * get coordinate of beginning of the ship on the x-axis
	 * 
	 * @return coordinate of beginning of the ship on the x-axis
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * get coordinate of ending of the ship on the x-axis
	 * 
	 * @return coordinate of ending of the ship on the x-axis
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * get coordinate of beginning of the ship on the u-axis
	 * 
	 * @return coordinate of beginning of the ship on the y-axis
	 */
	public int getY1() {
		return y1;
	}

	/**
	 * get coordinate of ending of the ship on the y-axis
	 * 
	 * @return coordinate of ending of the ship on the y-axis
	 */
	public int getY2() {
		return y2;
	}

	/**
	 * get state of this ship
	 * 
	 * @return true if ship is destroyed
	 */
	public boolean isDestroyed() {
		return destroyed;
	}

	/**
	 * get plane of the ship. Horizontal == false, vertical == true
	 * 
	 * @return plane of the ship
	 */
	public boolean isPlane() {
		return plane;
	}

	/**
	 * set state of ship
	 * 
	 * @param destroyed
	 *            true if destroyed, else in oter case
	 */
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	/**
	 * set state of parts of ship
	 * 
	 * @param destroyedParts
	 *            boolean array of states of the part of ships. if state ===
	 *            true part of ship is destroyer, false in other case
	 */
	public void setDestroyedParts(boolean[] destroyedParts) {
		this.destroyedParts = destroyedParts;
	}

	/**
	 * set number of hits
	 * 
	 * @param hitCount
	 *            number of hits
	 */
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	/**
	 * set plane of the ship. Horizontal == false, vertical == true
	 * 
	 * @param plane
	 *            plane of the ship
	 */
	public void setPlane(boolean plane) {
		this.plane = plane;
	}

	/**
	 * get size of the sheep, between 2 and 5
	 * 
	 * @param size
	 *            size of the ship
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * set coordinate of beginning of the ship on the x-axis
	 * 
	 * @param x1
	 *            coordinate of beginning of the ship on the x-axis
	 */
	public void setX1(int x1) {
		this.x1 = x1;
	}

	/**
	 * set coordinate of ending of the ship on the x-axis
	 * 
	 * @param x2
	 *            coordinate of ending of the ship on the x-axis
	 */
	public void setX2(int x2) {
		this.x2 = x2;
	}

	/**
	 * set coordinate of beginning of the ship on the y-axis
	 * 
	 * @param y1
	 *            coordinate of beginning of the ship on the y-axis
	 */
	public void setY1(int y1) {
		this.y1 = y1;
	}

	/**
	 * set coordinate of ending of the ship on the y-axis
	 * 
	 * @param y2
	 *            coordinate of ending of the ship on the y-axis
	 */
	public void setY2(int y2) {
		this.y2 = y2;
	}
}
