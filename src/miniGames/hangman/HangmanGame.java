package miniGames.hangman;

import gameData.DataBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The main hangman minigame class operating algorithmic and graphical parts of
 * the minigame
 * 
 * @author Piotr Szerszeń
 * 
 */
public class HangmanGame implements ActionListener, KeyListener {
	/**
	 * definition of guessed word
	 */
	private String def;
	/**
	 * object of algorithmic part of the minigame
	 */
	private Hangman game;
	/**
	 * object of graphical part of the minigame
	 */
	private HangmanView gui;
	/**
	 * a thread that is responsible for displaying current gametime
	 */
	private Thread timeRef;

	/**
	 * A constructor that initializes fields in dependence of word given as
	 * parameter
	 * 
	 * @param word
	 *            The word witch is to be guessed by player
	 * @param def
	 *            the definition of the guessed word
	 */
	public HangmanGame(String word, String def) {
		game = new Hangman(word.toUpperCase());
		gui = new HangmanView(game.getTheWord());
		gui.addAction(this, this);
		this.def = def;
		timeRef = new Thread() {
			public void run() {
				while (true)
					gui.getTime().setText(game.getTime());
			}
		};
	}

	/**
	 * the method called after every turn i.e. after player types letter when
	 * guessing the word
	 * 
	 * @param c
	 *            the letter that is to be examined
	 */
	@SuppressWarnings("deprecation")
	private void makeRound(char c) {
		if (game.getStartTime() != 0 && game.getStopTime() == 0) {
			gui.signLetter(c);
			if (game.isLetterInTheWord(c)) {
				gui.unleashLetter(c);
			} else {
				game.setPossibleMistakes(game.getPossibleMistakes() - 1);
				gui.setIconsIcon(12 - game.getPossibleMistakes());
			}
			if (game.won() || game.lost()) {
				gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				game.stopTime();
				timeRef.stop();
				if (game.lost())
					gui.unleashWord();
				gui.showDefinition(def);
				JOptionPane.showMessageDialog(null, "Napisales na ocene"
						+ grade(), "Koniec testu", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	/**
	 * An overridden method supporting the button action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "START") {
			if (game.getStartTime() == 0) {
				game.startTime();
				timeRef.start();
			}
		}
	}

	/**
	 * method that counts the grade after player wins/fails the minigame
	 * 
	 * @return The mark as float
	 */
	public float grade() {
		if (game.getPossibleMistakes() == 0) {
			DataBase.setGrade(2.0f);
			return 2.0f;
		}
		float wyn = 0.0f;
		double a = (game.getTheWord().length() / (game.getGameTime() / 1000))
				- (12 - game.getPossibleMistakes()) + 13;
		a = a * 100 / 14;
		if (a <= 50) {
			wyn = 2.0f;
		} else if (50 < a && a <= 60) {
			wyn = 3.0f;
		} else if (60 < a && a <= 70) {
			wyn = 3.5f;
		} else if (70 < a && a <= 80) {
			wyn = 4.0f;
		} else if (80 < a && a <= 90) {
			wyn = 4.5f;
		} else if (90 < a && a < 100) {
			wyn = 5.0f;
		} else if (100 <= a) {
			wyn = 5.5f;
		}
		DataBase.setGrade(wyn);
		return wyn;
	}

	/**
	 * A method that had to be overridden; not used actually
	 */
	@Override
	public void keyPressed(KeyEvent e) {

	}

	/**
	 * A method that had to be overridden; not used actually
	 */
	@Override
	public void keyReleased(KeyEvent e) {

	}

	/**
	 * An overridden method that supports keys action, calls makeRound(char c)
	 * method with typed letter as parameter
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		makeRound(e.getKeyChar());
	}

}
