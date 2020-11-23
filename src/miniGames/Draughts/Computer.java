/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniGames.Draughts;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 
 * @author Mateusz
 */
public class Computer {

	Board board;
	ArrayList<Pole> canM;
	ExperimentBoard experimentBoard;
	boolean goodP = false;
	Pole P3;
	String position;
	int son = 0;
	ArrayList<ArrayList<Boolean>> WinLose;

	public Computer() {
		position = new String();
		experimentBoard = new ExperimentBoard();
		P3 = new Pole();
	}

	public boolean check(Pole P) throws IOException {

		experimentBoard.updateBoard();
		experimentBoard.Move(P.getXY()[1], P.getXY()[0]);

		// if((experimentBoard.get_isWhite() &&
		// experimentBoard.get_whiteBeat().isEmpty()) ||
		// (!experimentBoard.get_isWhite() &&
		// experimentBoard.get_blackBeat().isEmpty()))
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (experimentBoard.getPoles()[i][j].get_isYellow()
						&& !experimentBoard.getPoles()[i][j].get_isPawn()) {
					experimentBoard.Move(
							experimentBoard.getPoles()[i][j].getXY()[1],
							experimentBoard.getPoles()[i][j].getXY()[0]);
					goodP = true;
					P3.copy(experimentBoard.getPoles()[experimentBoard
							.getPoles()[i][j].getXY()[1]][experimentBoard
							.getPoles()[i][j].getXY()[0]]);
					experimentBoard.updateBoard();
					if ((experimentBoard.get_isWhite() && experimentBoard
							.get_whiteBeat().isEmpty())
							|| (!experimentBoard.get_isWhite() && experimentBoard
									.get_blackBeat().isEmpty())) {
						return true;
					}
				}
		return false;
	}

