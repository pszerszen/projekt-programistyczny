package miniGames.ships;

import java.security.SecureRandom;

/**
 * class Player represents a player, stores the data of player and provides
 * methods that let player to make moves and interact with opponents.
 * 
 * @see Computer
 * @see ShipsBoard
 * @author Wociech Stokłosa
 */
public class ShipPlayer {

	/**
	 * board of this player
	 */
	private ShipsBoard board;
	/**
	 * array with states of fields of opponents. true == field was under fire,
	 * else in other case
	 */
	private boolean FieldsIShooted[][];
	/**
	 * state of game. if true, player lost
	 */
	private boolean loose;
	/**
	 * Number of shots that this player taken
	 */
	private int NumberOfShotsTaken;
	/**
	 * number of casted shots
	 */
	private int NumberOfTotalShots;
	/**
	 * generate random numbers
	 */
	private SecureRandom rand;
	/**
	 * horizontal size of board.
	 */
	private int SizeX = 12;
	/**
	 * vertical size of board.
	 */
	private int SizeY = 12;
	/**
	 * fields where you can't locane new ship
	 */
	boolean[][] usedFields = new boolean[12][12];

	/**
	 * constructor of a player.
	 */
	public ShipPlayer() {
		board = new ShipsBoard(SizeX, SizeY);
		NumberOfShotsTaken = 0;
		NumberOfTotalShots = 0;
		loose = false;
		FieldsIShooted = new boolean[SizeX][SizeY];
		int i, j;
		for (i = 0; i < SizeX; i++) {
			for (j = 0; j < SizeY; j++) {
				FieldsIShooted[i][j] = false;
			}
		}
		rand = new SecureRandom();
		for (i = 0; i < 12; i++) {
			for (j = 0; j < 12; j++) {
				usedFields[i][j] = false;
			}
		}
	}

	/**
	 * * get board of this player
	 * 
	 * @return board of this player
	 */
	public ShipsBoard getBoard() {
		return board;
	}

	/**
	 * 
	 * @return Fields of this board
	 */
	public ShipsField[][] getFields() {
		return board.getFields();
	}

	/**
	 * get array with states of fields of opponent. true == already shooted
	 * field, else in other case
	 * 
	 * @return array with states of fields of opponent. true == already shooted
	 *         field, else in other case
	 */
	public boolean[][] getFieldsIShooted() {
		return FieldsIShooted;
	}

	/**
	 * get horizontal size of board
	 * 
	 * @return horizontal size of board
	 */
	public int getSizeX() {
		return SizeX;
	}

	/**
	 * get vertical size of board
	 * 
	 * @return horizontal size of board
	 */
	public int getSizeY() {
		return SizeY;
	}

	/**
	 * Method called when the oponent shoot to this player
	 * 
	 * @param x
	 *            coordinate of shoot on the x-axis
	 * @param y
	 *            coordinate of shoot on the y-axis
	 * @return if ship is destroyed return 1, if there is a ship return 2, if
	 *         there are no ships return 3, if player lost return 4;
	 */
	public int IGetShooted(int x, int y) {
		NumberOfShotsTaken++;
		int shot = board.Shoot(x, y);
		if (shot == 4) {
			ShipsController.makeGrade("computer");
			loose = true;
		}
		if (shot == 1) {
			ShipsController.infoFrame.getMessage().setText("Straciłeś statek!");
			ShipsController.infoFrame.setVisible(true);
		}
		return shot;
	}

	/**
	 * get state of game
	 * 
	 * @return true if player lost, else in other case
	 */
	public boolean isLoose() {
		return loose;
	}

	/**
	 * Method that randomly locate ships at board. Cares about colisions
	 */
	public void locateShips() {
		Ship ship;
		boolean busy = true;
		boolean[][] usedFields = new boolean[12][12];
		int i, x, y, j;
		// ranges to setting fields as used
		int startX, startY, endX, endY;
		for (i = 0; i < 12; i++) {
			for (j = 0; j < 12; j++) {
				usedFields[i][j] = false;
			}
		}
		for (j = 2; j <= 5; j++) {
			i = rand.nextInt(2);
			// for horizontally located ships
			if (i == 1) {
				// locate ships at free spaces
				while (busy) {
					busy = false;
					x = rand.nextInt(12 - j);
					y = rand.nextInt(12);
					for (i = x; i < x + j; i++) {
						if (usedFields[i][y]) {
							busy = true;
						}
					}
					// if space is free, locate ship
					if (!busy) {
						ship = new Ship(j, x, y, x + j - 1, y);
						board.setShip(ship);
						startX = x - 1;
						startY = y - 1;
						endX = x + j;
						endY = y + 1;
						if (startX < 0) {
							startX++;
						}
						if (startY < 0) {
							startY++;
						}
						if (endX > 11) {
							endX--;
						}
						if (endY > 11) {
							endY--;
						}
						for (int m = startY; m <= endY; m++) {
							for (i = startX; i <= endX; i++) {
								usedFields[i][m] = true;
							}
						}
					}
				}
			} // for vertically located ships
			else {
				// locate ships at free spaces
				while (busy) {
					busy = false;
					x = rand.nextInt(12);
					y = rand.nextInt(12 - j);
					for (i = y; i < y + j; i++) {
						if (usedFields[x][i]) {
							busy = true;
						}
					}
					// if space is free, locate ship
					if (busy == false) {
						ship = new Ship(j, x, y, x, y + j - 1);
						board.setShip(ship);
						startX = x - 1;
						startY = y - 1;
						endX = x + 1;
						endY = y + j;
						if (startX < 0) {
							startX++;
						}
						if (startY < 0) {
							startY++;
						}
						if (endX > 11) {
							endX--;
						}
						if (endY > 11) {
							endY--;
						}
						for (int m = startY; m <= endY; m++) {
							for (i = startX; i <= endX; i++) {
								usedFields[i][m] = true;
							}
						}
					}
				}
			}
			busy = true;
		}
	}

