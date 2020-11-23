/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniGames.blockd;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.security.SecureRandom;

/**
 * Frame which contains graphics of the miinigame
 * 
 * @author Andrzej Tarnowski
 */
@SuppressWarnings("serial")
public class MyFrame extends Frame {
	/** Array with all colors that might be on panel */
	Color[] arraycol = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW };
	/** variable which contains index of arraycol */
	int indcol;
	MyPanel panel;
	SecureRandom rand = new SecureRandom();
	String str_ind;

	/**
	 * Creates frame with 100 panels with random colors selected from arraycol
	 * 
	 * @param g
	 * @param w
	 *            width of Frame
	 * @param h
	 *            height of Frame
	 */
	public MyFrame(GUI g, int w, int h) {
		super("BLOCK'D");
		setBounds(100, 100, w, h);
		setLayout(new GridLayout(10, 1));
		setBackground(SystemColor.LIGHT_GRAY);
		for (int i = 0; i < 100; i++) {
			indcol = rand.nextInt(4);
			panel = new MyPanel(g, this, arraycol[indcol], Integer.toString(i));
			g.getpanel_color().put(Integer.toString(i), arraycol[indcol]);
			add(panel);

		}

	}
}
