/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniGames.blockd;

/**
 * Classes which handle minigame Block'd
 * 
 * @author Andrzej Tarnowski
 */

public class Blockd {

	/**
	 * Creates Game
	 * 
	 * 
	 */
	public void create() {
		int w = 640, h = 640;
		GUI g = new GUI();
		MyFrame mf = new MyFrame(g, w, h);
		mf.setVisible(true);
	}
}