	public void check2(ExperimentBoard eB2, int p) throws IOException {

		eB2.updateBoard();
		if (!eB2.exist("Black") || eB2.drawn()) {
			// System.out.println("SON " + son);
			WinLose.get(son).add(true);
		} else if (!eB2.exist("White")) {
			// System.out.println("SON " + son);
			WinLose.get(son).add(false);
		} else if (p < 4) {

			Pole P2 = new Pole();

			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++)
					if (eB2.getPoles()[i][j].get_isPawn()
							&& eB2.getPoles()[i][j].getPawn().getColor() == !eB2
									.get_playerRound()) {
						eB2.Move(eB2.getPoles()[i][j].getXY()[1],
								eB2.getPoles()[i][j].getXY()[0]);
						P2.copy(eB2.getPoles()[i][j]);
					}
			// P2.info();
			// System.out.println("size" + P2.getPawn().get_canMove().size());
			Pole P4 = new Pole();

			for (int i = 0; i < P2.getPawn().get_canMove().size(); i++) {
				eB2.makeYellow(P2.getXY()[1], P2.getXY()[0]);

				// if(((P2.getPawn().getColor() &&
				// eB2.get_blackBeat().size()==0) || (!P2.getPawn().getColor()
				// && eB2.get_whiteBeat().size()==0)) ||
				// ((P2.getPawn().getColor() && eB2.get_blackBeat().size()<=i+1)
				// || (!P2.getPawn().getColor() &&
				// eB2.get_whiteBeat().size()<=i+1)))

				if (P2.getPawn().get_canMove().get(i).get_isYellow()) {
					// if(P2.getPawn().get_fieldsToBeat().size()==0) {
					P4.copy(P2.getPawn().get_canMove().get(i));

					eB2.Move(P2.getPawn().get_canMove().get(i).getXY()[1], P2
							.getPawn().get_canMove().get(i).getXY()[0]);
					eB2.set_playerRound(!eB2.get_playerRound());
					check2(eB2, p + 1);

					eB2.getPoles()[P2.getXY()[1]][P2.getXY()[0]].copy(P2);
					eB2.getPoles()[P4.getXY()[1]][P4.getXY()[0]].copy(P4);
					eB2.updateBoard();

					if (p == 0) {
						System.out.println("ppppp " + p + "   " + i);
						canM.add(P2.getPawn().get_canMove().get(i));
						son++;
					}
					eB2.set_playerRound(!eB2.get_playerRound());
					// }
				}

			}
		}
	}

	public int[] findEmptyPole() {
		board.updateBoard();

		if (goodP) {
			goodP = false;
			return P3.getXY();
		}

		Pole P = new Pole();
		Pole P2 = new Pole();
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (board.getPoles()[i][j].get_isPawn()
						&& board.getPoles()[i][j].get_isYellow()) {
					P2 = board.getPoles()[i][j];
				}
			}

		//int[] XY = new int[2];
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (!board.getPoles()[i][j].get_isPawn()
						&& board.getPoles()[i][j].get_isYellow()) {
					if (board.size(board.getPoles()[i][j].get_fieldsToBeat(),
							P2) >= board.size(P.get_fieldsToBeat(), P2))
						P = board.getPoles()[i][j];
				}
			}
		return P.getXY();
	}

	@SuppressWarnings("rawtypes")
	public int[] findPawnPole() throws IOException {
		board.updateBoard();
		Pole P = new Pole();
		HashSet Beat = new HashSet();

		if (board.get_playerRound())
			if (board.get_isWhite())
				Beat = board.get_whiteBeat();
			else
				Beat = board.get_blackBeat();
		else if (board.get_isWhite())
			Beat = board.get_blackBeat();
		else
			Beat = board.get_whiteBeat();

		if (Beat.size() > 0) {
			Iterator i = Beat.iterator();
			while (i.hasNext()) {
				P = (Pole) i.next();
				// ktore bicie wybrac
			}
			return P.getXY();
		} else
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++) {
					if (board.getPoles()[i][j].get_isPawn()
							&& board.getPoles()[i][j].getPawn().getColor() == board
									.get_isWhite()
							&& board.getPoles()[i][j].getPawn().get_canMove()
									.size() > 0) {
						experimentBoard.copy(board);
						if (board.numberOf("White") != 1
								|| board.numberOf("Black") != 1) {
							if (check(board.getPoles()[i][j])) {

								board.updateBoard();
								experimentBoard.copy(board);
								return board.getPoles()[i][j].getXY();
							}
						}
						/*
						 * else { son = 0; ArrayList<Boolean> AL; WinLose = new
						 * ArrayList<ArrayList<Boolean>>(); canM = new
						 * ArrayList<Pole>();
						 * 
						 * board.updateBoard();
						 * 
						 * for(int a=0; a<8; a++) for(int b=0; b<8; b++) {
						 * if(board.getPoles()[i][j].get_isPawn() &&
						 * board.getPoles()[i][j].getPawn().getColor() ==
						 * !board.get_playerRound()) {
						 * board.makeYellow(board.getPoles()[i][j].getXY()[1],
						 * board.getPoles()[i][j].getXY()[0]); } }
						 * 
						 * board.updateBoard(); experimentBoard.copy(board);
						 * goodMove(); board.updateBoard();
						 * experimentBoard.copy(board); return
						 * board.getPoles()[i][j].getXY(); }
						 */
					}
				}
		board.updateBoard();
		experimentBoard.copy(board);
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (board.getPoles()[i][j].get_isPawn()
						&& board.getPoles()[i][j].getPawn().getColor() == board
								.get_isWhite()
						&& board.getPoles()[i][j].getPawn().get_canMove()
								.size() > 0) {
					// System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
					goodP = false;
					return board.getPoles()[i][j].getXY();
				}
			}
		return null;
	}

	public void goodMove() throws IOException {
		experimentBoard.copy(board);
		ArrayList<Boolean> AL;

		double prob = 1;
		Pole P2 = new Pole();

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (experimentBoard.getPoles()[i][j].get_isPawn()
						&& experimentBoard.getPoles()[i][j].getPawn()
								.getColor() == !experimentBoard
								.get_playerRound()) {
					experimentBoard.makeYellow(
							experimentBoard.getPoles()[i][j].getXY()[1],
							experimentBoard.getPoles()[i][j].getXY()[0]);
					P2.copy(experimentBoard.getPoles()[i][j]);
				}
			}
		P2.info();
		Pole P4 = new Pole();
		experimentBoard.info();
		System.out.println();
		for (int i = 0; i < P2.getPawn().get_canMove().size(); i++) {
			experimentBoard.copy(board);
			experimentBoard.updateBoard();
			if (P2.getPawn().get_canMove().get(i).get_isYellow()) {

				P4.copy(P2.getPawn().get_canMove().get(i));
				// P4.info();

				experimentBoard.Move(P4.getXY()[1], P4.getXY()[0]);
				experimentBoard.updateBoard();

				experimentBoard.set_playerRound(!experimentBoard
						.get_playerRound());
				for (int a = 0; a < 8; a++)
					for (int b = 0; b < 8; b++) {
						if (experimentBoard.getPoles()[a][b].get_isPawn()
								&& experimentBoard.getPoles()[a][b].getPawn()
										.getColor() != experimentBoard
										.get_isWhite()) {
							experimentBoard
									.makeYellow(
											experimentBoard.getPoles()[a][b]
													.getXY()[1],
											experimentBoard.getPoles()[a][b]
													.getXY()[0]);
						}
					}

				WinLose.clear();
				for (int a = 0; a < 8; a++)
					for (int b = 0; b < 8; b++)
						if (!experimentBoard.getPoles()[a][b].get_isPawn()
								&& experimentBoard.getPoles()[a][b]
										.get_isYellow()) {
							AL = new ArrayList<Boolean>();
							WinLose.add(AL);
						}

				son = 0;
				// check2(experimentBoard,0);

				// experimentBoard.info();
				// System.out.println();

				experimentBoard.set_playerRound(!experimentBoard
						.get_playerRound());
				experimentBoard.copy(board);
				experimentBoard.getPoles()[P2.getXY()[1]][P2.getXY()[0]]
						.copy(P2);
				experimentBoard.getPoles()[P4.getXY()[1]][P4.getXY()[0]]
						.copy(P4);
				experimentBoard.updateBoard();

				int n = 0;
				double m = 0;

				// for(int a=0; a<WinLose.size(); a++) {
				// for(int b=0; b<WinLose.get(a).size(); b++) {
				// System.out.print(WinLose.get(a).get(b));
				// }
				// System.out.println();
				// }

				for (int a = 0; a < WinLose.size(); a++) {
					for (int b = 0; b < WinLose.get(a).size(); b++) {
						if (WinLose.get(a).get(b) == true)
							n++;
					}
					System.out.println((double) n / WinLose.get(a).size());
					if (((double) n / WinLose.get(a).size()) > m) {
						// goodP = true;
						m = n / WinLose.get(a).size();
					}
					n = 0;
				}
				if (m < prob) {
					prob = m;
					goodP = true;
					P3.copy(P2.getPawn().get_canMove().get(i));
				}
			}
		}
	}

	public void makePosition() {
		position = new String();
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (board.getPoles()[i][j].get_isPawn()) {
					if (!board.getPoles()[i][j].getPawn().get_isQueen()) {
						if (board.getPoles()[i][j].getPawn().getColor())
							position = position + 1;
						else
							position = position + -1;
					} else {
						if (board.getPoles()[i][j].getPawn().getColor()) {
							position = position + 2;
						} else
							position = position + -2;
					}
				} else if (board.getPoles()[i][j].get_isBlack())
					position = position + 0;
			}
	}

	public void next() throws IOException {

		int[] XY = new int[2];

		if (!board.exist("Black") || board.drawn()) {
			board.Winner(false);
			write();
			return;
		}
		write();

		XY = findPawnPole();

		board.Move(XY[1], XY[0]);
		XY = findEmptyPole();

		board.Move(XY[1], XY[0]);

		if (!board.exist("White")) {
			board.Winner(true);
			write();
			return;
		}
		write();
	}

	public void setBoard(Board B) {
		board = B;
	}

	public void setExperimentBoard(ExperimentBoard B) {
		experimentBoard = B;
	}

	public void write() throws IOException {
		PrintWriter printWriter = null;
		makePosition();

		try {
			printWriter = new PrintWriter(new FileWriter("res/positions.txt",
					true));
			if (board.get_isWinner()) {
				printWriter.println(board.get_Winner());
				printWriter.println();
			} else
				printWriter.println(position);

		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}
	}
}
