/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniGames.memory;

import gameData.DataBase;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;

/**
 * Class implements ActionLstener. It's added to EndButton and ends game when
 * pressed. (Enabled only when game is finished)
 * 
 * @author Marcin Szymkowiak
 */
class EndMemoryActionListener implements ActionListener {

	private Memory m;

	/**
	 * EndMemoryActionListener constructor
	 * 
	 * @param m
	 *            Instance of Memory object.
	 */
	EndMemoryActionListener(Memory m) {
		this.m = m;
	}

	@Override
	/**
	 * Disposes of MemoryFrame when EndButton is pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		short tempSh = m.getMovesmade();
		if (tempSh > 100) {
			DataBase.setGrade(2.0);
		} else if (tempSh > 80) {
			DataBase.setGrade(3.5 - (tempSh - 80) / 40);
		} else if (tempSh > 70) {
			DataBase.setGrade(4.0 - (tempSh - 70) / 20);
		} else if (tempSh > 60) {
			DataBase.setGrade(4.5 - (tempSh - 60) / 20);
		} else if (tempSh > 50) {
			DataBase.setGrade(5.0 - (tempSh - 50) / 20);
		} else if (tempSh > 40) {
			DataBase.setGrade(5.5 - (tempSh - 40) / 20);
		} else {
			DataBase.setGrade(5.5);
		}

		m.getMemoryframe().dispose();
	}
}

/**
 * 
 * Class implements ActionListener. It's added to StartButton and starts game
 * when pressed.
 * 
 * @author Marcin Szymkowiak
 */
class KonamiAdapter extends KeyAdapter {
	int i = 0;
	int[] keysPressed = new int[10];

