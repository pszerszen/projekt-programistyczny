package miniGames.hangman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Class responsible for graphical interpretation of the minigame
 * 
 * @author Piotr Szerszeń
 * 
 */
@SuppressWarnings("serial")
public class HangmanView extends JFrame {
	/**
	 * a graphical array of all the letters in alphabet
	 */
	private JLabel[] aletters;
	/**
	 * ArrayList with an alphabet as Characters; this field hastens some
	 * initiations and operations
	 */
	private ArrayList<Character> alphabetCh;
	/**
	 * A JPanel where all alphabet letters are displayed
	 */
	private JPanel alphabetPanel;
	/**
	 * shows a proper image icon depending on how many mistakes has been made
	 */
	private JLabel icon;
	/**
	 * a graphical array of the guessed word
	 */
	private JLabel[] lword;
	/**
	 * the button that starts the game
	 */
	private JButton Starter;
	/**
	 * the guessed word as String
	 */
	private String theWord;
	/**
	 * shows how long player is guessing the word
	 */
	private JTextArea time;

	/**
	 * A constructor that initializes fields in dependence of word given as
	 * parameter
	 * 
	 * @param word
	 *            The word witch is to be guessed by player
	 */
	public HangmanView(String word) {
		super("Hangman");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.theWord = word;
		setFont(new Font("Calibri", Font.PLAIN, 14));
		setSize(new Dimension(1000, 600));
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblTime.setBounds(886, 15, 28, 14);
		getContentPane().add(lblTime);

		time = new JTextArea();
		time.setFocusable(false);
		time.setEditable(false);
		time.setFont(new Font("Monospaced", Font.PLAIN, 12));
		time.setText("00:00:00");
		time.setBounds(924, 11, 60, 21);
		getContentPane().add(time);

		JPanel TheWordPanel = new JPanel();
		TheWordPanel.setBounds(10, 334, 974, 102);
		getContentPane().add(TheWordPanel);
		TheWordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

		alphabetPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) alphabetPanel.getLayout();
		flowLayout.setHgap(25);
		alphabetPanel.setBounds(10, 447, 974, 114);
		getContentPane().add(alphabetPanel);

		icon = new JLabel();
		icon.setBounds(99, 15, 287, 287);
		icon.setIcon(new ImageIcon("res/hangman/0.jpg"));
		getContentPane().add(icon);

		Starter = new JButton("START");
		Starter.setToolTipText("Rozpocznij test!");
		Starter.setFont(new Font("Calibri", Font.BOLD, 28));
		Starter.setBounds(627, 142, 167, 64);
		getContentPane().add(Starter);

		Font font = new Font("Calibri", Font.BOLD, 60);
		lword = new JLabel[theWord.length()];
		for (int i = 0; i < lword.length; i++) {
			lword[i] = new JLabel("_");
			lword[i].setFont(font);
			TheWordPanel.add(lword[i]);
		}

		alphabetCh = new ArrayList<>();
		alphabetCh.add((char) 32);
		for (int i = 65; i < 91; i++) {
			alphabetCh.add((char) i);
		}
		alphabetCh.add(2, (char) 260);
		alphabetCh.add(5, (char) 262);
		alphabetCh.add(8, (char) 280);
		alphabetCh.add(16, (char) 321);
		alphabetCh.add(19, (char) 323);
		alphabetCh.add(21, (char) 211);
		alphabetCh.add(26, (char) 346);
		alphabetCh.add(34, (char) 377);
		alphabetCh.add(35, (char) 379);

		Font afont = new Font("Calibri", Font.PLAIN, 40);
		aletters = new JLabel[36];
		for (int i = 0; i < aletters.length; i++) {
			aletters[i] = new JLabel(Character.toString(alphabetCh.get(i)));
			aletters[i].setFont(afont);
			alphabetPanel.add(aletters[i]);
		}
		setVisible(true);
	}

	/**
	 * gives an access to JLabel showing the letter given as a parameter
	 * 
	 * @param c
	 *            a letter that graphical analog is to be given
	 * @return graphical analog of c as JLabel
	 */
	private JLabel getAlphabetLetter(char c) {
		int i = alphabetCh.indexOf(c);
		return aletters[i];
	}

	/**
	 * converts given char to upper case version of a letter if necessary
	 * 
	 * @param c
	 *            a char to convert
	 * @return upper case version of the c letter
	 */
	private char toUpperCase(char c) {
		return Character.toString(c).toUpperCase().toCharArray()[0];
	}

	/**
	 * adds Listeners that are to operate an action of the game
	 * 
	 * @param a
	 *            an ActionListener added to JButton
	 * @param k
	 *            a KeyListener that will operate actions of typed keys
	 */
	public void addAction(ActionListener a, KeyListener k) {
		Starter.addActionListener(a);
		Starter.addKeyListener(k);
		getContentPane().addKeyListener(k);
	}

	/**
	 * gives an access to JTextArea showing gametime
	 * 
	 * @return JTextArea field showing time
	 */
	public JTextArea getTime() {
		return time;
	}

	/**
	 * Sets image to icon showing "hangman"; the image is as complete as many
	 * mistakes player has made
	 * 
	 * @param mistake
	 *            number of mistakes made
	 */
	public void setIconsIcon(int mistake) {
		String filename = "res/hangman/" + mistake + ".jpg";
		icon.setIcon(new ImageIcon(filename));
	}

	/**
	 * shows the definition of guessed word in the panel where alphabet was
	 * displayed
	 * 
	 * @param def
	 *            the definition to be shown
	 */
	public void showDefinition(String def) {
		JTextArea definition = new JTextArea(def);
		JScrollPane span = new JScrollPane(definition);
		definition.setFont(new Font("Calibri", Font.ITALIC, 24));
		definition.setLineWrap(true);
		definition.setWrapStyleWord(true);
		alphabetPanel.removeAll();
		alphabetPanel.setLayout(null);
		span.setBounds(1, 1, 974, 114);
		alphabetPanel.add(span);
		alphabetPanel.repaint();
	}

	/**
	 * changes color of the chosen letter
	 * 
	 * @param c
	 *            the chosen letter
	 */
	public void signLetter(char c) {
		getAlphabetLetter(toUpperCase(c)).setForeground(Color.RED);
	}

	/**
	 * shows letter in guessed word if player guessed well
	 * 
	 * @param c
	 *            letter chosen by player
	 */
	public void unleashLetter(char c) {
		c = toUpperCase(c);
		for (int i = 0; i < theWord.length(); i++) {
			if (theWord.charAt(i) == c) {
				lword[i].setText(Character.toString(c));
			}
		}
	}

	/**
	 * shows all the guessed password
	 */
	public void unleashWord() {
		for (int i = 0; i < lword.length; i++) {
			lword[i].setText(Character.toString(theWord.charAt(i)));
		}
	}
}