	/**
	 * Method that randomly locate ships at board. Cares about colisions
	 */
	public boolean locateShips(Ship ship) {
		boolean busy = false;
		int i;
		// ranges to setting fields as used
		int startX, startY, endX, endY;
		// for horizontally located ships
		if (ship.isPlane()) {
			if (ship.getX1() + ship.getSize() - 1 < 12) {
				// locate ships at free spaces
				for (i = ship.getX1(); i < ship.getX1() + ship.getSize(); i++) {
					if (usedFields[i][ship.getY1()]) {
						busy = true;
					}
				}
				// if space is free, locate ship
				if (!busy) {
					board.setShip(ship);
					startX = ship.getX1() - 1;
					startY = ship.getY1() - 1;
					endX = ship.getX1() + ship.getSize();
					endY = ship.getY1() + 1;
					if (startX < 0) {
						startX++;
					}
					if (startY < 0) {
						startY++;
					}
					if (endX > 11) {
						endX--;
					}
					if (endY > 11) {
						endY--;
					}
					for (int m = startY; m <= endY; m++) {
						for (i = startX; i <= endX; i++) {
							usedFields[i][m] = true;
						}
					}
				}
			} else {
				busy = true;
			}
		}// for vertically located ships
		else {
			if (ship.getY1() + ship.getSize() - 1 < 12) {
				// locate ships at free spaces
				for (i = ship.getY1(); i < ship.getY1() + ship.getSize(); i++) {
					if (usedFields[ship.getX1()][i]) {
						busy = true;
					}
				}
				// if space is free, locate ship
				if (!busy) {
					board.setShip(ship);
					startX = ship.getX1() - 1;
					startY = ship.getY1() - 1;
					endX = ship.getX1() + 1;
					endY = ship.getY1() + ship.getSize();
					if (startX < 0) {
						startX++;
					}
					if (startY < 0) {
						startY++;
					}
					if (endX > 11) {
						endX--;
					}
					if (endY > 11) {
						endY--;
					}
					for (int m = startY; m <= endY; m++) {
						for (i = startX; i <= endX; i++) {
							usedFields[i][m] = true;
						}
					}
				}
			} else {
				busy = true;
			}
		}
		return !busy;
	}

	/**
	 * set board of this player
	 * 
	 * @param board
	 *            board of this player
	 */
	public void setBoard(ShipsBoard board) {
		this.board = board;
	}

	/**
	 * set array with states of fields of opponent. true == already shooted
	 * field, else in other case
	 * 
	 * @param FieldsIShooted
	 *            array with states of fields of opponent. true == already
	 *            shooted field, else in other case
	 */
	public void setFieldsIShooted(boolean[][] FieldsIShooted) {
		this.FieldsIShooted = FieldsIShooted;
	}

	/**
	 * set state of game
	 * 
	 * @param loose
	 *            true if player lost, else in other case
	 */
	public void setLoose(boolean loose) {
		this.loose = loose;
	}

	/**
	 * set horizontal size of board
	 */
	public void setSizeX(int SizeX) {
		this.SizeX = SizeX;
	}

	/**
	 * set vertical size of board
	 */
	public void setSizeY(int SizeY) {
		this.SizeY = SizeY;
	}

	/**
	 * Method called to shoot in opponent.
	 * 
	 * @param x
	 *            coordinate of shoot on the x-axis
	 * @param y
	 *            coordinate of shoot on the y-axis
	 */
	public boolean shoot(int x, int y) {
		if (NumberOfShotsTaken == NumberOfTotalShots) {
			FieldsIShooted[x][y] = true;
			ShipsController.computer.IGetShooted(x, y);
			NumberOfTotalShots++;
			return true;
		}
		return false;
	}
}