	@Override
	public void keyPressed(KeyEvent e) {

		if (i < 10) {
			keysPressed[i] = e.getKeyCode();
			i++;
		} else {
			for (int j = 0; j < 9; j++) {
				keysPressed[j] = keysPressed[j + 1];
			}
			keysPressed[9] = e.getKeyCode();
		}
		if (keysPressed[0] == 38 && keysPressed[1] == 38
				&& keysPressed[2] == 40 && keysPressed[3] == 40
				&& keysPressed[4] == 37 && keysPressed[5] == 39
				&& keysPressed[6] == 37 && keysPressed[7] == 39
				&& keysPressed[8] == 66 && keysPressed[9] == 65) {
			System.out.println("Konami");

			File contraFile = new File("res/Memory/asdfg");
			try {
				AudioInputStream contraIS = AudioSystem
						.getAudioInputStream(contraFile);
				Clip ContraClip;
				try {
					ContraClip = AudioSystem.getClip();
					ContraClip.open(contraIS);
					ContraClip.start();
				} catch (LineUnavailableException ex) {
					Logger.getLogger(KonamiAdapter.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			} catch (UnsupportedAudioFileException | IOException ex) {
				Logger.getLogger(KonamiAdapter.class.getName()).log(
						Level.SEVERE, null, ex);
			}

		}

		// up - 38
		// down - 40
		// left - 37
		// right - 39
		// A - 65
		// B - 66
	}

}

/**
 * 
 * @author Marcin Szymkowiak
 */
class MyMouseAdapter extends MouseAdapter {

	private int i;
	private Memory m;

	/**
	 * 
	 * @param i
	 *            Number of JLabel to which this MyMouseAdapter is added.
	 * @param m
	 *            Instance of Memory object.
	 */
	MyMouseAdapter(int i, Memory m) {
		this.i = i;
		this.m = m;
	}

	@Override
	/**
	 * Flips clicked cards if less than two of all have front sides shown.
	 * Increments "flipped" value in Memory.
	 */
	public void mouseClicked(java.awt.event.MouseEvent evt) {
		byte tempB = m.getFlipped();
		if (m.getDrawnCards()[i].isBackCard() && tempB < 2) {
			m.getDrawnCards()[i].setBackCard(false);
			JLabel tempL = (JLabel) m.getComponents()[i];
			tempL.setIcon(m.getDrawnCards()[i].getImageIcon());

			tempB++;
			m.setFlipped(tempB);
		}
	}
}

class StartMemoryActionListener implements ActionListener {

	private Memory m;

	/**
	 * StartMemoryActionListener constructor
	 * 
	 * @param m
	 *            Instance of Memory object.
	 */
	StartMemoryActionListener(Memory m) {
		this.m = m;
	}

	@Override
	/**
	 * Starts game
	 */
	public void actionPerformed(ActionEvent e) {
		m.startGame();
	}
}

/**
 * Memory game.
 * 
 * @author Marcin Szymkowiak
 */
public class Memory {

	/**
	 * ArrayList containing all cards available.
	 */
	private ArrayList<MemoryCard> cards = new ArrayList<>();
	/**
	 * Amount of cards left on board. 20 on start.
	 */
	private byte cardsleft = 20;
	/**
	 * Array with JLabels on which cards are displayed.
	 */
	private Component[] components;
	/**
	 * Array with 20 drawn cards.
	 */
	private MemoryCard[] drawnCards;
	/**
	 * Number of flipped (their front side is visible) cards on board.
	 */
	private byte flipped = 0;
	/**
	 * Folder in which cards images are stored.
	 */
	private String folder;
	/**
	 * MemoryFrame on which game is shown.
	 */
	private MemoryFrame memoryframe;
	/**
	 * How many moves have been already made.
	 */
	private short movesmade;
	/**
	 * Thread that flips cards on back side when 2 are shown or hides them if
	 * they match with each other.
	 */
	private Thread refresher;

	/**
	 * Constructor for Memory. Sets standard folder.
	 */
	public Memory() {
		memoryframe = new MemoryFrame();
		memoryframe.StartButton
				.addActionListener(new StartMemoryActionListener(this));
		memoryframe.EndButton.setEnabled(false);
		memoryframe.EndButton.addActionListener(new EndMemoryActionListener(
				this));
		folder = "res/Memory/" + DataBase.FieldOfStudy + "/";
		memoryframe.GamePanel.addKeyListener(new KonamiAdapter());
		memoryframe.GamePanel.setFocusable(true);
		memoryframe.setVisible(true);
	}

	/**
	 * Draws 20 cards.
	 * 
	 * @return Array with 20 memory cards.
	 */
	private MemoryCard[] drawCards() {

		MemoryCard[] mc = new MemoryCard[20];
		int i, j, k;
		int quantityOfCards = cards.size();
		Random rand = new Random();
		for (i = 0; i < 20; i += 2) {
			j = rand.nextInt(quantityOfCards - 1) + 1;
			do {
				k = rand.nextInt(20);
			} while (mc[k] != null);
			MemoryCard tempMC = cards.get(j);
			cards.remove(j);
			quantityOfCards--;
			mc[k] = tempMC;
			do {
				k = rand.nextInt(20);
			} while (mc[k] != null);
			MemoryCard tempMC2 = tempMC.getTheAnswer();
			mc[k] = tempMC2;
			int l = 0;
			while (cards.get(l) != tempMC2) {
				l++;
			}
			cards.remove(l);
			quantityOfCards--;
		}
		return mc;
	}

	/**
	 * Enbales the EndButton.
	 */
	private void endGame() {
		memoryframe.EndButton.setEnabled(true);
	}

	/**
	 * Loads cards from folder in alphabetical order. It's important to have
	 * card with it's answer right next to each other. Back side should be
	 * stored in back.jpg image.
	 */
	private void loadCards() {
		int i, limit;
		String[] paths;
		MemoryCard tempMC;
		String tempS;
		paths = new File(folder).list();
		limit = paths.length;
		for (i = 0; i < limit; i++) {
			tempS = paths[i];
			if (tempS.endsWith(".jpg")) {
				tempMC = new MemoryCard(folder + tempS, folder + "back.jpg");
				cards.add(tempMC);
				if (i % 2 == 0 && i != 0) {
					tempMC.setTheAnswer(cards.get(i - 1));
					cards.get(i - 1).setTheAnswer(tempMC);
				}
			}
		}
	}

	/**
	 * Starts game. Sets all drawnCards in JLabels, starts new Thread that hides
	 * two cards if guessed correctly or flips them back otherwise.
	 */
	protected void startGame() {

		memoryframe.StartButton.setEnabled(false);
		loadCards();
		drawnCards = drawCards();
		memoryframe.GamePanel.setVisible(false);
		components = memoryframe.GamePanel.getComponents();

		for (int i = 0; i < components.length; i++) {
			JLabel tempL = (JLabel) components[i];
			tempL.setIcon(drawnCards[i].getBackImageIcon());
			tempL.addMouseListener(new MyMouseAdapter(i, this));
		}
		memoryframe.GamePanel.setVisible(true);
		refresher = new Thread() {
			int i, j;
			JLabel tempL;
			MemoryCard tempMC, tempMC2;

			@Override
			public void run() {

				while (true) {
					try {
						if (flipped == 2) {
							movesmade += 2;
							memoryframe.movesleftLabel.setText(String
									.valueOf(movesmade));
							for (i = 0; i < 20; i++) {
								tempMC = drawnCards[i];
								if (!tempMC.isBackCard()) {
									break;
								}
							}
							for (j = i + 1; j < 20; j++) {
								tempMC2 = drawnCards[j];
								if (!tempMC2.isBackCard()) {
									break;
								}
							}
							if (tempMC.getTheAnswer().equals(tempMC2)) {
								hideCards(i, j);
								tempMC.setBackCard(true);
								tempMC2.setBackCard(true);
								flipped = 0;
							} else {
								sleep(1000);
								tempL = (JLabel) components[i];
								tempL.setIcon(tempMC.getBackImageIcon());
								tempL = (JLabel) components[j];
								tempL.setIcon(tempMC2.getBackImageIcon());
								tempMC.setBackCard(true);
								tempMC2.setBackCard(true);
								flipped = 0;
							}

						}
						sleep(100);
					} catch (InterruptedException ex) {
						Logger.getLogger(Memory.class.getName()).log(
								Level.SEVERE, null, ex);
					}
				}
			}
		};
		refresher.start();

	}

	/**
	 * Returns array with JLabels.
	 * 
	 * @return Array with components.
	 */
	public Component[] getComponents() {
		return components;
	}

	/**
	 * Returns array with drawn cards.
	 * 
	 * @return array with MemoryCard instances.
	 */
	public MemoryCard[] getDrawnCards() {
		return drawnCards;
	}

	/**
	 * 
	 * @return Byte number storing number of flipped cards.
	 */
	public byte getFlipped() {
		return flipped;
	}

	/**
	 * Returns MemoryFrame.
	 * 
	 * @return MemoryFrame instance.
	 */

	public MemoryFrame getMemoryframe() {
		return memoryframe;
	}

	/**
	 * 
	 * @return number of moves made by player.
	 */
	public short getMovesmade() {
		return movesmade;
	}

	/**
	 * Hides two cards by setting their JLabels invisible and calls endGame()
	 * method if there are none cards left on board after that.
	 * 
	 * @param i
	 *            First card to be hidden.
	 * @param j
	 *            Second card to be hidden.
	 */
	public void hideCards(int i, int j) {
		components[i].setVisible(false);
		components[j].setVisible(false);
		cardsleft -= 2;
		if (cardsleft == 0) {
			endGame();
		}
	}

	/**
	 * Sets how many cards are flipped.
	 * 
	 * @param flipped
	 *            number to be set.
	 */
	public void setFlipped(byte flipped) {
		this.flipped = flipped;
	}
}
