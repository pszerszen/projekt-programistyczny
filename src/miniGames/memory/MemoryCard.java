/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniGames.memory;

import javax.swing.ImageIcon;

/**
 * 
 * @author Marcin Szymkowiak
 */
public class MemoryCard {
	private boolean backCard; // czy to jest tył karty
	private ImageIcon image, back;
	private MemoryCard theAnswer;
	String pathToCard, pathToBackCard;

	public MemoryCard(String path, String back) {
		this.pathToCard = path;
		this.pathToBackCard = back;
		this.image = new ImageIcon(pathToCard);
		this.back = new ImageIcon(pathToBackCard);
		this.backCard = true;
	}

	public ImageIcon getBackImageIcon() {
		return back;
	}

	public ImageIcon getImageIcon() {
		return image;
	}

	public MemoryCard getTheAnswer() {
		return theAnswer;
	}

	public boolean isBackCard() {
		return backCard;
	}

	public void setBackCard(boolean backCard) {
		this.backCard = backCard;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

	public void setTheAnswer(MemoryCard theAnswer) {
		this.theAnswer = theAnswer;
	}

}
