package View;

import gameData.Activity;
import gameData.DataBase;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;

/**
 * class containing all GUI in the Game
 * 
 * @author Piotr Szerszeń
 * 
 * @see Activity
 * @see DataBase
 * @see EquipmentView
 * @see calPanel
 * @see IndexView
 * @see ShopView
 * @see NewGame
 * 
 */
@SuppressWarnings("serial")
public class ViewManager extends JFrame {
	/**
	 * Class responsible for file filtering so that in JFileChooser only folders
	 * and *.xml files.
	 * 
	 * @author Piotr Szerszeń
	 * 
	 */
	private class XMLFilter extends FileFilter {
		/**
		 * The description of this filter
		 */
		private String description;
		/**
		 * Names of files in currently watched folder
		 */
		private String extensions[];

		/**
		 * Constructor for filtering class object
		 * 
		 * @param description
		 *            The description of filter
		 * @param extension
		 *            Names of files to being shown
		 */
		public XMLFilter(String description, String extension) {
			this(description, new String[] { extension });
		}

		/**
		 * Constructor for filtering class object but this one filters files in
		 * subfolders
		 * 
		 * @param description
		 * @param extensions
		 */
		public XMLFilter(String description, String extensions[]) {
			if (description == null) {
				this.description = extensions[0];
			} else {
				this.description = description;
			}
			this.extensions = (String[]) extensions.clone();
			toLower(this.extensions);
		}

		/**
		 * Changes every letter in file names to lower case
		 * 
		 * @param array
		 *            an array of file names that are to be changed
		 */
		private void toLower(String array[]) {
			for (int i = 0, n = array.length; i < n; i++) {
				array[i] = array[i].toLowerCase();
			}
		}

		/**
		 * An overridden method checking if file is a folder or XML file
		 * 
		 * @param file
		 *            file to be tested
		 * @return true if file is accepted by this filter
		 */
		@Override
		public boolean accept(File file) {
			if (file.isDirectory()) {
				return true;
			} else {
				String path = file.getAbsolutePath().toLowerCase();
				for (int i = 0, n = extensions.length; i < n; i++) {
					String extension = extensions[i];
					if ((path.endsWith(extension) && (path.charAt(path.length()
							- extension.length() - 1)) == '.')) {
						return true;
					}
				}
			}
			return false;
		}

		/**
		 * An overridden getter for filter description
		 */
		@Override
		public String getDescription() {
			return description;
		}

	}

	/**
	 * Show game Log; contains important actions made by player
	 */
	private static JTextArea Log;
	/**
	 * A font used in every subpanel as small label font
	 */
	public static Font f = new Font("Calibri", Font.PLAIN, 16);
	/**
	 * A font used in every subpanel as TextArea font
	 */
	public static Font fs = new Font("Monospaced", Font.PLAIN, 13);

	/**
	 * appends log to GameLog in Main Panel
	 * 
	 * @param s
	 *            a string to be appended
	 */
	public static void addLog(String s) {
		Log.append("\n" + s);
	}

	/**
	 * JList component with names of activities currently enabled to do
	 */
	private JList<String> activityList;
	/**
	 * ListModel for activityList JList
	 */
	private DefaultListModel<String> almodel;
	/**
	 * An ImageIcon containing avatarPicture
	 */
	private ImageIcon avatarIcon = new ImageIcon("res/avatar2.jpg");
	/**
	 * Perspective showing player's items
	 */
	private EquipmentView bag;
	/**
	 * Opens bag perspective
	 */
	private JButton Bag;
	/**
	 * A button calling performance of selected activity
	 */
	private JButton btnWykonaj;
	/**
	 * Perspective showing player's schedule
	 */
	private calPanel calendar;
	/**
	 * Opens schedule perspective
	 */
	private JButton Callendar;
	/**
	 * Opens shop perspective
	 */
	private JButton Cart;
	/**
	 * Shows players remaining money
	 */
	private JTextArea cash;
	/**
	 * Shows players current charm level
	 */
	private JTextArea charm;
	/**
	 * Shows number of days in current semester
	 */
	private JTextArea countDay;
	/**
	 * Shows number of hours in current day
	 */
	private JTextArea countHour;
	/**
	 * Shows which semester is current
	 */
	private JTextArea countSemester;
	/**
	 * Shows players current energy level
	 */
	private JTextArea erg;
	/**
	 * MenuItem which exits from the game
	 */
	private JMenuItem Exit;
	/**
	 * FileChooser component for easier saving and loading game
	 */
	private JFileChooser fileChooser;
	/**
	 * Shows players current happiness level
	 */
	private JTextArea happy;
	/**
	 * Opens index perspective
	 */
	private JButton Indeks;
	/**
	 * Perspective showing player's matricula
	 */
	private IndexView index;
	/**
	 * Shows players current intelligence level
	 */
	private JTextArea inteligence;
	/**
	 * Shows players current knowledge level
	 */
	private JTextArea know;
	/**
	 * Main perspective of the game
	 */
	private JPanel mainPan;

