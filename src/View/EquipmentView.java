package View;

import gameData.DataBase;
import gameData.Item;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * A class responsible for showing actual Player's items
 * 
 * @author Piotr Szerszeń
 * 
 * @see View.ItemView
 * @see gameData.DataBase
 * @see gameData.Item
 * 
 */
@SuppressWarnings("serial")
public class EquipmentView extends ItemView {
	/**
	 * A button causing usage of a player's item
	 */
	private JButton Use;

	/**
	 * Initializes newly created panel of Player's Bag
	 */
	public EquipmentView() {
		super("Plecak");
		Use = new JButton("U\u017Cyj");
		Use.setToolTipText("Wykorzystanie zaznaczonego przedmiotu");
		Use.setActionCommand("uzyj");
		Use.setFont(new Font("Calibri", Font.PLAIN, 40));
		Use.setBounds(822, 336, 109, 109);
		add(Use);

		statsDisplayer = new Thread() {
			public void run() {
				Item item = new Item();
				while (true) {
					try {
						if (itemlist.getSelectedValue() != null) {
							item = DataBase
									.getItem(itemlist.getSelectedValue());
							setErg("" + item.getItemEnergy());
							setHappy("" + item.getItemHappiness());
							setTime("" + item.getDuration());
							setKnow("" + item.getItemKnowledge());
							setInteligence("" + item.getItemIntelligence());
							setCharm("" + item.getItemCharisma());
							setStamina("" + item.getItemEndurance());
							setStressResis("" + item.getItemStressResistance());
							setScroupulatio("" + item.getItemScrupulousness());
						}
						sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

	}

	/**
	 * Adds specified ActionListener to JButtons on this panel
	 * 
	 * @param l
	 *            ActionListener object to add
	 */
	public void addListeners(ActionListener l) {
		this.back.addActionListener(l);
		this.Use.addActionListener(l);
	}
}
