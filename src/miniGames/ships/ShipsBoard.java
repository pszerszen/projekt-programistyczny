package miniGames.ships;

import javax.swing.ImageIcon;

/**
 * class ShipsBoard represents a board, stores the data of board and provides
 * methods to check state of fields after shoot
 * 
 * @see ShipsField
 * @see Ship
 * @author Wociech Stokłosa
 */
public class ShipsBoard extends Thread {

	/**
	 * icon of horizontal ship
	 */
	private final ImageIcon body = new ImageIcon("res/ships/shipBody.jpg");
	/**
	 * icon of vertical located ship
	 */
	private final ImageIcon bodyP = new ImageIcon("res/ships/shipBodyP.jpg");
	/**
	 * Number of totally destroyed ships on the board. If numberOfShips ==
	 * DestroyedShips game if over and ovner of this board loose the game.
	 */
	private int DestroyedShips;
	/**
	 * array of fields.
	 */
	private ShipsField Fields[][];
	/**
	 * Number of ships on this board;
	 */
	private int NumberOfShips;
	/**
	 * array of ships on this board.
	 */
	private Ship Ships[];
	/**
	 * array of states of fields. true = field was shooted, false in other case
	 */
	private boolean ShootedFields[][];
	/**
	 * horizontal size of board. In other words number of fields in each row of
	 * the board
	 */
	private int SizeX;
	/**
	 * vertical size of board. In other words number of fields in each column of
	 * board.
	 */
	private int SizeY;
	/**
	 * icon of water, used in thread to animate waves
	 */
	private final ImageIcon water = new ImageIcon("res/ships/woda.jpg");
	/**
	 * icon of alternative water, used in thread to animate waves
	 */
	private final ImageIcon water2 = new ImageIcon("res/ships/woda2.jpg");

	/**
	 * constructor of this board
	 * 
	 * @param SizeX
	 *            horizontal size of board.
	 * @param SizeY
	 *            vertical size of board.
	 */
	public ShipsBoard(int SizeX, int SizeY) {
		this.SizeX = SizeX;
		this.SizeY = SizeY;
		Fields = new ShipsField[SizeX][SizeY];
		ShootedFields = new boolean[SizeX][SizeY];
		int i, j;
		for (i = 0; i < SizeX; i++) {
			for (j = 0; j < SizeY; j++) {
				Fields[i][j] = new ShipsField(i, j);
				ShootedFields[i][j] = false;
			}
		}
		Ships = new Ship[5];
		DestroyedShips = 0;
		this.start();
	}

	/**
	 * get information about number of destroyed ships
	 * 
	 * @return number of destroyed ships
	 */
	public int getDestroyedShips() {
		return DestroyedShips;
	}

	/**
	 * get fields
	 * 
	 * @return array of fields on this board
	 */
	public ShipsField[][] getFields() {
		return Fields;
	}

	/**
	 * get number of ships on this field
	 * 
	 * @return number of ships on this field
	 */
	public int getNumberOfShips() {
		return NumberOfShips;
	}

	/**
	 * get ships on this field
	 * 
	 * @return array with ships that exist on this field
	 */
	public Ship[] getShips() {
		return Ships;
	}

	/**
	 * get information about fields
	 * 
	 * @return array of state of fields. true == field was shooted, else in
	 *         other case.
	 */
	public boolean[][] getShootedFields() {
		return ShootedFields;
	}

	/**
	 * get horizontal size of board.
	 * 
	 * @return horizontal size of board.
	 */
	public int getSizeX() {
		return SizeX;
	}

	/**
	 * vertical size of board.
	 * 
	 * @return vertical size of board.
	 */
	public int getSizeY() {
		return SizeY;
	}

	/**
	 * get state of each field
	 * 
	 * @param x
	 *            coordinate of field on the x-axis
	 * @param y
	 *            coordinate of field on the y-axis
	 * @return true if field was already shooted, else in other case
	 */
	public boolean isFieldShooted(int x, int y) {
		return ShootedFields[x][y];
	}