	/**
	 * Standard menu on the menuBar
	 */
	private JMenu menu;
	/**
	 * Standard menuBar witch has attached menu onto it
	 */
	private JMenuBar menuBar;
	/**
	 * Shows players Name
	 */
	private JLabel Name;
	/**
	 * Perspective where player can make a new game
	 */
	private NewGame newGame;
	/**
	 * Option of creating new game in menu
	 */
	private JMenuItem NewGame;
	/**
	 * Option of loading previously saved game from XML file
	 */
	private JMenuItem Open;
	/**
	 * Option of saving currently played game
	 */
	private JMenuItem Save;
	/**
	 * Option of saving game to the specified file
	 */
	private JMenuItem SaveAs;
	/**
	 * Shows players current scrupulousness level
	 */
	private JTextArea scroupulatio;
	/**
	 * Perspective showing shop where player can buy new items
	 */
	private ShopView shop;
	/**
	 * The field where player decides how long the activity will be performed
	 */
	private JSpinner spinnerRounds;
	/**
	 * NubmerModel of spinnerRounds field
	 */
	private SpinnerNumberModel spinnerRoundsModel;
	/**
	 * Shows players current endurance level
	 */
	private JTextArea stamina;
	/**
	 * A Thread working separately, refreshes players statistics onboard
	 */
	private Thread statsRefresher;

	/**
	 * Shows players current stress resistance level
	 */
	private JTextArea stressResis;

