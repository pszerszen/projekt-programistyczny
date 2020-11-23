package View;

import gameData.Item;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

/**
 * An abstract class provides skeletal implementation of panel for showing
 * certain Item objects. Contains every common elements of EquipmentView and
 * ShopView.
 * 
 * @author Piotr Szerszeń
 * 
 * @see gameData.Item
 * 
 */
@SuppressWarnings("serial")
public abstract class ItemView extends JPanel {
	/**
	 * Shows how many points an item adds to charm parameter
	 */
	private JTextArea charm;
	/**
	 * Shows how many points an item adds to energy parameter
	 */
	private JTextArea erg;
	/**
	 * Shows how many points an item adds to happiness parameter
	 */
	private JTextArea happy;
	/**
	 * Shows how many points an item adds to intelligence parameter
	 */
	private JTextArea inteligence;
	/**
	 * Shows how many points an item adds to knowledge parameter
	 */
	private JTextArea know;
	/**
	 * SrollPane for JList showing items
	 */
	private JScrollPane scrlPan;
	/**
	 * Shows how many points an item adds to scrupulousness parameter
	 */
	private JTextArea scroupulatio;
	/**
	 * Shows how many points an item adds to endurance parameter
	 */
	private JTextArea stamina;
	/**
	 * Shows how many points an item adds to stress resistance parameter
	 */
	private JTextArea stressResis;
	/**
	 * Shows how long an item works
	 */
	private JTextArea time;
	/**
	 * A label displayed on top of panel; says if panel is shop or player's bag
	 */
	private JLabel Title;
	/**
	 * A button causing return to main Panel of the game
	 */
	protected JButton back;
	/**
	 * JComponent containing names of Items
	 */
	protected JList<String> itemlist;
	/**
	 * A JList model for an itemlist field
	 */
	protected DefaultListModel<String> model;
	/**
	 * A thread object responsible for onboard displaying Statistics of
	 * currently selected item
	 */
	protected Thread statsDisplayer;

	/**
	 * Creates a skeletal implementation of panel showing items
	 * 
	 * @param title
	 *            A Title of panel shown in extending instances of this class
	 */
	public ItemView(String title) {
		setSize(new Dimension(1000, 650));
		setLayout(null);

		Title = new JLabel(title);
		Title.setHorizontalTextPosition(SwingConstants.CENTER);
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(new Font("Calibri", Font.BOLD, 63));
		Title.setBounds(10, 33, 980, 58);
		add(Title);

		back = new JButton("   ");
		back.setHorizontalTextPosition(SwingConstants.CENTER);
		back.setActionCommand("back");
		back.setBounds(822, 464, 109, 109);
		back.setFocusable(false);
		back.setToolTipText("Powrót do panelu głównego");
		back.setIcon(new ImageIcon("res/back.jpg"));
		add(back);

		JLabel lblEnergia = new JLabel("Energia");
		lblEnergia.setBounds(433, 236, 135, 14);
		lblEnergia.setFont(ViewManager.f);
		add(lblEnergia);

		JLabel lblRado = new JLabel("Rado\u015B\u0107");
		lblRado.setBounds(433, 261, 135, 14);
		lblRado.setFont(ViewManager.f);
		add(lblRado);

		JLabel lblCzas = new JLabel("D\u0142ugo\u015B\u0107 dzia\u0142ania");
		lblCzas.setBounds(433, 191, 135, 14);
		lblCzas.setFont(ViewManager.f);
		add(lblCzas);

		JLabel lblWiedza = new JLabel("Wiedza");
		lblWiedza.setBounds(433, 288, 135, 14);
		lblWiedza.setFont(ViewManager.f);
		add(lblWiedza);

		JLabel lblInteligencja = new JLabel("Inteligencja");
		lblInteligencja.setBounds(433, 313, 135, 14);
		lblInteligencja.setFont(ViewManager.f);
		add(lblInteligencja);

		JLabel lblCharyzma = new JLabel("Charyzma");
		lblCharyzma.setBounds(433, 338, 135, 14);
		lblCharyzma.setFont(ViewManager.f);
		add(lblCharyzma);

		JLabel lblWytrzymao = new JLabel("Wytrzyma\u0142o\u015B\u0107");
		lblWytrzymao.setBounds(433, 363, 135, 14);
		lblWytrzymao.setFont(ViewManager.f);
		add(lblWytrzymao);

		JLabel lblOdpornoNaStres = new JLabel("Odporno\u015B\u0107 na stres");
		lblOdpornoNaStres.setBounds(433, 388, 135, 14);
		lblOdpornoNaStres.setFont(ViewManager.f);
		add(lblOdpornoNaStres);

		JLabel lblSkrupulatno = new JLabel("Skrupulatno\u015B\u0107");
		lblSkrupulatno.setBounds(433, 413, 135, 14);
		lblSkrupulatno.setFont(ViewManager.f);
		add(lblSkrupulatno);

		erg = new JTextArea();
		erg.setText("0");
		erg.setBounds(564, 236, 50, 16);
		erg.setFont(ViewManager.fs);
		add(erg);

		happy = new JTextArea();
		happy.setText("0");
		happy.setBounds(564, 261, 50, 16);
		happy.setFont(ViewManager.fs);
		add(happy);

		time = new JTextArea();
		time.setText("0");
		time.setBounds(564, 191, 50, 16);
		time.setFont(ViewManager.fs);
		add(time);

		know = new JTextArea();
		know.setText("0");
		know.setBounds(564, 288, 50, 16);
		know.setFont(ViewManager.fs);
		add(know);

		inteligence = new JTextArea();
		inteligence.setText("0");
		inteligence.setBounds(564, 313, 50, 16);
		inteligence.setFont(ViewManager.fs);
		add(inteligence);

		charm = new JTextArea();
		charm.setText("0");
		charm.setBounds(564, 338, 50, 16);
		charm.setFont(ViewManager.fs);
		add(charm);

		stamina = new JTextArea();
		stamina.setText("0");
		stamina.setBounds(564, 363, 50, 16);
		stamina.setFont(ViewManager.fs);
		add(stamina);

		stressResis = new JTextArea();
		stressResis.setText("0");
		stressResis.setBounds(564, 388, 50, 16);
		stressResis.setFont(ViewManager.fs);
		add(stressResis);

		scroupulatio = new JTextArea();
		scroupulatio.setText("0");
		scroupulatio.setBounds(564, 413, 50, 16);
		scroupulatio.setFont(ViewManager.fs);
		add(scroupulatio);

		scrlPan = new JScrollPane();
		scrlPan.setBounds(92, 191, 241, 300);
		add(scrlPan);

		model = new DefaultListModel<String>();

		itemlist = new JList<String>();
		itemlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemlist.setModel(model);
		scrlPan.setViewportView(itemlist);
	}

