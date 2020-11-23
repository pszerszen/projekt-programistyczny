/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniGames.ships;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * @author Wojtek
 */
public class PlayerMouseAdapter extends MouseAdapter {

	/**
	 * horizontal coordinate
	 */
	private int ID1;
	/**
	 * vertical coordinate
	 */
	private int ID2;
	/**
	 * unable player to setting ships after start of game
	 */
	private boolean start;

	/**
	 * constructor
	 * 
	 * @param ID1
	 *            horizontal coordinate
	 * @param ID2
	 *            vertical coordinate
	 */
	PlayerMouseAdapter(int ID1, int ID2) {
		this.start = false;
		this.ID1 = ID1;
		this.ID2 = ID2;
	}

	/**
	 * action
	 * 
	 * @param evt
	 */
	@Override
	public void mouseClicked(MouseEvent evt) {
		if (!start) {
			Ship ship = ShipsController.frame.getShip(ID1, ID2);
			if (ship != null)
				if (!ShipsController.player.locateShips(ship)) {
					ShipsController.frame.shipsSetted[ship.getSize()] = false;
					ShipsController.infoFrame.getMessage().setText(
							"Błędna lokalizacja!");
					ShipsController.infoFrame.setVisible(true);
				}
		}
	}

	/**
	 * unable player to setting ships
	 * 
	 * @param start
	 */
	public void setStart(boolean start) {
		this.start = start;
	}
}