	/**
	 * Initializes newly created GUI for the game
	 */
	public ViewManager() {
		super("Student Symulator");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1000, 650));

		getContentPane().setLayout(null);

		mainPan = new JPanel();
		mainPan.setLayout(null);
		mainPan.setBounds(0, 0, 994, 650);
		getContentPane().add(mainPan);

		menuBar = new JMenuBar();
		menu = new JMenu("Gra");
		menuBar.add(menu);

		NewGame = new JMenuItem("Nowa gra");
		NewGame.setIcon(new ImageIcon(ViewManager.class
				.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		Open = new JMenuItem("Wczytaj");
		Open.setIcon(new ImageIcon(
				ViewManager.class
						.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		Save = new JMenuItem("Zapisz");
		Save.setIcon(new ImageIcon(ViewManager.class
				.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		SaveAs = new JMenuItem("Zapisz jako...");
		SaveAs.setIcon(new ImageIcon(
				ViewManager.class
						.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
		Exit = new JMenuItem("Wyjdź");
		Exit.setActionCommand("Wyjdz");
		Exit.setIcon(new ImageIcon(ViewManager.class
				.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));

		menu.add(NewGame);
		menu.addSeparator();
		menu.add(Open);
		menu.add(Save);
		menu.add(SaveAs);
		menu.addSeparator();
		menu.add(Exit);

		setJMenuBar(menuBar);

		fileChooser = new JFileChooser();
		XMLFilter filtr = new XMLFilter("Pliki .xml", "XML");
		fileChooser.setFileFilter(filtr);

		JLabel panel = new JLabel(avatarIcon);
		panel.setBounds(26, 42, 170, 190);
		mainPan.add(panel);

		Name = new JLabel("Imie Nazwisko");
		Name.setFont(new Font("Calibri", Font.BOLD, 18));
		Name.setBounds(206, 42, 335, 23);
		mainPan.add(Name);

		JLabel lblEnergia = new JLabel("Energia");
		lblEnergia.setBounds(206, 76, 68, 14);
		lblEnergia.setFont(f);
		mainPan.add(lblEnergia);

		JLabel lblRado = new JLabel("Rado\u015B\u0107");
		lblRado.setBounds(206, 101, 68, 14);
		lblRado.setFont(f);
		mainPan.add(lblRado);

		JLabel lblKasa = new JLabel("Kasa");
		lblKasa.setBounds(206, 126, 68, 14);
		lblKasa.setFont(f);
		mainPan.add(lblKasa);

		JLabel lblWiedza = new JLabel("Wiedza");
		lblWiedza.setBounds(206, 151, 68, 14);
		lblWiedza.setFont(f);
		mainPan.add(lblWiedza);

		erg = new JTextArea();
		erg.setFocusable(false);
		erg.setEditable(false);
		erg.setText("0");
		erg.setBounds(284, 76, 30, 16);
		erg.setFont(fs);
		mainPan.add(erg);

		happy = new JTextArea();
		happy.setFocusable(false);
		happy.setEditable(false);
		happy.setText("0");
		happy.setBounds(284, 101, 30, 16);
		happy.setFont(fs);
		mainPan.add(happy);

		cash = new JTextArea();
		cash.setFocusable(false);
		cash.setEditable(false);
		cash.setText("0");
		cash.setBounds(284, 126, 30, 16);
		cash.setFont(fs);
		mainPan.add(cash);

		know = new JTextArea();
		know.setFocusable(false);
		know.setEditable(false);
		know.setText("0");
		know.setBounds(284, 151, 30, 16);
		know.setFont(fs);
		mainPan.add(know);

		JLabel lblInteligencja = new JLabel("Inteligencja");
		lblInteligencja.setBounds(356, 76, 135, 14);
		lblInteligencja.setFont(f);
		mainPan.add(lblInteligencja);

		JLabel lblCharyzma = new JLabel("Charyzma");
		lblCharyzma.setBounds(356, 101, 135, 14);
		lblCharyzma.setFont(f);
		mainPan.add(lblCharyzma);

		JLabel lblWytrzymao = new JLabel("Wytrzyma\u0142o\u015B\u0107");
		lblWytrzymao.setBounds(356, 126, 135, 14);
		lblWytrzymao.setFont(f);
		mainPan.add(lblWytrzymao);

		JLabel lblOdpornoNaStres = new JLabel("Odporno\u015B\u0107 na stres");
		lblOdpornoNaStres.setBounds(356, 151, 135, 14);
		lblOdpornoNaStres.setFont(f);
		mainPan.add(lblOdpornoNaStres);

		JLabel lblSkrupulatno = new JLabel("Skrupulatno\u015B\u0107");
		lblSkrupulatno.setBounds(356, 176, 135, 14);
		lblSkrupulatno.setFont(f);
		mainPan.add(lblSkrupulatno);

		inteligence = new JTextArea();
		inteligence.setFocusable(false);
		inteligence.setEditable(false);
		inteligence.setText("0");
		inteligence.setBounds(501, 76, 30, 16);
		inteligence.setFont(fs);
		mainPan.add(inteligence);

		charm = new JTextArea();
		charm.setFocusable(false);
		charm.setEditable(false);
		charm.setText("0");
		charm.setBounds(501, 101, 30, 16);
		charm.setFont(fs);
		mainPan.add(charm);

		stamina = new JTextArea();
		stamina.setFocusable(false);
		stamina.setEditable(false);
		stamina.setText("0");
		stamina.setBounds(501, 126, 30, 16);
		stamina.setFont(fs);
		mainPan.add(stamina);

		stressResis = new JTextArea();
		stressResis.setFocusable(false);
		stressResis.setEditable(false);
		stressResis.setText("0");
		stressResis.setBounds(501, 151, 30, 16);
		stressResis.setFont(fs);
		mainPan.add(stressResis);

		scroupulatio = new JTextArea();
		scroupulatio.setFocusable(false);
		scroupulatio.setEditable(false);
		scroupulatio.setText("0");
		scroupulatio.setBounds(501, 176, 30, 16);
		scroupulatio.setFont(fs);
		mainPan.add(scroupulatio);

		Indeks = new JButton("   ");
		Indeks.setToolTipText("Indeks Gracza");
		Indeks.setFocusable(false);
		Indeks.setActionCommand("Indeks");
		Indeks.setHorizontalTextPosition(SwingConstants.CENTER);
		Indeks.setIcon(new ImageIcon("res/indeks.jpg"));
		Indeks.setBounds(724, 28, 135, 115);
		mainPan.add(Indeks);

		Callendar = new JButton("   ");
		Callendar.setToolTipText("Kalendarz");
		Callendar.setFocusable(false);
		Callendar.setActionCommand("Callendar");
		Callendar.setHorizontalTextPosition(SwingConstants.CENTER);
		Callendar.setIcon(new ImageIcon("res/cal.jpg"));
		Callendar.setBounds(579, 28, 135, 115);
		mainPan.add(Callendar);

		Bag = new JButton("   ");
		Bag.setToolTipText("Plecak Gracza");
		Bag.setFocusable(false);
		Bag.setActionCommand("Bag");
		Bag.setHorizontalTextPosition(SwingConstants.CENTER);
		Bag.setIcon(new ImageIcon("res/bag.jpg"));
		Bag.setBounds(579, 154, 135, 115);
		mainPan.add(Bag);

		Cart = new JButton("   ");
		Cart.setToolTipText("Sklep");
		Cart.setFocusable(false);
		Cart.setIcon(new ImageIcon("res/cart.jpg"));
		Cart.setHorizontalTextPosition(SwingConstants.CENTER);
		Cart.setActionCommand("Cart");
		Cart.setBounds(724, 154, 135, 115);
		mainPan.add(Cart);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(52, 294, 297, 215);
		mainPan.add(scrollPane);

		almodel = new DefaultListModel<>();

		activityList = new JList<String>();
		activityList.setModel(almodel);
		scrollPane.setViewportView(activityList);

		btnWykonaj = new JButton("Wykonaj");
		btnWykonaj.setFocusable(false);
		btnWykonaj.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnWykonaj.setBounds(161, 535, 170, 45);
		mainPan.add(btnWykonaj);

		JLabel lblWybrCzynnoci = new JLabel("Wyb\u00F3r czynno\u015Bci:");
		lblWybrCzynnoci.setBounds(52, 257, 140, 26);
		lblWybrCzynnoci.setFont(f);
		mainPan.add(lblWybrCzynnoci);

		JLabel lblLog = new JLabel("Log:");
		lblLog.setBounds(579, 297, 46, 14);
		lblLog.setFont(f);
		mainPan.add(lblLog);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(579, 322, 358, 258);
		mainPan.add(scrollPane_1);

		Log = new JTextArea("Jak dotąd w grze:");
		Log.setFont(new Font("Monospaced", Font.PLAIN, 13));
		Log.setEditable(false);
		Log.setFocusable(false);
		Log.setLineWrap(true);
		Log.setWrapStyleWord(true);
		scrollPane_1.setViewportView(Log);
		scrollPane_1.getVerticalScrollBar().addAdjustmentListener(
				new AdjustmentListener() {
					@Override
					public void adjustmentValueChanged(AdjustmentEvent e) {
						Log.select(Log.getHeight() * 10000, 0);
					}
				});

		spinnerRoundsModel = new SpinnerNumberModel(1, 1, null, 1);

		spinnerRounds = new JSpinner();
		spinnerRounds.setBounds(366, 312, 68, 23);
		spinnerRounds.setModel(spinnerRoundsModel);
		mainPan.add(spinnerRounds);

		JLabel lblIleCzasu = new JLabel("Ile czasu ma zająć");
		lblIleCzasu.setBounds(356, 261, 135, 23);
		lblIleCzasu.setFont(f);
		mainPan.add(lblIleCzasu);

		JLabel lblCzynnosc = new JLabel("czynność:");
		lblCzynnosc.setBounds(356, 283, 83, 16);
		lblCzynnosc.setFont(f);
		mainPan.add(lblCzynnosc);

		JLabel lblSemestr = new JLabel("Semestr");
		lblSemestr.setBounds(932, 28, 52, 14);
		lblSemestr.setFont(f);
		mainPan.add(lblSemestr);

		JLabel lblDzie = new JLabel("Dzień");
		lblDzie.setBounds(932, 76, 46, 14);
		lblDzie.setFont(f);
		mainPan.add(lblDzie);

		JLabel lblGodzina = new JLabel("Godzina");
		lblGodzina.setBounds(932, 126, 52, 14);
		lblGodzina.setFont(f);
		mainPan.add(lblGodzina);

		countSemester = new JTextArea();
		countSemester.setEditable(false);
		countSemester.setText("0");
		countSemester.setBounds(932, 42, 52, 16);
		countSemester.setFont(fs);
		mainPan.add(countSemester);

		countDay = new JTextArea();
		countDay.setEditable(false);
		countDay.setText("0");
		countDay.setBounds(932, 96, 52, 16);
		countDay.setFont(fs);
		mainPan.add(countDay);

		countHour = new JTextArea();
		countHour.setEditable(false);
		countHour.setText("0");
		countHour.setBounds(932, 146, 52, 16);
		countHour.setFont(fs);
		mainPan.add(countHour);

		statsRefresher = new Thread() {
			public void run() {
				while (true) {
					try {
						refreshStatsView();
						refreshTime();
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		statsRefresher.start();
		shop = new ShopView();
		shop.setVisible(false);

		bag = new EquipmentView();
		bag.setVisible(false);

		index = new IndexView();
		index.setVisible(false);

		calendar = new calPanel();
		calendar.setVisible(false);

		newGame = new NewGame();
		newGame.setVisible(false);

		setVisible(true);

	}

	/**
	 * Sets text directly from String to cash TextArea
	 * 
	 * @param cash
	 *            text which is to be new TextArea text
	 */
	private void setCash(String cash) {
		this.cash.setText(cash);
	}

	/**
	 * Sets text directly from String to charm TextArea
	 * 
	 * @param charm
	 *            text which is to be new TextArea text
	 */
	private void setCharm(String charm) {
		this.charm.setText(charm);
	}

	/**
	 * Sets text directly from String to erg TextArea
	 * 
	 * @param erg
	 *            text which is to be new TextArea text
	 */
	private void setErg(String erg) {
		this.erg.setText(erg);
	}

	/**
	 * Sets text directly from String to happy TextArea
	 * 
	 * @param happy
	 *            text which is to be new TextArea text
	 */
	private void setHappy(String happy) {
		this.happy.setText(happy);
	}

	/**
	 * Sets text directly from String to inteligence TextArea
	 * 
	 * @param inteligence
	 *            text which is to be new TextArea text
	 */
	private void setInteligence(String inteligence) {
		this.inteligence.setText(inteligence);
	}

	/**
	 * Sets text directly from String to know TextArea
	 * 
	 * @param know
	 *            text which is to be new TextArea text
	 */
	private void setKnow(String know) {
		this.know.setText(know);
	}

	/**
	 * Sets text directly from String to scroupulatio TextArea
	 * 
	 * @param scroupulatio
	 *            text which is to be new TextArea text
	 */
	private void setScroupulatio(String scroupulatio) {
		this.scroupulatio.setText(scroupulatio);
	}

	/**
	 * Sets text directly from String to stamina TextArea
	 * 
	 * @param stamina
	 *            text which is to be new TextArea text
	 */
	private void setStamina(String stamina) {
		this.stamina.setText(stamina);
	}

	/**
	 * Sets text directly from String to stressResis TextArea
	 * 
	 * @param stressResis
	 *            text which is to be new TextArea text
	 */
	private void setStressResis(String stressResis) {
		this.stressResis.setText(stressResis);
	}

	/**
	 * Adds specified ActionListener to JButtons on this panel
	 * 
	 * @param a
	 *            ActionListener object to add
	 */
	public void addActions(ActionListener a) {
		Indeks.addActionListener(a);
		Bag.addActionListener(a);
		Cart.addActionListener(a);
		Callendar.addActionListener(a);
		NewGame.addActionListener(a);
		Open.addActionListener(a);
		Save.addActionListener(a);
		SaveAs.addActionListener(a);
		Exit.addActionListener(a);
		shop.addListeners(a);
		bag.addListeners(a);
		index.getBack().addActionListener(a);
		calendar.addActions(a);
		newGame.addAction(a);
		btnWykonaj.addActionListener(a);
	}

	/**
	 * Gives an access to the JList JComponent containing activities
	 * 
	 * @return JList JComponent activities
	 */
	public JList<String> getActivityList() {
		return activityList;
	}

	/**
	 * Gives an access to model of JList containing list of activity names
	 * enabled to do
	 * 
	 * @return object of model of JList with activities
	 */
	public DefaultListModel<String> getAlmodel() {
		return almodel;
	}

	/**
	 * Gives an access to subpanel which is object of EquipmentView class
	 * 
	 * @return object of EquipmentView
	 */
	public EquipmentView getBag() {
		return bag;
	}

	/**
	 * Gives an access to subpanel which is object of calPanel class
	 * 
	 * @return object of calPanel
	 */
	public calPanel getCalendar() {
		return calendar;
	}

	/**
	 * Gives acces to JFileChooser used in the game
	 * 
	 * @return JFileChooser object
	 */
	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	/**
	 * Gives an access to subpanel which is object of IndexView class
	 * 
	 * @return object of IndexView
	 */
	public IndexView getIndex() {
		return index;
	}

	/**
	 * gets all text contained in Log
	 * 
	 * @return all text contained in Log as String
	 */
	public String getLog() {
		return Log.getText();
	}

	/**
	 * Gives an access to main subpanel of the game
	 * 
	 * @return main panel of the game which is extension of JPanel
	 */
	public JPanel getMainPan() {
		return mainPan;
	}

	/**
	 * Gives an access to subpanel which is object of NewGame class
	 * 
	 * @return object of NewGame
	 */
	public NewGame getNewGame() {
		return newGame;
	}

	/**
	 * Gives number of rounds the activity will be performed
	 * 
	 * @return number rounds as int
	 */
	public int getRounds() {
		return spinnerRoundsModel.getNumber().intValue();
	}

	/**
	 * Gives an access to subpanel which is object of ShopView class
	 * 
	 * @return object of ShopView
	 */
	public ShopView getShop() {
		return shop;
	}

	/**
	 * Cleans up and then fills with the names of activities from DataBase in
	 * JList with names of activities on mainPanel
	 */
	public void refreshActivitiesView() {
		almodel.removeAllElements();
		for (Activity a : DataBase.activities) {
			if (a.getName().equals("Kolokwium/Egzamin")) {
				if (DataBase.cal.IsExamNow())
					almodel.addElement(a.getName());
			} else
				almodel.addElement(a.getName());
		}
	}

	/**
	 * Refresh player's statistics on mainPanel
	 */
	public void refreshStatsView() {
		setCash("" + DataBase.player.getMoney());
		setCharm("" + DataBase.player.getCharisma());
		setErg("" + DataBase.player.getEnergy());
		setHappy("" + DataBase.player.getHappiness());
		setInteligence("" + DataBase.player.getInteligence());
		setKnow("" + DataBase.player.getKnowledge());
		setScroupulatio("" + DataBase.player.getScrupulousness());
		setStamina("" + DataBase.player.getEndurance());
		setStressResis("" + DataBase.player.getStressResistance());

	}

	/**
	 * Refreshes fields showing time on main panel
	 */
	public void refreshTime() {
		int NumberOfSemester = DataBase.Round / (120 * 24) + 1;
		int NumberOfDay = ((DataBase.Round - (NumberOfSemester - 1) * 120 * 24) / 24) + 1;
		int godzina = (DataBase.Round) % 24;

		countSemester.setText("" + NumberOfSemester);
		countDay.setText("" + NumberOfDay);
		countHour.setText("" + godzina);
	}

	/**
	 * Sets text of Log on mainPanel
	 * 
	 * @param log
	 *            a string that log is to be filled with
	 */
	public void setLog(String log) {
		ViewManager.Log.setText(log);
	}

	/**
	 * Sets the player's name on the certain label on main panel
	 * 
	 * @param name
	 *            A string containing players name and surname
	 */
	public void setPName(String name) {
		Name.setText(name);
	}
}
