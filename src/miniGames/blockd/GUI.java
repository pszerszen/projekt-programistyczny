/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniGames.blockd;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class which contains fields and methods used in several classes of this
 * package
 */
class GUI {
	/**
	 * private ArrayList which contains indexes of columns in which blocks have
	 * disappeared in current move
	 */
	private ArrayList<Integer> columns = new ArrayList<Integer>();
	/**
	 * private variable which contains a degree which player's earn from Block'd
	 */

	private double degree;
	/**
	 * Private Map that maps panel's name to that panel's color
	 */
	private Map<String, Color> panel_color = new HashMap<String, Color>();

	/**
	 * Returns variable columns
	 * 
	 * @return List of indexes of column
	 */
	public ArrayList<Integer> getcolumns() {
		return columns;
	}

	/**
	 * Returns Player's degree
	 * 
	 * @return Player's degree
	 */
	public double getdegree() {
		return degree;
	}

	/**
	 * Returns variable panel_color
	 * 
	 * @return Map which maps panel's name with panel's color
	 */
	public Map<String, Color> getpanel_color() {
		return panel_color;
	}

	/**
	 * Changes value in private variable degree into value from parameter
	 * 
	 * @param degree
	 *            variable which is to be changed
	 */
	public void setdegree(double degree) {
		this.degree = degree;
	}

}
