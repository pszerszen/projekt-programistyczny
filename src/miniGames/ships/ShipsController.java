package miniGames.ships;

import gameData.DataBase;

import javax.swing.JFrame;

/**
 * Class Controller contains players, let to cooperation between players and
 * panels. Contain method that count final grade
 * 
 * @author Wojtek
 */
public class ShipsController {

	/**
	 * CPU player
	 */
	public static Computer computer;
	/**
	 * Main frame
	 */
	public static MyFrame frame;
	/**
	 * Information frame
	 */
	public static MyInfoFrame infoFrame;
	/**
	 * Player
	 */
	public static ShipPlayer player;

	/**
	 * Method count grade of the exam
	 * 
	 * @param s
	 *            tells method who won the game
	 */
	public static void makeGrade(String s) {
		float Grade = (float) 2.0;
		int numberOfShips;
		if (s.equals("player")) {
			numberOfShips = player.getBoard().getDestroyedShips();
			if (numberOfShips == 0) {
				Grade = (float) 5.5;
			}
			if (numberOfShips == 1) {
				Grade = (float) 5.0;
			}
			if (numberOfShips == 2) {
				Grade = (float) 4.5;
			}
			if (numberOfShips == 3) {
				Grade = (float) 4.0;
			}
		} else {
			numberOfShips = computer.getBoard().getDestroyedShips();
			if (numberOfShips == 0) {
				Grade = (float) 2.0;
			}
			if (numberOfShips == 1) {
				Grade = (float) 3.0;
			}
			if (numberOfShips == 2) {
				Grade = (float) 3.5;
			}
			if (numberOfShips == 3) {
				Grade = (float) 4.0;
			}
		}
		infoFrame.setVisible(true);
		infoFrame.getMessage().setText("Ocena: " + Grade);
		DataBase.setGrade(Grade);
	}

	/**
	 * constructor
	 */
	public ShipsController() {
		player = new ShipPlayer();
		computer = new Computer();
		frame = new MyFrame();
		frame.setVisible(true);
		infoFrame = new MyInfoFrame();
		infoFrame.setVisible(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
