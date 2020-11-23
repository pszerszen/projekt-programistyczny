/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniGames.blockd;

import gameData.DataBase;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

/**
 * This class receives mouse events and is most important class in this Minigame
 * 
 * @author Andrzej Tarnowski
 */
public class MyMouseAdapter extends MouseAdapter {
	private Frame fr;
	private GUI g;
	private int Neigh;
	/**
	 * private variable which contains counter of panels with other colors than
	 * white
	 */
	private byte panelcount;
	private String pomName;
	/**
	 * Array of boolean which contains information is method Disappear was
	 * already elicited for indexed panel
	 */
	boolean[] SameColor = new boolean[100];

	public MyMouseAdapter(GUI g, Frame fr) {
		this.g = g;
		this.fr = fr;
	}

	/**
	 * Method searches through panels is any panel is neighbor with panel with
	 * the same color(other than white). If such panel isn't found method counts
	 * how many panels with color other than white left and assign this to field
	 * panelcount. After that, method set a degree depending on panelcount. Than
	 * creates a Dialog with information to the player what degree player gets.
	 * 
	 * 
	 * @param i
	 *            Index of Component from Frame
	 */
	private void CheckEnd(int i) {
		while (i < 100) {
			if (fr.getComponent(i).getBackground() != Color.white
					&& i % 10 < 9
					&& i < 99
					&& fr.getComponent(i).getBackground() == fr.getComponent(
							i + 1).getBackground()) {
				break;
			}
			if (fr.getComponent(i).getBackground() != Color.white
					&& i < 90
					&& fr.getComponent(i).getBackground() == fr.getComponent(
							i + 10).getBackground()) {
				break;
			}
			i++;
		}
		if (i > 99) {
			for (int j = 0; j < 100; j++) {
				if (fr.getComponent(j).getBackground() != Color.white) {
					panelcount++;
				}
			}
			switch (panelcount) {
			case 0:
				DataBase.setGrade(5.5);
				g.setdegree(5.5);
				break;
			case 1:
				DataBase.setGrade(5.0);
				g.setdegree(5);
				break;
			case 2:
				DataBase.setGrade(4.5);
				g.setdegree(4.5);
				break;
			case 3:
				DataBase.setGrade(4.0);
				g.setdegree(4);
				break;
			case 4:
				DataBase.setGrade(3.5);
				g.setdegree(3.5);
				break;
			case 5:
				DataBase.setGrade(3.0);
				g.setdegree(3);
				break;

			default:
				DataBase.setGrade(2);
				g.setdegree(2);
			}
			JOptionPane.showMessageDialog(null,
					"Napisales na ocene: " + g.getdegree(), "Koniec testu",
					JOptionPane.PLAIN_MESSAGE);
			fr.dispose();
		}

	}

	/**
	 * Method checks is any neighbor(other than parent) of target panel has the
	 * same color as that panel. If such neighbor exists Neigh is increased and
	 * method elicit itself by recursion where current panel becomes parent
	 * panel and neighbor becomes current panel. If the only neighbor with the
	 * same color is parent or such neighbor doesn't exits than method changes
	 * current panel's color to white if Neigh is bigger than 0 and current
	 * recursion ends and method go back to earlier recursion
	 * 
	 * @param Name
	 *            Name of current panel
	 * @param parentp
	 *            Name of parent panel
	 * @param Neigh
	 *            variable which contains information is current panel has any
	 *            neighbors with the same color as itself(when method is
	 *            elicited that parameter is always 0)
	 */
	private void Disappear(String Name, String parentp, int Neigh) {
		SameColor[Integer.parseInt(Name)] = true;
		pomName = Integer.toString(Integer.parseInt(Name) + 1);
		if (Integer.parseInt(Name) % 10 != 9
				&& SameColor[Integer.parseInt(pomName)] == false
				&& g.getpanel_color().get(pomName) == g.getpanel_color().get(
						Name)) {
			Neigh++;
			Disappear(pomName, Name, 0);
		} else if (pomName.equals(parentp))
			Neigh++;
		pomName = Integer.toString(Integer.parseInt(Name) + 10);
		if (Integer.parseInt(Name) < 90
				&& SameColor[Integer.parseInt(pomName)] == false
				&& g.getpanel_color().get(pomName) == g.getpanel_color().get(
						Name)) {
			Neigh++;
			Disappear(pomName, Name, 0);

		} else if (pomName.equals(parentp))
			Neigh++;
		pomName = Integer.toString(Integer.parseInt(Name) - 1);
		if (Integer.parseInt(Name) % 10 != 0
				&& SameColor[Integer.parseInt(pomName)] == false
				&& g.getpanel_color().get(pomName) == g.getpanel_color().get(
						Name)) {
			Neigh++;
			Disappear(pomName, Name, 0);
		} else if (pomName.equals(parentp))
			Neigh++;
		pomName = Integer.toString(Integer.parseInt(Name) - 10);
		if (Integer.parseInt(Name) > 9
				&& SameColor[Integer.parseInt(pomName)] == false
				&& g.getpanel_color().get(pomName) == g.getpanel_color().get(
						Name)) {
			Neigh++;
			Disappear(pomName, Name, 0);

		} else if (pomName.equals(parentp))
			Neigh++;
		if (Neigh != 0) {
			fr.getComponent(Integer.parseInt(Name)).setBackground(Color.white);
			g.getpanel_color().put(Name, Color.white);
			if (!g.getcolumns().contains(Integer.parseInt(Name) % 10)) {
				g.getcolumns().add(Integer.parseInt(Name) % 10);
			}

		}

	}

