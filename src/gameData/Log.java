package gameData;

import java.util.ArrayList;

import View.ViewManager;

/**
 * This class represents the log that deposits a game's history.It also contains
 * method that operates on log
 * 
 * @author Andrzej Tarnowski
 */
public class Log {
	private ArrayList<String> log;

	/**
	 * Constructor that creates empty ArrayList
	 * 
	 */
	public Log() {
		log = new ArrayList<>();
	}

	/**
	 * Constructor that assign already existing ArrayList to log
	 * 
	 * @param lg
	 *            ArrayList
	 */
	public Log(ArrayList<String> lg) {
		log = new ArrayList<String>(lg);
	}

	/**
	 * Adds new information to the game's history and automatically appends it
	 * to GUI log
	 * 
	 * @param s
	 *            information to be added to the list
	 */
	public void AddLine(String s) {
		log.add(s);
		ViewManager.addLog(s);
	}

	/**
	 * Returns last information that was added to game's history
	 * 
	 * @return last added information to log
	 */
	public String getLastLog() {
		return log.get(log.size() - 1);
	}

	/**
	 * Return's game history as ArrayList of String
	 * 
	 * @return game's history
	 */
	public ArrayList<String> getLog() {
		return log;
	}
}
