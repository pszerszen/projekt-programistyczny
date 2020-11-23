/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniGames.Draughts;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JLabel;

public class Pawn {

	private ArrayList<Pole> canMove;
	private boolean color;
	private ArrayList<Pole> fieldsCanBeat;
	private ArrayList<Pole> fieldsToBeat;
	private boolean isQueen;
	private JLabel JPawn;
	private String Kind;

	public Pawn() {
		JPawn = new JLabel();
		canMove = new ArrayList<Pole>();
		fieldsToBeat = new ArrayList<Pole>();
		fieldsCanBeat = new ArrayList<Pole>();
		isQueen = false;
		Kind = new String();
	}

	public void add_canMovePole(Pole P) {
		canMove.add(P);
	}

	public void add_fieldCanBeat(Pole P) {
		fieldsCanBeat.add(P);
	}

	public void add_fieldToBeat(Pole P) {
		fieldsToBeat.add(P);
	}

	public void copy(Pawn P) {
		canMove = P.canMove;
		fieldsToBeat = P.fieldsToBeat;
		fieldsCanBeat = P.fieldsCanBeat;
		JPawn = P.JPawn;
		color = P.color;
		isQueen = P.isQueen;
		Kind = P.getKind();
	}

	public ArrayList<Pole> get_canMove() {
		return canMove;
	}

	public ArrayList<Pole> get_fieldsCanBeat() {
		return fieldsCanBeat;
	}

	public ArrayList<Pole> get_fieldsToBeat() {
		return fieldsToBeat;
	}

	public boolean get_isQueen() {
		return isQueen;
	}

	public boolean getColor() {
		return color;
	}

	public Icon getIcon() {
		return JPawn.getIcon();
	}

	public JLabel getJPawn() {
		return JPawn;
	}

	public String getKind() {
		return Kind;
	}

	public void set_canMove(ArrayList<Pole> AL) {
		canMove.clear();
		for (int i = 0; i < AL.size(); i++)
			canMove.add(AL.get(i));
	}

	public void set_isQueen(boolean c) {
		isQueen = c;
	}

	public void setColor(boolean c) {
		color = c;
	}

	public void setIcon(Image I) {
		JPawn.setIcon(null);
	}

	public void setKind(String kind) {
		Kind = kind;
	}
}
