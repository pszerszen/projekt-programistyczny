package miniGames.ships;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * class ShipsField represents a field, stores the data of field and provides
 * methods to operate on state of field
 * 
 * @see ShipsBoard
 * @see Ship
 * @author Wociech Stokłosa
 */
public class ShipsField {

	/**
	 * field type. "Water" == clean field, "Ship" == field with ship,
	 * "ShooTedWater" == clean field that was shooted, "DestroyedPartOfShip" ==
	 * field with part of ship that was hitted.
	 */
	private String FieldType;
	/**
	 * label of field in oponent's view
	 */
	private JLabel OponentViewField;
	/**
	 * icon of vertical located ship after shoot
	 */
	private final ImageIcon OshootedShipBody = new ImageIcon(
			"res/ships/Shwoda.jpg");
	/**
	 * label of field in player's view
	 */
	private JLabel PlayerViewField;
	/**
	 * Ship that is at this field
	 */
	private Ship ship;
	/**
	 * icon of horizontal located ship
	 */
	@SuppressWarnings("unused")
	private final ImageIcon shipBody = new ImageIcon("res/ships/shipBody.jpg");
	/**
	 * icon of vertical located ship
	 */
	@SuppressWarnings("unused")
	private final ImageIcon shipbodyP = new ImageIcon("res/ships/shipBodyP.jpg");
	/**
	 * state of field. true if field has a ship;
	 */
	private boolean shiphere;
	/**
	 * icon of horizontal located ship after shoot
	 */
	private final ImageIcon shootedShipBody = new ImageIcon(
			"res/ships/shootedShipBody.jpg");
	/**
	 * icon of vertical located ship after shoot
	 */
	private final ImageIcon shootedShipBodyP = new ImageIcon(
			"res/ships/shootedShipBodyP.jpg");
	/**
	 * icon of water after shoot
	 */
	private final ImageIcon shootedWater = new ImageIcon(
			"res/ships/Shwoda2.jpg");
	/**
	 * icon of water
	 */
	private final ImageIcon water = new ImageIcon("res/ships/woda.jpg");
	/**
	 * coordinate of field on x-axis
	 */
	private int x;
	/**
	 * coordinate of field on y-axis
	 */
	private int y;

	/**
	 * constructor of field
	 * 
	 * @param x
	 *            coordinate of field on x-axis
	 * @param y
	 *            coordinate of field on y-axis
	 */
	public ShipsField(int x, int y) {
		this.x = x;
		this.y = y;
		FieldType = "Water";
		shiphere = false;
		PlayerViewField = new JLabel();
		PlayerViewField.setIcon(water);
		PlayerViewField.setBackground(Color.BLUE);
		PlayerViewField.setPreferredSize(new java.awt.Dimension(10, 10));
		OponentViewField = new JLabel();
		OponentViewField.setIcon(water);
		OponentViewField.setBackground(Color.BLUE);
		OponentViewField.setPreferredSize(new java.awt.Dimension(10, 10));
	}

	/**
	 * get field type. "Water" == clean field, "Ship" == field with ship,
	 * "ShooTedWater" == clean field that was shooted, "DestroyedPartOfShip" ==
	 * field with part of ship that was hitted.
	 * 
	 * @return "Water" == clean field, "Ship" == field with ship, "ShooTedWater"
	 *         == clean field that was shooted, "DestroyedPartOfShip" == field
	 *         with part of ship that was hitted.
	 */
	public String getFieldType() {
		return FieldType;
	}

	/**
	 * get this field label in oponents view
	 * 
	 * @return field label
	 */
	public JLabel getOponentViewField() {
		return OponentViewField;
	}

	/**
	 * get this field label in player's view
	 * 
	 * @return field label
	 */
	public JLabel getPlayerViewField() {
		return PlayerViewField;
	}

	/**
	 * get ship that iis on this field
	 * 
	 * @return ship that is on this field
	 */
	public Ship getShip() {
		return ship;
	}

	/**
	 * get coordinate of field on x-axis
	 * 
	 * @return coordinate of field on x-axis
	 */
	public int getX() {
		return x;
	}

	/**
	 * get coordinate of field on y-axis
	 * 
	 * @return coordinate of field on y-axis
	 */
	public int getY() {
		return y;
	}

	/**
	 * get state of field
	 * 
	 * @return true if this field contains a ship, false in other case
	 */
	public boolean isShiphere() {
		return shiphere;
	}

	/**
	 * set field type. "Water" == clean field, "Ship" == field with ship,
	 * "ShooTedWater" == clean field that was shooted, "DestroyedPartOfShip" ==
	 * field with part of ship that was hitted.
	 * 
	 * @param FieldType
	 *            "Water" == clean field, "Ship" == field with ship,
	 *            "ShooTedWater" == clean field that was shooted,
	 *            "DestroyedPartOfShip" == field with part of ship that was
	 *            hitted.
	 */
	public void setFieldType(String FieldType) {
		this.FieldType = FieldType;
	}

	/**
	 * set field label in oponents view
	 * 
	 * @param Field
	 *            field label
	 */
	public void setOponentViewField(JLabel Field) {
		this.OponentViewField = Field;
	}

	/**
	 * set field label in player's view
	 * 
	 * @param Field
	 *            field label
	 */
	public void setPlayerViewField(JLabel Field) {
		this.PlayerViewField = Field;
	}

	/**
	 * set ship on this field
	 * 
	 * @param ship
	 *            shit to set at this field
	 */
	public void setShip(Ship ship) {
		this.ship = ship;
		shiphere = true;
	}

	/**
	 * set state of field
	 * 
	 * @param shiphere
	 *            true if this field contains a ship, false in other case
	 */
	public void setShiphere(boolean shiphere) {
		this.shiphere = shiphere;
		PlayerViewField.setIcon(shootedWater);
		PlayerViewField.setBackground(Color.black);
	}

	/**
	 * set coordinate of field on x-axis
	 * 
	 * @param x
	 *            coordinate of field on x-axis
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * set coordinate of field on x-axis
	 * 
	 * @param y
	 *            coordinate of field on x-axis
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * method called after shoot
	 * 
	 * @return if ship is destroyed return 1, if there is a ship return 2, else
	 *         return 3;
	 */
	public int ShootHere() {
		if (shiphere) {
			boolean result = ship.Destroy(x, y);
			PlayerViewField.setBackground(Color.RED);
			if (FieldType.equals("Ship")) {
				PlayerViewField.setIcon(shootedShipBody);
				OponentViewField.setIcon(OshootedShipBody);
			} else {
				PlayerViewField.setIcon(shootedShipBodyP);
				OponentViewField.setIcon(OshootedShipBody);
			}
			if (result) {
				return 1;
			} else {
				return 2;
			}
		} else {
			PlayerViewField.setBackground(Color.DARK_GRAY);
			FieldType = "ShootedWater";
			PlayerViewField.setIcon(shootedWater);
			OponentViewField.setIcon(shootedWater);

		}
		return 3;
	}
}
