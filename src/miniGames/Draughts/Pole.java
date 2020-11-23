/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniGames.Draughts;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author Mateusz
 */
public class Pole {

	public static class PolePawn {
		private int numberBeat;
		private Pole Pa;
		private Pole Po;

		public PolePawn() {
			Po = new Pole();
			Pa = new Pole();
			numberBeat = 0;
		}

		public void add_PolePawn(Pole P1, Pole P2) {
			Po = P1;
			Pa = P2;
		}

		public Pole get_Pawn() {
			return Pa;
		}

		public Pole get_Pole() {
			return Po;
		}

		public int getNumber() {
			return numberBeat;
		}

		public void setNumber(int i) {
			numberBeat = i;
		}
	}

	private ArrayList<PolePawn> fieldsToBeat;
	private ImageIcon image[] = new ImageIcon[6];
	private boolean isBlack;
	private boolean isPawn;
	private boolean isYellow;
	private JPanel JPole;
	private String Name;
	private Pawn pawn;

	private int[] XY;

	public Pole() {
		JPole = new JPanel();
		XY = new int[2];
		pawn = new Pawn();
		JPole.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0,
				0, 0), 1, true));
		JPole.setLayout(new java.awt.GridLayout(1, 1));
		JPole.add(pawn.getJPawn());
		fieldsToBeat = new ArrayList<PolePawn>();
		image[0] = new ImageIcon("res/WhitePole.jpg");
		image[1] = new ImageIcon("res/BlackPole.jpg");
		image[2] = new ImageIcon("res/WhitePawn.jpg");
		image[3] = new ImageIcon("res/BlackPawn.jpg");
		image[4] = new ImageIcon("res/WhiteQueen.jpg");
		image[5] = new ImageIcon("res/BlackQueen.jpg");
	}

	public void add_fieldToBeat(PolePawn P) {
		fieldsToBeat.add(P);
	}

	public void copy(Pole P) {
		// P.info();

		this.setXY(P.getXY()[0], P.getXY()[1]);
		this.setName(P.getName());

		pawn = new Pawn();
		pawn.copy(P.getPawn());
		if (P.getPawn().getKind().equals("0"))
			pawn.getJPawn().setIcon(image[0]);
		else if (P.getPawn().getKind().equals("1"))
			pawn.getJPawn().setIcon(image[1]);
		else if (P.getPawn().getKind().equals("2"))
			pawn.getJPawn().setIcon(image[2]);
		else if (P.getPawn().getKind().equals("3"))
			pawn.getJPawn().setIcon(image[3]);
		else if (P.getPawn().getKind().equals("4"))
			pawn.getJPawn().setIcon(image[4]);
		else if (P.getPawn().getKind().equals("5"))
			pawn.getJPawn().setIcon(image[5]);
		JPole.add(pawn.getJPawn());

		this.set_isBlack(P.get_isBlack());
		this.set_isPawn(P.get_isPawn());
		this.set_isYellow(P.get_isYellow());

		JPole = new JPanel();
		JPole = P.getJPole();
		JPole.setLayout(P.getJPole().getLayout());
		JPole.add(pawn.getJPawn());
	}

	public ArrayList<PolePawn> get_fieldsToBeat() {
		return fieldsToBeat;
	}

	public boolean get_isBlack() {
		return isBlack;
	}

	public boolean get_isPawn() {
		return isPawn;
	}

	public boolean get_isYellow() {
		return isYellow;
	}

	public JPanel getJPole() {
		return JPole;
	}

	public String getName() {
		return Name;
	}

	public int getNumberOfImage() {
		int i = -1;
		for (i = 0; i < 6; i++)
			if (pawn.getKind().equals(Integer.toString(i)))
				return i;
		return i;
	}

	public Pawn getPawn() {
		return pawn;
	}

	public int[] getXY() {
		return XY;
	}

	public void info() {

		System.out.println("\n***************************");
		System.out.println("image: " + pawn.getKind() + "isPawn: " + isPawn
				+ "\nisYellow: " + isYellow + "\nisBlack: " + isBlack
				+ "\nisColor: " + pawn.getColor() + "\nName: " + Name
				+ "\nXY: " + XY[0] + "," + XY[1]);

	}

	public void reverse() {
		int x = XY[0];
		XY[0] = XY[1];
		XY[1] = x;
	}

	public void set_isBlack(boolean b) {
		isBlack = b;
	}

	public void set_isPawn(boolean b) {
		isPawn = b;
	}

	public void set_isYellow(boolean b) {
		isYellow = b;
	}

	public void setImage(int i) {
		pawn.setKind(Integer.toString(i));
		pawn.getJPawn().setIcon(image[i]);
	}

	public void setName(String Name) {
		this.Name = Name;
		this.JPole.setName(Name);
	}

	public void setPawn(Pawn P) {
		pawn = P;
	}

	public void setXY(int x, int y) {
		XY[0] = x;
		XY[1] = y;
	}

	public void update() {

		if ((pawn.getKind().equals("0")) || pawn.getKind().equals("1"))
			isPawn = false;
		else
			isPawn = true;

		fieldsToBeat.clear();
		pawn.get_fieldsCanBeat().clear();
		pawn.get_fieldsToBeat().clear();
	}
}
