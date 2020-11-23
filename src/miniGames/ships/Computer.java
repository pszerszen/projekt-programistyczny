package miniGames.ships;

import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * class Computer represents a computer player, stores the data of computer
 * player and provides AI methods that let CPU to make moves and interact with
 * opponent.
 * 
 * @see ShipPlayer
 * @see ShipsBoard
 * @author Wociech Stokłosa
 */
public class Computer extends Thread {

	/**
	 * board of this player
	 */
	private ShipsBoard board;
	/**
	 * spaces in wchich computer select coordinates to shoot
	 */
	private int density;
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
	private SecureRandom rand;
	/**
	 * random shift of shoots at X-axis. this provides better randomness of
	 * shoots
	 */
	private int shiftX;
	/**
	 * random shift of shoots at Y-axis. this provides better randomness of
	 * shoots
	 */
	private int shiftY;
	/**
	 * true if computer hit ship, but it is not destroyed yet. cause different
	 * way of executing shoot.
	 */
	private boolean ShipToFinish;
	/**
	 * first coordinate of unfinished ship on the x-axis
	 */
	private int shipx1;
	/**
	 * second coordinate of unfinished ship on the x-axis
	 */
	private int shipx2;
	/**
	 * first coordinate of unfinished ship on the y-axis
	 */
	private int shipy1;
	/**
	 * second coordinate of unfinished ship on the y-axis
	 */
	private int shipy2;
	/**
	 * horizontal size of board.
	 */
	private int SizeX = 12;
	/**
	 * vertical size of board.
	 */
	private int SizeY = 12;
	/**
	 * arraylist with coordinate at X-axis that computer will shoot primarily
	 */
	private ArrayList<Integer> toShootX;
	/**
	 * arraylist with coordinate at Y-axis that computer will shoot primarily
	 */
	private ArrayList<Integer> toShootY;

	/**
	 * constructor of the CPU player
	 */
	public Computer() {
		board = new ShipsBoard(SizeX, SizeY);
		NumberOfShotsTaken = 0;
		NumberOfTotalShots = 0;
		toShootX = new ArrayList<>();
		toShootY = new ArrayList<>();
		loose = false;
		ShipToFinish = false;
		rand = new SecureRandom();
		FieldsIShooted = new boolean[SizeX][SizeY];
		int i, j;
		for (i = 0; i < SizeX; i++) {
			for (j = 0; j < SizeY; j++) {
				FieldsIShooted[i][j] = false;
			}
		}
		density = 4;
		shiftX = rand.nextInt(4);
		shiftY = rand.nextInt(4);
		locateShips();
		countToShoot();
		this.start();
	}

	/**
	 * Method that count coordinates to shoot primarily
	 */
	private void countToShoot() {
		int x, y;
		int i = 0, j;
		while (i < 12 / density) {
			j = 0;
			while (j < 12 / density) {
				x = (shiftX + i * density) % SizeX;
				y = (shiftY + j * density) % SizeY;
				if (!FieldsIShooted[x][y]) {
					toShootX.add(x);
					toShootY.add(y);
				}
				x = (shiftX + density / 2 + i * density) % SizeX;
				y = (shiftY + density / 2 + j * density) % SizeY;
				if (!FieldsIShooted[x][y]) {
					toShootX.add(x);
					toShootY.add(y);
				}
				j++;
			}
			i++;
		}
	}