	/**
	 * UDOSKONALIĆ WĄTEK I GRAFIKE Thread that makes wave effect
	 */
	@Override
	public void run() {
		int i, j;
		boolean wave = false;
		while (true) {
			for (i = 0; i < SizeX; i++) {
				for (j = 0; j < SizeY; j++) {
					if (Fields[i][j].getFieldType().equals("Water")) {
						if (wave) {
							Fields[i][j].getPlayerViewField().setIcon(water2);
						} else {
							Fields[i][j].getPlayerViewField().setIcon(water);
						}
					}
				}
			}
			wave = !wave;
			try {
				Computer.sleep(200);
			} catch (InterruptedException ex) {
			}
		}
	}

	/**
	 * set number of destroyed ships
	 * 
	 * @param DestroyedShips
	 *            number of destroyed ships
	 */
	public void setDestroyedShips(int DestroyedShips) {
		this.DestroyedShips = DestroyedShips;
	}

	/**
	 * set fields
	 * 
	 * @param Fields
	 *            array of fields on this board
	 */
	public void setFields(ShipsField[][] Fields) {
		this.Fields = Fields;
	}

	/**
	 * set number of ships on this field
	 * 
	 * @param NumberOfShips
	 *            number of ships on this field
	 */
	public void setNumberOfShips(int NumberOfShips) {
		this.NumberOfShips = NumberOfShips;
	}

	/**
	 * method setting ship at this board
	 * 
	 * @param ship
	 *            ship to set
	 */
	public void setShip(Ship ship) {
		Ships[NumberOfShips] = ship;
		NumberOfShips++;
		if (ship.isPlane()) {
			for (int i = ship.getX1(); i <= ship.getX2(); i++) {
				Fields[i][ship.getY1()].setShip(ship);
				Fields[i][ship.getY1()].setFieldType("Ship");
				Fields[i][ship.getY1()].getPlayerViewField().setIcon(body);
			}
		} else {
			for (int i = ship.getY1(); i <= ship.getY2(); i++) {
				Fields[ship.getX1()][i].setShip(ship);
				Fields[ship.getX1()][i].setFieldType("ShipP");
				Fields[ship.getX1()][i].getPlayerViewField().setIcon(bodyP);
			}
		}
	}

	/**
	 * set ships on this field
	 * 
	 * @param Ships
	 *            array with ships
	 */
	public void setShips(Ship[] Ships) {
		this.Ships = Ships;
	}

	/**
	 * set information about fields
	 * 
	 * @param ShootedFields
	 *            array of state of fields. true == field was shooted, else in
	 *            other case.
	 */
	public void setShootedFields(boolean[][] ShootedFields) {
		this.ShootedFields = ShootedFields;
	}

	/**
	 * set horizontal size of board.
	 * 
	 * @param SizeX
	 *            horizontal size of board.
	 */
	public void setSizeX(int SizeX) {
		this.SizeX = SizeX;
	}

	/**
	 * vertical size of board.
	 * 
	 * @param SizeY
	 *            vertical size of board.
	 */
	public void setSizeY(int SizeY) {
		this.SizeY = SizeY;
	}

	/**
	 * method called when opponent shoot at this board
	 * 
	 * @param x
	 *            coordinate of shoot on the x-axis
	 * @param y
	 *            coordinate of shoot on the y-axis
	 * @return if ship is destroyed return 1, if there is a ship return 2, else
	 *         return 3, if player lost return 4;
	 */
	public int Shoot(int x, int y) {
		ShootedFields[x][y] = true;
		int shot = Fields[x][y].ShootHere();
		if (shot == 1) {
			DestroyedShips++;
		}
		if (NumberOfShips <= DestroyedShips) {
			return 4;
		} else {
			return shot;
		}
	}
}
