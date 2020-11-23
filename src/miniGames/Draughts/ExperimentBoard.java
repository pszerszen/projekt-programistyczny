/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniGames.Draughts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 
 * @author Mateusz
 */
public class ExperimentBoard {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Board.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Board.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Board.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Board.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ExperimentBoard();
			}
		});
	}

	private String[] ABC = { "A", "B", "C", "D", "E", "F", "G", "H" };
	@SuppressWarnings("rawtypes")
	private HashSet blackBeat;
	private Computer comp;
	private boolean isWhite = true;
	private boolean isWinner = false;
	private boolean playerRound = true;
	private Pole[][] Poles;
	@SuppressWarnings("rawtypes")
	private HashSet whiteBeat;

	private boolean Winner = false;

	@SuppressWarnings("rawtypes")
	public ExperimentBoard() {
		whiteBeat = new HashSet();
		blackBeat = new HashSet();

		Poles = new Pole[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				Poles[i][j] = new Pole();
				Poles[i][j].setName(ABC[j] + Integer.toString(8 - i));
				Poles[i][j].setXY(j, i);

				if (i % 2 == 0) {
					if (j % 2 == 0) {
						Poles[i][j].setImage(0);
						Poles[i][j].set_isBlack(false);
					} else {
						Poles[i][j].setImage(1);
						Poles[i][j].set_isBlack(true);
					}
				} else {
					if (j % 2 == 0) {
						Poles[i][j].setImage(1);
						Poles[i][j].set_isBlack(true);
					} else {
						Poles[i][j].setImage(0);
						Poles[i][j].set_isBlack(false);
					}
				}
			}
		}
	}

	int whatPawn(int i, int j) {
		if (isWhite) {
			if (i < 3)
				return 3;
			if (i > 4)
				return 2;
		} else {
			if (i < 3)
				return 2;
			if (i > 4)
				return 3;
		}
		return 6;
	}

	@SuppressWarnings("unchecked")
	public void add_blackBeat(Pole P) {
		blackBeat.add(P);
	}

	@SuppressWarnings("unchecked")
	public void add_whiteBeat(Pole P) {
		whiteBeat.add(P);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void copy(Board B) {
		Winner = B.get_Winner();
		isWinner = B.get_isWinner();
		comp = B.get_computer();

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				Poles[i][j].copy(B.getPoles()[i][j]);
			}

		whiteBeat.clear();
		Iterator it = whiteBeat.iterator();
		while (it.hasNext())
			whiteBeat.add(it.next());

		blackBeat.clear();
		it = blackBeat.iterator();
		while (it.hasNext())
			blackBeat.add(it.next());

		isWhite = B.get_isWhite();
		playerRound = B.get_playerRound();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void copy(ExperimentBoard B) {
		Winner = B.get_Winner();
		isWinner = B.get_isWinner();
		// comp = B.get_computer();

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				Poles[i][j].copy(B.getPoles()[i][j]);
			}

		whiteBeat.clear();
		Iterator it = whiteBeat.iterator();
		while (it.hasNext())
			whiteBeat.add(it.next());

		blackBeat.clear();
		it = blackBeat.iterator();
		while (it.hasNext())
			blackBeat.add(it.next());

		isWhite = B.get_isWhite();
		playerRound = B.get_playerRound();
	}

	public boolean directPawnMove(int a, int b, Pole P2) {

		int x2 = P2.getXY()[0];
		int y2 = P2.getXY()[1];

		x2 = x2 + a;
		y2 = y2 + b;

		if (x2 <= 7 && x2 >= 0 && y2 <= 7 && y2 >= 0) {
			if (Poles[x2][y2].get_isPawn()) {
				if (Poles[x2][y2].getPawn().getColor() != P2.getPawn()
						.getColor()) {
					if ((x2 + a) <= 7 && (x2 + a) >= 0 && (y2 + b) <= 7
							&& (y2 + b) >= 0) {
						if (!Poles[x2 + a][y2 + b].get_isPawn()) {
							return true;
						} else
							return false;
					} else
						return false;
				} else
					return false;
			} else
				return false;
		} else
			return false;
	}

	public boolean directQueenMove(int a, int b, Pole P2) {
		int x2 = P2.getXY()[0];
		int y2 = P2.getXY()[1];
		while (true) {
			x2 = x2 + a;
			y2 = y2 + b;

			if (x2 <= 7 && x2 >= 0 && y2 <= 7 && y2 >= 0) {
				if (Poles[x2][y2].get_isPawn()) {
					if (Poles[x2][y2].getPawn().getColor() != P2.getPawn()
							.getColor()) {
						if ((x2 + a) <= 7 && (x2 + a) >= 0 && (y2 + b) <= 7
								&& (y2 + b) >= 0) {
							if (!Poles[x2 + a][y2 + b].get_isPawn()) {
								return true;
							} else
								return false;
						} else
							return false;
					} else
						return false;
				}
			} else
				return false;
		}
	}

	public boolean drawn() {
		boolean wh = false;
		boolean bl = false;
		boolean beat = false;
		for (int a = 0; a < 8; a++)
			for (int b = 0; b < 8; b++) {
				if (Poles[a][b].getPawn().get_fieldsToBeat().size() > 0)
					beat = true;
			}
		if (!beat)
			if (numberOf("White") < 4 && numberOf("Black") < 4) {
				for (int a = 0; a < 8; a++)
					for (int b = 0; b < 8; b++) {
						if (Poles[a][b].get_isPawn()
								&& Poles[a][b].getPawn().get_isQueen()
								&& Poles[a][b].getPawn().getColor())
							bl = true;
						if (Poles[a][b].get_isPawn()
								&& Poles[a][b].getPawn().get_isQueen()
								&& !Poles[a][b].getPawn().getColor())
							wh = true;
					}
				if (bl && wh) {
					return true;
				}
			}
		return false;
	}

	public boolean exist(String S) {
		updateBoard();
		for (int a = 0; a < 8; a++)
			for (int b = 0; b < 8; b++)
				if (Poles[a][b].get_isPawn()
						&& ((Poles[a][b].getPawn().getColor() && S
								.equals("Black")) || (!Poles[a][b].getPawn()
								.getColor() && S.equals("White")))) {
					if (Poles[a][b].getPawn().get_canMove().size() > 0)
						return true;
				}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public HashSet get_blackBeat() {
		return blackBeat;
	}

	public Computer get_computer() {
		return comp;
	}

	public boolean get_isWhite() {
		return isWhite;
	}

	public boolean get_isWinner() {
		return isWinner;
	}

	public boolean get_playerRound() {
		return playerRound;
	}

	@SuppressWarnings("rawtypes")
	public HashSet get_whiteBeat() {
		return whiteBeat;
	}

	public boolean get_Winner() {
		return Winner;
	}

	public Pole[][] getPoles() {
		return Poles;
	}

	public void info() {
		String position = new String();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (Poles[i][j].get_isPawn()) {
					if (!Poles[i][j].getPawn().get_isQueen()) {
						if (Poles[i][j].getPawn().getColor())
							position = position + 1;
						else
							position = position + -1;
					} else {
						if (Poles[i][j].getPawn().getColor()) {
							position = position + 2;
						} else
							position = position + -2;
					}
				} else
					position = position + 0;
			}
			System.out.println(position);
			position = new String();
		}

	}

	public void makeCanMoves(Pole P) {

		P.getPawn().get_canMove().clear();
		P.get_fieldsToBeat().clear();
		Pole P2 = new Pole();
		P2.copy(P);
		int x2 = P2.getXY()[1];
		int y2 = P2.getXY()[0];

		if (!P.getPawn().get_isQueen()) {

			boolean b = false;
			P2.setXY(x2, y2);
			if (directPawnMove(1, 1, P2)) {
				b = true;
				P2.copy(P);
				makePawnMove(1, 1, P, P2);
			}
			P2.setXY(x2, y2);
			if (directPawnMove(-1, 1, P2)) {
				b = true;
				P2.copy(P);
				makePawnMove(-1, 1, P, P2);
			}
			P2.setXY(x2, y2);
			if (directPawnMove(1, -1, P2)) {
				b = true;
				P2.copy(P);
				makePawnMove(1, -1, P, P2);
			}
			P2.setXY(x2, y2);
			if (directPawnMove(-1, -1, P2)) {
				b = true;
				P2.copy(P);
				makePawnMove(-1, -1, P, P2);
			}
			if (!b) {
				P2.copy(P);
				makePawnMove(1, 1, P, P2);
				P2.copy(P);
				makePawnMove(-1, 1, P, P2);
				P2.copy(P);
				makePawnMove(1, -1, P, P2);
				P2.copy(P);
				makePawnMove(-1, -1, P, P2);
			}

		} else if (P.getPawn().get_isQueen()) {
			boolean b = false;
			P2.setXY(x2, y2);
			if (directQueenMove(1, 1, P2)) {
				b = true;
				P2.copy(P);
				makeQueenMove(1, 1, P, P2);
			}
			P2.setXY(x2, y2);
			if (directQueenMove(-1, 1, P2)) {
				b = true;
				P2.copy(P);
				makeQueenMove(-1, 1, P, P2);
			}
			P2.setXY(x2, y2);
			if (directQueenMove(1, -1, P2)) {
				b = true;
				P2.copy(P);
				makeQueenMove(1, -1, P, P2);
			}
			P2.setXY(x2, y2);
			if (directQueenMove(-1, -1, P2)) {
				b = true;
				P2.copy(P);
				makeQueenMove(-1, -1, P, P2);
			}
			if (!b) {
				P2.copy(P);
				makeQueenMove(1, 1, P, P2);
				P2.copy(P);
				makeQueenMove(-1, 1, P, P2);
				P2.copy(P);
				makeQueenMove(1, -1, P, P2);
				P2.copy(P);
				makeQueenMove(-1, -1, P, P2);
			}

		}
	}

	public void makeMove(int i, int j) {

		for (int k = 0; k < 8; k++)
			for (int l = 0; l < 8; l++)
				if (Poles[k][l].get_isPawn() && Poles[k][l].get_isYellow()) {
					int n = Poles[k][l].getNumberOfImage();
					boolean q = Poles[k][l].getPawn().get_isQueen();

					Poles[k][l].setImage(1);
					Poles[k][l].set_isPawn(false);

					Poles[i][j].set_isPawn(true);

					if (Poles[k][l].getPawn().getColor()) {
						if ((i != 7 && !playerRound) || (i != 0 && playerRound)) {
							Poles[i][j].setImage(n);
							Poles[i][j].getPawn().setColor(true);
							Poles[i][j].getPawn().set_isQueen(q);
						} else {
							Poles[i][j].setImage(5);
							Poles[i][j].getPawn().setColor(true);
							Poles[i][j].getPawn().set_isQueen(true);
						}
					} else {
						if ((i != 7 && !playerRound) || (i != 0 && playerRound)) {
							Poles[i][j].setImage(n);
							Poles[i][j].getPawn().setColor(false);
							Poles[i][j].getPawn().set_isQueen(q);
						} else {
							Poles[i][j].setImage(4);
							Poles[i][j].getPawn().setColor(false);
							Poles[i][j].getPawn().set_isQueen(true);
						}
					}

					for (int u = 0; u < Poles[i][j].get_fieldsToBeat().size(); u++)
						if (Poles[i][j].get_fieldsToBeat().get(u).get_Pawn() == Poles[k][l]) {
							Poles[i][j].get_fieldsToBeat().get(u).get_Pole()
									.setImage(1);
							Poles[i][j].set_isPawn(false);
						}
				}

		for (int k = 0; k < 8; k++)
			for (int l = 0; l < 8; l++)
				if (Poles[k][l].get_isBlack() && Poles[k][l].get_isYellow()) {
					Poles[k][l].set_isYellow(false);
					Poles[k][l].getJPole().setBorder(
							new javax.swing.border.LineBorder(
									new java.awt.Color(0, 0, 0), 1, true));
				}
	}

	public void makePawnMove(int a, int b, Pole P, Pole P2) {

		boolean isPlayer;
		if (isWhite == !P.getPawn().getColor())
			isPlayer = true;
		else
			isPlayer = false;

		boolean beat = false;
		int x2, y2, c, d;
		x2 = P2.getXY()[1];
		y2 = P2.getXY()[0];

		while (true) {
			c = x2;
			d = y2;
			P2.setXY(x2, y2);
			if (b > 0) {
				if (y2 < 7)
					y2++;
			} else {
				if (y2 > 0)
					y2--;
			}
			if (a > 0) {
				if (x2 < 7) {
					if (!isPlayer)
						x2++;
					else if (directPawnMove(a, b, P2) || beat)
						x2++;
				}
			} else {
				if (x2 > 0) {
					if (isPlayer)
						x2--;
					else if (directPawnMove(a, b, P2) || beat)
						x2--;
				}
			}

			if (x2 != c && y2 != d) {
				if (!Poles[x2][y2].get_isPawn()) {
					P2.setXY(x2, y2);
					if (beat) {
						for (int i = 0; i < Poles[x2 - a][y2 - b]
								.get_fieldsToBeat().size(); i++)
							if (Poles[x2 - a][y2 - b].get_fieldsToBeat().get(i)
									.get_Pawn() == P)
								Poles[x2][y2].add_fieldToBeat(Poles[x2 - a][y2
										- b].get_fieldsToBeat().get(i));

						if (directPawnMove(-a, b, P2)) {
							P2.setXY(y2, x2);
							makePawnMove(-a, b, P, P2);
						}
						P2.setXY(x2, y2);
						if (directPawnMove(a, -b, P2)) {
							P2.setXY(y2, x2);
							makePawnMove(a, -b, P, P2);
						}
						P2.setXY(x2, y2);
						if (directPawnMove(a, b, P2)) {
							P2.setXY(y2, x2);
							makePawnMove(a, b, P, P2);
						}

						P.getPawn().add_canMovePole(Poles[x2][y2]);
						return;

					} else {
						P.getPawn().add_canMovePole(Poles[x2][y2]);
						return;
					}
				} else if (Poles[x2][y2].getPawn().getColor() != P.getPawn()
						.getColor()) {

					beat = true;

					if (x2 + a <= 7 && x2 + a >= 0 && y2 + b <= 7
							&& y2 + b >= 0) {
						if (!Poles[x2 + a][y2 + b].get_isPawn()) {

							P.getPawn().add_fieldCanBeat(Poles[x2 + a][y2 + b]);
							P.getPawn().add_fieldToBeat(Poles[x2][y2]);

							for (int i = 0; i < Poles[x2 - a][y2 - b]
									.get_fieldsToBeat().size(); i++)
								if (Poles[x2 - a][y2 - b].get_fieldsToBeat()
										.get(i).get_Pawn() == P)
									Poles[x2][y2]
											.add_fieldToBeat(Poles[x2 - a][y2
													- b].get_fieldsToBeat()
													.get(i));

							Pole.PolePawn PP = new Pole.PolePawn();
							PP.add_PolePawn(Poles[x2][y2], P);
							// PP.setNumber(N);
							Poles[x2][y2].add_fieldToBeat(PP);

						} else
							return;
					} else
						return;
				} else
					return;
			} else
				return;
		}

	}

	public void makeQueenMove(int a, int b, Pole P, Pole P2) {
		boolean beat = false;
		//int N = 0;
		int x2, y2, c, d;
		x2 = P2.getXY()[1];
		y2 = P2.getXY()[0];

		while (true) {
			c = x2;
			d = y2;
			if (a > 0) {
				if (x2 < 7)
					x2++;
			} else {
				if (x2 > 0)
					x2--;
			}
			if (b > 0) {
				if (y2 < 7)
					y2++;
			} else {
				if (y2 > 0)
					y2--;
			}

			if (x2 != c && y2 != d) {
				if (!Poles[x2][y2].get_isPawn()) {
					if (beat) {
						for (int i = 0; i < Poles[x2 - a][y2 - b]
								.get_fieldsToBeat().size(); i++)
							if (Poles[x2 - a][y2 - b].get_fieldsToBeat().get(i)
									.get_Pawn() == P)
								Poles[x2][y2].add_fieldToBeat(Poles[x2 - a][y2
										- b].get_fieldsToBeat().get(i));

						boolean t = false;

						P2.setXY(x2, y2);
						if (directQueenMove(-a, b, P2)) {
							t = true;
							P2.setXY(y2, x2);
							makeQueenMove(-a, b, P, P2);
						}
						P2.setXY(x2, y2);
						if (directQueenMove(a, -b, P2)) {
							t = true;
							P2.setXY(y2, x2);
							makeQueenMove(a, -b, P, P2);
						}

						if (t) {
							P.getPawn().add_canMovePole(Poles[x2][y2]);

							Pole P3 = new Pole();
							boolean f = false;
							P3.copy(P2);

							while (true) {
								P3.setXY(P3.getXY()[0] + a, P3.getXY()[1] + b);
								if (P3.getXY()[0] <= 7 && P3.getXY()[0] >= 0
										&& P3.getXY()[1] <= 7
										&& P3.getXY()[1] >= 0) {
									if (!Poles[P3.getXY()[0]][P3.getXY()[1]]
											.get_isPawn()) {
										if (directQueenMove(-a, b, P3))
											f = true;
										if (directQueenMove(a, -b, P3))
											f = true;
									} else
										break;
								} else
									break;
							}
							if (directQueenMove(a, b, P2))
								f = true;
							if (!f)
								return;
						} else
							P.getPawn().add_canMovePole(Poles[x2][y2]);
					} else
						P.getPawn().add_canMovePole(Poles[x2][y2]);
				} else if (Poles[x2][y2].getPawn().getColor() != P.getPawn()
						.getColor()) {
					beat = true;
					if (x2 + a <= 7 && x2 + a >= 0 && y2 + b <= 7
							&& y2 + b >= 0) {
						if (!Poles[x2 + a][y2 + b].get_isPawn()) {

							P.getPawn().add_fieldCanBeat(Poles[x2 + a][y2 + b]);
							P.getPawn().add_fieldToBeat(Poles[x2][y2]);

							for (int i = 0; i < Poles[x2 - a][y2 - b]
									.get_fieldsToBeat().size(); i++)
								if (Poles[x2 - a][y2 - b].get_fieldsToBeat()
										.get(i).get_Pawn() == P)
									Poles[x2][y2]
											.add_fieldToBeat(Poles[x2 - a][y2
													- b].get_fieldsToBeat()
													.get(i));

							Pole.PolePawn PP = new Pole.PolePawn();
							PP.add_PolePawn(Poles[x2][y2], P);

							Poles[x2][y2].add_fieldToBeat(PP);
						} else
							return;
					} else
						return;
				} else
					return;
			} else
				return;
		}
	}

	public void makeYellow(int i, int j) {

		for (int k = 0; k < 8; k++)
			for (int l = 0; l < 8; l++)
				if (Poles[k][l].get_isBlack() && Poles[k][l].get_isYellow()) {
					Poles[k][l].set_isYellow(false);
					Poles[k][l].getJPole().setBorder(
							new javax.swing.border.LineBorder(
									new java.awt.Color(0, 0, 0), 1, true));
				}

		int s = 0;
		ArrayList<Pole> AL = new ArrayList<>();
		AL.clear();
		int size = Poles[i][j].getPawn().get_canMove().size();
		if (size > 0) {
			for (int k = 0; k < size; k++) {
				if (size(Poles[i][j].getPawn().get_canMove().get(k)
						.get_fieldsToBeat(), Poles[i][j]) > s) {
					AL.clear();
					s = size(Poles[i][j].getPawn().get_canMove().get(k)
							.get_fieldsToBeat(), Poles[i][j]);
					AL.add(Poles[i][j].getPawn().get_canMove().get(k));
				} else if (size(Poles[i][j].getPawn().get_canMove().get(k)
						.get_fieldsToBeat(), Poles[i][j]) == s)
					AL.add(Poles[i][j].getPawn().get_canMove().get(k));
			}
		}
		Poles[i][j].getPawn().set_canMove(AL);
		size = Poles[i][j].getPawn().get_canMove().size();
		if (size > 0) {
			Poles[i][j].getJPole().setBorder(
					new javax.swing.border.LineBorder(new java.awt.Color(233,
							255, 0), 1, true));
			Poles[i][j].set_isYellow(true);
			for (int k = 0; k < AL.size(); k++) {
				Poles[i][j]
						.getPawn()
						.get_canMove()
						.get(k)
						.getJPole()
						.setBorder(
								new javax.swing.border.LineBorder(
										new java.awt.Color(233, 255, 0), 1,
										true));
				Poles[i][j].getPawn().get_canMove().get(k).set_isYellow(true);
				// Poles[i][j].getPawn().get_canMove().get(k).info();
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void Move(int x, int y) throws IOException {
		// Poles[x][y].info();
		updateBoard();
		HashSet Beat = new HashSet();
		Pole P = new Pole();

		if (playerRound)
			if (isWhite)
				Beat = whiteBeat;
			else
				Beat = blackBeat;
		else if (isWhite)
			Beat = blackBeat;
		else
			Beat = whiteBeat;

		if (!Poles[x][y].get_isYellow() && Poles[x][y].get_isPawn()) {
			if (Beat.size() > 0) {
				boolean bool = false;
				for (int k = 0; k < 8; k++) {
					if (bool)
						break;
					for (int l = 0; l < 8; l++) {
						if (Poles[k][l].get_isYellow()) {
							bool = true;
							break;
						}
					}
				}

				if (Beat.contains(Poles[x][y])) {
					if (!bool) {
						Iterator i = Beat.iterator();
						while (i.hasNext()) {
							P = (Pole) i.next();
							Poles[P.getXY()[1]][P.getXY()[0]]
									.getJPole()
									.setBorder(
											new javax.swing.border.LineBorder(
													new java.awt.Color(0, 0, 0),
													1, true));
						}
						makeYellow(x, y);
					}
				} else {
					if (!bool) {
						Iterator i = Beat.iterator();
						while (i.hasNext()) {
							P = (Pole) i.next();
							Poles[P.getXY()[1]][P.getXY()[0]].getJPole()
									.setBorder(
											new javax.swing.border.LineBorder(
													new java.awt.Color(255, 0,
															0), 1, true));
						}
					}
				}
			} else {
				makeYellow(x, y);
			}
			comp.setExperimentBoard(this);
		} else if (Poles[x][y].get_isYellow() && !Poles[x][y].get_isPawn()) {
			makeMove(x, y);
			// Poles[x][y].info();////////////////////////////////
			// playerRound = !playerRound;
			// isWhite = !isWhite;
			// if(!playerRound)
			// comp.next();
		}
	}

	public int numberOf(String S) {
		int wh = 0;
		int bl = 0;
		for (int a = 0; a < 8; a++)
			for (int b = 0; b < 8; b++)
				if (Poles[a][b].get_isPawn())
					if (Poles[a][b].getPawn().getColor())
						bl++;
					else
						wh++;
		if (S.equals("White"))
			return wh;
		else
			return bl;
	}

	public void set_isWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

	public void set_playerRound(boolean pR) {
		playerRound = pR;
	}

	public int size(ArrayList<Pole.PolePawn> _fieldsToBeat, Pole P2) {
		int n = 0;
		for (int i = 0; i < _fieldsToBeat.size(); i++)
			if (_fieldsToBeat.get(i).get_Pawn() == P2)
				n++;
		return n;
	}

	// Variables declaration - do not modify

	@SuppressWarnings("unchecked")
	public void updateBoard() {

		for (int a = 0; a < 8; a++)
			for (int b = 0; b < 8; b++) {
				Poles[a][b].update();
			}

		for (int a = 0; a < 8; a++)
			for (int b = 0; b < 8; b++) {
				if (Poles[a][b].get_isPawn())
					makeCanMoves(Poles[a][b]);
			}
		whiteBeat.clear();
		blackBeat.clear();
		boolean bool = false;
		for (int a = 0; a < 8; a++)
			for (int b = 0; b < 8; b++) {
				if (Poles[a][b].get_fieldsToBeat().size() > 0)
					for (int k = 0; k < Poles[a][b].get_fieldsToBeat().size(); k++)
						if (Poles[a][b].get_fieldsToBeat().get(k).get_Pawn()
								.getPawn().getColor()) {
							if (Poles[a][b].get_fieldsToBeat().get(k)
									.get_Pawn().getPawn().get_isQueen()) {
								if (!bool) {
									blackBeat.clear();
									bool = true;
								}
								blackBeat.add(Poles[a][b].get_fieldsToBeat()
										.get(k).get_Pawn());
							} else if (!bool)
								blackBeat.add(Poles[a][b].get_fieldsToBeat()
										.get(k).get_Pawn());
						} else {
							if (Poles[a][b].get_fieldsToBeat().get(k)
									.get_Pawn().getPawn().get_isQueen()) {
								if (!bool) {
									whiteBeat.clear();
									bool = true;
								}
								whiteBeat.add(Poles[a][b].get_fieldsToBeat()
										.get(k).get_Pawn());
							} else if (!bool)
								whiteBeat.add(Poles[a][b].get_fieldsToBeat()
										.get(k).get_Pawn());
						}
			}
	}
}