	/**
	 * Method contains algorithm of falling panels: Searches first bottom white
	 * panel,then counts how many white panels are in row. Then sets color of
	 * bottom white panel to the color of first panel higher on last white panel
	 * in row then sets color of panel which is on earlier white panel to the
	 * color which is on the first panel higher on last white panel in row.
	 * 
	 * @param col
	 *            index of column
	 */
	private void Gravity(int col) {
		int index = 90 + col;
		int counter = 0;
		int pom;
		while (index >= 0) {
			if (fr.getComponent(index).getBackground() == Color.white) {
				pom = index;
				while (pom >= 0
						&& fr.getComponent(pom).getBackground() == Color.white) {
					counter++;
					pom = pom - 10;
				}
				if (pom < 0) {
					pom += 10;
					counter--;
				}
				fr.getComponent(index).setBackground(
						fr.getComponent(pom).getBackground());
				g.getpanel_color().put(Integer.toString(index),
						fr.getComponent(index).getBackground());
				for (int k = counter; k > 0; k--) {
					fr.getComponent(index - k * 10).setBackground(Color.white);
					g.getpanel_color().put(Integer.toString(index - k * 10),
							Color.white);
				}
			}
			counter = 0;
			index -= 10;
		}

	}

	/**
	 * This method removes column where each panel is white and set each color
	 * of panel from columns with index modulo 10 bigger or equeal than removed
	 * column to color of panel with the current index+1 and sets color of each
	 * panel from column 9 to white
	 * 
	 */
	private void RemoveEmptyColumns() {
		int j;
		int row = 0;
		for (int i = 8; i > 0; i--) {
			if (fr.getComponent(90 + i).getBackground() == Color.white) {
				j = i;
				while (j < 9) {
					while (row < 10) {
						fr.getComponent(row * 10 + j).setBackground(
								fr.getComponent(row * 10 + j + 1)
										.getBackground());
						g.getpanel_color().put(Integer.toString(row * 10 + j),
								fr.getComponent(row * 10 + j).getBackground());
						row++;
					}
					j++;
					row = 0;
				}
				for (int k = 0; k < 10; k++) {
					fr.getComponent(k * 10 + 9).setBackground(Color.white);
					g.getpanel_color().put(Integer.toString(k * 10 + j),
							Color.white);
				}
			}
		}
	}

	/**
	 * Method activates
	 * {@link #Disappear(java.lang.String, java.lang.String, int)} for clicked
	 * panel then activates {@link #Gravity(int)} for columns where panels
	 * disappeared than activate {@link #RemoveEmptyColumns()} and
	 * {@link #CheckEnd(int)}
	 * 
	 * @param e
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		for (int j = 0; j < 100; j++) {
			if (fr.getComponent(j).getBackground() != Color.white) {
				SameColor[j] = false;
			}
		}
		Neigh = 0;
		e.getComponent().getName();
		Disappear(e.getComponent().getName(), e.getComponent().getName(), Neigh);
		for (int i = 0; i < g.getcolumns().size(); i++) {
			Gravity(g.getcolumns().get(i));
		}
		RemoveEmptyColumns();
		for (int j = 0; j < 100; j++) {
			SameColor[j] = true;
		}
		g.getcolumns().clear();
		CheckEnd(0);
	}

	/**
	 * Changes panel's color to black when mouse entered to that panel
	 * 
	 * @param e
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getComponent().getBackground() != Color.white) {
			e.getComponent().setBackground(Color.black);
		}

	}

	/**
	 * Changes panel's color to color from Map panel_color with value equals to
	 * that panel name
	 * 
	 * @param e
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getComponent().getBackground() != Color.white) {
			e.getComponent().setBackground(
					g.getpanel_color().get(e.getComponent().getName()));
		}

	}

	/**
	 * A method that had to be overridden. Not used in this class.
	 * 
	 * @param e
	 */
	@Override
	public void mousePressed(MouseEvent e) {

	}

	/**
	 * A method that had to be overridden. Not used in this class.
	 * 
	 * @param e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {

	}

}