	/**
	 * Sets value of displayed charm status
	 * 
	 * @param charm
	 *            string witch is to be displayed
	 */
	protected void setCharm(String charm) {
		this.charm.setText(charm);
	}

	/**
	 * Sets value of displayed energy status
	 * 
	 * @param erg
	 *            string witch is to be displayed
	 */
	protected void setErg(String erg) {
		this.erg.setText(erg);
	}

	/**
	 * Sets value of displayed happiness status
	 * 
	 * @param happy
	 *            string witch is to be displayed
	 */
	protected void setHappy(String happy) {
		this.happy.setText(happy);
	}

	/**
	 * Sets value of displayed intelligence status
	 * 
	 * @param inteligence
	 *            string witch is to be displayed
	 */
	protected void setInteligence(String inteligence) {
		this.inteligence.setText(inteligence);
	}

	/**
	 * Sets value of displayed knowledge status
	 * 
	 * @param know
	 *            string witch is to be displayed
	 */
	protected void setKnow(String know) {
		this.know.setText(know);
	}

	/**
	 * Sets value of displayed scrupulousness status
	 * 
	 * @param scroupulatio
	 *            string witch is to be displayed
	 */
	protected void setScroupulatio(String scroupulatio) {
		this.scroupulatio.setText(scroupulatio);
	}

	/**
	 * Sets value of displayed endurance status
	 * 
	 * @param stamina
	 *            string witch is to be displayed
	 */
	protected void setStamina(String stamina) {
		this.stamina.setText(stamina);
	}

	/**
	 * Sets value of displayed stress resistance status
	 * 
	 * @param stressResis
	 *            string witch is to be displayed
	 */
	protected void setStressResis(String stressResis) {
		this.stressResis.setText(stressResis);
	}

	/**
	 * Sets value of displayed duration status
	 * 
	 * @param time
	 *            string witch is to be displayed
	 */
	protected void setTime(String time) {
		this.time.setText(time);
	}

	/**
	 * Sets value of displayed item's name
	 * 
	 * @param title
	 *            string witch is to be displayed
	 */
	protected void setTitle(String title) {
		this.Title.setText(title);
	}

	/**
	 * Activates thread object responsible for onboard displaying Statistics of
	 * currently selected item
	 */
	public void activateThread() {
		statsDisplayer.start();
	}

	/**
	 * Deactivates thread object responsible for onboard displaying Statistics
	 * of currently selected item
	 */
	@SuppressWarnings("deprecation")
	public void deActivateThread() {
		statsDisplayer.stop();
	}

	/**
	 * Returns JList with names of items within
	 * 
	 * @return JList with names of items within
	 */
	public JList<String> getItemlist() {
		return itemlist;
	}

	/**
	 * Cleans JList up and then fills with names of items in ArrayList
	 * 
	 * @param items
	 *            list of items to be displayed in JList
	 */
	public void setItemList(ArrayList<Item> items) {
		this.model.removeAllElements();
		for (Item i : items) {
			this.model.addElement(i.getName());
		}
	}
}
