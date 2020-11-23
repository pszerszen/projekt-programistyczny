package miniGames.hangman;

/**
 * An algorithmic part of the minigame. Contains data about the guessed word and
 * time of the game
 * 
 * @author Piotr Szerszeń
 * 
 */
public class Hangman {
	/**
	 * an array of bits in length of guessed password; true - guessed char.
	 * false - not guessed char.
	 */
	private boolean[] guessedWord;
	/**
	 * number of remaining possible mistakes
	 */
	private int possibleMistakes = 12;
	/**
	 * the time stamp of the beginning of the game
	 */
	private long startTime = 0;
	/**
	 * the time stamp of the end of the game
	 */
	private long stopTime = 0;
	/**
	 * the guessed password
	 */
	private String theWord;

	/**
	 * the constructor of class, sets guessed word from parameter and fills
	 * guessedWord[] array with false value
	 * 
	 * @param theWord
	 *            the guessed password
	 */
	public Hangman(String theWord) {
		this.setTheWord(theWord);
		this.guessedWord = new boolean[theWord.length()];
		for (int i = 0; i < guessedWord.length; i++) {
			guessedWord[i] = false;
		}
	}

	/**
	 * tells how long player is guessing password
	 * 
	 * @return variance of stopTime and startTime
	 */
	public long getGameTime() {
		return stopTime - startTime;
	}

	/**
	 * gives the number of possible mistakes that player still can make
	 * 
	 * @return number of possible mistakes
	 */
	public int getPossibleMistakes() {
		return possibleMistakes;
	}

	/**
	 * gives the time stamp of the beginning of the game
	 * 
	 * @return the time stamp of the beginning of the game
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * gives the time stamp of the end of the game
	 * 
	 * @return the time stamp of the end of the game
	 */
	public long getStopTime() {
		return stopTime;
	}

	/**
	 * gives the guessed password
	 * 
	 * @return the guessed password
	 */
	public String getTheWord() {
		return theWord;
	}

	/**
	 * gives amount of time, player already has played displaying it more for
	 * human edition
	 * 
	 * @return time of played game as String
	 */
	public String getTime() {
		long h = 0, m = 0, s = 0, ss = 0;
		long ms = System.currentTimeMillis() - startTime;

		if (ms >= 100)
			ss = ms / 10;
		else
			ss = ms;

		if (ms >= 1000) {
			s = ms / 1000;
			ms -= s * 1000;
			if (ms >= 100)
				ss = ms / 10;
			else
				ss = ms;
		}
		if (s >= 60) {
			m = s / 60;
			s -= m * 60;
		}
		if (m >= 60) {
			h = m / 60;
			m -= h * 60;
		}

		String stm, sts, stss;
		if (m < 10)
			stm = "0" + m;
		else
			stm = "" + m;
		if (s < 10)
			sts = "0" + s;
		else
			sts = "" + s;
		if (ss < 10)
			stss = "0" + ss;
		else
			stss = "" + ss;
		String out;
		out = stm + ":" + sts + ":" + stss;

		return out;
	}

	/**
	 * checks if given letter is in the guessed password and if there is, proper
	 * fields in guessedWord array are marked "true", and if there is just one
	 * found letter returns true
	 * 
	 * @param c
	 *            the letter to be found in the guessed password
	 * @return true if letter from parameter is in the password and false
	 *         otherwise
	 */
	public boolean isLetterInTheWord(char c) {
		c = Character.toString(c).toUpperCase().toCharArray()[0];
		boolean out = false;
		for (int i = 0; i < guessedWord.length; i++) {
			if (theWord.charAt(i) == c) {
				guessedWord[i] = true;
				out = true;
			}
		}
		return out;
	}

	/**
	 * tells if game is lost based on possibleMistakes
	 * 
	 * @return true if game is already lost, and false otherwise
	 */
	public boolean lost() {
		return possibleMistakes == 0;
	}

	/**
	 * standard setter for possibleMistakes
	 * 
	 * @param possibleMistakes
	 *            new value for possibleMistakes
	 */
	public void setPossibleMistakes(int possibleMistakes) {
		this.possibleMistakes = possibleMistakes;
	}

	/**
	 * standard setter for theWord
	 * 
	 * @param theWord
	 *            new value of theWord
	 */
	public void setTheWord(String theWord) {
		this.theWord = theWord;
	}

	/**
	 * assigns startTime current time stamp
	 */
	public void startTime() {
		startTime = System.currentTimeMillis();
	}

	/**
	 * assigns stopTime current time stamp
	 */
	public void stopTime() {
		stopTime = System.currentTimeMillis();
	}

	/**
	 * tells if game is lost based on guessedWord array
	 * 
	 * @return true if all guessedWord array is filled with true value, false
	 *         otherwise
	 */
	public boolean won() {
		boolean wyn = true;
		for (int i = 0; i < guessedWord.length; i++) {
			if (guessedWord[i] == false) {
				wyn = false;
				break;
			}
		}
		return wyn;
	}
}
