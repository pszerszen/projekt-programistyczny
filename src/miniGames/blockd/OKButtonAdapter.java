/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniGames.blockd;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Close game when clicked
 * 
 */
class OKButtonAdapter implements ActionListener {
	Frame mf;

	public OKButtonAdapter(Frame mf, GUI g) {
		this.mf = mf;
	}

	public void actionPerformed(ActionEvent e) {
		mf.dispose();
	}

}
