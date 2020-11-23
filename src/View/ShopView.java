package View;

import gameData.DataBase;
import gameData.Item;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * A class responsible for showing items from The Shop
 * 
 * @author Piotr Szerszeń
 * 
 * @see View.ItemView
 * @see gameData.DataBase
 * @see gameData.Item
 * 
 */
@SuppressWarnings("serial")
public class ShopView extends ItemView {
	/**
	 * A button causing a purchase of an item
	 */
	private JButton buy;
	/**
	 * Shows player's remaining money
	 */
	private JTextArea cash;
	/**
	 * Shows price of an item
	 */
	private JTextArea cost;

	/**
	 * Initializes newly created panel of The Shop
	 */
	public ShopView() {
		super("Sklep");

		buy = new JButton("Kup");
		buy.setToolTipText("Zakupienie zaznaczonego przedmiotu");
		buy.setFont(new Font("Calibri", Font.PLAIN, 40));
		buy.setBounds(822, 321, 109, 92);
		add(buy);

		JLabel lblPozostaaCena = new JLabel("Pozosta\u0142a kasa");
		lblPozostaaCena.setBounds(433, 513, 135, 14);
		lblPozostaaCena.setFont(ViewManager.f);
		add(lblPozostaaCena);

		JLabel lblCena = new JLabel("Cena");
		lblCena.setBounds(433, 474, 135, 14);
		lblCena.setFont(ViewManager.f);
		add(lblCena);

		cost = new JTextArea();
		cost.setText("0");
		cost.setBounds(564, 474, 50, 16);
		cost.setFont(ViewManager.fs);
		add(cost);

		cash = new JTextArea();
		cash.setText("0");
		cash.setBounds(564, 513, 50, 16);
		cash.setFont(ViewManager.fs);
		add(cash);

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
							cost.setText("" + item.getPrice());
							cash.setText("" + DataBase.player.getMoney());
						}
						sleep(200);

					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (IndexOutOfBoundsException outta) {
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
		this.buy.addActionListener(l);
	}

	/**
	 * Returns Component showing Player's remaining money
	 * 
	 * @return JTextArea component showing remaining money
	 */
	public JTextArea getCash() {
		return cash;
	}

	/**
	 * Enables/Disables JButton responsible for buying items
	 * 
	 * @param c
	 *            A boolean value deciding about availability of buying button
	 */
	public void setPurchase(boolean c) {
		buy.setEnabled(c);
	}
}