	/**
	 * Method that randomly locate ships at board. Cares about colisions
	 */
	private void locateShips() {
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
	 * 
	 * method called by {@link #shoot() }, it shoots in the area of found and
	 * 'unfinished' ship.
	 */
	private void SeekShip() {
		boolean shootToDo = true;
		// when ship is horizontally sited
		if (shipx1 != shipx2) {
			if (shipx1 > shipx2) {
				if (shipx2 != 0 && shootToDo) {
					if (!FieldsIShooted[shipx2 - 1][shipy1]) {
						seekShoot(shipx2 - 1, shipy1);
						shootToDo = false;
					}
				}
				if (shipx1 != 0 && shootToDo) {
					if (!FieldsIShooted[shipx1 + 1][shipy1]) {
						seekShoot(shipx1 + 1, shipy1);
						shootToDo = false;
					}
				}
			} else {
				if (shipx2 != 11 && shootToDo) {
					if (!FieldsIShooted[shipx2 + 1][shipy1]) {
						seekShoot(shipx2 + 1, shipy1);
						shootToDo = false;
					}
				}
				if (shipx1 != 0 && shootToDo) {
					if (!FieldsIShooted[shipx1 - 1][shipy1]) {
						seekShoot(shipx1 - 1, shipy1);
						shootToDo = false;
					}
				}
			}
		} // when ship is vertically sited
		if (shipy1 != shipy2) {
			if (shipy1 > shipy2) {
				if (shipy2 != 0 && shootToDo) {
					if (!FieldsIShooted[shipx1][shipy2 - 1]) {
						seekShoot(shipx1, shipy2 - 1);
						shootToDo = false;
					}
				}
				if (shipy1 != 0 && shootToDo) {
					if (!FieldsIShooted[shipx1][shipy1 + 1]) {
						seekShoot(shipx1, shipy1 + 1);
						shootToDo = false;
					}
				}
			} else {
				if (shipy2 != 11 && shootToDo) {
					if (!FieldsIShooted[shipx1][shipy2 + 1]) {
						seekShoot(shipx1, shipy2 + 1);
						shootToDo = false;
					}
				}
				if (shipy1 != 0 && shootToDo) {
					if (!FieldsIShooted[shipx1][shipy1 - 1]) {
						seekShoot(shipx1, shipy1 - 1);
						shootToDo = false;
					}
				}
			}
		}
		// when we know only 1 ship location
		if (shipx1 == shipx2 && shipy1 == shipy2) {
			// when ship located at the top of board
			if (shipy1 != 11 && shootToDo) {
				if (!FieldsIShooted[shipx1][shipy1 + 1]) {
					seekShoot(shipx1, shipy1 + 1);
					shootToDo = false;
				}
			}
			if (shipy1 != 0 && shootToDo) {
				if (!FieldsIShooted[shipx1][shipy1 - 1]) {
					seekShoot(shipx1, shipy1 - 1);
					shootToDo = false;
				}
			}
			if (shipx1 != 11 && shootToDo) {
				if (!FieldsIShooted[shipx1 + 1][shipy1]) {
					seekShoot(shipx1 + 1, shipy1);
					shootToDo = false;
				}
			}
			if (shipx1 != 0 && shootToDo) {
				if (!FieldsIShooted[shipx1 - 1][shipy1]) {
					seekShoot(shipx1 - 1, shipy1);
				}
			}
		}
	}

	/**
	 * Shoot at (x,y) and verify did ship is finished, or where to shoot next
	 * time
	 * 
	 * @param x
	 *            X location to shoot
	 * @param y
	 *            Y location to shoot
	 */
	private void seekShoot(int x, int y) {
		int result = ShipsController.player.IGetShooted(x, y);
		FieldsIShooted[x][y] = true;
		if (result == 2) {
			shipy2 = y;
			shipx2 = x;
		}
		if (result == 1) {
			ShipToFinish = false;
		}
	}

	/**
	 * Method called to shoot in opponent. Shoot in the area of 'unfinished'
	 * ships of opponent primarily, else shoots in counted coordinates
	 */
	@SuppressWarnings("unused")
	private void shoot() {
		int i = 0;
		boolean notDone = true;
		NumberOfTotalShots++;
		while (notDone) {
			i++;
			if (ShipToFinish) {
				notDone = false;
				SeekShip();
			} else {
				if (toShootX.size() > 0) {
					int r = rand.nextInt(toShootX.size());
					int x = toShootX.get(r);
					int y = toShootY.get(r);
					if (FieldsIShooted[x][y]) {
						toShootX.remove(r);
						toShootY.remove(r);
					} else {
						FieldsIShooted[x][y] = true;
						if (ShipsController.player.IGetShooted(x, y) == 2) {
							ShipToFinish = true;
							shipx1 = x;
							shipy1 = y;
							shipx2 = x;
							shipy2 = y;
						}
						toShootX.remove(r);
						toShootY.remove(r);
						notDone = false;
					}
				} else {
					density /= 2;
					countToShoot();
				}
			}
		}
	}

	/**
	 * set vertical size of board public void setSizeY(int SizeY) { this.SizeY =
	 * SizeY; }
	 * 
	 * /** get board of this CPU player
	 * 
	 * @return board of this CPU player
	 */
	public ShipsBoard getBoard() {
		return board;
	}

	/**
	 * get density of shoots
	 * 
	 * @return density of shoots
	 */
	public int getDensity() {
		return density;
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
	 * get random shift of shoots at X-axis.
	 * 
	 * @return random shift of shoots at X-axis.
	 */
	public int getShiftX() {
		return shiftX;
	}

	/**
	 * get random shift of shoots at Y-axis.
	 * 
	 * @return random shift of shoots at Y-axis.
	 */
	public int getShiftY() {
		return shiftY;
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
	 * get arraylist with coordinate at X-axis that computer will shoot
	 * primarily
	 * 
	 * @return arraylist with coordinate at X-axis that computer will shoot
	 *         primarily
	 */
	public ArrayList<Integer> getToShootX() {
		return toShootX;
	}

	/**
	 * get arraylist with coordinate at Y-axis that computer will shoot
	 * primarily
	 * 
	 * @return arraylist with coordinate at Y-axis that computer will shoot
	 *         primarily
	 */
	public ArrayList<Integer> getToShootY() {
		return toShootY;
	}

	/**
	 * Method called when the oponent shoot to this player
	 * 
	 * @param x
	 *            coordinate of shoot on the x-axis
	 * @param y
	 *            coordinate of shoot on the y-axis
	 * @return if ship is destroyed return 1, if there is a ship return 2, if
	 *         there are no ships return 3, if CPU player lost return 4;
	 */
	public int IGetShooted(int x, int y) {
		NumberOfShotsTaken++;
		int shot = board.Shoot(x, y);
		if (shot == 4) {
			ShipsController.makeGrade("player");
			loose = true;
		}
		if (shot == 1) {
			ShipsController.infoFrame.getMessage().setText(
					"Zniszczyłeś statek!");
			ShipsController.infoFrame.setVisible(true);
		}
		return shot;
	}

	/**
	 * get state of game
	 * 
	 * @return true if CPU player lost, else in other case
	 */
	public boolean isLoose() {
		return loose;
	}

	/**
	 * thread in this class.
	 */
	@Override
	public void run() {
		while (!loose) {
			if (NumberOfShotsTaken > NumberOfTotalShots) {
				shoot();
			}
			try {
				Computer.sleep(1000);
			} catch (InterruptedException ex) {
			}
		}
	}

	/**
	 * set board of this CPU player
	 * 
	 * @param board
	 *            board of this CPU player
	 */
	public void setBoard(ShipsBoard board) {
		this.board = board;
	}

	/**
	 * set density of shoots
	 * 
	 * @param density
	 *            density of shoots
	 */
	public void setDensity(int density) {
		this.density = density;
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
	 *            true if CPU player lost, else in other case
	 */
	public void setLoose(boolean loose) {
		this.loose = loose;
	}

	/**
	 * random shift of shoots at Y-axis.
	 * 
	 * @param shiftX
	 *            random shift of shoots at X-axis.
	 */
	public void setShiftX(int shiftX) {
		this.shiftX = shiftX;
	}

	/**
	 * set random shift of shoots at Y-axis.
	 * 
	 * @param shiftY
	 *            random shift of shoots at Y-axis.
	 */
	public void setShiftY(int shiftY) {
		this.shiftY = shiftY;
	}

	/**
	 * set horizontal size of board
	 */
	public void setSizeX(int SizeX) {
		this.SizeX = SizeX;
	}

	/**
	 * set arraylist with coordinate at X-axis that computer will shoot
	 * primarily
	 * 
	 * @param toShootX
	 *            arraylist with coordinate at X-axis that computer will shoot
	 *            primarily
	 */
	public void setToShootX(ArrayList<Integer> toShootX) {
		this.toShootX = toShootX;
	}

	/**
	 * set arraylist with coordinate at Y-axis that computer will shoot
	 * primarily
	 * 
	 * @param toShootY
	 *            arraylist with coordinate at Y-axis that computer will shoot
	 *            primarily
	 */
	public void setToShootY(ArrayList<Integer> toShootY) {
		this.toShootY = toShootY;
	}
}
