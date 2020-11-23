/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniGames.ships;

import java.awt.event.MouseAdapter;

/**
 * Computer panel's mouse adapter
 * 
 * @author Wojtek
 */
public class ComputerMouseAdapter extends MouseAdapter {

	/**
	 * horizontal coordinate
	 */
	private int ID1;
	/**
	 * vertical coordinate
	 */
	private int ID2;
	/**
	 * don't let to shoot before start
	 */
	private boolean start;
	/**
	 * don't let to use field more than one time
	 */
	private boolean used;

	/**
	 * constructor
	 * 
	 * @param ID1
	 *            horizontal coordinate
	 * @param ID2
	 *            vertical coordinate
	 */
	ComputerMouseAdapter(int ID1, int ID2) {
		this.ID1 = ID1;
		this.ID2 = ID2;
		this.used = false;
		this.start = false;
	}

	/**
	 * action
	 * 
	 * @param evt
	 *            mouse event
	 */
	@Override
	public void mouseClicked(java.awt.event.MouseEvent evt) {
		if (!used && start) {
			used = ShipsController.player.shoot(ID1, ID2);
		}
	}

	/**
	 * let's pleyer to shoot
	 * 
	 * @param start
	 *            did player can shoot
	 */
	public void setStart(boolean start) {
		this.start = start;
	}
}
