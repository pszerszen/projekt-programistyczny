/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniGames.blockd;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;

/**
 * Creates panel in minigame and sets name and color.
 * 
 * @param g
 *            GUI
 * @param fr
 *            Frame of Minigame
 * @param col
 *            Color of panel
 * @param i
 *            Name of panel
 */
@SuppressWarnings("serial")
class MyPanel extends Panel {
	public MyPanel(GUI g, Frame fr, Color col, String i) {
		super();
		setName(i);
		setBackground(col);
		addMouseListener(new MyMouseAdapter(g, fr));

	}

}
